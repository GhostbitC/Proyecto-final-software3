package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.CategoriaProducto;
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
    public CategoriaProducto registrarCategoria(CategoriaProducto c) throws Exception {

        if (c.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }
        return categoriaRepo.save(c);
    }

    @Override
    public void actualizarCategoria(CategoriaProducto c, int id) throws Exception {

        CategoriaProducto categoriaEncontrada = obtenerCategoria(id);

        if (categoriaEncontrada!=null){
            categoriaEncontrada.setNombre(c.getNombre());
            categoriaEncontrada.setDescripcion(c.getDescripcion());
        }else {
            throw new Exception("La categoria a actualizar no existe");
        }
    }

    @Override
    public void eliminarCategoria(int id) throws Exception {

        CategoriaProducto categoriaEncontrada = obtenerCategoria(id);

        if (categoriaEncontrada != null){

            categoriaRepo.delete(categoriaEncontrada);
        }else {
            throw new Exception("La categoria a eliminar no existe");
        }

    }


    @Override
    public CategoriaProducto obtenerCategoria(int id) throws Exception {

        Optional<CategoriaProducto> categoriaEncontrada = categoriaRepo.findById(id);

        if (categoriaEncontrada.isEmpty()){
            throw new Exception("La categoria a encontrar no existe");
        }

        return categoriaEncontrada.get();
    }

    @Override
    public List<CategoriaProducto> listarCategorias() {
        return categoriaRepo.findAll();
    }
}
