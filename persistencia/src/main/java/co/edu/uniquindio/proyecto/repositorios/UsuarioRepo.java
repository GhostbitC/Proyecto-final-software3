package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    Usuario findByEmailAndPassword(String email,String password);

    Usuario findByEmail(String email);

    Optional<Usuario> findByNickname(String nickname);
}
