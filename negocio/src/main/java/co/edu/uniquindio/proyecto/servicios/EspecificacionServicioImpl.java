package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Especificacion;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import org.springframework.stereotype.Service;

@Service
public class EspecificacionServicioImpl implements EspecificacionServicio {
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
