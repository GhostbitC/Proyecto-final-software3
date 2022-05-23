package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobantePagoRepo extends JpaRepository<ComprobantePago,Integer>{

    @Query("select com.url from ComprobantePago com where com.compra.id=:idCompra")
    String obtenerUrlCompra(Integer idCompra);
}
