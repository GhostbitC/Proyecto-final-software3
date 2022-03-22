package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepo extends JpaRepository<Envio,Integer> {

    //================================= REPOSITORIO DE PRODUCTO ENVÍO =================================//
}
