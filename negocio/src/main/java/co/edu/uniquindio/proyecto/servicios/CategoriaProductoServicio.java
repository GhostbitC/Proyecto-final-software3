package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface CategoriaProductoServicio {


    Categoria registrarCategoria(Categoria c) throws Exception;

    void actualizarCategoria(Categoria c, int id) throws Exception;

    void eliminarCategoria(int id) throws Exception;

    Categoria obtenerCategoria(int id) throws Exception;

    List<Categoria> listarCategorias();

}
