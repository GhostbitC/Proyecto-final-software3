package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private DireccionServicio direccionServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EspecificacionServicio especificacionServicio;

    @Autowired
    private ComprobantePagoServicio comprobantePagoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            //--------------------------- ADMINISTRADORES ----------------------------//

            Administrador admin1= new Administrador("Sebastian","Quintero","Botsorio","admin1","sebastianquinteroosorio2104@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Administrador admin2= new Administrador("Braian","Piedrahita","Ghostbit","admin2","admin2@gmail.com");
            administradorServicio.registrarAdministrador(admin2);

            //--------------------------- USUARIOS ----------------------------//

            Usuario u = new Usuario("Michelle", "Gonzalez", "MAGH", "m123", "michellea.gonzalezh@uqvirtual.edu.co","05-04-2002");
            usuarioServicio.registrarUsuario(u);

            Usuario us = new Usuario("Julian", "Quintero", "JGQ", "j123", "quintero.10a2016@gmail.com","05-04-2000");
            usuarioServicio.registrarUsuario(us);

            //--------------------------- CIUDADES ----------------------------//

            Ciudad ciudad1 = new Ciudad("Calarcá");
            ciudadServicio.registrarCiudad(ciudad1);

            Ciudad ciudad3 = new Ciudad("Medellin");
            ciudadServicio.registrarCiudad(ciudad3);
            Ciudad ciudad4 = new Ciudad("Pereira");
            ciudadServicio.registrarCiudad(ciudad4);

            Ciudad ciudad5 = new Ciudad("Armenia");
            ciudadServicio.registrarCiudad(ciudad5);

            Ciudad ciudad6 = new Ciudad("Bogota");
            ciudadServicio.registrarCiudad(ciudad6);

            Ciudad ciudad7 = new Ciudad("Cúcuta");
            ciudadServicio.registrarCiudad(ciudad7);

            Ciudad ciudad8 = new Ciudad("Villavicencio");
            ciudadServicio.registrarCiudad(ciudad8);

            Ciudad ciudad9 = new Ciudad("Cali");
            ciudadServicio.registrarCiudad(ciudad9);

            Ciudad ciudad10 = new Ciudad("Tulua");
            ciudadServicio.registrarCiudad(ciudad10);

            Ciudad ciudad11 = new Ciudad("Ibague");
            ciudadServicio.registrarCiudad(ciudad11);

            //--------------------------- CATEGORÍAS ----------------------------//

            Categoria categoria1 = new Categoria("Mouses","Periféricos",admin1);
            categoriaServicio.registrarCategoria(categoria1);

            Categoria categoria2 = new Categoria("Teclados","Periféricos",admin1);
            categoriaServicio.registrarCategoria(categoria2);

            Categoria categoria3 = new Categoria("Audífonos","Periféricos",admin1);
            categoriaServicio.registrarCategoria(categoria3);

            Categoria categoria4 = new Categoria("Portátiles","Periféricos",admin1);
            categoriaServicio.registrarCategoria(categoria4);

            Categoria categoria5 = new Categoria("Destacados","Destacados",admin2);
            categoriaServicio.registrarCategoria(categoria5);


            //=========================== PRODUCTOS DESTACADOS ================================//
            Producto producto1 = new Producto("Asus Tuf Gaming","ASUS TUF Gaming FX505 cambiará su forma de ver las computadoras portátiles para juegos. Supera las expectativas, con un hardware impresionante y un chasis compacto y de diseño agresivo que es excepcionalmente resistente.¡Con una pantalla NanoEdge de nivel IPS de vanguardia y una durabilidad certificada MIL-STD-810G.",4099.755,admin1,categoria5);
            producto1.setUnidades(3);
            admin1.getProductos().add(producto1);
            Imagen img1 = new Imagen("Asus-Gamer-TUF.png");
            Imagen img2 = new Imagen("Asus-gamer.png");
            Imagen img3 = new Imagen("Asus TUF Gaming.png");
            Imagen img4 = new Imagen("Asus gaming.png");
            img1.setProducto(producto1);
            img2.setProducto(producto1);
            img3.setProducto(producto1);
            img4.setProducto(producto1);
            Especificacion e1 = new Especificacion("Core I5 10300h 16gb 512gb Gtx 1650 4gb 15.6");
            Especificacion e2 = new Especificacion("Tecnología HyperCool y enfriamiento anti polvo (ADC)");
            Especificacion e3 = new Especificacion("Panel de nivel IPS con una frecuencia de actualización 60Hz");
            Especificacion e4 = new Especificacion("Chasis más duro que el estándar(Certificación MIL-STD-810G)");
            Especificacion e5 = new Especificacion("Teclado con retroiluminación RGB");
            e1.setProducto(producto1);
            e2.setProducto(producto1);
            e3.setProducto(producto1);
            e4.setProducto(producto1);
            e5.setProducto(producto1);
            producto1.getImagenes().add(img1);
            producto1.getImagenes().add(img2);
            producto1.getImagenes().add(img3);
            producto1.getImagenes().add(img4);
            producto1.getEspecificaciones().add(e1);
            producto1.getEspecificaciones().add(e2);
            producto1.getEspecificaciones().add(e3);
            producto1.getEspecificaciones().add(e4);
            producto1.getEspecificaciones().add(e5);
            productoServicio.registrarProducto(producto1);
            productoServicio.obtenerProducto(producto1.getId());
            producto1.setEstado(true);
            productoServicio.actualizarProducto(producto1);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img1);
            imagenServicio.registrarImagen(img2);
            imagenServicio.registrarImagen(img3);
            imagenServicio.registrarImagen(img4);
            especificacionServicio.registrarEspecificacion(e1);
            especificacionServicio.registrarEspecificacion(e2);
            especificacionServicio.registrarEspecificacion(e3);
            especificacionServicio.registrarEspecificacion(e4);
            especificacionServicio.registrarEspecificacion(e5);

            Producto producto2 = new Producto("G502 Lightspeed","G502 es el mejor mouse para juegos de Logitech G, completamente rediseñado desde adentro hacia afuera con velocidad de luz inalámbrica y compatibilidad de juego de energía para que puedas jugar más rápido y con más precisión.", 440.599,admin1,categoria5);
            producto2.setUnidades(8);
            admin1.getProductos().add(producto2);
            Imagen img5 = new Imagen("g502-mouse.png");
            Imagen img6 = new Imagen("g502.png");
            Imagen img7 = new Imagen("g502-lightspeed.png");
            Imagen img8 = new Imagen("Logitech-G502.png");
            img5.setProducto(producto2);
            img6.setProducto(producto2);
            img7.setProducto(producto2);
            img8.setProducto(producto2);
            Especificacion e6 = new Especificacion("Tecnología Lightsync RGB: personaliza completamente la iluminación RGB de casi 16.8 millones de colores, sincroniza la iluminación con tu juego y crea tus propias animaciones RGB personales");
            Especificacion e7 = new Especificacion("Sistema de peso ajustable: organizar hasta seis pesos extraíbles dentro del mouse para un ajuste personalizado de peso y equilibrio");
            Especificacion e8 = new Especificacion("Sensor Hero 25K a través de una actualización de software de G HUB, esta actualización es gratuita para todos los jugadores: nuestro más avanzado, con seguimiento 1:1, 400 plus ips, y 100 – 25.600 dpi máxima sensibilidad más cero suavizado, filtrado o aceleración");
            Especificacion e9 = new Especificacion("Carga inalámbrica PowerPlay: nunca más te preocupes por la duración de la batería. Añade el sistema de carga inalámbrica Power Play para mantener tu mouse inalámbrico G502 Lightspeed y otros ratones G compatibles cargados mientras descansas y juegas.");
            Especificacion e10 = new Especificacion("Certificación plástica neutral: por cada kilogramo de plástico utilizado en los ratones G502, Logitech G ayuda a financiar programas de reciclaje de plástico que compensan una cantidad de plástico atado al océano igual a la cantidad utilizada en la velocidad de luz G502.");
            e6.setProducto(producto2);
            e7.setProducto(producto2);
            e8.setProducto(producto2);
            e9.setProducto(producto2);
            e10.setProducto(producto2);
            producto2.getImagenes().add(img5);
            producto2.getImagenes().add(img6);
            producto2.getImagenes().add(img7);
            producto2.getImagenes().add(img8);
            producto2.getEspecificaciones().add(e6);
            producto2.getEspecificaciones().add(e7);
            producto2.getEspecificaciones().add(e8);
            producto2.getEspecificaciones().add(e9);
            producto2.getEspecificaciones().add(e10);
            productoServicio.registrarProducto(producto2);
            productoServicio.obtenerProducto(producto2.getId());
            producto2.setEstado(true);
            productoServicio.actualizarProducto(producto2);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img5);
            imagenServicio.registrarImagen(img6);
            imagenServicio.registrarImagen(img7);
            imagenServicio.registrarImagen(img8);
            especificacionServicio.registrarEspecificacion(e6);
            especificacionServicio.registrarEspecificacion(e7);
            especificacionServicio.registrarEspecificacion(e8);
            especificacionServicio.registrarEspecificacion(e9);
            especificacionServicio.registrarEspecificacion(e10);

            Producto producto3 = new Producto("Redragon H260 RGB","Auriculares para juegos con micrófono, cableados, compatibles con Xbox One, Nintendo Switch, PS4, PS5, PC, portátiles y Nintendo Switch",82.411,admin1,categoria5);
            producto3.setUnidades(7);
            admin1.getProductos().add(producto3);
            Imagen img9 = new Imagen("Hylas-h260.png");
            Imagen img10 = new Imagen("h260.png");
            Imagen img11 = new Imagen("h260w-rgb.png");
            Imagen img12 = new Imagen("Hylas-modal-h260.png");
            img9.setProducto(producto3);
            img10.setProducto(producto3);
            img11.setProducto(producto3);
            img12.setProducto(producto3);
            Especificacion e11 = new Especificacion("Retroiluminación RGB; con control de volumen integrado y micrófono omnidireccional.");
            Especificacion e12 = new Especificacion("Sonido estéreo nítido; los controladores direccionales de neodimio de 1.969 in con sensibilidad de 110 dB ofrecen una precisión de audio nítida, mientras que los suaves auriculares cerrados acolchados ofrecen un avanzado aislamiento de ruido pasivo.");
            Especificacion e13 = new Especificacion("Micrófono con cancelación de ruido; el micrófono ofrece una tecnología optimizada de cancelación de ruido y eco para tu chat de voz durante las sesiones de juego.");
            Especificacion e14 = new Especificacion("Diseño acústico cerrado");
            Especificacion e15 = new Especificacion("Chapado en oro libre de corrosión 0.138 in jack de audio, conector de micrófono de 0.138 in y conector USB-A");
            e11.setProducto(producto3);
            e12.setProducto(producto3);
            e13.setProducto(producto3);
            e14.setProducto(producto3);
            e15.setProducto(producto3);
            producto3.getImagenes().add(img9);
            producto3.getImagenes().add(img10);
            producto3.getImagenes().add(img11);
            producto3.getImagenes().add(img12);
            producto3.getEspecificaciones().add(e11);
            producto3.getEspecificaciones().add(e12);
            producto3.getEspecificaciones().add(e13);
            producto3.getEspecificaciones().add(e14);
            producto3.getEspecificaciones().add(e15);
            productoServicio.registrarProducto(producto3);
            productoServicio.obtenerProducto(producto3.getId());
            producto3.setEstado(true);
            productoServicio.actualizarProducto(producto3);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(img9);
            imagenServicio.registrarImagen(img10);
            imagenServicio.registrarImagen(img11);
            imagenServicio.registrarImagen(img12);
            especificacionServicio.registrarEspecificacion(e11);
            especificacionServicio.registrarEspecificacion(e12);
            especificacionServicio.registrarEspecificacion(e13);
            especificacionServicio.registrarEspecificacion(e14);
            especificacionServicio.registrarEspecificacion(e15);

            Producto producto4 = new Producto("Krom Kuma - NXKROMKUMA","Krom Kuma presenta un robusto diseño con interruptores híbridos que te brindan una respuesta táctil precisa y un nivel superior de confort y agilidad, ya que requiere un menor esfuerzo en la pulsación. El aliado perfecto para largas sesiones de juego.",126.799,admin2,categoria5);
            producto4.setUnidades(5);
            admin2.getProductos().add(producto4);
            Imagen img13 = new Imagen("krom_kuma_2.png");
            Imagen img14 = new Imagen("Krom-Kuma.png");
            Imagen img15 = new Imagen("krom_kuma.png");
            Imagen img16 = new Imagen("krom_kuma_hotspots.png");
            img13.setProducto(producto4);
            img14.setProducto(producto4);
            img15.setProducto(producto4);
            img16.setProducto(producto4);
            Especificacion e16 = new Especificacion("Teclado híbrido PC gaming KROM Kuma QWERTY (con “ñ”), anti-ghosting");
            Especificacion e17 = new Especificacion("Switches híbridos");
            Especificacion e18 = new Especificacion("Teclas multimedia dedicadas");
            Especificacion e19 = new Especificacion("Iluminación rgb con efectos y zonas de color");
            Especificacion e20 = new Especificacion("Soporte retráctil para smartphone");
            e16.setProducto(producto4);
            e17.setProducto(producto4);
            e18.setProducto(producto4);
            e19.setProducto(producto4);
            e20.setProducto(producto4);
            producto4.getImagenes().add(img13);
            producto4.getImagenes().add(img14);
            producto4.getImagenes().add(img15);
            producto4.getImagenes().add(img16);
            producto4.getEspecificaciones().add(e16);
            producto4.getEspecificaciones().add(e17);
            producto4.getEspecificaciones().add(e18);
            producto4.getEspecificaciones().add(e19);
            producto4.getEspecificaciones().add(e20);
            productoServicio.registrarProducto(producto4);
            productoServicio.obtenerProducto(producto4.getId());
            producto4.setEstado(true);
            productoServicio.actualizarProducto(producto4);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(img13);
            imagenServicio.registrarImagen(img14);
            imagenServicio.registrarImagen(img15);
            imagenServicio.registrarImagen(img16);
            especificacionServicio.registrarEspecificacion(e16);
            especificacionServicio.registrarEspecificacion(e17);
            especificacionServicio.registrarEspecificacion(e18);
            especificacionServicio.registrarEspecificacion(e19);
            especificacionServicio.registrarEspecificacion(e20);

            Producto producto5 = new Producto("kumara k552 rgb","Teclado mecánico TKL súper aclamado, compacto y ampliamente funcional. Es virtualmente irrompible y tiene una durabilidad extraordinaria gracias a que su estructura está reforzada con acero. Además, cuenta con bloqueo de tecla Windows y los excelentes interruptores Redragon Blue/Red, sonoros y táctiles, para 5 millones de clics",229.999,admin2,categoria5);
            producto5.setUnidades(10);
            admin2.getProductos().add(producto5);
            Imagen img17 = new Imagen("KUMARA.png");
            Imagen img18 = new Imagen("k552.png");
            Imagen img19 = new Imagen("Kumara-k552.png");
            Imagen img20 = new Imagen("k552-RGB.png");
            img17.setProducto(producto5);
            img18.setProducto(producto5);
            img19.setProducto(producto5);
            img20.setProducto(producto5);
            Especificacion e21 = new Especificacion("ILUMINACIÓN RGB La iluminación del Kumara K-552 RGB consta de teclas retroiluminadas configurables mediante software independiente o 18 modos directo desde el teclado, con posibilidades de guardar perfiles dentro de la memoria interna, logrando un resultado óptimo basado en sus necesidades.");
            Especificacion e22 = new Especificacion("Tipo Switch: Redragon Blue, táctil con click audible, fuerza de actuación 50 gr");
            Especificacion e23 = new Especificacion("Cable: Goma, reforzado. Soporta 12kg y 10000 flexiones. USB enchapado en oro y filtro anti interferencias");
            Especificacion e24 = new Especificacion("Matriz: 100% Anti-Ghosting con Full Key Rollover");
            Especificacion e25 = new Especificacion("ANTI GHOSTING y N-KEY ROLL OVER en todas sus teclas. Esto significa que cada tecla será escaneada de forma independiente y gracias a esto cada una será detectada correctamente, sin importar cuantas teclas pulsemos a la vez.");
            e21.setProducto(producto5);
            e22.setProducto(producto5);
            e23.setProducto(producto5);
            e24.setProducto(producto5);
            e25.setProducto(producto5);
            producto5.getImagenes().add(img17);
            producto5.getImagenes().add(img18);
            producto5.getImagenes().add(img19);
            producto5.getImagenes().add(img20);
            producto5.getEspecificaciones().add(e21);
            producto5.getEspecificaciones().add(e22);
            producto5.getEspecificaciones().add(e23);
            producto5.getEspecificaciones().add(e24);
            producto5.getEspecificaciones().add(e25);
            productoServicio.registrarProducto(producto5);
            productoServicio.obtenerProducto(producto5.getId());
            producto5.setEstado(true);
            productoServicio.actualizarProducto(producto5);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(img17);
            imagenServicio.registrarImagen(img18);
            imagenServicio.registrarImagen(img19);
            imagenServicio.registrarImagen(img20);
            especificacionServicio.registrarEspecificacion(e21);
            especificacionServicio.registrarEspecificacion(e22);
            especificacionServicio.registrarEspecificacion(e23);
            especificacionServicio.registrarEspecificacion(e24);
            especificacionServicio.registrarEspecificacion(e25);


            //---------------------------------- COMENTARIOS PRODUCTOS DESTACADOS -------------------------------//

            Comentario comentario1= new Comentario();
            comentario1.setComentario("I've only had it a day but so far this machine is flawless. Very easy set up even if your not very computer literate.");
            comentario1.setCalificacion(5);
            comentario1.setProducto(producto1);
            comentario1.setUsuario(u);

            Comentario comentario2= new Comentario();
            comentario2.setComentario("Excelente equipo. Mejora mucho mi flujo de trabajo además su apariencia es muy elegante.");
            comentario2.setCalificacion(3);
            comentario2.setProducto(producto1);
            comentario2.setUsuario(us);

            Comentario comentario3= new Comentario();
            comentario3.setComentario("Cumple con todas las expectativas, lo que más me gusta es que puedes escoger diferentes modos de rendimiento que se aplican si estas conectado a la energía o no.");
            comentario3.setCalificacion(5);
            comentario3.setProducto(producto1);
            comentario3.setUsuario(u);

            Comentario comentario4= new Comentario();
            comentario4.setComentario("If this mouse works, it's pretty fantastic. I like the layout, it's quick as heck, fairly lightweight for a wireless mouse, and the software, though not what I prefer, works fine for me.");
            comentario4.setCalificacion(5);
            comentario4.setProducto(producto2);
            comentario4.setUsuario(us);

            Comentario comentario5= new Comentario();
            comentario5.setComentario("Super customizable y funcional. Llevo poco mas de un mes usandolo exclusivamente para gaming y programacion");
            comentario5.setCalificacion(4);
            comentario5.setProducto(producto2);
            comentario5.setUsuario(u);

            Comentario comentario6= new Comentario();
            comentario6.setComentario("Bought this product about 2 months back and I've already started facing problems. Its \"w\" key stops working in the middle of the games.");
            comentario6.setCalificacion(2);
            comentario6.setProducto(producto3);
            comentario6.setUsuario(us);

            Comentario comentario7= new Comentario();
            comentario7.setComentario("Ya realice la compra");
            comentario7.setCalificacion(4);
            comentario7.setProducto(producto3);
            comentario7.setUsuario(u);

            Comentario comentario8= new Comentario();
            comentario8.setComentario(" Its so good, i loved it, but for those who doesn't like clicky sound then don't buy cuz its bit loud.");
            comentario8.setCalificacion(4);
            comentario8.setProducto(producto4);
            comentario8.setUsuario(us);

            Comentario comentario9= new Comentario();
            comentario9.setComentario("Cumple todo lo que promete, los switches funcionan muy bien y trae algunos de repuesto, recomendado.");
            comentario9.setCalificacion(5);
            comentario9.setProducto(producto4);
            comentario9.setUsuario(u);

            Comentario comentario10= new Comentario();
            comentario10.setComentario("Excelente teclado muy bueno, suena duro pero no es un problema.");
            comentario10.setCalificacion(5);
            comentario10.setProducto(producto5);
            comentario10.setUsuario(us);

            Comentario comentario11= new Comentario();
            comentario11.setComentario("Encantado con tan maravilloso teclado, soy desarrollador y utilizar este keyboard es trabajar como los dioses, excelente. Me encanta porque es bastante ergonomico, las teclas se sienten demasiado bien al utilizarlo.");
            comentario11.setCalificacion(5);
            comentario11.setProducto(producto5);
            comentario11.setUsuario(u);

            productoServicio.registrarComentario(comentario1);
            productoServicio.registrarComentario(comentario2);
            productoServicio.registrarComentario(comentario3);
            productoServicio.registrarComentario(comentario4);
            productoServicio.registrarComentario(comentario5);
            productoServicio.registrarComentario(comentario6);
            productoServicio.registrarComentario(comentario7);
            productoServicio.registrarComentario(comentario8);
            productoServicio.registrarComentario(comentario9);
            productoServicio.registrarComentario(comentario10);
            productoServicio.registrarComentario(comentario11);

            //=========================== PRODUCTOS GENERALES ================================//

                        //==================== AUDÍFONOS ====================//
            Producto p1 = new Producto("HECATE by Edifier GX07","Auriculares inalámbricos para juegos, auriculares Bluetooth con cancelación activa de ruido, latencia ultra baja de 60 ms, micrófono con cancelación de ruido, modo de juego/música, control de aplicaciones, color gris.",448.239,admin1,categoria3);
            p1.setUnidades(3);
            admin1.getProductos().add(p1);
            Imagen i1 = new Imagen("Hecate-GX07.png");
            Imagen i2 = new Imagen("gx07.png");
            i1.setProducto(p1);
            i2.setProducto(p1);
            Especificacion es1 = new Especificacion("Diseño único: aspecto único y diseño de puerta de ala voladora con efectos de iluminación RGB, alma de Cyberpunk.");
            Especificacion es2 = new Especificacion("Equipado con la última tecnología Bluetooth 5.0.");
            Especificacion es3 = new Especificacion("Funciones básicas como avance/retroceso, reproducción/detención de música, entre otras.");
            Especificacion es4 = new Especificacion("Cancelación de ruido activa híbrida de 38 db en un diseño futurista.");
            es1.setProducto(p1);
            es2.setProducto(p1);
            es3.setProducto(p1);
            es4.setProducto(p1);
            p1.getImagenes().add(i1);
            p1.getImagenes().add(i2);
            p1.getEspecificaciones().add(es1);
            p1.getEspecificaciones().add(es2);
            p1.getEspecificaciones().add(es3);
            p1.getEspecificaciones().add(es4);
            productoServicio.registrarProducto(p1);
            productoServicio.obtenerProducto(p1.getId());
            p1.setEstado(true);
            productoServicio.actualizarProducto(p1);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(i1);
            imagenServicio.registrarImagen(i2);
            especificacionServicio.registrarEspecificacion(es1);
            especificacionServicio.registrarEspecificacion(es2);
            especificacionServicio.registrarEspecificacion(es3);
            especificacionServicio.registrarEspecificacion(es4);

            Producto p2 = new Producto("Logitech G Series G332","Auriculares para juegos con cable, audífonos giratorios de cuero sintético, conector de audio de 0.138 in, micrófono abatible a silencio, ligero para PC, Xbox One, Xbox Series X, S, PS5, PS4, Nintendo Switch, negro.",171.819,5,true,admin1,us,categoria3);
            admin1.getProductos().add(p2);
            us.getProductos().add(p2);
            Imagen i3 = new Imagen("G332.png");
            Imagen i4 = new Imagen("lg332.png");
            Imagen i5 = new Imagen("Logitech-G332.png");
            i3.setProducto(p2);
            i4.setProducto(p2);
            i5.setProducto(p2);
            Especificacion es5 = new Especificacion("Usa un auricular para todas las plataformas de juego.");
            Especificacion es6 = new Especificacion("Con micrófono incorporado.");
            Especificacion es7 = new Especificacion("Tipo de conector: Jack 3.5 mm.");
            Especificacion es8 = new Especificacion("Cómodos y prácticos.");
            es5.setProducto(p2);
            es6.setProducto(p2);
            es7.setProducto(p2);
            es8.setProducto(p2);
            p2.getImagenes().add(i3);
            p2.getImagenes().add(i4);
            p2.getImagenes().add(i5);
            p2.getEspecificaciones().add(es5);
            p2.getEspecificaciones().add(es6);
            p2.getEspecificaciones().add(es7);
            p2.getEspecificaciones().add(es8);
            productoServicio.registrarProducto(p2);
            productoServicio.obtenerProducto(p2.getId());
            p2.setEstado(true);
            productoServicio.actualizarProducto(p2);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(us.getEmail(),us.getPassword(),us);
            imagenServicio.registrarImagen(i3);
            imagenServicio.registrarImagen(i4);
            imagenServicio.registrarImagen(i5);
            especificacionServicio.registrarEspecificacion(es5);
            especificacionServicio.registrarEspecificacion(es6);
            especificacionServicio.registrarEspecificacion(es7);
            especificacionServicio.registrarEspecificacion(es8);

            Producto p3 = new Producto("Redragon Ares","Los auriculares para juegos con cable Redragon H101 te permiten escuchar cada detalle en todas las direcciones, tal como el desarrollador del juego pretendía. ¡Fuego enemigo, las huellas de tu oponente y puedes enfocarte para ganar!.",46.909,10,true,admin1,us,categoria3);
            admin1.getProductos().add(p3);
            us.getProductos().add(p3);
            Imagen i6 = new Imagen("Ares.png");
            Imagen i7 = new Imagen("rAres.png");
            Imagen i8 = new Imagen("redragon-ares.png");
            i6.setProducto(p3);
            i7.setProducto(p3);
            i8.setProducto(p3);
            Especificacion es9 = new Especificacion("Aislamiento de ruido pasivo ayuda a bloquear el ruido ambiental.");
            Especificacion es10 = new Especificacion("Conector de 0.138 in chapado en oro libre de corrosión y controlador de volumen en línea.");
            Especificacion es11 = new Especificacion("Multiplataforma compatible con Xbox One, PlayStation, PS3, PS4, MAC, PC y portátil.");
            Especificacion es12 = new Especificacion("Sensibilidad de 103 dB.");
            es9.setProducto(p3);
            es10.setProducto(p3);
            es11.setProducto(p3);
            es12.setProducto(p3);
            p2.getImagenes().add(i6);
            p2.getImagenes().add(i7);
            p2.getImagenes().add(i8);
            p2.getEspecificaciones().add(es9);
            p2.getEspecificaciones().add(es10);
            p2.getEspecificaciones().add(es11);
            p2.getEspecificaciones().add(es12);
            productoServicio.registrarProducto(p3);
            productoServicio.obtenerProducto(p3.getId());
            p3.setEstado(true);
            productoServicio.actualizarProducto(p3);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(us.getEmail(),us.getPassword(),us);
            imagenServicio.registrarImagen(i6);
            imagenServicio.registrarImagen(i7);
            imagenServicio.registrarImagen(i8);
            especificacionServicio.registrarEspecificacion(es9);
            especificacionServicio.registrarEspecificacion(es10);
            especificacionServicio.registrarEspecificacion(es11);
            especificacionServicio.registrarEspecificacion(es12);

            Producto p4 = new Producto("Vsg Kuiper","",46.909,admin1,categoria3);
            p4.setUnidades(10);
            admin1.getProductos().add(p4);
            Imagen i9 = new Imagen("vsg-Kui.png");
            Imagen i10 = new Imagen("Vsg-K.png");
            i9.setProducto(p4);
            i10.setProducto(p4);
            Especificacion es13 = new Especificacion("Iluminación: Led verde constante.");
            Especificacion es14 = new Especificacion("Rango de frecuencia del micrófono: 100Hz - 10KHz.");
            Especificacion es15 = new Especificacion(" Material de construcción: ABS + Metal.");
            es13.setProducto(p3);
            es14.setProducto(p3);
            es15.setProducto(p3);
            p4.getImagenes().add(i9);
            p4.getEspecificaciones().add(es13);
            p4.getEspecificaciones().add(es14);
            p4.getEspecificaciones().add(es15);
            productoServicio.registrarProducto(p4);
            productoServicio.obtenerProducto(p4.getId());
            p4.setEstado(true);
            productoServicio.actualizarProducto(p4);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(i9);
            imagenServicio.registrarImagen(i10);
            especificacionServicio.registrarEspecificacion(es13);
            especificacionServicio.registrarEspecificacion(es14);
            especificacionServicio.registrarEspecificacion(es15);


                        //==================== PORTÁTILES ====================//
            Producto p5 = new Producto("Logitech Legion","Lenovo Legion 5 15 Gaming Laptop, 15.6\" FHD (1920 x 1080) Display, AMD Ryzen 7 5800H Processor, 16GB DDR4 RAM, 512GB NVMe SSD, NVIDIA GeForce RTX 3050Ti, Windows 11H, 82JW0012US, Phantom Blue.",5249.999,3,true,admin1,us,categoria4);
            admin1.getProductos().add(p5);
            us.getProductos().add(p5);
            Imagen i11 = new Imagen("lenovo.png");
            Imagen i12 = new Imagen("legion.png");
            Imagen i13 = new Imagen("lenovo-legion.png");
            i11.setProducto(p5);
            i12.setProducto(p5);
            i13.setProducto(p5);
            Especificacion es16 = new Especificacion("Pantalla: 17.3 pulgadas FHD (1920 x1080) IPS 300 nits antireflejos, 144 Hz.");
            Especificacion es17 = new Especificacion("Procesador: AMD Ryzen 7 5800H (8 núcleos, 16 hilos, hasta 4.4 GHz).");
            Especificacion es18 = new Especificacion("32 GB de RAM, unidad de estado sólido PCle de 1 TB.");
            Especificacion es19 = new Especificacion("Puertos: 4 puertos USB 3.2 Gen 1; 1 puerto USB 3.2 Gen 1 tipo C; 1 HDMI 2.1; 1 combo de auriculares/micrófono.");
            es16.setProducto(p5);
            es17.setProducto(p5);
            es18.setProducto(p5);
            es19.setProducto(p5);
            p5.getImagenes().add(i11);
            p5.getImagenes().add(i12);
            p5.getImagenes().add(i13);
            p5.getEspecificaciones().add(es16);
            p5.getEspecificaciones().add(es17);
            p5.getEspecificaciones().add(es18);
            p5.getEspecificaciones().add(es19);
            productoServicio.registrarProducto(p5);
            productoServicio.obtenerProducto(p5.getId());
            p5.setEstado(true);
            productoServicio.actualizarProducto(p5);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(us.getEmail(),us.getPassword(),us);
            imagenServicio.registrarImagen(i11);
            imagenServicio.registrarImagen(i12);
            imagenServicio.registrarImagen(i13);
            especificacionServicio.registrarEspecificacion(es16);
            especificacionServicio.registrarEspecificacion(es17);
            especificacionServicio.registrarEspecificacion(es18);
            especificacionServicio.registrarEspecificacion(es19);

            Producto p6 = new Producto("Acer Predator Helios 300","Acer Predator Helios 300 PH315-54-760S Gaming Laptop | Intel i7-11800H | NVIDIA GeForce RTX 3060 Laptop GPU | 15.6\" Full HD 144Hz 3ms IPS Display | 16GB DDR4 | 512GB SSD | Killer WiFi 6 | RGB Keyboard",6249.999,2,true,admin1,us,categoria4);
            admin1.getProductos().add(p6);
            us.getProductos().add(p6);
            Imagen i14 = new Imagen("Predator-helios-300.png");
            Imagen i15 = new Imagen("predator.png");
            Imagen i16 = new Imagen("Predator-Helios.png");
            i14.setProducto(p6);
            i15.setProducto(p6);
            i16.setProducto(p6);
            Especificacion es20 = new Especificacion("Procesador Core i7 11800H de OCHO nucleos hasta 4.6Ghz.");
            Especificacion es21 = new Especificacion("Unidad de estado solido 512gb Nvme.");
            Especificacion es22 = new Especificacion("Pantalla 15.6\" full HD 144hz 72%NTSC IPS-level 100%srgb 300 NITS LG Philips.");
            Especificacion es23 = new Especificacion("Tarjeta de video RTX 3060 6gb hasta 105W mas rápida que la generación pasada.");
            es20.setProducto(p6);
            es21.setProducto(p6);
            es22.setProducto(p6);
            es23.setProducto(p6);
            p6.getImagenes().add(i14);
            p6.getImagenes().add(i15);
            p6.getImagenes().add(i16);
            p6.getEspecificaciones().add(es20);
            p6.getEspecificaciones().add(es21);
            p6.getEspecificaciones().add(es22);
            p6.getEspecificaciones().add(es23);
            productoServicio.registrarProducto(p6);
            productoServicio.obtenerProducto(p6.getId());
            p6.setEstado(true);
            productoServicio.actualizarProducto(p6);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(us.getEmail(),us.getPassword(),us);
            imagenServicio.registrarImagen(i14);
            imagenServicio.registrarImagen(i15);
            imagenServicio.registrarImagen(i16);
            especificacionServicio.registrarEspecificacion(es20);
            especificacionServicio.registrarEspecificacion(es21);
            especificacionServicio.registrarEspecificacion(es22);
            especificacionServicio.registrarEspecificacion(es23);

            Producto p7 = new Producto("HP Pavilion Gaming","Portátil Micro-EDGE de 15.6 pulgadas, procesador Intel Core i5-9300H, NVIDIA GeForce GTX 1650 (4 GB), 8 GB SDRAM, 256 GB SSD, Windows 10 Home (15-dk0020nr, Shadow Black/Acid Green).",4299.019,admin2,categoria4);
            p7.setUnidades(2);
            admin2.getProductos().add(p7);
            Imagen i17 = new Imagen("Hp-pavilion.png");
            Imagen i18 = new Imagen("Hp.png");
            Imagen i19 = new Imagen("Hp-gaming.png");
            i17.setProducto(p7);
            i18.setProducto(p7);
            i19.setProducto(p7);
            Especificacion es24 = new Especificacion("Multitarea rápida y sencilla.");
            Especificacion es25 = new Especificacion("Tamaño pantalla: 15.6\" FHD IPS con micromarco y Luz de Fondo.");
            Especificacion es26 = new Especificacion("Dimensiones: 51.99 cm x 6.91 cm x 30.48 cm");
            Especificacion es27 = new Especificacion("Tarjeta gráfica: NVIDIA GeForce GTX 1050");
            es24.setProducto(p7);
            es25.setProducto(p7);
            es26.setProducto(p7);
            es27.setProducto(p7);
            p7.getImagenes().add(i17);
            p7.getImagenes().add(i18);
            p7.getImagenes().add(i19);
            p7.getEspecificaciones().add(es24);
            p7.getEspecificaciones().add(es25);
            p7.getEspecificaciones().add(es26);
            p7.getEspecificaciones().add(es27);
            productoServicio.registrarProducto(p7);
            productoServicio.obtenerProducto(p7.getId());
            p7.setEstado(true);
            productoServicio.actualizarProducto(p7);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(i17);
            imagenServicio.registrarImagen(i18);
            imagenServicio.registrarImagen(i19);
            especificacionServicio.registrarEspecificacion(es24);
            especificacionServicio.registrarEspecificacion(es25);
            especificacionServicio.registrarEspecificacion(es26);
            especificacionServicio.registrarEspecificacion(es27);

            Producto p8 = new Producto("Acer Nitro","Portátil para videojuegos con Intel Core i5-10300H, unidad de procesamiento de gráficos NVIDIA GeForce RTX 3050, pantalla IPS FHD de 15.6 pulgadas con frecuencia de actualización de 144 Hz, 8 GB DDR4, unidad de estado sólido SSD NVMe de 256 GB, Intel Wi-Fi 6, teclado retroiluminado.",3059.356,admin2,categoria4);
            p8.setUnidades(4);
            admin2.getProductos().add(p8);
            Imagen i20 = new Imagen("Acer-N5.png");
            Imagen i21 = new Imagen("Acer-Nitro.png");
            Imagen i22 = new Imagen("N5.png");
            i20.setProducto(p8);
            i21.setProducto(p8);
            i22.setProducto(p8);
            Especificacion es28 = new Especificacion("PROCESADOR: INTEL CORE I5-10300H. 10A GENERACIÓN HASTA 4.20 GHZ.");
            Especificacion es29 = new Especificacion("CAPACIDAD DE RAM: 16GB.");
            Especificacion es30 = new Especificacion("DISCO DE ESTADO SÓLIDO: DE 512 GB (NVME TLC)");
            Especificacion es31 = new Especificacion("TASA DE REFRESCO MONITOR: IPS DE 144HZ.");
            es28.setProducto(p8);
            es29.setProducto(p8);
            es30.setProducto(p8);
            es31.setProducto(p8);
            p8.getImagenes().add(i20);
            p8.getImagenes().add(i21);
            p8.getImagenes().add(i22);
            p8.getEspecificaciones().add(es28);
            p8.getEspecificaciones().add(es29);
            p8.getEspecificaciones().add(es30);
            p8.getEspecificaciones().add(es31);
            productoServicio.registrarProducto(p8);
            productoServicio.obtenerProducto(p8.getId());
            p8.setEstado(true);
            productoServicio.actualizarProducto(p8);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(i20);
            imagenServicio.registrarImagen(i21);
            imagenServicio.registrarImagen(i22);
            especificacionServicio.registrarEspecificacion(es28);
            especificacionServicio.registrarEspecificacion(es29);
            especificacionServicio.registrarEspecificacion(es30);
            especificacionServicio.registrarEspecificacion(es31);

                        //==================== TECLADOS ====================//
            Producto p9 = new Producto("VSG Pulsar QWERTY","Teclado VSG de alto rendimiento permite que puedas disfrutar de horas ilimitadas de juegos. Está diseñado especialmente para que puedas expresar tanto tus habilidades como tu estilo. Podrás mejorar tu experiencia de gaming, ya seas un aficionado o todo un experto, y hacer que tus jugadas alcancen otro nivel.",104.077,admin2,categoria2);
            p9.setUnidades(5);
            admin2.getProductos().add(p9);
            Imagen i23 = new Imagen("Pulsar.png");
            Imagen i24 = new Imagen("pulsar-vsg.png");
            Imagen i25 = new Imagen("vsg-pulsar.png");
            i23.setProducto(p9);
            i24.setProducto(p9);
            i25.setProducto(p9);
            Especificacion es32 = new Especificacion("Función antighosting incorporada.");
            Especificacion es33 = new Especificacion("Medidas: 370mm de ancho, 150mm de alto y 34mm de profundidad.");
            Especificacion es34 = new Especificacion("Tipo de teclado: membrana.");
            Especificacion es35 = new Especificacion("Su rueda metálica hace tributo al intenso campo electromagnético de un Pulsar, brindándote el control total sobre el volumen y la intensidad de iluminación de tu teclado, todo sin perderte ni un segundo de la acción.");
            es32.setProducto(p9);
            es33.setProducto(p9);
            es34.setProducto(p9);
            es35.setProducto(p9);
            p9.getImagenes().add(i23);
            p9.getImagenes().add(i24);
            p9.getImagenes().add(i25);
            p9.getEspecificaciones().add(es32);
            p9.getEspecificaciones().add(es33);
            p9.getEspecificaciones().add(es34);
            p9.getEspecificaciones().add(es35);
            productoServicio.registrarProducto(p9);
            productoServicio.obtenerProducto(p9.getId());
            p9.setEstado(true);
            productoServicio.actualizarProducto(p9);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(i23);
            imagenServicio.registrarImagen(i24);
            imagenServicio.registrarImagen(i25);
            especificacionServicio.registrarEspecificacion(es32);
            especificacionServicio.registrarEspecificacion(es33);
            especificacionServicio.registrarEspecificacion(es34);
            especificacionServicio.registrarEspecificacion(es35);

            Producto p10 = new Producto("Redragon K585 DITI","Teclado mecánico RGB para juegos con una sola mano, teclado profesional para juegos tipo C con 7 teclas macro integradas, reposa muñecas desmontable, interruptor rojo lineal, 42 teclas.",148.599,admin2,categoria2);
            p10.setUnidades(5);
            admin2.getProductos().add(p10);
            Imagen i26 = new Imagen("Diti-k585.png");
            Imagen i27 = new Imagen("K585.png");
            Imagen i28 = new Imagen("R-K585.png");
            i26.setProducto(p10);
            i27.setProducto(p10);
            i28.setProducto(p10);
            Especificacion es36 = new Especificacion("Delgado y silencioso: el teclado K585 elimina esas teclas adicionales y mantiene las más útiles.");
            Especificacion es37 = new Especificacion("Juega para ganar: con 7 teclas macro programables, vincular múltiples comandos y activarlos al instante.");
            Especificacion es38 = new Especificacion("Puerto de paso USB: posicionado para un juego ininterrumpido y listo para tu mouse.");
            Especificacion es39 = new Especificacion("Medidas: 28cm de ancho, 23cm de alto y 5cm de profundidad.");
            es36.setProducto(p10);
            es37.setProducto(p10);
            es38.setProducto(p10);
            es39.setProducto(p10);
            p10.getImagenes().add(i26);
            p10.getImagenes().add(i27);
            p10.getImagenes().add(i28);
            p10.getEspecificaciones().add(es36);
            p10.getEspecificaciones().add(es37);
            p10.getEspecificaciones().add(es38);
            p10.getEspecificaciones().add(es39);
            productoServicio.registrarProducto(p10);
            productoServicio.obtenerProducto(p10.getId());
            p10.setEstado(true);
            productoServicio.actualizarProducto(p10);
            administradorServicio.actualizarAdministrador(admin2,admin2.getEmail(),admin2.getPassword());
            imagenServicio.registrarImagen(i26);
            imagenServicio.registrarImagen(i27);
            imagenServicio.registrarImagen(i28);
            especificacionServicio.registrarEspecificacion(es36);
            especificacionServicio.registrarEspecificacion(es37);
            especificacionServicio.registrarEspecificacion(es38);
            especificacionServicio.registrarEspecificacion(es39);

            Producto p11 = new Producto("Redragon K617 Fizz","Slim Fresh K617: el nuevo teclado con cable de diseño del 60% de Redragon en un estilo refrescante con una oferta económica sin obviedad. Compacto 61 teclas con teclas seleccionadas, dedicadas a FPS Gamers y un trabajo eficiente.\n" +
                    "Interruptores rojos intercambiables en caliente: la mayoría de los interruptores mecánicos silenciosos, los desplazamientos lineales y suaves hacen que cada clic sea fácil de registrar. Intercambiable en caliente con otros interruptores Redragon.",234.999,3,true,admin1,u,categoria2);
            admin1.getProductos().add(p11);
            u.getProductos().add(p11);
            Imagen i29 = new Imagen("K617.png");
            Imagen i30 = new Imagen("F-K617.png");
            Imagen i31 = new Imagen("Fizz k617.png");
            i29.setProducto(p11);
            i30.setProducto(p11);
            i31.setProducto(p11);
            Especificacion es40 = new Especificacion("Teclado con cable de diseño del 60% de Redragon en un estilo refrescante con una oferta económica sin obviedad.");
            Especificacion es41 = new Especificacion("Interruptores rojos intercambiables en caliente: la mayoría de los interruptores mecánicos silenciosos, los desplazamientos lineales y suaves hacen que cada clic sea fácil de registrar.");
            Especificacion es42 = new Especificacion("RGB vibrante: hasta 20 modos de retroiluminación preestablecidos son libres de elegir por el propio teclado.");
            Especificacion es43 = new Especificacion("Software Pro Personalizable: amplíe sus opciones con el software disponible para diseñar sus propios modos y efectos nuevos.");
            es40.setProducto(p11);
            es41.setProducto(p11);
            es42.setProducto(p11);
            es43.setProducto(p11);
            p11.getImagenes().add(i29);
            p11.getImagenes().add(i30);
            p11.getImagenes().add(i31);
            p11.getEspecificaciones().add(es40);
            p11.getEspecificaciones().add(es41);
            p11.getEspecificaciones().add(es42);
            p11.getEspecificaciones().add(es43);
            productoServicio.registrarProducto(p11);
            productoServicio.obtenerProducto(p11.getId());
            p11.setEstado(true);
            productoServicio.actualizarProducto(p11);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(u.getEmail(),u.getPassword(),u);
            imagenServicio.registrarImagen(i29);
            imagenServicio.registrarImagen(i30);
            imagenServicio.registrarImagen(i31);
            especificacionServicio.registrarEspecificacion(es40);
            especificacionServicio.registrarEspecificacion(es41);
            especificacionServicio.registrarEspecificacion(es42);
            especificacionServicio.registrarEspecificacion(es43);

            Producto p12 = new Producto("Logitech Serie G G413 QWERTY","Este teclado Logitech de alto rendimiento permite que puedas disfrutar de horas ilimitadas de juegos. Está diseñado especialmente para que puedas expresar tanto tus habilidades como tu estilo. Podrás mejorar tu experiencia de gaming, ya seas un aficionado o todo un experto, y hacer que tus jugadas alcancen otro nivel.",335.615,10,true,admin1,u,categoria2);
            admin1.getProductos().add(p12);
            u.getProductos().add(p12);
            Imagen i32 = new Imagen("L-G413.png");
            Imagen i33 = new Imagen("LG-413.png");
            Imagen i34 = new Imagen("413.jpg");
            i32.setProducto(p12);
            i33.setProducto(p12);
            i34.setProducto(p12);
            Especificacion es44 = new Especificacion("Medidas: 445mm de ancho, 132mm de alto y 34mm de profundidad.");
            Especificacion es45 = new Especificacion("Contiene teclado numérico.");
            Especificacion es46 = new Especificacion("Indispensable para tus actividades.");
            Especificacion es47 = new Especificacion("Tecla cilíndrica.");
            es44.setProducto(p12);
            es45.setProducto(p12);
            es46.setProducto(p12);
            es47.setProducto(p12);
            p12.getImagenes().add(i32);
            p12.getImagenes().add(i33);
            p12.getImagenes().add(i34);
            p12.getEspecificaciones().add(es44);
            p12.getEspecificaciones().add(es45);
            p12.getEspecificaciones().add(es46);
            p12.getEspecificaciones().add(es47);
            productoServicio.registrarProducto(p12);
            productoServicio.obtenerProducto(p12.getId());
            p12.setEstado(true);
            productoServicio.actualizarProducto(p12);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(u.getEmail(),u.getPassword(),u);
            imagenServicio.registrarImagen(i32);
            imagenServicio.registrarImagen(i33);
            imagenServicio.registrarImagen(i34);
            especificacionServicio.registrarEspecificacion(es44);
            especificacionServicio.registrarEspecificacion(es45);
            especificacionServicio.registrarEspecificacion(es46);
            especificacionServicio.registrarEspecificacion(es47);

                        //==================== MOUSES ====================//
            Producto p13 = new Producto("Logitech G Series Prodigy G203","Logitech diseña productos y experiencias que ocupan un lugar cotidiano en la vida de las personas, poniendo foco en la innovación y la calidad. Su objetivo es crear momentos verdaderamente únicos y significativos para sus usuarios. Los mouses Logitech se adaptan a la forma de tu mano para proporcionarte horas de comodidad.",95.999,2,true,admin1,u,categoria1);
            admin1.getProductos().add(p13);
            u.getProductos().add(p13);
            Imagen i35 = new Imagen("g203.png");
            Imagen i36 = new Imagen("L-g203.png");
            Imagen i37 = new Imagen("LG203.png");
            i35.setProducto(p13);
            i36.setProducto(p13);
            i37.setProducto(p13);
            Especificacion es48 = new Especificacion("Utiliza cable.");
            Especificacion es49 = new Especificacion("Con luces para mejorar la experiencia de uso.");
            Especificacion es50 = new Especificacion("Resolución de 8000dpi.");
            Especificacion es51 = new Especificacion("Creado para llevar a todas partes.");
            es48.setProducto(p13);
            es49.setProducto(p13);
            es50.setProducto(p13);
            es51.setProducto(p13);
            p13.getImagenes().add(i35);
            p13.getImagenes().add(i36);
            p13.getImagenes().add(i37);
            p13.getEspecificaciones().add(es48);
            p13.getEspecificaciones().add(es49);
            p13.getEspecificaciones().add(es50);
            p13.getEspecificaciones().add(es51);
            productoServicio.registrarProducto(p13);
            productoServicio.obtenerProducto(p13.getId());
            p13.setEstado(true);
            productoServicio.actualizarProducto(p13);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(u.getEmail(),u.getPassword(),u);
            imagenServicio.registrarImagen(i35);
            imagenServicio.registrarImagen(i36);
            imagenServicio.registrarImagen(i37);
            especificacionServicio.registrarEspecificacion(es48);
            especificacionServicio.registrarEspecificacion(es49);
            especificacionServicio.registrarEspecificacion(es50);
            especificacionServicio.registrarEspecificacion(es51);

            Producto p14 = new Producto("Razer DeathAdder V2 Gaming","Desde 2005 Razer es la marca líder de estilo de vida para jugadores. Ha diseñado y construido el ecosistema de hardware, software y servicios más grande del mundo. La ubicación de cada botón y curva de los mouses Razer se crearon para adaptarse perfectamente a tu mano. Gracias a estos dispositivos, conseguirás una experiencia de juego cómoda y placentera.",130.609,5,true,admin1,u,categoria1);
            admin1.getProductos().add(p14);
            u.getProductos().add(p14);
            Imagen i38 = new Imagen("razer-vsg.png");
            Imagen i39 = new Imagen("VSG-razer.png");
            Imagen i40 = new Imagen("razer.png");
            i38.setProducto(p14);
            i39.setProducto(p14);
            i40.setProducto(p14);
            Especificacion es52 = new Especificacion("Cuenta con 6 botones para un mayor control.");
            Especificacion es53 = new Especificacion("Resolución de 8500dpi.");
            Especificacion es54 = new Especificacion("Con luces para mejorar la experiencia de uso.");
            Especificacion es55 = new Especificacion("Control inteligente y navegación fácil.");
            es52.setProducto(p14);
            es53.setProducto(p14);
            es54.setProducto(p14);
            es55.setProducto(p14);
            p14.getImagenes().add(i38);
            p14.getImagenes().add(i39);
            p14.getImagenes().add(i40);
            p14.getEspecificaciones().add(es52);
            p14.getEspecificaciones().add(es53);
            p14.getEspecificaciones().add(es54);
            p14.getEspecificaciones().add(es55);
            productoServicio.registrarProducto(p14);
            productoServicio.obtenerProducto(p14.getId());
            p14.setEstado(true);
            productoServicio.actualizarProducto(p14);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            usuarioServicio.actualizarUsuario(u.getEmail(),u.getPassword(),u);
            imagenServicio.registrarImagen(i38);
            imagenServicio.registrarImagen(i39);
            imagenServicio.registrarImagen(i40);
            especificacionServicio.registrarEspecificacion(es52);
            especificacionServicio.registrarEspecificacion(es53);
            especificacionServicio.registrarEspecificacion(es54);
            especificacionServicio.registrarEspecificacion(es55);

            Producto p15 = new Producto("Logitech G Series Lightspeed G305 blue","Logitech diseña productos y experiencias que ocupan un lugar cotidiano en la vida de las personas, poniendo foco en la innovación y la calidad. Su objetivo es crear momentos verdaderamente únicos y significativos para sus usuarios. Los mouses Logitech se adaptan a la forma de tu mano para proporcionarte horas de comodidad.", 129.999,admin1,categoria1);
            p15.setUnidades(4);
            admin1.getProductos().add(p15);
            Imagen i41 = new Imagen("L305.png");
            Imagen i42 = new Imagen("G305.png");
            Imagen i43 = new Imagen("LG305.jpg");
            i41.setProducto(p15);
            i42.setProducto(p15);
            i43.setProducto(p15);
            Especificacion es56 = new Especificacion("Contiene usb inalámbrico.");
            Especificacion es57 = new Especificacion("Resolución de 12000dpi.");
            Especificacion es58 = new Especificacion("Con sensor óptico.");
            Especificacion es59 = new Especificacion("Posee rueda de desplazamiento.");
            es56.setProducto(p15);
            es57.setProducto(p15);
            es58.setProducto(p15);
            es59.setProducto(p15);
            p15.getImagenes().add(i41);
            p15.getImagenes().add(i42);
            p15.getImagenes().add(i43);
            p15.getEspecificaciones().add(es56);
            p15.getEspecificaciones().add(es57);
            p15.getEspecificaciones().add(es58);
            p15.getEspecificaciones().add(es59);
            productoServicio.registrarProducto(p15);
            productoServicio.obtenerProducto(p15.getId());
            p15.setEstado(true);
            productoServicio.actualizarProducto(p15);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(i41);
            imagenServicio.registrarImagen(i42);
            imagenServicio.registrarImagen(i43);
            especificacionServicio.registrarEspecificacion(es56);
            especificacionServicio.registrarEspecificacion(es57);
            especificacionServicio.registrarEspecificacion(es58);
            especificacionServicio.registrarEspecificacion(es59);

            Producto p16 = new Producto("Redragon Centrophorus2 M601-RGB","Redragon innova día a día en diseño y calidad. Su objetivo es producir equipamiento de alta gama para jugadores, con excelentes prestaciones y a un precio accesible. Los mouses Redragon son adecuados para todas las ocasiones, ya sea para entretenerse en el hogar o usarlo en el trabajo. Experimenta el diseño cómodo y elegante de este dispositivo.",70.019,admin1,categoria1);
            p16.setUnidades(4);
            admin1.getProductos().add(p16);
            Imagen i44 = new Imagen("CETROPHORUS-RGB.png");
            Imagen i45 = new Imagen("redragon-centrop.png");
            Imagen i46 = new Imagen("redragon-centrop.jpg");
            i44.setProducto(p16);
            i45.setProducto(p16);
            i46.setProducto(p16);
            Especificacion es60 = new Especificacion("Mouse profesional para juegos: el mouse óptico Redragon M908 para juegos está diseñado con hasta 12400 DPI.");
            Especificacion es61 = new Especificacion("Retroiluminación RGB y botones programables: 16.8 millones de opciones de color LED RGB.");
            Especificacion es62 = new Especificacion("Comodidad y precisión a tus manos: el mouse para juegos Redragon M908 es un accesorio esencial para los jugadores.");
            Especificacion es63 = new Especificacion("Diseño de gama alta: el mouse Redragon M908 cuenta con 8 botones y 12 botones laterales programables MMO.");
            es60.setProducto(p16);
            es61.setProducto(p16);
            es62.setProducto(p16);
            es63.setProducto(p16);
            p16.getImagenes().add(i44);
            p16.getImagenes().add(i45);
            p16.getImagenes().add(i46);
            p16.getEspecificaciones().add(es60);
            p16.getEspecificaciones().add(es61);
            p16.getEspecificaciones().add(es62);
            p16.getEspecificaciones().add(es63);
            productoServicio.registrarProducto(p16);
            productoServicio.obtenerProducto(p16.getId());
            p16.setEstado(true);
            productoServicio.actualizarProducto(p16);
            administradorServicio.actualizarAdministrador(admin1,admin1.getEmail(),admin1.getPassword());
            imagenServicio.registrarImagen(i44);
            imagenServicio.registrarImagen(i45);
            imagenServicio.registrarImagen(i46);
            especificacionServicio.registrarEspecificacion(es60);
            especificacionServicio.registrarEspecificacion(es61);
            especificacionServicio.registrarEspecificacion(es62);
            especificacionServicio.registrarEspecificacion(es63);


            //=========================== COMPROBANTE ================================//
            ComprobantePago cp = new ComprobantePago();
            cp.setUrl("ComprobantePago.jpeg");

            //=========================== COMPRA ================================//
            Compra c = new Compra(new Date(),true,"Consignación",us,admin1);

            //=========================== DETALLE COMPRA ================================//
            DetalleCompra dc = new DetalleCompra(1,(float)producto1.getPrecio(),producto1,c);
            DetalleCompra dc1 = new DetalleCompra(1,(float)producto2.getPrecio(),producto2,c);
            DetalleCompra dc2 = new DetalleCompra(1,(float)producto3.getPrecio(),producto3,c);

            List<DetalleCompra> dt = new ArrayList<>();
            dt.add(dc);
            dt.add(dc1);
            dt.add(dc2);

            c.setListaDetallesCompra(dt);
            c.setComprobantePago(cp);
            compraServicio.crearCompra(c);
            c.setComprobantePago(cp);
            c.setAdministrador(admin1);
            c.setEstado(true);
            compraServicio.crearCompra(c);

            Comentario c1= new Comentario();
            c1.setComentario("Una bestia de audífonos, micrófono excelente, producto recomendado sobre todo para shooters, se escucha todo y sonido muy envolvente, y se reconoce fácilmente la dirección del enemigo. Nos vemos en valorant.");
            c1.setCalificacion(5);
            c1.setProducto(p2);
            c1.setUsuario(us);

            Comentario c2= new Comentario();
            c2.setComentario("Súper producto, de muy buena calidad, es muy ligero y de suave manejo.");
            c2.setCalificacion(5);
            c2.setProducto(p14);
            c2.setUsuario(u);

            productoServicio.registrarComentario(c1);
            productoServicio.registrarComentario(c2);
        }
    }
}
