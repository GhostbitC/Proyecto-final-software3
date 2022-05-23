package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;
import java.util.List;

public interface CiudadServicio extends Serializable {

    void registrarCiudad(Ciudad ciudad) throws ObjetoNoEncontradoException;

    Ciudad obtenerCiudad(int id) throws ObjetoNoEncontradoException;

    List<Ciudad> listarCiudades();
}
