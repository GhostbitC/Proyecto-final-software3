package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class EspecificacionServicioTest {

    @Autowired
    private EspecificacionServicio especificacionServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EspecificacionRepo especificacionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarEspecificacionTest(){
        try {
            Producto p = productoServicio.obtenerProducto(2);

            Especificacion e = new Especificacion("Compacto",p);

            especificacionServicio.registrarEspecificacion(e);

            Optional<Especificacion> registrada = especificacionRepo.findById(1);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
