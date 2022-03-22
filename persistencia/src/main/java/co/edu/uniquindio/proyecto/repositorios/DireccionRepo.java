package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepo extends JpaRepository<Direccion,Integer> {

    //================================= REPOSITORIO DE PRODUCTO DIRECCIÓN =================================//
}
