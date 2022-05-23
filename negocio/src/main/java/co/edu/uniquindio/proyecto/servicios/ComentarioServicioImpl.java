package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
<<<<<<< HEAD
public class ComentarioServicioImpl implements ComentarioServicio {
=======
public class ComentarioServicioImpl implements ComentarioServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

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
