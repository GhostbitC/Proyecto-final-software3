package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ObjetoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.*;

@Service
public class AdministradorServicioImpl implements AdministradorServicio, Serializable {

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
    public void registrarAdministrador(Administrador a) throws ObjetoNoEncontradoException{

        if (a.getNickname().length()>100){
            throw new ObjetoNoEncontradoException("El nickname solo puede tener 100 caracteres ");
        }

        if (a.getEmail().length()>100){
            throw new ObjetoNoEncontradoException("El correo solo puede tener 100 caracteres ");
        }

        if (a.getNombre().length()>100){
            throw new ObjetoNoEncontradoException("El nombre solo puede tener 100 caracteres ");
        }

        if (a.getPassword().length()>100){
            throw new ObjetoNoEncontradoException("La contraseña solo puede tener 100 caracteres ");
        }

        Optional<Administrador> admNick= administradorRepo.findByNickname(a.getNickname());
        if(admNick.isPresent()){
            throw new ObjetoNoEncontradoException("El administrador ya existe");
        }

        if(estaDisponible(a.getEmail())){
            throw new ObjetoNoEncontradoException("El administrador ya existe");
        }
        administradorRepo.save(a);
    }


    @Override
    public void actualizarAdministrador(Administrador a,String email, String password){

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
    public Administrador obtenerAdministrador(int id) throws ObjetoNoEncontradoException {
        Optional<Administrador> administrador = administradorRepo.findById(id);

        return administrador.orElseThrow(() ->new ObjetoNoEncontradoException("No se encontraron administradores con los datos proporcionados"));
    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador obtenerEmailPassword(String email, String password) throws ObjetoNoEncontradoException {

        Administrador administrador =administradorRepo.findByEmailAndPassword(email, password);

        if(administrador == null){

            throw new ObjetoNoEncontradoException("No se encontraron registros");
        }
        return administrador;
    }


    @Override
    public void aprobarProductoUsuario(int idProducto, int idAdministrador) throws ObjetoNoEncontradoException {

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Producto> productoEncontrado = productoRepo.findById(idProducto);

        if(adminEncontrado.isPresent() && productoEncontrado.isPresent()){
            productoEncontrado.get().setEstado(true);
            productoEncontrado.get().setAdministrador(adminEncontrado.get());
            adminEncontrado.get().getProductosAprobados().add(productoEncontrado.get());
            administradorRepo.save(adminEncontrado.get());
            productoRepo.save(productoEncontrado.get());
        }else {
            throw new ObjetoNoEncontradoException("No se encontraron administradores ni productos");
        }
    }

    @Override
    public void rechazarProductoUsuario(int idProducto, int idAdministrador) throws ObjetoNoEncontradoException {

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Producto> productoEncontrado = productoRepo.findById(idProducto);

        if(adminEncontrado.isPresent() && productoEncontrado.isPresent()){

            imagenRepo.deleteAll(productoEncontrado.get().getImagenes());
            especificacionRepo.deleteAll(productoEncontrado.get().getEspecificaciones());
            productoRepo.delete(productoEncontrado.get());
        }else {
            throw new ObjetoNoEncontradoException("No se encontraron registros en la base de datos");
        }
    }

    @Override
    public void aprobarCompra(int idCompra, int idAdministrador){
        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Optional<Compra> compraEncontrada = compraRepo.findById(idCompra);

        if(adminEncontrado.isPresent() && compraEncontrada.isPresent()){
            compraEncontrada.get().setEstado(true);
            compraEncontrada.get().setAdministrador(adminEncontrado.get());
            compraRepo.save(compraEncontrada.get());
        }
    }

    public Compra obtenerCompra(int idCompra){

        Optional<Compra> compraEncontrada;

        compraEncontrada= compraRepo.findById(idCompra);

        return compraEncontrada.orElse(null);
    }

    @Override
    public void rechazarCompra(int idCompra, int idAdministrador){

        Optional<Administrador> adminEncontrado = administradorRepo.findById(idAdministrador);
        Compra compraEncontrada = obtenerCompra(idCompra);

        if(adminEncontrado.isPresent() && compraEncontrada!=null){

            ComprobantePago comprobanteCompra = compraEncontrada.getComprobantePago();

            if(compraEncontrada.getListaDetallesCompra()!=null && compraEncontrada.getListaDetallesCompra().isEmpty()){

                detalleCompraRepo.deleteAll(compraEncontrada.getListaDetallesCompra());
                compraEncontrada.getListaDetallesCompra().clear();

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
