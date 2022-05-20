package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

public interface UsuarioServicio {

    void registrarUsuario(Usuario u) throws Exception;

    void actualizarUsuario(String email, String password, Usuario u) throws Exception;

    void eliminarUsuario(String email,String password) throws Exception;

    void cambiarPassword(String email,String password) throws Exception;

    Usuario obtenerUsuario(int id) throws Exception;

    Usuario obtenerUsuarioNombre(String nombre) throws Exception;

    Usuario obtenerUsuarioEmail(String email) throws Exception;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws Exception;
}
