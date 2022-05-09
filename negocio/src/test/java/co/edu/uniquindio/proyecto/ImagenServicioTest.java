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
public class ImagenServicioTest {

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void registrarImagenTest(){

        try {
            Producto p = productoServicio.obtenerProducto(1);

            Imagen i = new Imagen("adsadasd",p);

            Imagen registrada = imagenServicio.registrarImagen(i);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarImagenTest(){

        try {

            Imagen i = imagenServicio.obtenerImagen(1);

            imagenServicio.eliminarImagen(i.getId());

            Imagen eliminada = imagenServicio.obtenerImagen(1);

            Assertions.assertNull(eliminada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarImagenTest(){

        try {
            Imagen i = imagenServicio.obtenerImagen(1);
            i.setUrl("adsderrrr");

            imagenServicio.actualizarImagen(i);

            Imagen buscada = imagenServicio.obtenerImagen(1);

            Assertions.assertEquals("adsderrrr",buscada.getUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerImagenTest(){

        try {
            Imagen i = imagenServicio.obtenerImagen(1);

            Assertions.assertNotNull(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarImagenesProductoTest(){

        try {
            Producto p = productoServicio.obtenerProducto(1);

            List<Imagen> lista = imagenServicio.obtenerImagenesProducto(p.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarImagenesTest(){

        List<Imagen> lista = imagenServicio.listarImagenes();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
