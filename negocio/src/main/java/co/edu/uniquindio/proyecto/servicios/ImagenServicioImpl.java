package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

@Service
public class ImagenServicioImpl implements ImagenServicio{

    private final ImagenRepo imagenRepo;

    public ImagenServicioImpl(ImagenRepo imagenRepo) {
        this.imagenRepo = imagenRepo;
    }

    @Override
    public void registrarImagen(Imagen i) throws ObjetoNoEncontradoException {

        if (i.getUrl().length() > 100){
            throw  new ObjetoNoEncontradoException("La URL no es valida");
        }
        imagenRepo.save(i);
    }
}
