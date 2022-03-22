package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen,Integer>{

    //================================= REPOSITORIO DE IMAGEN =================================//

}
