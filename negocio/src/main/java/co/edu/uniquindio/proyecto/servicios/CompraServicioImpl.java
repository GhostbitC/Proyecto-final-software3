package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioRepo usuarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final ProductoRepo productoRepo;

    private final ComprobantePagoRepo comprobantePagoRepo;

    public CompraServicioImpl(CompraRepo compraRepo, UsuarioRepo usuarioRepo, DetalleCompraRepo detalleCompraRepo, ProductoRepo productoRepo, ComprobantePagoRepo comprobantePagoRepo) {
        this.compraRepo = compraRepo;
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
            throw new ObjetoNoEncontradoException("La compra no existe");
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
            throw new ObjetoNoEncontradoException("La compra no existe");
        }

        Compra compraU = compraRepo.obtenerCompraUsuario(u.get().getId(),c.get().getId());

        if (compraU == null) {
            throw new ObjetoNoEncontradoException("El usuario no cuenta con esta compra");
        }
        return compraU;
    }

}
