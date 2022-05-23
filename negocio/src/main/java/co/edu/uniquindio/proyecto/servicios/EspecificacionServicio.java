package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

public interface EspecificacionServicio {

    void registrarEspecificacion (Especificacion e) throws ObjetoNoEncontradoException;

}
