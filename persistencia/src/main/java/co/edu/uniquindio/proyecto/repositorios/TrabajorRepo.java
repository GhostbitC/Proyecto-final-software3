package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajorRepo extends JpaRepository<Trabajador,String> {

    //================================= REPOSITORIO DE MODERADOR =================================//

    Optional<Trabajador> findByEmail(String email);

    Optional<Trabajador> findByNickname(String nickname);

    Trabajador findByEmailAndPassword(String email, String password);
}
