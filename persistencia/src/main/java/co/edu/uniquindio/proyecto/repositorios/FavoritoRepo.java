package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito,Integer> {

    //================================= REPOSITORIO DE PRODUCTO FAVORITO =================================//
}
