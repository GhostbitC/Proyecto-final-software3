package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.MascotaServicio;
import co.edu.uniquindio.proyecto.servicios.TrabajadorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
@RequestScope
public class TrabajadorBean implements Serializable {

    private final TrabajadorServicio trabajadorServicio;
    private final MascotaServicio mascotaServicio;

    @Getter
    @Setter
    private Trabajador trabajador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public TrabajadorBean(TrabajadorServicio trabajadorServicio, MascotaServicio mascotaServicio) {
        this.trabajadorServicio = trabajadorServicio;
        this.mascotaServicio = mascotaServicio;
    }

    @PostConstruct
    public void inicializar() {

        try {
            this.trabajador = obtenerTrabajador();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Trabajador obtenerTrabajador() {

        Trabajador trabajadorEncontrado = new Trabajador();

        if (personaLogin != null) {

            try {
                trabajadorEncontrado= trabajadorServicio.obtenerTrabajador(personaLogin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return trabajadorEncontrado;
    }

}
