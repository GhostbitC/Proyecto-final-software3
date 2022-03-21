package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;

import java.util.List;

public interface ReseniaServicio {

    Comentario registrarResenia(Comentario resenia) throws Exception;

    List<Comentario> obtenerReseniasProducto(int idProducto) throws Exception;

    List<Comentario> listarResenias();
}
