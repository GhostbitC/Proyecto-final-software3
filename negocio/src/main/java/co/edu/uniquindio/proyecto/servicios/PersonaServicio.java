package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;

public interface PersonaServicio extends Serializable {

    Persona login(String email,String password) throws ObjetoNoEncontradoException;

}
