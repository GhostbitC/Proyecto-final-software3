package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u, Direccion direccionUsuario) throws Exception;

    void actualizarUsuario(String email, String password, Usuario u) throws Exception;

    void eliminarUsuario(String email, String password) throws Exception;
//
//    void registrarTarjetaUsuario(String idUsuario,String numero,String codigo,String fecha)throws Exception;
//
    Usuario obtenerUsuario(String id) throws Exception;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws Exception;

    void crearCompra(String cedulaUsuario, int idProducto) throws Exception;

    void agregarProductoCompra(String cedulaUsuario, int idCompra, int idProducto) throws Exception;

    void eliminarCompra(String cedulaUsuario, int idCompra) throws Exception;

    void eliminarProductosCompra(String cedulaUsuario, int idCompra, int idProducto) throws Exception;

    Compra obtenerCompra(int id) throws Exception;
//
//    List<Compra> obtenerHistorialServicios(String cedulaU);
//
//    List<Compra> obtenerServiciosActivos(String cedulaU);
//
//    List<CompraProducto> obtenerProductosUsuario(String cedulaU);
//
//    void cancelarServicio(int idCompra,int idServicio,String cedula) throws Exception;
//
//    void adquirirProducto(Producto producto,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception;

    List<Usuario> listarUsuarios();
}
