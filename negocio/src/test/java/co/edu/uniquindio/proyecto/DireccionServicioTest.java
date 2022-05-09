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
public class DireccionServicioTest {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private DireccionServicio direccionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDireccionTest(){

        try {
            Ciudad c = ciudadServicio.obtenerCiudad(1);
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Direccion d = new Direccion("27a","24-31",u,c);

            Direccion registrada = direccionServicio.registrarDireccion(d);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarDireccionTest(){

        try {

            Direccion d = direccionServicio.obtenerDireccion(1);

            direccionServicio.eliminarDireccion(d.getId());

            Direccion eliminada = direccionServicio.obtenerDireccion(1);

            Assertions.assertNull(eliminada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarDireccionTest(){

        try {

            Direccion d = direccionServicio.obtenerDireccion(1);
            d.setCalle("29");

            direccionServicio.actualizarDireccion(d);

            Direccion buscada = direccionServicio.obtenerDireccion(1);

            Assertions.assertEquals("29",buscada.getCalle());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDireccionTest(){

        try {
            Direccion d = direccionServicio.obtenerDireccion(1);

            Assertions.assertNotNull(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarDireccionesTest(){

        List<Direccion> lista = direccionServicio.listarDirecciones();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
