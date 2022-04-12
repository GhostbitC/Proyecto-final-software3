package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface ProductoServicio {

    Producto registrarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p,String nombre) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(String nombre) throws Exception;

    Producto obtenerProducto(int id) throws Exception;

    List<Producto> buscarProductos(String cadena);

    List<Producto> listarProductosDestacados();

    void ingresarComentario(Comentario r, Producto producto) throws Exception;

    Producto obtenerProductoNombre(String nombre) throws Exception;

    int obtenerCalificacionPromedio(int idProducto) throws Exception;

    void ingresarComentario(Comentario c, Producto producto, Persona persona) throws Exception;

    void registrarComentario(Comentario c) throws Exception;

    int[] obtenerPorcentaje(int idProducto) throws Exception;

    List<Producto> listarProductos();

}
