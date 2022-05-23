package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;
import java.util.List;

public interface ProductoServicio extends Serializable {

    Producto registrarProducto(Producto p) throws ObjetoNoEncontradoException;

    void actualizarProducto(Producto p,String nombre) throws ObjetoNoEncontradoException;

    void actualizarProducto(Producto p) throws ObjetoNoEncontradoException;

    void eliminarProducto(int idProducto) throws ObjetoNoEncontradoException;

    Producto obtenerProducto(int id) throws ObjetoNoEncontradoException;

    List<Producto> buscarProductos(String cadena);

    List<Producto> listarProductosDestacados();

    Producto obtenerProductoNombre(String nombre) throws ObjetoNoEncontradoException;

    Producto obtenerProductoEstrella(int idUsuario);

    int obtenerCalificacionPromedio(int idProducto) throws ObjetoNoEncontradoException;

    void ingresarComentario(Comentario c, Producto producto, Persona persona) throws ObjetoNoEncontradoException;

    void registrarComentario(Comentario c) throws ObjetoNoEncontradoException;

    int[] obtenerPorcentaje(int idProducto) throws ObjetoNoEncontradoException;

    List<Producto> listarProductos();

    List<Producto> listarTeclados();

    List<Producto> listarMouses();

    List<Producto> listarAudifonos();

    List<Producto> listarPortatiles();

    List<Producto> listarProductosUsuario(int idUsuario);

    List<Producto> listarProductosSinAprobarUsuarios();

    List<Producto> listarProductosPorMenorPrecio();

    List<Producto> listarProductosPorMayorPrecio();

    List<Producto> listarProductosAdmin(int idAdmin);

}
