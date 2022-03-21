package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MascotaServicioImpl implements MascotaServicio {

    private final MascotaRepo mascotaRepo;


    public MascotaServicioImpl(MascotaRepo mascotaRepo) {
        this.mascotaRepo = mascotaRepo;

    }

    @Override
    public Mascota registrarMascota(Mascota m) throws Exception {

        m.setFechaRegistro(new Date());

        return mascotaRepo.save(m);
    }

    @Override
    public void actualizarMascota(Mascota m, String nombre) throws Exception {

        Mascota mascota = obtenerMascotaNombre(nombre);

        if(mascota != null){
            mascota.setNombre(m.getNombre());
            mascota.setCantidadVacunas(m.getCantidadVacunas());
            mascota.setPeso(m.getPeso());
            mascota.setRaza(m.getRaza());
            mascota.setTipo(m.getTipo());

            mascotaRepo.save(mascota);
        }
    }

    @Override
    public void eliminarMascota(String nombre) throws Exception {

        Mascota mascotaEncontrada = obtenerMascotaNombre(nombre);

        if(mascotaEncontrada != null){

            mascotaRepo.delete(mascotaEncontrada);

        } else {
            throw  new Exception("No se encontro la mascota");
        }
    }

    @Override
    public void actualizarMascota(Mascota m) throws Exception{

        mascotaRepo.save(m);

    }

    @Override
    public Mascota obtenerMascota(int id) throws Exception {

      Optional<Mascota> mascota = mascotaRepo.findById(id);

      if(mascota.isEmpty()){
          throw  new Exception("No se encontro la mascota");
      }

        return mascota.get();
    }

    @Override
    public Mascota obtenerMascotaNombre(String nombre) throws Exception {

        Mascota mascota = mascotaRepo.obtenerMascotaNombre(nombre);

        if(mascota==null){
            throw  new Exception("No se encontro la mascota");
        }
        return mascota;
    }

    @Override
    public Mascota obtenerMascota2(int id) throws Exception {

        Mascota mascota = mascotaRepo.obtenerMascota(id);

        if(mascota==null){
            throw  new Exception("No se encontro la mascota");
        }

        return mascota;
    }

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepo.findAll();
    }

    @Override
    public List<Mascota> buscarMascotas(String cadena) {
        return mascotaRepo.busquedaMascotasTipoNombre(cadena);
    }

    @Override
    public List<Mascota> obtenerMascotasPorTipo(String tipo){

        List<Mascota>lugares;

        lugares = mascotaRepo.busquedaMascotasTipoNombre(tipo);

        return lugares;
    }

}
