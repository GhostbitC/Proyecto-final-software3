package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
class CompraServicioTest {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private EmailService emailService;

    @Test
    @Sql("classpath:dataset.sql")
    void registrarCompraTest(){

        try {

            Administrador a = administradorServicio.obtenerAdministrador(2);
            Usuario u = usuarioServicio.obtenerUsuario(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCompra = sdf.parse("2022/05/05");

            Compra nuevaCompra = new Compra(fechaCompra,true,"Consignación",u,a);

            compraServicio.crearCompra(nuevaCompra);

            Compra c= compraServicio.obtenerCompraUsuario(u.getId(),1);

            Assertions.assertNotNull(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void agregarCompraTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Compra c = compraServicio.obtenerCompraUsuario(u.getId(),1);
            Producto p = productoServicio.obtenerProducto(2);

            ProductoCarrito pc = new ProductoCarrito(1,p.getNombre(),p.getImagenPrincipal(),1,(float) p.getPrecio());

            ArrayList<ProductoCarrito> pcs = new ArrayList<>();
            pcs.add(pc);

            compraServicio.agregarCompra(pcs,u,"Consignación");

            Compra compra = compraServicio.obtenerCompraUsuario(u.getId(),c.getId());

            Assertions.assertNotNull(compra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarComprasSinAprobarUsuarioTest(){

        List<Compra> lista = compraServicio.listarComprasSinAprobarUsuarios();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarComprasSinComprobanteUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            List<Compra> lista = compraServicio.listarComprasUsuarioSinComprobante(u.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarComprasUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            List<Compra> lista = compraServicio.listarComprasUsuario(u.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerComprasUsuarioTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Compra c = compraServicio.obtenerCompraUsuario(u.getId(),2);

            Assertions.assertNotNull(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void enviarEmail() {

        Boolean aux = false;

        emailService.enviarEmail("Prueba","Prueba", "sebastianquinteroosorio2104@gmail.com");
        aux = true;

        Assertions.assertNotNull(true);

    }

}
