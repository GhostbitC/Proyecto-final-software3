package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface DireccionServicio {

    Direccion registrarDireccion(Direccion d) throws Exception;

    Direccion actualizarDireccion(Direccion d) throws Exception;

    void eliminarDireccion(int id) throws Exception;

    Direccion obtenerDireccion(int id) throws Exception;

    List<Direccion> listarDirecciones();
}
