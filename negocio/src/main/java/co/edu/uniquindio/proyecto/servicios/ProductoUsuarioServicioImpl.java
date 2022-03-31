package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.ProductoUsuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoUsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoUsuarioServicioImpl implements  ProductoUsuarioServicio{

    private final ProductoUsuarioRepo productoUsuarioRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoUsuarioServicioImpl(ProductoUsuarioRepo productoUsuarioRepo, ComentarioRepo comentarioRepo) {
        this.productoUsuarioRepo = productoUsuarioRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public ProductoUsuario registrarProductoUsuario(ProductoUsuario p) throws Exception {

        if (p.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }
        return productoUsuarioRepo.save(p);
    }

    @Override
    public void actualizarProductoUsuario(ProductoUsuario p, String nombre) throws Exception {

        ProductoUsuario productoEncontrado = obtenerProductoNombreUsuario(nombre);

        if (productoEncontrado!=null){
            productoEncontrado.setNombre(p.getNombre());
            productoEncontrado.setDescripcion(p.getDescripcion());
            productoEncontrado.setPrecio(p.getPrecio());
        }else {
            throw new Exception("El producto a actualizar no existe");
        }

    }

    @Override
    public void actualizarProductoUsuario(ProductoUsuario p) throws Exception {

        productoUsuarioRepo.save(p);
    }

    @Override
    public void eliminarProductoUsuario(String nombre) throws Exception {

        ProductoUsuario productoEncontrado = obtenerProductoNombreUsuario(nombre);

        if (productoEncontrado!=null){
            productoUsuarioRepo.delete(productoEncontrado);
        }else{
            throw new Exception("El producto a eliminar no existe");
        }
    }

    @Override
    public ProductoUsuario obtenerProductoUsuario(int id) throws Exception {

        Optional<ProductoUsuario> productoEncontrado = productoUsuarioRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado.get();
    }

    @Override
    public ProductoUsuario obtenerProductoNombreUsuario(String nombre) throws Exception {

        ProductoUsuario productoEncontrado = productoUsuarioRepo.obtenerProductoUsuarioNombre(nombre);

        if (productoEncontrado==null){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado;
    }

//    @Override
//    public void ingresarComentario(Comentario r, ProductoUsuario producto) throws Exception {
//
//        try {
//            if (producto != null) {
//
//                r.setProducto(producto);
//                comentarioRepo.save(r);
//
//            }
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }

    @Override
    public List<ProductoUsuario> buscarProductos(String cadena) {
        return productoUsuarioRepo.busquedaProductosUsuarioNombre(cadena);
    }


    @Override
    public List<ProductoUsuario> listarProductos() {
        return productoUsuarioRepo.findAll();
    }
}
