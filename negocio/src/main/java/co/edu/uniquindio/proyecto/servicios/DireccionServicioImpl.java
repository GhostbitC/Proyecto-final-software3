package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Direccion;
import co.edu.uniquindio.proyecto.repositorios.DireccionRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DireccionServicioImpl implements DireccionServicio{

    private final DireccionRepo direccionRepo;

    public DireccionServicioImpl(DireccionRepo direccionRepo) {
        this.direccionRepo = direccionRepo;
    }

    @Override
    public Direccion registrarDireccion(Direccion d) throws Exception {

        return direccionRepo.save(d);
    }

    @Override
    public Direccion actualizarDireccion(Direccion d) throws Exception {

        return direccionRepo.save(d);
    }

    @Override
    public void eliminarDireccion(int id) throws Exception {

        Direccion direccionEncontrada=obtenerDireccion(id);

        if (direccionEncontrada != null){
           direccionRepo.delete(direccionEncontrada);
        }else{
            throw new Exception("La dirección que desea eliminar no existe");
        }
    }

    @Override
    public Direccion obtenerDireccion(int id) throws Exception {

        Optional<Direccion> direccion= direccionRepo.findById(id);

        if (direccion.isEmpty()){
            throw new Exception("La dirección buscada no existe");
        }

        return direccion.get();
    }

    @Override
    public List<Direccion> listarDirecciones() {
        return direccionRepo.findAll();
    }
}
