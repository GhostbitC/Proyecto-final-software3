package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;

public interface ImagenServicio extends Serializable {

    void registrarImagen(Imagen i) throws ObjetoNoEncontradoException;
}
