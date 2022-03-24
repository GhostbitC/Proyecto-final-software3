package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;


@Component
@ViewScoped
public class DetalleServicioBean implements Serializable {

    @Value("#{param['servicio']}")
    private  String idServicio;

    @Autowired
    private ServicioServicio servicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Servicio guarderia;

    @Getter @Setter
    private Servicio veterinaria;

    @Getter @Setter
    private Servicio hospital;

    @Getter @Setter
    private Mascota mascota;

    @Getter @Setter
    private Usuario usuario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    @PostConstruct
    public void inicializar(){

        try {
            this.guarderia = servicio.obtenerServicioNombre("Guarderia");
            this.hospital = servicio.obtenerServicioNombre("Hospital");
            this.veterinaria = servicio.obtenerServicioNombre("Veterinaria");
            this.usuario = new Usuario();
            this.mascota = new Mascota();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void adquirirServicioGuarderia() {

        Servicio servicioEncontrado;
        Mascota mascotaUsuario;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());
                mascotaUsuario = usuarioServicio.obtenerMascotaUsuario(mascota.getNombre(),usuario.getId());

                servicioEncontrado = servicio.obtenerServicioNombre("Guarderia");
                servicioEncontrado.getMascotas().add(mascotaUsuario);
                servicio.adquirirServicioGuarderia(servicioEncontrado,mascotaUsuario.getNombre(),usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el servicio");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }


    public void adquirirServicioHospital() {

        Servicio servicioEncontrado;
        Mascota mascotaUsuario;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());
                mascotaUsuario = usuarioServicio.obtenerMascotaUsuario(mascota.getNombre(),usuario.getId());

                servicioEncontrado = servicio.obtenerServicioNombre("Hospital");
                servicioEncontrado.getMascotas().add(mascotaUsuario);
                servicio.adquirirServicioHospital(servicioEncontrado,mascotaUsuario.getNombre(),usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el servicio");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }


    public void adquirirServicioVeterinaria() {

        Servicio servicioEncontrado;
        Mascota mascotaUsuario;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());
                mascotaUsuario = usuarioServicio.obtenerMascotaUsuario(mascota.getNombre(),usuario.getId());

                servicioEncontrado = servicio.obtenerServicioNombre("Veterinaria");
                servicioEncontrado.getMascotas().add(mascotaUsuario);
                servicio.adquirirServicioHospital(servicioEncontrado,mascotaUsuario.getNombre(),usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el servicio");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }
}
