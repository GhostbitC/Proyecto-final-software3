package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.DireccionRepo;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class DireccionServicioImpl implements DireccionServicio, Serializable {

    private final DireccionRepo direccionRepo;

    public DireccionServicioImpl(DireccionRepo direccionRepo) {
        this.direccionRepo = direccionRepo;
    }

    @Override
    public void registrarDireccion(Direccion d) throws ObjetoNoEncontradoException {

        if (d != null){
            direccionRepo.save(d);
        }else{
            throw new ObjetoNoEncontradoException("No se pueden validar los datos");
        }
    }
}
