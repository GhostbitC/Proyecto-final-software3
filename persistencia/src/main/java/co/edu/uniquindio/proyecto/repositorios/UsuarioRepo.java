package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    //================================= REPOSITORIO DE USUARIO =================================//
}
