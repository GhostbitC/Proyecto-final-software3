package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    //================================= REPOSITORIO DE USUARIO =================================//
    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    Usuario findByEmailAndPassword(String email,String password);

    Optional<Usuario> findByNickname(String nickname);

    Usuario findByEmail(String email);

    @Query("select u from Usuario u where u.cedula =:cedula")
    Usuario obtenerUsuarioCedula(String cedula);

}
