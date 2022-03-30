package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    private final AdministradorRepo administradorRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Administrador> admEmail = administradorRepo.findByEmail(email);

        return admEmail.isPresent();
    }

    @Override
    public Administrador registrarAdministrador(Administrador a) throws Exception {

        if (a.getCedula().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres");
        }

        if (a.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres ");
        }

        if (a.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres ");
        }

        if (a.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres ");
        }

        if (a.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres ");
        }

        Optional<Administrador> admNick= administradorRepo.findByNickname(a.getNickname());
        if(admNick.isPresent()){
            throw new Exception("El administrador ya existe");
        }

        if(estaDisponible(a.getEmail())){
            throw new Exception("El administrador ya existe");
        }

        return administradorRepo.save(a);
    }


    @Override
    public void actualizarAdministrador(Administrador a,String email, String password) throws Exception {

        Administrador administradorObtenido = obtenerEmailPassword(email,password);

        if(administradorObtenido!= null){
            administradorObtenido.setCedula(a.getCedula());
            administradorObtenido.setEmail(a.getEmail());
            administradorObtenido.setNickname(a.getNickname());
            administradorObtenido.setNombre(a.getNombre());
            administradorObtenido.setPassword(a.getPassword());
            administradorObtenido.setProductos(a.getProductos());
            administradorObtenido.setProductosUsuarios(a.getProductosUsuarios());

            administradorRepo.save(administradorObtenido);
        }
    }

    @Override
    public void eliminarAdministrador(String email) throws Exception {
        Administrador administradorEncontrado = obtenerAdministradorEmail(email);

        if (administradorEncontrado  != null){
           administradorRepo.delete(administradorEncontrado );
        } else {
            throw new Exception("El administrador no ha sido encontrado");
        }
    }

    @Override
    public Administrador obtenerAdministrador(String id) throws Exception {
        Optional<Administrador> administrador = administradorRepo.findById(id);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador con el id ingresado");
        }

        return administrador.get();
    }

    @Override
    public Administrador obtenerAdministradorEmail(String email) throws Exception {

        Optional<Administrador> administrador = administradorRepo.findByEmail(email);

        if(administrador.isEmpty()){
            throw new Exception("No existe un administrador con el correo ingresado");
        }

        return administrador.get();
    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador obtenerEmailPassword(String email, String password) throws Exception {

        Administrador administrador =administradorRepo.findByEmailAndPassword(email, password);

        if(administrador == null){

            throw new Exception("¡Ups! No te hemos podido encontrar");
        }

        return administrador;
    }
}
