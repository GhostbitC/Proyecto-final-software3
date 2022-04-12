package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Especificacion;
import co.edu.uniquindio.proyecto.repositorios.EspecificacionRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecificacionServicioImpl implements EspecificacionServicio{

   private final EspecificacionRepo especificacionRepo;
   private final ProductoRepo productoRepo;

    public EspecificacionServicioImpl(EspecificacionRepo especificacionRepo, ProductoRepo productoRepo) {
        this.especificacionRepo = especificacionRepo;
        this.productoRepo = productoRepo;
    }


    @Override
    public Especificacion registrarEspecificacion(Especificacion e) throws Exception {

        if(e.getEspecificacion().length() > 500){
            throw new Exception("No puede superar los 500 caracteres");
        }

        return especificacionRepo.save(e);
    }

    @Override
    public void actualizarEspecificacion(Especificacion e, int codigoEspecificacion) throws Exception {

        Especificacion especificacion = obtenerEspecificacion(codigoEspecificacion);

        if (especificacion != null){

            especificacion.setEspecificacion(e.getEspecificacion());

            especificacionRepo.save(especificacion);
        }
    }

    @Override
    public void eliminarEspecificacion(int id) throws Exception {

        Especificacion especificacionEconcontrada = obtenerEspecificacion(id);

        if(especificacionEconcontrada != null){
            especificacionRepo.delete(especificacionEconcontrada);
        } else {
            throw new Exception("No se encontro la especificacion");
        }
    }

    @Override
    public List<Especificacion> obtenerEspecificacionesProducto(int idProducto) throws Exception {

        List<Especificacion> especificaciones = productoRepo.obtenerEspecificaciones(idProducto);

        if(especificaciones ==null){
            throw new Exception("No se encontraron especificaciones");
        }

        return especificaciones;
    }

    @Override
    public Especificacion obtenerEspecificacion(int id) throws Exception {

        Optional<Especificacion> especificacion = especificacionRepo.findById(id);

        if(especificacion.isEmpty()){
            throw new Exception("No se encontró la especificación");
        }

        return especificacion.get();
    }

    @Override
    public List<Especificacion> listarEspecificaciones() {
        return especificacionRepo.findAll();
    }
}
