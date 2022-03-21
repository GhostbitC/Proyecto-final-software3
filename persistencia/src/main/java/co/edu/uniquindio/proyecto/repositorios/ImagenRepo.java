package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen,Integer>{

    //================================= REPOSITORIO DE IMAGEN =================================//

    @Query("select i.url from Imagen i where i.mascota.id=:idMascota")
    String obtenerUrlImagen(Integer idMascota);

    @Query("select i.url from Imagen i where i.producto.id=:idProducto")
    String obtenerUrlProducto(Integer idProducto);
}
