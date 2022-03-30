package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad,Integer>{

    //================================= REPOSITORIO DE CIUDAD =================================//


}
