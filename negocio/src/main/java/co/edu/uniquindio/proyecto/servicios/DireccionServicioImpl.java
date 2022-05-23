package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import co.edu.uniquindio.proyecto.repositorios.DireccionRepo;
import org.springframework.stereotype.Service;


@Service
public class DireccionServicioImpl implements DireccionServicio {

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
