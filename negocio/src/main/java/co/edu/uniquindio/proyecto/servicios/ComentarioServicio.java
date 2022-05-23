package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;
import java.util.List;

public interface ComentarioServicio extends Serializable {

    List<Comentario> obtenerComentariosProducto(int idProducto) throws ObjetoNoEncontradoException;
}
