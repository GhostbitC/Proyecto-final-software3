package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito,Integer>, Serializable {

    //================================= REPOSITORIO DE PRODUCTO FAVORITO =================================//
}
