package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Especificacion;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
<<<<<<< HEAD
public class EspecificacionServicioImpl implements EspecificacionServicio {
=======
public class EspecificacionServicioImpl implements EspecificacionServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

   private final EspecificacionRepo especificacionRepo;

    public EspecificacionServicioImpl(EspecificacionRepo especificacionRepo) {
        this.especificacionRepo = especificacionRepo;
    }

    @Override
    public void registrarEspecificacion(Especificacion e) throws Exception {

        if(e.getEspecification().length() > 500){
            throw new Exception("No puede superar los 500 caracteres");
        }
        especificacionRepo.save(e);
    }
}
