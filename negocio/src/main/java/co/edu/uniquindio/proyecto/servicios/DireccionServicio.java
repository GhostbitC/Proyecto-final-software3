package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

public interface DireccionServicio {

    void registrarDireccion(Direccion d) throws ObjetoNoEncontradoException;
}
