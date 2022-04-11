package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            Administrador admin1= new Administrador("Sebastian","Quintero","Botsorio","admin1","admin1@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Administrador admin2= new Administrador("Braian","Piedrahita","Ghostbit","admin2","admin2@gmail.com");
            administradorServicio.registrarAdministrador(admin2);

            Categoria categoria1 = new Categoria("Mouses","Perifericos",admin1);
            categoriaServicio.registrarCategoria(categoria1);

            Categoria categoria2 = new Categoria("Teclados","Perifericos",admin1);
            categoriaServicio.registrarCategoria(categoria2);

            Categoria categoria3 = new Categoria("Audifonos","Perifericos",admin1);
            categoriaServicio.registrarCategoria(categoria3);

            Categoria categoria4 = new Categoria("Portatiles","Perifericos",admin1);
            categoriaServicio.registrarCategoria(categoria4);

            Categoria categoria5 = new Categoria("Destacados","Destacados",admin2);
            categoriaServicio.registrarCategoria(categoria5);

            //=========================== PRODUCTOS ================================//

            Producto producto1 = new Producto("Asus Tuf Gaming","Core I5 10300h 16gb 512gb Gtx 1650 4gb 15.6",4099.755,admin1,categoria5);
            Imagen img1 = new Imagen("Asus-gamer.png");
            img1.setProducto(producto1);
            producto1.getImagenes().add(img1);
            productoServicio.registrarProducto(producto1);
            imagenServicio.registrarImagen(img1);

            Producto producto2 = new Producto("G502 Lightspeed","Mouse inalámbrico para juegos",440.599,admin1,categoria5);
            Imagen img2 = new Imagen("g502-lightspeed.png");
            img2.setProducto(producto2);
            producto2.getImagenes().add(img2);
            productoServicio.registrarProducto(producto2);
            imagenServicio.registrarImagen(img2);

            Producto producto3 = new Producto("Redragon H260 RGB","Auriculares para juegos con micrófono, cableados, compatibles con Xbox One, Nintendo Switch, PS4, PS5, PC, portátiles y Nintendo Switch",82.411,admin1,categoria5);
            Imagen img3 = new Imagen("Hylas-modal-h260.png");
            img3.setProducto(producto3);
            producto3.getImagenes().add(img3);
            productoServicio.registrarProducto(producto3);
            imagenServicio.registrarImagen(img3);

            Producto producto4 = new Producto("Krom Kuma - NXKROMKUMA","Teclado híbrido PC gaming KROM Kuma QWERTY (con “ñ”), anti-ghosting, modo Juego, con soporte para móvil, RGB con 4 efectos, USB",126.799,admin2,categoria5);
            Imagen img4 = new Imagen("Krom-Kuma.png");
            img4.setProducto(producto4);
            producto4.getImagenes().add(img4);
            productoServicio.registrarProducto(producto4);
            imagenServicio.registrarImagen(img4);

            Producto producto5 = new Producto("kumara k552 rgb","Teclado mecánico TKL súper aclamado, compacto y ampliamente funcional. Es virtualmente irrompible y tiene una durabilidad extraordinaria gracias a que su estructura está reforzada con acero. Además, cuenta con bloqueo de tecla Windows y los excelentes interruptores Redragon Blue/Red, sonoros y táctiles, para 5 millones de clics",229.999,admin2,categoria5);
            Imagen img5 = new Imagen("Kumara-k552.png");
            img5.setProducto(producto5);
            producto5.getImagenes().add(img5);
            productoServicio.registrarProducto(producto5);
            imagenServicio.registrarImagen(img5);
        }

    }
}
