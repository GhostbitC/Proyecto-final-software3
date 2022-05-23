package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
<<<<<<< HEAD
public class CiudadServicioImpl implements CiudadServicio {
=======
public class CiudadServicioImpl implements CiudadServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public void registrarCiudad(Ciudad ciudad) throws Exception {

        if (ciudad.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }
        ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws Exception {

        Optional<Ciudad> ciudad= ciudadRepo.findById(id);

        if (ciudad.isEmpty()){
            throw new Exception("La ciudad buscada no existe");
        }

        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
