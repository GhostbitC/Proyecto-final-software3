package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import java.util.List;

public interface ComentarioServicio {

    List<Comentario> obtenerComentariosProducto(int idProducto) throws  Exception;
}
