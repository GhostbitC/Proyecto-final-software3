package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;

    private final ImagenRepo imagenRepo;

    private final EspecificacionRepo especificacionRepo;


    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, ImagenRepo imagenRepo, EspecificacionRepo especificacionRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.imagenRepo = imagenRepo;
        this.especificacionRepo = especificacionRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws ObjetoNoEncontradoException {

        if (p.getNombre().length() >100){
            throw new ObjetoNoEncontradoException("No se puede exceder los 100 caracteres");
        }
        p.setEstado(false);

        return productoRepo.save(p);
    }

    @Override
    public void actualizarProducto(Producto p, String nombre) throws ObjetoNoEncontradoException {

        Producto productoEncontrado = obtenerProductoNombre(nombre);

        if (productoEncontrado!=null){
            productoEncontrado.setNombre(p.getNombre());
            productoEncontrado.setDescripcion(p.getDescripcion());
            productoEncontrado.setPrecio(p.getPrecio());
            productoEncontrado.setCategoria(p.getCategoria());
            productoEncontrado.setUnidades(p.getUnidades());
            productoEncontrado.setImagenes(p.getImagenes());
            productoEncontrado.setEspecificaciones(p.getEspecificaciones());

            productoRepo.save(productoEncontrado);
        }else {
            throw new ObjetoNoEncontradoException("El producto a actualizar no existe");
        }

    }

    @Override
    public void actualizarProducto(Producto p) throws ObjetoNoEncontradoException {

        if (p !=null){
            productoRepo.save(p);
        }else{
            throw new ObjetoNoEncontradoException("No se encontraron registros");
        }
    }

    @Override
    public void eliminarProducto(int idProducto) throws ObjetoNoEncontradoException {

        Producto productoEncontrado = obtenerProducto(idProducto);

        if (productoEncontrado!=null){

            if(productoEncontrado.getImagenes()!=null && productoEncontrado.getImagenes().isEmpty()){

                imagenRepo.deleteAll(productoEncontrado.getImagenes());

                productoEncontrado.getImagenes().clear();

            }

            if(productoEncontrado.getComentarios()!=null && productoEncontrado.getComentarios().isEmpty()){

                comentarioRepo.deleteAll(productoEncontrado.getComentarios());

                productoEncontrado.getComentarios().clear();

            }

            especificacionRepo.deleteAll(productoEncontrado.getEspecificaciones());

            productoEncontrado.getEspecificaciones().clear();

            productoEncontrado.setAdministrador(null);

            if(productoEncontrado.getUsuario() !=null){
                productoEncontrado.setUsuario(null);
            }

            productoRepo.save(productoEncontrado);
            productoRepo.delete(productoEncontrado);
        }else{
            throw new ObjetoNoEncontradoException("El producto a eliminar no existe");
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws ObjetoNoEncontradoException {

        Optional<Producto> productoEncontrado = productoRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new ObjetoNoEncontradoException("El producto buscado no existe");
        }
        return productoEncontrado.get();
    }

    @Override
    public Producto obtenerProductoNombre(String nombre) throws ObjetoNoEncontradoException {

        Producto productoEncontrado = productoRepo.obtenerProductoNombre(nombre);

        if (productoEncontrado==null){
            throw new ObjetoNoEncontradoException("El producto buscado no existe");
        }
        return productoEncontrado;
    }

    @Override
    public Producto obtenerProductoEstrella(int idUsuario) {

        List<Producto>productos = listarProductosUsuario(idUsuario);
        Producto productoEstrella = new Producto();

        if (!productos.isEmpty()){

            int calificacion = 0;

            for(Producto p:productos){

                if(p.getComentarios()!=null){

                    Integer calificacionAux = productoRepo.obtenerCalificacion(p.getId());

                    if(calificacionAux!=null && calificacionAux>calificacion){

                            calificacion = calificacionAux;
                            productoEstrella = p;
                    }
                }
            }
            return productoEstrella;
        }
        return productoEstrella;
    }

    @Override
    public int obtenerCalificacionPromedio(int idProducto) throws ObjetoNoEncontradoException {

        Integer calification;
        Producto productoEncontrado= obtenerProducto(idProducto);

        if (productoEncontrado!=null){

            calification = productoRepo.obtenerCalificacion(productoEncontrado.getId());
        }else{
            throw new ObjetoNoEncontradoException("El producto no fue encontrado");
        }

        return Objects.requireNonNullElse(calification, 0);

    }

    @Override
    public int[] obtenerPorcentaje(int idProducto) throws ObjetoNoEncontradoException {

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
            }

        }else{
            throw new ObjetoNoEncontradoException("El producto no existe");
        }
        return promedios;
    }

    @Override
    public void registrarComentario(Comentario c) throws ObjetoNoEncontradoException {

        try{
            c.setFechaComentario(new Date());
            comentarioRepo.save(c);
        }catch (Exception e){
            throw new ObjetoNoEncontradoException(e.getMessage());
        }
    }

    @Override
    public void ingresarComentario(Comentario c, Producto producto, Persona persona) throws ObjetoNoEncontradoException {

        try {
            if (producto != null && persona != null) {

                c.setFechaComentario(new Date());
                c.setProducto(producto);
                c.setUsuario((Usuario) persona);
                comentarioRepo.save(c);
            }
        }catch (Exception e){
            throw new ObjetoNoEncontradoException(e.getMessage());
        }
    }

    @Override
    public List<Producto> buscarProductos(String cadena) {
        return productoRepo.busquedaProductosNombre(cadena);
    }


    @Override
    public List<Producto> listarProductos() {
        return productoRepo.listarProductosGeneral();
    }

    @Override
    public List<Producto> listarProductosDestacados() {
        return productoRepo.listarProductosDestacados("Destacados");
    }

    @Override
    public List<Producto> listarTeclados() {
        return productoRepo.listarProductosPorCategoria("Teclados");
    }

    @Override
    public List<Producto> listarMouses() {
        return productoRepo.listarProductosPorCategoria("Mouses");
    }

    @Override
    public List<Producto> listarAudifonos() {
        return productoRepo.listarProductosPorCategoria("Audífonos");
    }

    @Override
    public List<Producto> listarPortatiles() {
        return productoRepo.listarProductosPorCategoria("Portátiles");
    }

    @Override
    public List<Producto> listarProductosUsuario(int idUsuario){
        return productoRepo.listarProductosPublicadosUsuario(idUsuario);
    }

    @Override
    public List<Producto> listarProductosAdmin(int idAdmin){
        return productoRepo.listarProductosPublicadosAdmin(idAdmin);
    }

    @Override
    public List<Producto> listarProductosSinAprobarUsuarios(){

        return productoRepo.listarProductosSinAprobarUsuarios();
    }

    @Override
    public List<Producto> listarProductosPorMenorPrecio() {
        return productoRepo.listarProductosPorMenorPrecio();
    }

    @Override
    public List<Producto> listarProductosPorMayorPrecio() {
        return productoRepo.listarProductosPorMayorPrecio();
    }
}