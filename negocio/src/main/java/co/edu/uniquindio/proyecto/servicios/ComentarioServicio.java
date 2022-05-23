package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.util.List;

public interface ComentarioServicio {

    List<Comentario> obtenerComentariosProducto(int idProducto) throws ObjetoNoEncontradoException;
}
