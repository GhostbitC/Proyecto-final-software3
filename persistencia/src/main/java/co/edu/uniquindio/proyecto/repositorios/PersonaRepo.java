package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonaRepo extends JpaRepository<Persona,Integer> {

    //================================= REPOSITORIO DE PERSONA =================================//

    Optional<Persona> findByEmailAndPassword(String email,String password);


}

