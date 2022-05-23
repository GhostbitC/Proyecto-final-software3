package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer>, Serializable {

    @Query("select p.unidades from Producto p where p.id =:id" )
    Integer verificarUnidadesProducto(Integer id);

}
