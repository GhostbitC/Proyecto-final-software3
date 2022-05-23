package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DireccionRepo extends JpaRepository<Direccion,Integer>, Serializable {

    //================================= REPOSITORIO DE PRODUCTO DIRECCIÓN =================================//
}
