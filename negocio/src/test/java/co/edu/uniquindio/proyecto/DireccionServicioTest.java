package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.DireccionRepo;
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
public class DireccionServicioTest {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private DireccionServicio direccionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DireccionRepo direccionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDireccionTest(){
        try {
            Ciudad c = ciudadServicio.obtenerCiudad(1);
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Direccion d = new Direccion("27a","24-31",u,c);

            direccionServicio.registrarDireccion(d);

            Optional<Direccion> registrada = direccionRepo.findById(1);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
