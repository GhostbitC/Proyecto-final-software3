package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ImagenServicioTest {

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ImagenRepo imagenRepo;

    @Test
    public void registrarImagenTest(){

        try {
            Producto p = productoServicio.obtenerProducto(1);

            Imagen i = new Imagen("adsadasd",p);

            imagenServicio.registrarImagen(i);

            Optional<Imagen> registrada = imagenRepo.findById(1);

            Assertions.assertNotNull(registrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
