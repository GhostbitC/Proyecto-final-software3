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
public class EspecificacionServicioTest {

    @Autowired
    private EspecificacionServicio especificacionServicio;

    @Autowired
    private ProductoServicio productoServicio;


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarEspecificacionTest(){

        try {

            Producto p = productoServicio.obtenerProducto(2);

            Especificacion e = new Especificacion("Compacto",p);

            Especificacion registrada = especificacionServicio.registrarEspecificacion(e);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarEspecificacionTest(){

        try {

            Especificacion e = especificacionServicio.obtenerEspecificacion(1);

            especificacionServicio.eliminarEspecificacion(e.getId());

            Especificacion eliminada = especificacionServicio.obtenerEspecificacion(1);

            Assertions.assertNull(eliminada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarEspecificacionTest(){

        try {

            Especificacion e = especificacionServicio.obtenerEspecificacion(1);
            e.setEspecificacion("Mal ensamblado");

            especificacionServicio.actualizarEspecificacion(e,e.getId());

            Especificacion buscada = especificacionServicio.obtenerEspecificacion(1);

            Assertions.assertEquals("Mal ensamblado",buscada.getEspecificacion());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDireccionTest(){

        try {
            Especificacion e = especificacionServicio.obtenerEspecificacion(1);

            Assertions.assertNotNull(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarEspecificacionesProductoTest(){

        try {
            Producto p = productoServicio.obtenerProducto(2);

            List<Especificacion> lista = especificacionServicio.obtenerEspecificacionesProducto(p.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarEspecificacionesTest(){

        List<Especificacion> lista = especificacionServicio.listarEspecificaciones();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
