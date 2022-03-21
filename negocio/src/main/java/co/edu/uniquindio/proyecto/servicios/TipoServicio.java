package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface TipoServicio {

    TipoMascota registrarTipo(TipoMascota t) throws Exception;

    void  actualizarTipo(TipoMascota t, int codigoTipo) throws Exception;

    void eliminarTipo(int id) throws  Exception;

    TipoMascota obtenerTipo(int id) throws Exception;

    List<TipoMascota> listarTipos();


}
