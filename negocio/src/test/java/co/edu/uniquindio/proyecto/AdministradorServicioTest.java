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
class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    void registrarAdministradorTest(){

        try {
            Administrador a = new Administrador("Sebastian","Quintero Osorio","Botsorio","21quintero04","sebastianquinteroosorio2104@gmail.com");

            administradorServicio.registrarAdministrador(a);

            Administrador aux = administradorServicio.obtenerAdministrador(1);

            Assertions.assertNotNull(aux);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void actualizarAdministradorTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);
            a.setApellido("Rodriguez");

            administradorServicio.actualizarAdministrador(a,a.getEmail(),a.getPassword());

            Administrador buscado = administradorServicio.obtenerAdministrador(1);

            Assertions.assertEquals("Rodriguez",buscado.getApellido());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerAdministradorTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);

            Assertions.assertNotNull(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerAdministradorEmailPasswordTest(){

        try {
            Administrador a = administradorServicio.obtenerEmailPassword("braianc.piedrahitar@uqvirtual.edu.co","b123");

            Assertions.assertNotNull(a);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void aprobarProductoUsuarioTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);
            Producto p = productoServicio.obtenerProducto(1);

            administradorServicio.aprobarProductoUsuario(p.getId(),a.getId());

            Producto aprobado = productoServicio.obtenerProducto(1);

            if (aprobado.getEstado() && aprobado.getAdministrador()!=null){
                Assertions.assertNotNull(aprobado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void rechazarProductoUsuarioTest() {

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);
            Producto p = productoServicio.obtenerProducto(2);

            administradorServicio.rechazarProductoUsuario(p.getId(),a.getId());

            Producto rechazado = productoServicio.obtenerProducto(2);

            if (!rechazado.getEstado() && rechazado.getAdministrador()==null){
             Assertions.assertNotNull(rechazado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void aprobarCompraUsuarioTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);
            Usuario u = usuarioServicio.obtenerUsuario(1);
            Compra c = compraServicio.obtenerCompraUsuario(u.getId(),1);

            administradorServicio.aprobarCompra(c.getId(),a.getId());

            Compra aprobada = compraServicio.obtenerCompraUsuario(u.getId(),1);

            if (aprobada.getEstado() && aprobada.getAdministrador()!=null){
                Assertions.assertNotNull(aprobada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void rechazarCompraUsuarioTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);
            Usuario u = usuarioServicio.obtenerUsuario(1);
            Compra c = compraServicio.obtenerCompraUsuario(u.getId(),2);

            administradorServicio.rechazarCompra(c.getId(),a.getId());

            Compra rechazada = compraServicio.obtenerCompraUsuario(u.getId(),2);

            if (!rechazada.getEstado() && rechazada.getAdministrador()!=null){
                Assertions.assertNotNull(rechazada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarAdministradoresTest(){

        List<Administrador> lista = administradorServicio.listarAdministradores();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
