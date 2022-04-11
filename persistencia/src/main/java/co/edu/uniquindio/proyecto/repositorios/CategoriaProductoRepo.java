package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaProductoRepo extends JpaRepository<Categoria,Integer> {

    //================================= REPOSITORIO DE CATEGORÍA =================================//

    @Query("select c from Categoria c where not c.nombre = :nombre")
    List<Categoria> obtenerCategoriasConverter(String nombre);

}
