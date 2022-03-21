package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    @Getter @Setter
    private CiudadServicio ciudadServicio;

    @Autowired
    @Getter @Setter
    private ServicioServicio servicioServicio;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private Usuario usuarioAux;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Mascota>mascotasUsuario;

    @Getter
    @Setter
    private List<Compra> historialServicios;

    @Getter
    @Setter
    private List<Compra> serviciosActivos;

    @Getter
    @Setter
    private List<CompraProducto> productos;


    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioAux = obtenerUsuario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.mascotasUsuario = obtenerMascotasUsuario();
        this.historialServicios = obtenerHistorialServicios();
        this.serviciosActivos = obtenerServiciosActivos();
        this.productos = obtenerProductos();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void registrarTarjetaUsuario(){

        try {

            usuarioServicio.registrarTarjetaUsuario(personaLogin.getId(),usuario.getNumeroTarjeta(),usuario.getCodigoTarjeta(),usuario.getFechatarjeta());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! tu tarjeta se ha registrado con exito");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }catch (Exception e){

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public String eliminarUsuario(){

        try {
            if (personaLogin!=null) {

                usuarioServicio.eliminarUsuario(usuario.getEmail(),usuario.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

        return null;
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
        return null;
    }

    public List<Compra> obtenerHistorialServicios(){

        List<Compra> registrados=null;

        if (personaLogin!=null){

            try{
                registrados= usuarioServicio.obtenerHistorialServicios(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return registrados;
    }

    public List<Compra> obtenerServiciosActivos(){

        List<Compra> registrados=null;

        if (personaLogin!=null){

            try{
                registrados= usuarioServicio.obtenerServiciosActivos(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return registrados;
    }

    public String metodos(int idCompra, int idServicio){

        cancelarServicio(idServicio,idCompra);

        return "/usuario/perfilUsuario?faces-redirect=true";

    }

    public List<CompraProducto> obtenerProductos(){

        List<CompraProducto> registrados=null;

        if (personaLogin!=null){

            try{
                registrados= usuarioServicio.obtenerProductosUsuario(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return registrados;
    }


    public List<Mascota> obtenerMascotasUsuario(){

        List<Mascota> mascotasU = null;

        if (personaLogin!=null){
            try{
                mascotasU= usuarioServicio.obtenerMascotasPorUsuario(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mascotasU;

    }

    public Usuario obtenerUsuario(){

        Usuario usuarioEncontrado = new Usuario();

        if (personaLogin!=null){

            try {
                usuarioEncontrado = usuarioServicio.obtenerUsuario(personaLogin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuarioEncontrado;
    }

    public void cancelarServicio(int idServicio,int idCompra) {

        Servicio servicioAux;
        Compra compraAux;

        if(personaLogin!= null){

            try {
                servicioAux = servicioServicio.obtenerServicio(idServicio);

                compraAux= usuarioServicio.obtenerCompra(idCompra);

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());

                usuarioServicio.cancelarServicio(compraAux.getId(),servicioAux.getId(),usuario.getId());
                this.serviciosActivos = obtenerServiciosActivos();
                this.historialServicios= obtenerHistorialServicios();

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }


}
