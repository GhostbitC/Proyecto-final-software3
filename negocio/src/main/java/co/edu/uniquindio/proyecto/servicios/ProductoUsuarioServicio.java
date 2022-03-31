package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.ProductoUsuario;

import java.util.List;

public interface ProductoUsuarioServicio {

    ProductoUsuario registrarProductoUsuario(ProductoUsuario p) throws Exception;

    void actualizarProductoUsuario(ProductoUsuario p, String nombre) throws Exception;

    void actualizarProductoUsuario(ProductoUsuario p) throws Exception;

    void eliminarProductoUsuario(String nombre) throws Exception;

    ProductoUsuario obtenerProductoUsuario(int id) throws Exception;

    ProductoUsuario obtenerProductoNombreUsuario(String nombre) throws Exception;

    List<ProductoUsuario> buscarProductos(String cadena);

    List<ProductoUsuario> listarProductos();
}
