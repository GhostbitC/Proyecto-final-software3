package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

public interface ImagenServicio {

    void registrarImagen(Imagen i) throws ObjetoNoEncontradoException;
}
