package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.ReseniaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaServicioImp implements ReseniaServicio {

    private final ReseniaRepo reseniaRepo;
    private final ProductoRepo productoRepo;

    public ReseniaServicioImp(ReseniaRepo reseniaRepo,ProductoRepo productoRepo) {
        this.reseniaRepo = reseniaRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Comentario registrarResenia(Comentario resenia) throws Exception {

        try{
            return reseniaRepo.save(resenia);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Comentario> obtenerReseniasProducto(int idProducto) throws Exception {

        List<Comentario>resenias = productoRepo.obtenerReseniasProducto(idProducto);

        if(resenias ==null){

            throw new Exception("No se encontraron las reseñas");
        }

        return resenias;
    }

    @Override
    public List<Comentario> listarResenias() {
        return reseniaRepo.findAll();
    }

}
