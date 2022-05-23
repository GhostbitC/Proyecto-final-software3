package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    void registrarUsuarioTest(){

        try {
            Usuario u = new Usuario("Sebastian","Quintero Osorio","Botsorio","21quintero04","sebastianquinteroosorio2104@gmail.com","04/01/2001");

            usuarioServicio.registrarUsuario(u);

            Usuario registrado = usuarioServicio.obtenerUsuario(1);

            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void eliminarUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            usuarioServicio.eliminarUsuario(u.getEmail(),u.getPassword());

            Usuario eliminado = usuarioServicio.obtenerUsuario(1);

            Assertions.assertNull(eliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void actualizarUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);
            u.setApellido("Osorio");

            usuarioServicio.actualizarUsuario(u.getEmail(),u.getPassword(),u);

            Usuario buscado = usuarioServicio.obtenerUsuario(1);

            Assertions.assertEquals("Osorio",buscado.getApellido());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Assertions.assertNotNull(u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerUsuarioEmailPasswordTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuarioEmailPassword("sebastianquinteroosorio2104@gmail.com","s123");

            Assertions.assertNotNull(u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerUsuarioNombreTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuarioNombre("Sebastian");

            Assertions.assertNotNull(u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
