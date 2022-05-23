package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
class ProductoServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaProductoServicio categoriaProductoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    void registrarProductoTest(){

        try {
            Administrador a = administradorServicio.obtenerAdministrador(1);

            Categoria c = categoriaProductoServicio.obtenerCategoria(1);

            Producto p = new Producto("Asus Gaming","Pc gamer",29.399,a,c);

            Producto registrado = productoServicio.registrarProducto(p);

            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void eliminarProductoTest(){

        try {

            Producto p = productoServicio.obtenerProducto(2);

            productoServicio.eliminarProducto(p.getId());

            Producto eliminado = productoServicio.obtenerProducto(2);

            Assertions.assertNull(eliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void actualizarProductoTest(){

        try {
            Producto p = productoServicio.obtenerProducto(2);
            p.setNombre("502 Logitech");

            productoServicio.actualizarProducto(p);

            Producto buscado = productoServicio.obtenerProducto(2);

            Assertions.assertEquals("502 Logitech",buscado.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerProductoTest(){

        try {
            Producto p = productoServicio.obtenerProducto(2);

            Assertions.assertNotNull(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerCalificacionPromedioTest(){

        try {
            boolean comprobado=false;
            int calificacionPromedio = productoServicio.obtenerCalificacionPromedio(2);

            if(calificacionPromedio>=0){
                comprobado=true;
            }

            Assertions.assertTrue(comprobado);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    void ingresarComentarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);
            Producto p = productoServicio.obtenerProducto(2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaComentario = sdf.parse("2022/05/05");
            Comentario c = new Comentario("Buen producto",4,fechaComentario,p,u);

            productoServicio.ingresarComentario(c,p,u);

            Producto buscado = productoServicio.obtenerProducto(2);

            if (buscado.getComentarios().contains(c)){
                Assertions.assertNotNull(buscado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void registrarComentarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);
            Producto p = productoServicio.obtenerProducto(2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaComentario = sdf.parse("2022/05/05");
            Comentario c = new Comentario("Buen producto",4,fechaComentario,p,u);

            productoServicio.registrarComentario(c);

            Producto buscado = productoServicio.obtenerProducto(2);

            if (buscado.getComentarios().contains(c)){
                Assertions.assertNotNull(buscado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerPorcentajeTest(){

        try {
            Producto p = productoServicio.obtenerProducto(2);

            int[] porcentaje = productoServicio.obtenerPorcentaje(p.getId());

            Assertions.assertNotNull(porcentaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerProductoEstrellaTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            Producto p = productoServicio.obtenerProductoEstrella(u.getId());

            Assertions.assertNotNull(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void obtenerProductoNombreTest(){

        try {
            Producto p = productoServicio.obtenerProductoNombre("Asus vivoBook");

            Assertions.assertNotNull(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarProductosTest(){

        List<Producto> lista = productoServicio.listarProductos();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarTecladosTest(){

        List<Producto> lista = productoServicio.listarTeclados();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarMousesTest(){

        List<Producto> lista = productoServicio.listarMouses();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarAudifonosTest(){

        List<Producto> lista = productoServicio.listarAudifonos();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarPortatilesTest(){

        List<Producto> lista = productoServicio.listarPortatiles();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarProductosUsuarioTest(){

        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);

            List<Producto> lista = productoServicio.listarProductosUsuario(u.getId());
            lista.forEach(System.out::println);

            Assertions.assertNotNull(lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarProductosSinAprobarTest(){

        List<Producto> lista = productoServicio.listarProductosSinAprobarUsuarios();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    void listarProductosDestacadosTest(){

        List<Producto> lista = productoServicio.listarProductosDestacados();
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }


    @Test
    @Sql("classpath:dataset.sql")
    void buscarProductosTest(){

        List<Producto> lista = productoServicio.buscarProductos("Asus");
        lista.forEach(System.out::println);

        Assertions.assertNotNull(lista);
    }

}
