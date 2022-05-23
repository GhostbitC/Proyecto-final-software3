package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.repositorios.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
@Transactional
<<<<<<< HEAD
public class PersonaServicioImpl implements PersonaServicio {
=======
public class PersonaServicioImpl implements PersonaServicio, Serializable {
>>>>>>> 7ee5dbbe7e1512263ca45170be7940984eca9eeb

    @Autowired
    private PersonaRepo personaRepo;

    @Override
    public Persona login(String email, String password) throws Exception {

        Optional<Persona> persona = personaRepo.findByEmailAndPassword(email,password);

        if (persona.isEmpty()){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return persona.get();
    }

}
