package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrabajadorServicioImpl implements TrabajadorServicio {

    private final TrabajorRepo trabajorRepo;
    private final MascotaRepo mascotaRepo;

    public TrabajadorServicioImpl(TrabajorRepo trabajorRepo, MascotaRepo mascotaRepo) {
        this.trabajorRepo = trabajorRepo;
        this.mascotaRepo = mascotaRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Trabajador> trabajadorEmail = trabajorRepo.findByEmail(email);

        return  trabajadorEmail.isPresent();

    }

    @Override
    public Trabajador registrarTrabajador(Trabajador t) throws Exception {

        if (t.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres");
        }

        if (t.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres ");
        }

        if (t.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres ");
        }

        if (t.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres ");
        }

        if (t.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres ");
        }

        Optional<Trabajador> trabajadorNick= trabajorRepo.findByNickname(t.getNickname());
        if(trabajadorNick.isPresent()){
            throw new Exception("El trabajador ya existe");
        }

        if(estaDisponible(t.getEmail())){
            throw new Exception("El trabajador ya existe");
        }

        return trabajorRepo.save(t);
    }

    @Override
    public void actualizarTrabajador(Trabajador t, String email, String password) throws Exception {

      Trabajador trabajadorObtenido = obtenerEmailPassword(email,password);

      if(trabajadorObtenido != null){
          trabajadorObtenido.setId(t.getId());
          trabajadorObtenido.setNombre(t.getNombre());
          trabajadorObtenido.setNickname(t.getNickname());
          trabajadorObtenido.setEmail(t.getEmail());
          trabajadorObtenido.setPassword(t.getPassword());
          trabajadorObtenido.setAdministrador(t.getAdministrador());
          trabajadorObtenido.setMascotas(t.getMascotas());

          trabajorRepo.save(trabajadorObtenido);
      }
    }

    @Override
    public void eliminarTrabajador(String email,String password) throws Exception {

        Trabajador trabajadorEncontrado = obtenerEmailPassword(email,password);

        if (trabajadorEncontrado != null){
            trabajorRepo.delete(trabajadorEncontrado);
        } else {
            throw new Exception("El trabjador no ha sido encontrado");
        }
    }

    @Override
    public Trabajador obtenerTrabajador(String id) throws Exception {

        Optional<Trabajador> trabajador = trabajorRepo.findById(id);

        if(trabajador.isEmpty()){
            throw new Exception("No existe un trabajador con el id dado");
        }

        return trabajador.get();
    }


    @Override
    public Trabajador obtenerEmailPassword(String email, String password) throws Exception {

        Trabajador trabajador = trabajorRepo.findByEmailAndPassword(email,password);

        if(trabajador == null){

            throw new Exception("¡Ups! No te hemos podido encontrar");
        }
        return trabajador;
    }


    @Override
    public List<Trabajador> listarTrabajadores() {

        return trabajorRepo.findAll();
    }
}
