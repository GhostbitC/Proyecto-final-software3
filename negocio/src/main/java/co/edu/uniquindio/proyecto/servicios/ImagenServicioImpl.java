package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
<<<<<<< HEAD
public class ImagenServicioImpl implements ImagenServicio {
=======
public class ImagenServicioImpl implements ImagenServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

    private final ImagenRepo imagenRepo;

    public ImagenServicioImpl(ImagenRepo imagenRepo) {
        this.imagenRepo = imagenRepo;
    }

    @Override
    public void registrarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }
        imagenRepo.save(i);
    }
}
