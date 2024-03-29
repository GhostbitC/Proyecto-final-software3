package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.time.*;
import java.util.*;

@Service
public class CompraServicioImpl implements CompraServicio, Serializable {

    private final CompraRepo compraRepo;
    private final EnvioRepo envioRepo;
    private final UsuarioRepo usuarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final ProductoRepo productoRepo;
    private final ComprobantePagoRepo comprobantePagoRepo;

    Random ran = new Random();

    public CompraServicioImpl(CompraRepo compraRepo, EnvioRepo envioRepo, UsuarioRepo usuarioRepo, DetalleCompraRepo detalleCompraRepo, ProductoRepo productoRepo, ComprobantePagoRepo comprobantePagoRepo) {
        this.compraRepo = compraRepo;
        this.envioRepo = envioRepo;
        this.usuarioRepo = usuarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.productoRepo = productoRepo;
        this.comprobantePagoRepo = comprobantePagoRepo;
    }

    @Override
    public void crearCompra(Compra c) throws ObjetoNoEncontradoException {

        if (c !=null){
            compraRepo.save(c);
        }else{
            throw new ObjetoNoEncontradoException("No se puede registrar esta compra");
        }
    }

    @Override
    public void agregarCompra(ArrayList<ProductoCarrito> productoCarrito, Usuario usuario, String medioPago) throws ObjetoNoEncontradoException {
        try {
            Compra compra = new Compra();
            compra.setFechaVenta(new Date());
            compra.setUsuario(usuario);
            compra.setMedioPago(medioPago);

            compraRepo.save(compra);
            usuario.getCompras().add(compra);
            usuarioRepo.save(usuario);
            DetalleCompra detalle;
            List<DetalleCompra> lista = new ArrayList<>();
            for (ProductoCarrito p : productoCarrito) {
                detalle = new DetalleCompra();
                if( p!=null && detalleCompraRepo.verificarUnidadesProducto(p.getId()) > p.getUnidades()){
                    detalle.setCompra(compra);
                    detalle.setPrecioProducto(p.getPrecio());
                    detalle.setUnidades(p.getUnidades());
                    detalle.setProducto(productoRepo.findById(p.getId()).orElse(null));
                    quitarUnidades(p.getId(), p.getUnidades());
                    detalleCompraRepo.save(detalle);
                    lista.add(detalle);
                    compra.setListaDetallesCompra(lista);
                }
            }
        } catch (Exception e) {
            throw new ObjetoNoEncontradoException(e.getMessage());
        }
    }

    @Override
    public void crearEnvio(Compra c) throws ObjetoNoEncontradoException {

        if (c!=null) {

            int codEnvio= (10000 + ran.nextInt() * 90000);
            Envio e = new Envio();
            List<Compra> compras = new ArrayList<>();
            LocalDate dateSend = LocalDate.now();
            LocalDate dateA = dateSend.plusDays(7);
            Duration tiempoAproximado = Duration.between(dateSend.atStartOfDay(), dateA.atStartOfDay());

            c.setEnvio(e);
            compras.add(c);
            e.setId(codEnvio);
            e.setFechaEnvio(dateSend);
            e.setFechaAproximadaLlegada(dateA);
            e.setTiempoAproximado(tiempoAproximado.toDays());
            e.setCompras(compras);
            e.setValor(7.699F);
            envioRepo.save(e);
        }else{
            throw new ObjetoNoEncontradoException("No se encontraron usuarios con los datos proporcionados");
        }
    }

    public void quitarUnidades (Integer codigoProducto, Integer unidadesComprada){
       Optional<Producto> producto = productoRepo.findById(codigoProducto);

       if (producto.isPresent()){
           Producto productoActual =producto.get();
           productoActual.setUnidades(productoActual.getUnidades() - unidadesComprada );
       }
    }

    public Compra obtenerCompra(int idCompra) throws ObjetoNoEncontradoException
    {

        Optional<Compra> compraEncontrada = compraRepo.findById(idCompra);

        if (compraEncontrada.isEmpty()){
            throw new ObjetoNoEncontradoException("La compra no se encuentra registrada");
        }

        return compraEncontrada.get();
    }

    public void agregarComprobanteCompra(int idCompra, ComprobantePago comprobantePago) throws ObjetoNoEncontradoException {

        Compra compraEncontrada = obtenerCompra(idCompra);

        if(compraEncontrada==null){
            throw new ObjetoNoEncontradoException("La compra no existe");
        }
        compraEncontrada.setComprobantePago(comprobantePago);
        comprobantePago.setCompra(compraEncontrada);
        comprobantePagoRepo.save(comprobantePago);
        compraRepo.save(compraEncontrada);
    }

    @Override
    public List<Compra> listarComprasUsuarioSinComprobante(int idUsuario){
        return compraRepo.listarComprasUsuarioSinComprobante(idUsuario);
    }

    @Override
    public List<Compra> listarComprasSinAprobarUsuarios(){
        return compraRepo.listarComprasSinAprobar();
    }

    @Override
    public List<Compra> listarComprasUsuario(int idUsuario) {
        return compraRepo.listarComprasUsuario(idUsuario);
    }

    @Override
    public Compra obtenerCompraUsuario(int idUsuario, int idCompra) throws ObjetoNoEncontradoException {
        Optional<Usuario> u = usuarioRepo.findById(idUsuario);
        Optional<Compra> c = compraRepo.findById(idCompra);

        if (u.isEmpty()){
            throw new ObjetoNoEncontradoException("El usuario no existe");
        }

        if (c.isEmpty()){
            throw new ObjetoNoEncontradoException("La compra no existe en el sistema");
        }

        Compra compraU = compraRepo.obtenerCompraUsuario(u.get().getId(),c.get().getId());

        if (compraU == null) {
            throw new ObjetoNoEncontradoException("El usuario no cuenta con esta compra");
        }
        return compraU;
    }

}
