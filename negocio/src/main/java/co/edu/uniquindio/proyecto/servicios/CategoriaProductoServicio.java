package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;

import java.io.Serializable;
import java.util.List;

public interface CategoriaProductoServicio extends Serializable {
    void registrarCategoria(Categoria c) throws ObjetoNoEncontradoException;

    Categoria obtenerCategoria(int id) throws ObjetoNoEncontradoException;

    List<Categoria> listarCategorias();

}
