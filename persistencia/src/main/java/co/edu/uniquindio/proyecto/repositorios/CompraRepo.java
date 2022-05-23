package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.ComprobantePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra,Integer> {

    //================================= REPOSITORIO DE COMPRA =================================//

    @Query("select c from Compra c where c.usuario.id = :id and c.estado = true")
    List <Compra> listarComprasUsuario (int id);

    @Query("Select c from Compra c where c.usuario.id = :id and c.comprobantePago is null")
    List <Compra> listarComprasUsuarioSinComprobante(int id);

    @Query("select c from Compra c where c.estado is null and c.comprobantePago is not null")
    List<Compra> listarComprasSinAprobar();

    @Query("select c from Compra c where c.usuario.id =:idUsuario and c.id =:idCompra")
    Compra obtenerCompraUsuario(int idUsuario,int idCompra);

}
