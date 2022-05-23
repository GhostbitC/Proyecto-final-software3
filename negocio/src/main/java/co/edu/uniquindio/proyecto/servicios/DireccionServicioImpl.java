package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import co.edu.uniquindio.proyecto.repositorios.DireccionRepo;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
<<<<<<< HEAD
public class DireccionServicioImpl implements DireccionServicio {
=======
public class DireccionServicioImpl implements DireccionServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

    private final DireccionRepo direccionRepo;

    public DireccionServicioImpl(DireccionRepo direccionRepo) {
        this.direccionRepo = direccionRepo;
    }

    @Override
    public void registrarDireccion(Direccion d) throws Exception {

        if (d != null){
            direccionRepo.save(d);
        }else{
            throw new Exception("No se pueden validar los datos");
        }
    }
}
