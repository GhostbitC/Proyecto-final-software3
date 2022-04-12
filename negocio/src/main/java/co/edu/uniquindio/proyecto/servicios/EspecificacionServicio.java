package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface EspecificacionServicio {

    Especificacion registrarEspecificacion (Especificacion e) throws Exception;

    void actualizarEspecificacion (Especificacion e, int codigoEspecificacion) throws Exception;

    void eliminarEspecificacion(int id) throws Exception;

    List<Especificacion> obtenerEspecificacionesProducto(int idProducto) throws Exception;

    Especificacion obtenerEspecificacion (int id) throws Exception;

    List<Especificacion> listarEspecificaciones ();
}
