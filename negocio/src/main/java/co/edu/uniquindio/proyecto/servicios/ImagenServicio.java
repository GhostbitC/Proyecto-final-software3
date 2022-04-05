package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;

import java.util.List;

public interface ImagenServicio {

    Imagen registrarImagen(Imagen i) throws Exception;

    Imagen actualizarImagen(Imagen i) throws Exception;

    void eliminarImagen (int id) throws Exception;

    List<Imagen> obtenerImagenesProducto(int idProducto) throws Exception;

    Imagen obtenerImagen(int id) throws Exception;

    List<Imagen> listarImagenes() ;

}
