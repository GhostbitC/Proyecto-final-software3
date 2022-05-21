package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CategoriaServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CategoriaProductoServicio categoriaProductoServicio;

    @Test
    public void registrarCategoriaProductoTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);

            Categoria c = new Categoria("Teclados","Mecánicos y de membrana",a);

            categoriaProductoServicio.registrarCategoria(c);

            Categoria registrada = categoriaProductoServicio.obtenerCategoria(1);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCategoriaProductoTest(){

        try {
            Categoria c = categoriaProductoServicio.obtenerCategoria(1);

            Assertions.assertNotNull(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCategoriasTest(){

        List<Categoria> lista = categoriaProductoServicio.listarCategorias();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
