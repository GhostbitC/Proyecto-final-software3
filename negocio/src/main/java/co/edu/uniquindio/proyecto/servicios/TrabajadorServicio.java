package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface TrabajadorServicio {

    Trabajador registrarTrabajador(Trabajador t) throws Exception;

    void actualizarTrabajador(Trabajador t, String email, String password) throws Exception;

    void eliminarTrabajador(String email,String password) throws Exception;

    Trabajador obtenerTrabajador(String id) throws Exception;

    List<Trabajador> listarTrabajadores();

    Trabajador obtenerEmailPassword(String email, String password) throws Exception;
}
