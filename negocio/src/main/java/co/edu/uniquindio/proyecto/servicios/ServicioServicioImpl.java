package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicioImpl implements ServicioServicio{

    private final ServicioRepo servicioRepo;
    private final UsuarioRepo usuarioRepo;
    private final CompraRepo compraRepo;

    public ServicioServicioImpl(ServicioRepo servicioRepo, UsuarioRepo usuarioRepo, CompraRepo compraRepo) {
        this.servicioRepo = servicioRepo;
        this.usuarioRepo = usuarioRepo;
        this.compraRepo = compraRepo;
    }

    @Override
    public Servicio registrarServicio(Servicio s) throws Exception {

        if (s.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if (s.getDescripcion().length()>200){
            throw new Exception("No se puede exceder los 200 caracteres");
        }
        return servicioRepo.save(s);
    }

    @Override
    public void actualizarServicio(Servicio s, int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado!=null){

            servicioEncontrado.setNombre(s.getNombre());
            servicioEncontrado.setDescripcion(s.getDescripcion());
        }else {
            throw new Exception("El servicio que desea modificar no existe");
        }
    }

    @Override
    public void eliminarServicio(int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado != null){
            servicioRepo.delete(servicioEncontrado);
        }else {
            throw new Exception("El servicio que desea borrar no existe");
        }
    }

    @Override
    public Servicio obtenerServicio(int id) throws Exception {

        Optional<Servicio> servicioEncontrado = servicioRepo.findById(id);

        if (servicioEncontrado.isEmpty()){

            throw new Exception("No existe un servicio con el id ingresado");
        }
        return servicioEncontrado.get();
    }

    @Override
    public Servicio obtenerServicioNombre(String nombre) throws Exception {

        Servicio servicioEncontrado = servicioRepo.obtenerServicioNombre(nombre);

        if (servicioEncontrado==null){

            throw new Exception("No existe un servicio con el nombre ingresado");
        }
        return servicioEncontrado;
    }

    @Override
    public void adquirirServicioGuarderia(Servicio servicio,String nombreMascota,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception {

        Compra compra = new Compra();
        Usuario usuario = usuarioRepo.obtenerUsuarioCompra(nombreUsuario,cedulaUsuario,numeroTarjeta);

        Date inicio = new Date();

        if(servicio != null && usuario!=null){
            compra.setServicio(servicio);
            compra.setUsuario(usuario);
            compra.setEstado(true);
            compra.setHoraInicio(inicio);
            compraRepo.save(compra);
            servicio.getCompras().add(compra);
            servicioRepo.save(servicio);
        }
    }


    @Override
    public void adquirirServicioHospital(Servicio servicio,String nombreMascota,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception {

        Compra compra = new Compra();
        Usuario usuario = usuarioRepo.obtenerUsuarioCompra(nombreUsuario,cedulaUsuario,numeroTarjeta);

        if(servicio != null && usuario!=null){
            compra.setServicio(servicio);
            compra.setUsuario(usuario);
            compra.setEstado(true);
            compra.setHoraInicio(new Date());
            compraRepo.save(compra);
            servicio.getCompras().add(compra);
            servicioRepo.save(servicio);
        }
    }

    @Override
    public void adquirirServicioVet(Servicio servicio,String nombreMascota,String nombreUsuario,String cedulaUsuario, String numeroTarjeta) throws Exception {

        Compra compra = new Compra();
        Usuario usuario = usuarioRepo.obtenerUsuarioCompra(nombreUsuario,cedulaUsuario,numeroTarjeta);

        if(servicio != null && usuario!=null){
            compra.setServicio(servicio);
            compra.setUsuario(usuario);
            compra.setEstado(true);
            compra.setHoraInicio(new Date());
            compraRepo.save(compra);
            servicio.getCompras().add(compra);
            servicioRepo.save(servicio);
        }
    }


    @Override
    public List<Servicio> listarServicios() {
        return servicioRepo.findAll();
    }
}
