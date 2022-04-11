package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioRepo usuarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final ProductoRepo productoRepo;
    private final ProductoUsuarioRepo productoUsuarioRepo;

    public CompraServicioImpl(CompraRepo compraRepo, UsuarioRepo usuarioRepo, DetalleCompraRepo detalleCompraRepo, ProductoRepo productoRepo, ProductoUsuarioRepo productoUsuarioRepo) {
        this.compraRepo = compraRepo;
        this.usuarioRepo = usuarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.productoRepo = productoRepo;
        this.productoUsuarioRepo = productoUsuarioRepo;
    }

    @Override
    public Compra crearCompra(Usuario usuario) throws Exception {
        if (usuarioRepo.findById(usuario.getId()).isPresent()) {
            return new Compra(new Date(), usuario);

        } else {
            throw new Exception("El usuario no existe");
        }
    }

    @Override
    public Compra agregarDetalleCompra(Compra compra, DetalleCompra detalle) {
        compra.getListaDetallesCompra().add(detalle);
        return compra;
    }

    @Override
    public Compra efectuarCompra(Compra compra) throws Exception {
        for (DetalleCompra d : compra.getListaDetallesCompra()) {
            detalleCompraRepo.save(d);
        }
        return compraRepo.save(compra);
    }

    @Override
    public Compra agregarCompra(ArrayList<ProductoCarrito> productoCarrito, Usuario usuario, String medioPago) throws Exception {
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
                if(detalleCompraRepo.verificarUnidades(p.getCodigo()) > p.getUnidades()){
                    detalle.setCompra(compra);
                    detalle.setPrecioProducto(p.getPrecio());
                    detalle.setUnidades(p.getUnidades());
                    detalle.setProducto(productoRepo.findById(p.getCodigo()).get());
                    //Verificar las unidades
                    quitarUnidades(p.getCodigo(), p.getUnidades());
                    detalleCompraRepo.save(detalle);
                    lista.add(detalle);
                    compra.setListaDetallesCompra(lista);
                }
            }
            return compra;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Compra agregarCompraUsuario(ArrayList<ProductoCarrito> productoCarrito, Usuario usuario, String medioPago) throws Exception {

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
                if(detalleCompraRepo.verificarUnidades(p.getCodigo()) > p.getUnidades()){
                    detalle.setCompra(compra);
                    detalle.setPrecioProducto(p.getPrecio());
                    detalle.setUnidades(p.getUnidades());
                    detalle.setProductoUsuario(productoUsuarioRepo.findById(p.getCodigo()).get());
                    //Verificar las unidades
                    quitarUnidadesUsuario(p.getCodigo(), p.getUnidades());
                    detalleCompraRepo.save(detalle);
                    lista.add(detalle);
                    compra.setListaDetallesCompra(lista);
                }
            }
            return compra;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void quitarUnidades (Integer codigoProducto, Integer unidadesComprada){
       Optional<Producto> producto = productoRepo.findById(codigoProducto);
       Producto productoActual =producto.get();
       productoActual.setUnidades(productoActual.getUnidades() - unidadesComprada );
    }

    public void quitarUnidadesUsuario (Integer codigoProducto, Integer unidadesComprada){
        Optional<ProductoUsuario> productoUsuario = productoUsuarioRepo.findById(codigoProducto);
        ProductoUsuario productoActual =productoUsuario.get();
        productoActual.setUnidades(productoActual.getUnidades() - unidadesComprada );
    }

    @Override
    public List<Compra> listarComprasUsuario(String idUsuario) {
        return compraRepo.listarComprasUsuario(idUsuario);
    }

}
