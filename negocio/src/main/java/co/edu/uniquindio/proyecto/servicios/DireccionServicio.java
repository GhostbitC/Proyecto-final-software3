package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;

public interface DireccionServicio extends Serializable {

    void registrarDireccion(Direccion d) throws ObjetoNoEncontradoException;
}
