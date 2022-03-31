package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Imagen;
import co.edu.uniquindio.proyecto.entidades.ProductoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoUsuarioRepo  extends JpaRepository<ProductoUsuario,Integer> {

    //================================= REPOSITORIO DE PRODUCTO USUARIO =================================//
    @Query("select i from ProductoUsuario p join p.imagenes i where p.id = :idProducto")
    List<Imagen> obtenerImagenes(int idProducto);

    @Query("select p from ProductoUsuario p where p.nombre =:nombreProducto")
    ProductoUsuario obtenerProductoUsuarioNombre(String nombreProducto);

    @Query("select p from ProductoUsuario p where p.nombre like concat('%',:cadena,'%')")
    List<ProductoUsuario> busquedaProductosUsuarioNombre(String cadena);
}
