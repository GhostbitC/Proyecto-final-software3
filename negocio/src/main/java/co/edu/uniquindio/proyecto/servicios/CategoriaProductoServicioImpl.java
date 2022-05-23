package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.CategoriaProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProductoServicioImpl implements CategoriaProductoServicio {

    private final CategoriaProductoRepo categoriaRepo;

    public CategoriaProductoServicioImpl(CategoriaProductoRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public void registrarCategoria(Categoria c) throws ObjetoNoEncontradoException {

        if (c.getNombre().length() >100){
            throw new ObjetoNoEncontradoException("No se puede exceder los 100 caracteres");
        }
        categoriaRepo.save(c);
    }

    @Override
    public Categoria obtenerCategoria(int id) throws ObjetoNoEncontradoException {

        Optional<Categoria> categoriaEncontrada = categoriaRepo.findById(id);

        if (categoriaEncontrada.isEmpty()){
            throw new ObjetoNoEncontradoException("La categoría a encontrar no existe");
        }
        return categoriaEncontrada.get();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.obtenerCategoriasConverter("Destacados");
    }
}
