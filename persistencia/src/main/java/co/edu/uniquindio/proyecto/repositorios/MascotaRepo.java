package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MascotaRepo extends JpaRepository<Mascota,Integer>{

    //================================= REPOSITORIO DE LUGAR =================================//

    @Query("select m.tipo.nombre from Mascota m where m.id = :idMascota ")
    String obtenerTipoMascota(Integer idMascota);

    @Query("select m from Mascota m where m.nombre like concat('%',:nombre, '%')")
    Mascota obtenerMascotaNombre(String nombre);

    //Lugares que estan aprobados
    @Query("select m from Mascota m where m.nombre like concat('%',:nombre, '%') ")
    List<Mascota> buscarMascotas(String nombre);

    @Query("select m from Mascota m where m.nombre like concat('%', :cadena,'%') or m.tipo.nombre like concat('%', :cadena,'%')")
    List<Mascota> busquedaMascotasTipoNombre(String cadena);

    @Query("select m from Mascota  m where m.id = :idMascota")
    Mascota obtenerMascota(int idMascota);

    @Query("select i from Mascota m join m.imagenes i where m.id = :idMascota")
    List<Imagen> obtenerImagenes(int idMascota);

    @Query("select m from Mascota m where m.usuario.id =:idUsuario and m.nombre =:nombreMascota")
    Mascota obtenerMascotaPorUsuario(String nombreMascota,String idUsuario);

    @Query("select m from Mascota m where m.usuario.id= :idUsuario")
    List<Mascota> obtenerMascotasPorUsuario(String idUsuario);


}
