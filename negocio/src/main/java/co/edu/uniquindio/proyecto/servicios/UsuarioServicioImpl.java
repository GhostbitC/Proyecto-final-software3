package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final MascotaRepo mascotaRepo;
    private final CompraProductoRepo compraProductoRepo;
    private final ServicioServicio servicioServicio;
    private final CompraRepo compraRepo;
    private final ProductoRepo productoRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, MascotaRepo mascotaRepo, CompraProductoRepo compraProductoRepo, ServicioServicio servicioServicio, CompraRepo compraRepo, ProductoRepo productoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.mascotaRepo = mascotaRepo;
        this.compraProductoRepo = compraProductoRepo;
        this.servicioServicio = servicioServicio;
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
    public Usuario registrarUsuario(Usuario u) throws Exception {

        if (u.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres");
        }

        if (u.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres");
        }

        if (u.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres");
        }

        if (u.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres");
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

        return usuarioRepo.save(u);
    }

    @Override
    public void registrarTarjetaUsuario(String idUsuario,String numero, String codigo, String fecha) throws Exception {

        Usuario usuarioEncontrado= obtenerUsuario(idUsuario);

        if (usuarioEncontrado!=null){

            usuarioEncontrado.setNumeroTarjeta(numero);
            usuarioEncontrado.setCodigoTarjeta(codigo);
            usuarioEncontrado.setFechatarjeta(fecha);
            usuarioRepo.save(usuarioEncontrado);
        }else{
            throw new Exception("El usuario no existe");
        }
    }

    @Override
    public void actualizarUsuario(String email, String password,Usuario u) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password);

        if(usuarioEncontrado!=null){

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setNickname(u.getNickname());
            usuarioEncontrado.setPassword(u.getPassword());
            usuarioEncontrado.setCiudad(u.getCiudad());
            usuarioEncontrado.setEmail(u.getEmail());

            usuarioRepo.save(usuarioEncontrado);
        }

    }

    @Override
    public void actualizar(String id,Usuario u){

        try {
            Usuario usuarioEncontrado = obtenerUsuario(id);

            if (usuarioEncontrado!=null){

                usuarioEncontrado.setNombre(u.getNombre());
                usuarioEncontrado.setNickname(u.getNickname());
                usuarioEncontrado.setPassword(u.getPassword());
                usuarioEncontrado.setCiudad(u.getCiudad());
                usuarioEncontrado.setEmail(u.getEmail());

                usuarioRepo.save(usuarioEncontrado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void eliminarUsuario(String email,String password) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password) ;

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
        }
    }


    @Override
    public void eliminarUsuarioId(String id) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuario(id);

        if (usuarioEncontrado!=null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
        }
    }


    @Override
    public Usuario obtenerUsuario(String id) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
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
    public Mascota obtenerMascotaUsuario(String nombreMascota, String idUsuario) throws Exception {

        Mascota mascotaEncontrada = mascotaRepo.obtenerMascotaPorUsuario(nombreMascota,idUsuario);

        if (mascotaEncontrada == null){

            throw new Exception("El usuario no tiene mascota alguna registrado con el nombre dado");
        }

        return mascotaEncontrada;
    }

    @Override
    public List<Mascota> obtenerMascotasPorUsuario(String idUsuario) throws Exception {

        List<Mascota> mascotasU = mascotaRepo.obtenerMascotasPorUsuario(idUsuario);

        return mascotasU;
    }

    @Override
    public List<Compra> obtenerHistorialServicios(String cedulaU){

        List<Compra> historialServicio = compraRepo.obtenerhistorialServiciosUsuario(cedulaU);

        return historialServicio;
    }

    @Override
    public List<Compra> obtenerServiciosActivos(String cedulaU)  {

        List<Compra> serviciosActivos = compraRepo.obtenerServiciosActivosUsuario(cedulaU);

        return serviciosActivos;
    }

    @Override
    public List<CompraProducto> obtenerProductosUsuario(String cedulaU)  {

        List<CompraProducto> productosU = compraProductoRepo.obtenerhistorialCompraUsuario(cedulaU);

        return productosU;
    }

    @Override
    public void adquirirProducto(Producto producto,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception {

        CompraProducto compraProducto = new CompraProducto();
        Usuario usuario = usuarioRepo.obtenerUsuarioCompra(nombreUsuario,cedulaUsuario,numeroTarjeta);

        if(producto != null && usuario!=null){
            compraProducto.setProducto(producto);
            compraProducto.setUsuario(usuario);
            compraProducto.setHoraCompra(new Date());
            compraProductoRepo.save(compraProducto);
            producto.getComprasProducto().add(compraProducto);
            productoRepo.save(producto);
        }
    }

    @Override
    public void cancelarServicio(int idCompra,int idServicio,String cedula) throws Exception{

        Usuario usuario = usuarioRepo.obtenerUsuarioCedula(cedula);
        Date devolucion = new Date();

        Compra compra = compraRepo.obtenerCompraUsuario(idCompra,usuario.getId());

        if (compra !=null && compra.getEstado()){

            compra.setEstado(false);
            compra.setHoraFin(devolucion);
            compraRepo.save(compra);
            usuario.getCompras().remove(compra);
            usuarioRepo.save(usuario);

        }else{
            throw new Exception("No tienes compras activas");
        }
    }

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
