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
class CiudadServicioTest {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    void registrarCiudadTest(){

        try {
            Ciudad c = new Ciudad("Armenia");

            ciudadServicio.registrarCiudad(c);

            Ciudad registrada = ciudadServicio.obtenerCiudad(1);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerCiudadTest(){

        try {
            Ciudad c = ciudadServicio.obtenerCiudad(1);

            Assertions.assertNotNull(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarCiudadesTest(){

        List<Ciudad> lista = ciudadServicio.listarCiudades();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
