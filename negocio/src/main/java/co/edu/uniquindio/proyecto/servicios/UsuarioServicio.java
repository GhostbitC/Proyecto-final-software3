package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;

public interface UsuarioServicio extends Serializable {

    void registrarUsuario(Usuario u) throws ObjetoNoEncontradoException;

    void actualizarUsuario(String email, String password, Usuario u) throws ObjetoNoEncontradoException;

    void eliminarUsuario(String email,String password) throws ObjetoNoEncontradoException;

    void cambiarPassword(String email,String password) throws ObjetoNoEncontradoException;

    Usuario obtenerUsuario(int id) throws ObjetoNoEncontradoException;

    Usuario obtenerUsuarioNombre(String nombre) throws ObjetoNoEncontradoException;

    Usuario obtenerUsuarioEmail(String email) throws ObjetoNoEncontradoException;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws ObjetoNoEncontradoException;
}
