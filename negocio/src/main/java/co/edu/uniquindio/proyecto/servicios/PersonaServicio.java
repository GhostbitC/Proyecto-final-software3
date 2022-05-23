package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

public interface PersonaServicio {

    Persona login(String email,String password) throws ObjetoNoEncontradoException;

}
