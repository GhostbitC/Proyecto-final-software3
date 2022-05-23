package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.util.List;

public interface AdministradorServicio {

    void registrarAdministrador(Administrador a) throws Exception;

    void actualizarAdministrador(Administrador a,String email,String password);
    Administrador obtenerAdministrador(int id) throws ObjetoNoEncontradoException;

    List<Administrador> listarAdministradores();

    Administrador obtenerEmailPassword(String email,String password) throws ObjetoNoEncontradoException;

   void aprobarProductoUsuario(int idProducto, int idAdministrador) throws ObjetoNoEncontradoException;

   void rechazarProductoUsuario(int idProducto, int idAdministrador) throws ObjetoNoEncontradoException;

   void aprobarCompra(int idCompra, int idAdministrador);

   void rechazarCompra(int idCompra, int idAdministrador);
}
