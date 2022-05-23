package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EnvioRepo extends JpaRepository<Envio,Integer> , Serializable {

    //================================= REPOSITORIO DE PRODUCTO ENVÍO =================================//
}
