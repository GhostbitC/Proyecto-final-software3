package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepo extends JpaRepository<Servicio,Integer> {

    @Query("select s from Servicio s where s.nombre =:nombreServicio ")
    Servicio obtenerServicioNombre(String nombreServicio);

}
