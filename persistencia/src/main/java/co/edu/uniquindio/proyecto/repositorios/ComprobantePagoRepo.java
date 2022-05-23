package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ComprobantePagoRepo extends JpaRepository<ComprobantePago,Integer>, Serializable {

    @Query("select com.url from ComprobantePago com where com.compra.id=:idCompra")
    String obtenerUrlCompra(Integer idCompra);
}
