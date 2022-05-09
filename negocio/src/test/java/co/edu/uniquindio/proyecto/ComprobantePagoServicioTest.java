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
public class ComprobantePagoServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ComprobantePagoServicio comprobantePagoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Test
    public void registrarComprobantePagoTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Compra c = compraServicio.obtenerCompraUsuario(u.getId(),1);

            ComprobantePago cp = new ComprobantePago("adasdwdad",c);

            ComprobantePago registrado = comprobantePagoServicio.registrarComprobante(cp);

            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarComprobantePagoTest(){

        try {

            ComprobantePago cp = comprobantePagoServicio.obtenerComprobante(1);

            comprobantePagoServicio.eliminarComprobante(cp.getId());

            ComprobantePago eliminado = comprobantePagoServicio.obtenerComprobante(1);

            Assertions.assertNull(eliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarComprobantePagoTest(){

        try {
            ComprobantePago cp = comprobantePagoServicio.obtenerComprobante(1);
            cp.setUrl("adsadad");

            comprobantePagoServicio.actualizarComprobante(cp);

            ComprobantePago buscado = comprobantePagoServicio.obtenerComprobante(1);

            Assertions.assertEquals("adsadad",buscado.getUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprobantePagoTest(){

        try {
            ComprobantePago cp = comprobantePagoServicio.obtenerComprobante(1);

            Assertions.assertNotNull(cp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerImagenComprobantePagoTest(){

        try {
            ComprobantePago cp = comprobantePagoServicio.obtenerImagenComprobanteCompra(2);
            Assertions.assertNotNull(cp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprobantesTest(){

        List<ComprobantePago> lista = comprobantePagoServicio.listarComprobantes();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
