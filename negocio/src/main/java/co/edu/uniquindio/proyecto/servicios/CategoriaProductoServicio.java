package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface CategoriaProductoServicio {


    CategoriaProducto registrarCategoria(CategoriaProducto c) throws Exception;

    void actualizarCategoria(CategoriaProducto c,int id) throws Exception;

    void eliminarCategoria(int id) throws Exception;

    CategoriaProducto obtenerCategoria(int id) throws Exception;

    List<CategoriaProducto> listarCategorias();

}
