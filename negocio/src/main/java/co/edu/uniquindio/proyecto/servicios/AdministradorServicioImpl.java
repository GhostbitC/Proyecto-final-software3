package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    private final AdministradorRepo administradorRepo;

    private final ProductoRepo productoRepo;

    private final CompraRepo compraRepo;

    private final DetalleCompraRepo detalleCompraRepo;

    private final ImagenRepo imagenRepo;

    private final ComprobantePagoRepo comprobantePagoRepo;

    private final EspecificacionRepo especificacionRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo, ProductoRepo productoRepo, DetalleCompraRepo detalleCompraRepo, ImagenRepo imagenRepo, EspecificacionRepo especificacionRepo, CompraRepo compraRepo, ComprobantePagoRepo comprobantePagoRepo) {
        this.administradorRepo = administradorRepo;
        this.productoRepo = productoRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.compraRepo = compraRepo;
        this.imagenRepo = imagenRepo;
        this.comprobantePagoRepo = comprobantePagoRepo;
        this.especificacionRepo = especificacionRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Administrador> admEmail = administradorRepo.findByEmail(email);

        return admEmail.isPresent();
    }

    @Override
    public Administrador registrarAdministrador(Administrador a) throws Exception {

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
            administradorObtenido.setEmail(a.getEmail());
            administradorObtenido.setNickname(a.getNickname());
            administradorObtenido.setNombre(a.getNombre());
            administradorObtenido.setPassword(a.getPassword());
            administradorObtenido.setProductos(a.getProductos());
            administradorObtenido.setProductosAprobados(a.getProductosAprobados());

            administradorRepo.save(administradorObtenido);
        }
    }

    @Override
    public void eliminarAdministrador(String email) throws Exception {
        Administrador administradorEncontrado = obtenerAdministradorEmail(email);

        if (administradorEncontrado  != null){
           administradorRepo.delete(administradorEncontrado);
        } else {
            throw new Exception("El administrador no ha sido encontrado");
        }
    }

    @Override
    public Administrador obtenerAdministrador(int id) throws Exception {
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


    @Override
    public void aprobarProductoUsuario(int idProducto, int idAdministrador) throws Exception {

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Producto> productoEncontrado = productoRepo.findById(idProducto);

        if(adminEncontrado!=null && productoEncontrado!=null){

            productoEncontrado.get().setEstado(true);
            productoEncontrado.get().setAdministrador(adminEncontrado.get());
            adminEncontrado.get().getProductosAprobados().add(productoEncontrado.get());
            administradorRepo.save(adminEncontrado.get());
            productoRepo.save(productoEncontrado.get());
        }

    }

    @Override
    public void rechazarProductoUsuario(int idProducto, int idAdministrador) throws Exception {

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Producto> productoEncontrado = productoRepo.findById(idProducto);

        if(adminEncontrado!=null && productoEncontrado!=null){

            for (Imagen i:productoEncontrado.get().getImagenes()) {
                imagenRepo.delete(i);
            }

            for(Especificacion e:productoEncontrado.get().getEspecificaciones()){
                especificacionRepo.delete(e);
            }

            productoRepo.delete(productoEncontrado.get());
        }

    }
    @Override
    public void aprobarCompra(int idCompra, int idAdministrador){

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Compra> compraEncontrada = compraRepo.findById(idCompra);

        if(adminEncontrado!=null && compraEncontrada!=null){

            compraEncontrada.get().setEstado(true);
            compraEncontrada.get().setAdministrador(adminEncontrado.get());
            compraRepo.save(compraEncontrada.get());
           // adminEncontrado.get().getCompras().add(compraEncontrada.get()); //Suponiendo que esa sea una lista de compras aprobadas
            //Falta mirar lo del envio y el detalle
        }

    }

    public Compra obtenerCompra(int idCompra){

        Optional<Compra> compraEncontrada ;

        compraEncontrada= compraRepo.findById(idCompra);

        return compraEncontrada.get();
    }
    @Override
    public void rechazarCompra(int idCompra, int idAdministrador){

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Compra compraEncontrada = obtenerCompra(idCompra);

        if(adminEncontrado!=null && compraEncontrada!=null){

            ComprobantePago comprobanteCompra = compraEncontrada.getComprobantePago();

            if(compraEncontrada.getListaDetallesCompra().size()!=0){

                int tam = compraEncontrada.getListaDetallesCompra().size();

                for(int i=0;i<tam;i++){

                    DetalleCompra det = compraEncontrada.getListaDetallesCompra().get(i);
                    detalleCompraRepo.delete(det);
                    compraEncontrada.getListaDetallesCompra().remove(det);
                }

            }

            comprobanteCompra.setCompra(null);
            comprobantePagoRepo.save(comprobanteCompra);
            compraEncontrada.setComprobantePago(null);
            compraRepo.save(compraEncontrada);
            comprobantePagoRepo.delete(comprobanteCompra);
            compraRepo.delete(compraEncontrada);
        }

    }

}
