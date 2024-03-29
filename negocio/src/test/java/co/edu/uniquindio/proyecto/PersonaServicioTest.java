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
class PersonaServicioTest {

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdministradorServicio administradorServicio;


    @Test
    @Sql("classpath:dataset.sql")
    void loginTest(){

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

}
