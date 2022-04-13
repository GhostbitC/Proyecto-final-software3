package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;


    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws Exception {

        if (p.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }
        p.setEstado(false);

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
    public void ingresarComentario(Comentario r, Producto producto) throws Exception {

        try {
            if (producto != null) {

                r.setProducto(producto);
                comentarioRepo.save(r);

            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int obtenerCalificacionPromedio(int idProducto) throws Exception {

        Integer calificacion=0;
        Producto productoEncontrado= obtenerProducto(idProducto);

        if (productoEncontrado!=null){

            calificacion = productoRepo.obtenerCalificacion(productoEncontrado.getId());


        }else{
            throw new Exception("El producto no fue encontrado");
        }

        if(calificacion==null){
            return 0;
        }
        else{
            return calificacion;
        }

    }

    @Override
    public int[] obtenerPorcentaje(int idProducto) throws Exception{

        int general;
        int especifico;
        int[] promedios = new int[5];

        Producto productoEncontrado= obtenerProducto(idProducto);

        if (productoEncontrado!=null){

            general = productoRepo.obtenerCantidad(productoEncontrado.getId());

            if(general!=0){
                for(int i=0;i<promedios.length;i++){
                    especifico = productoRepo.obtenerCantidadCalificacion(productoEncontrado.getId(),i+1);

                    promedios[i]= (especifico*100)/(general);
                }
            }else {
                for(int i=0;i<promedios.length;i++){
                    promedios[i]= 0;
                }
            }

        }else{
            throw new Exception("El producto no existe");
        }
        return promedios;
    }

    @Override
    public void registrarComentario(Comentario c) throws Exception {

        try{
            c.setFechaComentario(new Date());
            comentarioRepo.save(c);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void ingresarComentario(Comentario c, Producto producto, Persona persona) throws Exception {

        try {
            if (producto != null && persona != null) {

                c.setFechaComentario(new Date());
                c.setProducto(producto);
                c.setUsuario((Usuario) persona);
                comentarioRepo.save(c);
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

    @Override
    public List<Producto> listarProductosDestacados() {
        return productoRepo.listarProductosDestacados("Destacados");
    }

    @Override
    public List<Producto> listarProductosUsuario(int idUsuario){

        return productoRepo.listarProductosPublicadosUsuario(idUsuario);
    }

    @Override
    public List<Producto> listarProductosSinAprobarUsuarios(){

        return productoRepo.listarProductosSinAprobarUsuarios();
    }
}