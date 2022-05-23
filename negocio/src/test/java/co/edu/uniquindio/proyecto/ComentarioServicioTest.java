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
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosPorProductoTest() {

        try {
            Producto p = productoServicio.obtenerProducto(2);

            List<Comentario> lista = comentarioServicio.obtenerComentariosProducto(p.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
