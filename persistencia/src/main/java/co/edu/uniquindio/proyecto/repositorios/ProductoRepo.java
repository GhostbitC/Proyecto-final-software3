package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto,Integer> {

    @Query("select p from Producto p where p.nombre =:nombreProducto")
    Producto obtenerProductoNombre(String nombreProducto);

    @Query("select p from Producto p where p.nombre like concat('%',:cadena,'%')")
    List<Producto> busquedaProductosNombre(String cadena);

    @Query("select p from Producto p join p.categoria c where c.nombre =:cadena")
    List<Producto> listarProductosDestacados(String cadena);

    @Query("select p from Producto p where p.estado is true")
    List<Producto> listarProductosGeneral();

    @Query("select p from Producto p join p.categoria c where c.nombre =:cadena and p.estado = true")
    List<Producto> listarProductosPorCategoria(String cadena);

    @Query("select avg(c.calificacion) from Comentario c where c.producto.id = :idProducto")
    Integer obtenerCalificacion(Integer idProducto);

    @Query("select count(*) from Comentario c where c.producto.id = :idProducto and c.calificacion = :calificacion")
    Integer obtenerCantidadCalificacion(Integer idProducto,Integer calificacion);

    @Query("select count(*) from Comentario c where c.producto.id = :idProducto")
    Integer obtenerCantidad(Integer idProducto);

    @Query("select c from Producto p join p.comentarios c where p.id =:idProducto")
    List<Comentario>obtenerComentariosProducto(Integer idProducto);

    @Query("select p from Producto p where p.estado = true and p.usuario.id = :idUsuario")
    List<Producto>listarProductosPublicadosUsuario(int idUsuario);

    @Query("select p from Producto p where p.estado = true and p.administrador.id =:idAdmin and p.usuario is null")
    List<Producto>listarProductosPublicadosAdmin(int idAdmin);

    @Query("select p from Producto p where p.estado = false and p.usuario is not null")
    List<Producto>listarProductosSinAprobarUsuarios();

    @Query("select p from Producto p order by p.precio asc")
    List<Producto>listarProductosPorMenorPrecio();

    @Query("select p from Producto p order by p.precio desc")
    List<Producto>listarProductosPorMayorPrecio();

}
