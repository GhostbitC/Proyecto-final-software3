package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServicioImpl implements ImagenServicio{

    private final ImagenRepo imagenRepo;
    private final MascotaRepo mascotaRepo;
    private final ProductoRepo productoRepo;

    public ImagenServicioImpl(ImagenRepo imagenRepo, MascotaRepo mascotaRepo, ProductoRepo productoRepo) {
        this.imagenRepo = imagenRepo;
        this.mascotaRepo = mascotaRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Imagen registrarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return imagenRepo.save(i);
    }

    @Override
    public Imagen actualizarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return imagenRepo.save(i);
    }

    @Override
    public void eliminarImagen(int id) throws Exception {

        Imagen imagenEncontrada = obtenerImagen(id);

        if(imagenEncontrada != null){
            imagenRepo.delete(imagenEncontrada);
        }else {
            throw new Exception("No se encontro la imagen");
        }
    }

    @Override
    public List<Imagen> obtenerImagenesMascota(int idMascota) throws Exception {

        List<Imagen> imagenes = mascotaRepo.obtenerImagenes(idMascota) ;

        if(imagenes ==null){
            throw new Exception("No se encontraron las imagenes");
        }

        return imagenes;
    }

    @Override
    public List<Imagen> obtenerImagenesProducto(int idProducto) throws Exception {

        List<Imagen> imagenes = productoRepo.obtenerImagenes(idProducto) ;

        if(imagenes ==null){
            throw new Exception("No se encontraron las imagenes");
        }

        return imagenes;
    }

    @Override
    public Imagen obtenerImagen(int id) throws Exception {

        Optional<Imagen> imagen = imagenRepo.findById(id);

        if(imagen.isEmpty()){
            throw  new Exception("No se encontro la imagen");
        }
        return imagen.get();
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepo.findAll();
    }
}
