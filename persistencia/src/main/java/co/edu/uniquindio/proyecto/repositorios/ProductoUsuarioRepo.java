package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.ProductoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoUsuarioRepo  extends JpaRepository<ProductoUsuario,Integer> {

    //================================= REPOSITORIO DE PRODUCTO USUARIO =================================//
}
