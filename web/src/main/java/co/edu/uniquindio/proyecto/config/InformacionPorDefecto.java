package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EspecificacionServicio especificacionServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            Administrador admin1= new Administrador("Sebastian","Quintero","Botsorio","admin1","admin1@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Administrador admin2= new Administrador("Braian","Piedrahita","Ghostbit","admin2","admin2@gmail.com");
            administradorServicio.registrarAdministrador(admin2);

            Usuario u = new Usuario("usuario", "usuario", "usuario", "usuario", "usuario@mail.com","04-01-2001");
            usuarioServicio.registrarUsuario(u);

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

            //=========================== PRODUCTOS ================================//

            Producto producto1 = new Producto("Asus Tuf Gaming","ASUS TUF Gaming FX505 cambiará su forma de ver las computadoras portátiles para juegos. Supera las expectativas, con un hardware impresionante y un chasis compacto y de diseño agresivo que es excepcionalmente resistente.¡Con una pantalla NanoEdge de nivel IPS de vanguardia y una durabilidad certificada MIL-STD-810G.",4099.755,admin1,categoria5);
            producto1.setUnidades(3);
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
            imagenServicio.registrarImagen(img17);
            imagenServicio.registrarImagen(img18);
            imagenServicio.registrarImagen(img19);
            imagenServicio.registrarImagen(img20);
            especificacionServicio.registrarEspecificacion(e21);
            especificacionServicio.registrarEspecificacion(e22);
            especificacionServicio.registrarEspecificacion(e23);
            especificacionServicio.registrarEspecificacion(e24);
            especificacionServicio.registrarEspecificacion(e25);

            //---------------------------------- COMENTARIOS -------------------------------//

            Comentario comentario1= new Comentario();
            comentario1.setComentario("I've only had it a day but so far this machine is flawless. Very easy set up even if your not very computer literate.");
            comentario1.setCalificacion(5);
            comentario1.setProducto(producto1);
            comentario1.setUsuario(u);

            Comentario comentario2= new Comentario();
            comentario2.setComentario("Excelente equipo. Mejora mucho mi flujo de trabajo además su apariencia es muy elegante.");
            comentario2.setCalificacion(3);
            comentario2.setProducto(producto1);
            comentario2.setUsuario(u);

            Comentario comentario3= new Comentario();
            comentario3.setComentario("Cumple con todas las expectativas, lo que más me gusta es que puedes escoger diferentes modos de rendimiento que se aplican si estas conectado a la energía o no.");
            comentario3.setCalificacion(5);
            comentario3.setProducto(producto1);
            comentario3.setUsuario(u);

            Comentario comentario4= new Comentario();
            comentario4.setComentario("If this mouse works, it's pretty fantastic. I like the layout, it's quick as heck, fairly lightweight for a wireless mouse, and the software, though not what I prefer, works fine for me.");
            comentario4.setCalificacion(5);
            comentario4.setProducto(producto2);
            comentario4.setUsuario(u);

            Comentario comentario5= new Comentario();
            comentario5.setComentario("Super customizable y funcional. Llevo poco mas de un mes usandolo exclusivamente para gaming y programacion");
            comentario5.setCalificacion(4);
            comentario5.setProducto(producto2);
            comentario5.setUsuario(u);

            Comentario comentario6= new Comentario();
            comentario6.setComentario("Bought this product about 2 months back and I've already started facing problems. Its \"w\" key stops working in the middle of the games.");
            comentario6.setCalificacion(2);
            comentario6.setProducto(producto3);
            comentario6.setUsuario(u);

            Comentario comentario7= new Comentario();
            comentario7.setComentario("Ya realice la compra");
            comentario7.setCalificacion(4);
            comentario7.setProducto(producto3);
            comentario7.setUsuario(u);

            Comentario comentario8= new Comentario();
            comentario8.setComentario(" Its so good, i loved it, but for those who doesn't like clicky sound then don't buy cuz its bit loud.");
            comentario8.setCalificacion(4);
            comentario8.setProducto(producto4);
            comentario8.setUsuario(u);

            Comentario comentario9= new Comentario();
            comentario9.setComentario("Cumple todo lo que promete, los switches funcionan muy bien y trae algunos de repuesto, recomendado.");
            comentario9.setCalificacion(5);
            comentario9.setProducto(producto4);
            comentario9.setUsuario(u);

            Comentario comentario10= new Comentario();
            comentario10.setComentario("Excelente teclado muy bueno, suena duro pero no es un problema.");
            comentario10.setCalificacion(5);
            comentario10.setProducto(producto5);
            comentario10.setUsuario(u);

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


        }

    }
}
