package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen,Integer>, Serializable {

    //================================= REPOSITORIO DE IMAGEN =================================//

}
