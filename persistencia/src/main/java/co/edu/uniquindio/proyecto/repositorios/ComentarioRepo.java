package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario,Integer>, Serializable {

    //================================= REPOSITORIO DE COMENTARIO =================================//

}
