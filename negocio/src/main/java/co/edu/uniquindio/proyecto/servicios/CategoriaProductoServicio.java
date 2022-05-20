package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import java.util.List;

public interface CategoriaProductoServicio {
    void registrarCategoria(Categoria c) throws Exception;

    Categoria obtenerCategoria(int id) throws Exception;

    List<Categoria> listarCategorias();

}
