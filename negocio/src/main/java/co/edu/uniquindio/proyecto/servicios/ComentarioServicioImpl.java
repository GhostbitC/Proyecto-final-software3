package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ProductoRepo productoRepo;

    public ComentarioServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public List<Comentario> obtenerComentariosProducto(int idProducto) throws Exception {

        List<Comentario>comentariosLugar = productoRepo.obtenerComentariosProducto(idProducto);

        if(comentariosLugar==null){

            throw new Exception("No se encontraron los comentarios");
        }
        return comentariosLugar;
    }
}
