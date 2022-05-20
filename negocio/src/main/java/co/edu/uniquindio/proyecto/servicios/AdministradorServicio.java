package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface AdministradorServicio {

    void registrarAdministrador(Administrador a) throws Exception;

    void actualizarAdministrador(Administrador a,String email,String password) throws Exception;

    Administrador obtenerAdministrador(int id) throws Exception;

    List<Administrador> listarAdministradores();

    Administrador obtenerEmailPassword(String email,String password) throws Exception;

   void aprobarProductoUsuario(int idProducto, int idAdministrador) throws Exception;

   void rechazarProductoUsuario(int idProducto, int idAdministrador) throws Exception;

   void aprobarCompra(int idCompra, int idAdministrador);

   void rechazarCompra(int idCompra, int idAdministrador);
}
