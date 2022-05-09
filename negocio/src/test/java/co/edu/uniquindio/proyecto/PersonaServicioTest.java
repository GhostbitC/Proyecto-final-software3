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
public class PersonaServicioTest {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private EmailService emailService;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);
            Administrador a = administradorServicio.obtenerAdministrador(2);

            Usuario uLogin = (Usuario) personaServicio.login(u.getEmail(),u.getPassword());
            Administrador aLogin = (Administrador) personaServicio.login(a.getEmail(),a.getPassword());

            Assertions.assertNotNull(uLogin);
            Assertions.assertNotNull(aLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPersonaEmailTest(){

        try {

            Usuario u = (Usuario) personaServicio.obtenerPersonaEmail("sebastianquinteroosorio2104@gmail.com");
            Administrador a = (Administrador) personaServicio.obtenerPersonaEmail("braianc.piedrahitar@uqvirtual.edu.co");

            Assertions.assertNotNull(u);
            Assertions.assertNotNull(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            personaServicio.cambiarPassword(u.getEmail(),"12345678");
            String contenido = "<b>Cambiaste tu contraseña</b> <br> Ahora tu contraseña es " + u.getPassword();
            emailService.enviarEmail("Cambio de contraseña",contenido,u.getEmail());

            Usuario buscado = usuarioServicio.obtenerUsuario(1);

            if (buscado.getPassword().equals("12345678")){
              Assertions.assertNotNull(buscado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
