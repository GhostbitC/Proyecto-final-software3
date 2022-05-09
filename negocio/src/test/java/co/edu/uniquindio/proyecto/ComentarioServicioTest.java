package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void registrarComentarioTest(){

        try {

            Usuario u = usuarioServicio.obtenerUsuario(1);
            Producto p = productoServicio.obtenerProducto(2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaComentario = sdf.parse("2022/05/05");

            Comentario c = new Comentario("Gran producto",4,fechaComentario,p,u);

            Comentario registrado = comentarioServicio.registrarComentario(c);

            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComentarioTest(){

        try {

            Comentario c = comentarioServicio.obtenerComentario(1);

            comentarioServicio.eliminarComentario(c.getId());

            Comentario eliminado = comentarioServicio.obtenerComentario(1);

            Assertions.assertNull(eliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComentarioTest(){

        try {
            Comentario c = comentarioServicio.obtenerComentario(1);
            c.setCalificacion(1);

            comentarioServicio.actualizarComentario(c,c.getId());

            Comentario buscado = comentarioServicio.obtenerComentario(1);

            Assertions.assertEquals(1,buscado.getCalificacion());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComentarioTest(){

        try {
            Comentario c = comentarioServicio.obtenerComentario(1);

            Assertions.assertNotNull(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosPorProductoTest(){

        try {
            Producto p = productoServicio.obtenerProducto(2);

            List<Comentario> lista = comentarioServicio.obtenerComentariosProducto(p.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosTest(){

        List<Comentario> lista = comentarioServicio.listarComentarios();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
