package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.ReseniaRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl  implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ReseniaRepo reseniaRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ReseniaRepo reseniaRepo) {
        this.productoRepo = productoRepo;
        this.reseniaRepo = reseniaRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws Exception {

        if (p.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }
        return productoRepo.save(p);
    }

    @Override
    public void actualizarProducto(Producto p, String nombre) throws Exception {

        Producto productoEncontrado = obtenerProductoNombre(nombre);

        if (productoEncontrado!=null){
            productoEncontrado.setNombre(p.getNombre());
            productoEncontrado.setDescripcion(p.getDescripcion());
            productoEncontrado.setPrecio(p.getPrecio());
        }else {
            throw new Exception("El producto a actualizar no existe");
        }

    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

        productoRepo.save(p);
    }

    @Override
    public void eliminarProducto(String nombre) throws Exception {

        Producto productoEncontrado = obtenerProductoNombre(nombre);

        if (productoEncontrado!=null){
            productoRepo.delete(productoEncontrado);
        }else{
            throw new Exception("El producto a eliminar no existe");
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws Exception {

        Optional<Producto> productoEncontrado = productoRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado.get();
    }

    @Override
    public Producto obtenerProductoNombre(String nombre) throws Exception {

        Producto productoEncontrado = productoRepo.obtenerProductoNombre(nombre);

        if (productoEncontrado==null){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado;
    }

    @Override
    public void ingresarResenia(Comentario r, Producto producto) throws Exception {

        try {
            if (producto != null) {

                r.setProducto(producto);
                reseniaRepo.save(r);

            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Producto> buscarProductos(String cadena) {
        return productoRepo.busquedaProductosNombre(cadena);
    }


    @Override
    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }
}
