package co.edu.uniquindio.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraProductoRepo extends JpaRepository<CompraProducto,Integer> {

    @Query("select c from CompraProducto c where c.id =:idCompra and c.usuario.id =:cedulaUsuario")
    CompraProducto obtenerCompraUsuario(int idCompra, String cedulaUsuario);

    @Query("select c from CompraProducto c where c.usuario.id =:cedulaU")
    List<CompraProducto> obtenerhistorialCompraUsuario(String cedulaU);

}
