package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final VentaProductoUsuarioRepo compraProductoRepo;
    private final CompraRepo compraRepo;
    private final ProductoRepo productoRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, VentaProductoUsuarioRepo compraProductoRepo, CompraRepo compraRepo, ProductoRepo productoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.compraProductoRepo = compraProductoRepo;
        this.compraRepo = compraRepo;
        this.productoRepo = productoRepo;
    }


    public boolean estaDisponible(String email){
        Usuario usuarioEmail = usuarioRepo.findByEmail(email);

        if (usuarioEmail!=null){
            return  true;
        }

        return false;
    }

    @Override
    public Usuario obtenerUsuarioEmailPassword(String email,String password) throws Exception {

        Usuario usuario = usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario==null){
            throw new Exception("No existe un usuario con el correo y contraseña ingresado");
        }

        return usuario;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

        if (u.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres");
        }

        if (u.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres");
        }

        if (u.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres");
        }

        if (u.getApellido().length()>100){
            throw new Exception("El apellido solo puede tener 100 caracteres");
        }

        if (u.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres");
        }

        Optional<Usuario> usuarioNick=usuarioRepo.findByNickname(u.getNickname());

        if(usuarioNick.isPresent()){
            throw new Exception("El usuario ya existe");
        }

        if(estaDisponible(u.getEmail())){
            throw new Exception("El usuario ya existe");
        }

        u.setSaldo(0);

        return usuarioRepo.save(u);
    }


    @Override
    public void actualizarUsuario(String email, String password,Usuario u) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password);

        if(usuarioEncontrado!=null){

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setNickname(u.getNickname());
            usuarioEncontrado.setPassword(u.getPassword());
            usuarioEncontrado.setDireccion(u.getDireccion());
            usuarioEncontrado.setEmail(u.getEmail());

            usuarioRepo.save(usuarioEncontrado);
        }

    }


    @Override
    public void eliminarUsuario(String email,String password) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email, password);

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
        }
    }

    @Override
    public Usuario obtenerUsuario(int id) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
    }

    @Override
    public void crearCompra(int cedulaUsuario, int idProducto) throws Exception {

        Compra compraNueva = new Compra();
        Usuario usuario = obtenerUsuario(cedulaUsuario);
        Optional<Producto> producto = productoRepo.findById(idProducto);

        if(usuario!=null && producto!=null){

            compraNueva.setUsuario(usuario);
            compraNueva.getProductos().add(producto.get());
            compraNueva.setValor((float) producto.get().getPrecio());
            compraNueva.setEstado(false);
            compraNueva.setFechaVenta(new Date());
            usuario.getCompras().add(compraNueva);

            usuarioRepo.save(usuario);
            compraRepo.save(compraNueva);
        }

    }

    @Override
    public void agregarProductoCompra(int cedulaUsuario, int idCompra, int idProducto) throws Exception {

        Compra compra = compraRepo.obtenerCompraUsuario(idCompra, cedulaUsuario);
        Usuario usuario = obtenerUsuario(cedulaUsuario);
        Optional<Producto> producto = productoRepo.findById(idProducto);

        if(usuario!=null && compra != null){

            compra.setValor((float) (compra.getValor() + producto.get().getPrecio()));
            compra.getProductos().add(producto.get());

            compraRepo.save(compra);
        }

    }

    @Override
    public void eliminarCompra(int cedulaUsuario, int idCompra) throws Exception {

        Compra compra = compraRepo.obtenerCompraUsuario(idCompra, cedulaUsuario);
        Usuario usuario = obtenerUsuario(cedulaUsuario);

        if(usuario!=null && compra != null){

            usuario.getCompras().remove(compra);
            compraRepo.delete(compra);
            usuarioRepo.save(usuario);
        }

    }

    @Override
    public void eliminarProductosCompra(int cedulaUsuario, int idCompra, int idProducto) throws Exception {

        Compra compra = compraRepo.obtenerCompraUsuario(idCompra, cedulaUsuario);
        Usuario usuario = obtenerUsuario(cedulaUsuario);
        Optional<Producto> producto = productoRepo.findById(idProducto);

        if(usuario!=null && compra != null && producto!=null){

            //usuario.getCompras().get(compra.getId()).getProductos().remove(producto);
            compra.getProductos().remove(producto);
            usuarioRepo.save(usuario);
            compraRepo.save(compra);
        }
    }


//    @Override
//    public void adquirirProducto(Producto producto,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception {
//
//        CompraProducto compraProducto = new CompraProducto();
//        Usuario usuario = usuarioRepo.obtenerUsuarioCompra(nombreUsuario,cedulaUsuario,numeroTarjeta);
//
//        if(producto != null && usuario!=null){
//            compraProducto.setProducto(producto);
//            compraProducto.setUsuario(usuario);
//            compraProducto.setHoraCompra(new Date());
//            compraProductoRepo.save(compraProducto);
//            producto.getComprasProducto().add(compraProducto);
//            productoRepo.save(producto);
//        }
//    }
//
//    @Override
//    public void cancelarServicio(int idCompra,int idServicio,String cedula) throws Exception{
//
//        Usuario usuario = usuarioRepo.obtenerUsuarioCedula(cedula);
//        Date devolucion = new Date();
//
//        Compra compra = compraRepo.obtenerCompraUsuario(idCompra,usuario.getId());
//
//        if (compra !=null && compra.getEstado()){
//
//            compra.setEstado(false);
//            compra.setHoraFin(devolucion);
//            compraRepo.save(compra);
//            usuario.getCompras().remove(compra);
//            usuarioRepo.save(usuario);
//
//        }else{
//            throw new Exception("No tienes compras activas");
//        }
//    }
//
    @Override
    public Compra obtenerCompra(int id) throws Exception {

        Optional<Compra> compra = compraRepo.findById(id);

        if (compra.isEmpty()){

            throw new Exception("No se encontro la compra");
        }

        return compra.get();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

}
