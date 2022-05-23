package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class CiudadServicioImpl implements CiudadServicio, Serializable {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public void registrarCiudad(Ciudad ciudad) throws ObjetoNoEncontradoException {

        if (ciudad.getNombre().length()>100){
            throw new ObjetoNoEncontradoException("El nombre solo puede tener 100 caracteres compa");
        }
        ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws ObjetoNoEncontradoException {

        Optional<Ciudad> ciudad= ciudadRepo.findById(id);

        if (ciudad.isEmpty()){
            throw new ObjetoNoEncontradoException("La ciudad buscada no existe");
        }

        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
