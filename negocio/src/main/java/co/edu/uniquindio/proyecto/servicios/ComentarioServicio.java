package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;

import java.util.List;

public interface ComentarioServicio {

    Comentario registrarComentario(Comentario c) throws  Exception;

    void actualizarComentario(Comentario c,int codigoComentario) throws  Exception;

    void eliminarComentario(int id) throws  Exception;

    void responderComentario(String respuesta, int idComentario) throws Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> listarComentarios();

    List<Comentario> obtenerComentariosProducto(int idProducto) throws  Exception;
}
