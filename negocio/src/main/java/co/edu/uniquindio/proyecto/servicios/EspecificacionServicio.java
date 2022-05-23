package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;

public interface EspecificacionServicio extends Serializable {

    void registrarEspecificacion (Especificacion e) throws ObjetoNoEncontradoException;

}
