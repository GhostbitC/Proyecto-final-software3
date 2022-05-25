package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio, Serializable {

    private final UsuarioRepo usuarioRepo;
    private final FavoritoRepo favoritoRepo;

    private  final DireccionRepo direccionRepo;

    private final ProductoServicio productoServicio;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, FavoritoRepo favoritoRepo, DireccionRepo direccionRepo, ProductoServicio productoServicio) {
        this.usuarioRepo = usuarioRepo;
        this.favoritoRepo = favoritoRepo;
        this.direccionRepo = direccionRepo;
        this.productoServicio = productoServicio;
    }


    public boolean estaDisponible(String email){
        Usuario usuarioEmail = usuarioRepo.findByEmail(email);
        return usuarioEmail != null;
    }

    @Override
    public Usuario obtenerUsuarioEmailPassword(String email,String password) throws ObjetoNoEncontradoException {

        Usuario usuario = usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario==null){
            throw new ObjetoNoEncontradoException("No existe un usuario con el correo y contraseña ingresado");
        }
        return usuario;
    }

    @Override
    public void registrarUsuario(Usuario u) throws ObjetoNoEncontradoException {

        if (u.getNickname().length()>100){
            throw new ObjetoNoEncontradoException("El nickname solo puede tener 100 caracteres");
        }

        if (u.getEmail().length()>100){
            throw new ObjetoNoEncontradoException("El correo solo puede tener 100 caracteres");
        }

        if (u.getNombre().length()>100){
            throw new ObjetoNoEncontradoException("El nombre solo puede tener 100 caracteres");
        }

        if (u.getApellido().length()>100){
            throw new ObjetoNoEncontradoException("El apellido solo puede tener 100 caracteres");
        }

        if (u.getPassword().length()>100){
            throw new ObjetoNoEncontradoException("La contraseña solo puede tener 100 caracteres");
        }

        Optional<Usuario> usuarioNick=usuarioRepo.findByNickname(u.getNickname());

        if(usuarioNick.isPresent()){
            throw new ObjetoNoEncontradoException("El usuario ya existe");
        }

        if(estaDisponible(u.getEmail())){
            throw new ObjetoNoEncontradoException("El usuario ya existe");
        }

        usuarioRepo.save(u);
    }


    @Override
    public void actualizarUsuario(String email, String password,Usuario u) {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password);

        if(usuarioEncontrado!=null){

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setApellido(u.getApellido());
            usuarioEncontrado.setFechaNacimiento(u.getFechaNacimiento());
            usuarioEncontrado.setNickname(u.getNickname());
            usuarioEncontrado.setPassword(u.getPassword());
            usuarioEncontrado.setEmail(u.getEmail());

            usuarioRepo.save(usuarioEncontrado);
        }
    }

    @Override
    public void eliminarUsuario(String email,String password) throws ObjetoNoEncontradoException {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email, password);

        if (usuarioEncontrado != null){

            List<Producto>productosUsuario = usuarioEncontrado.getProductos();
            List<Favorito>favoritos = usuarioEncontrado.getFavoritos();

            if(productosUsuario!=null && productosUsuario.isEmpty()){

                for(Producto p:usuarioEncontrado.getProductos()){

                    productoServicio.eliminarProducto(p.getId());
                }

                usuarioEncontrado.getProductos().clear();
            }

            if(favoritos!=null && favoritos.isEmpty()){

                favoritoRepo.deleteAll(usuarioEncontrado.getFavoritos());
            }

            if(usuarioEncontrado.getDireccion()!=null){

                Direccion direccionUsuario = usuarioEncontrado.getDireccion();
                usuarioEncontrado.setDireccion(null);
                usuarioRepo.save(usuarioEncontrado);
                direccionRepo.delete(direccionUsuario);
                usuarioRepo.delete(usuarioEncontrado);
            }else{

                usuarioRepo.save(usuarioEncontrado);
                usuarioRepo.delete(usuarioEncontrado);
            }
        }else{
            throw new ObjetoNoEncontradoException("Usuario no encontrado");
        }
    }

    @Override
    public void cambiarPassword(String email, String password) throws ObjetoNoEncontradoException {

        Usuario u = usuarioRepo.findByEmail(email);

        if (u!=null){
            u.setPassword(password);
            usuarioRepo.save(u);
        }else{
            throw new ObjetoNoEncontradoException("No existe una cuenta vinculada con este correo");
        }
    }

    @Override
    public Usuario obtenerUsuario(int id) throws ObjetoNoEncontradoException {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new ObjetoNoEncontradoException("No existe un usuario con el id dado");
        }

        return usuario.get();
    }

    @Override
    public Usuario obtenerUsuarioNombre(String nombre) throws ObjetoNoEncontradoException {

       Usuario usuario = usuarioRepo.findByNombre(nombre);

        if(usuario ==null){
            throw new ObjetoNoEncontradoException("No existe un usuario con el id dado");
        }
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioEmail(String email) throws ObjetoNoEncontradoException {

        Usuario u = usuarioRepo.findByEmail(email);

        if (u==null){
            throw new ObjetoNoEncontradoException("El usuario no existe");
        }
        return u;
    }
}
