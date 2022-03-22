package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.servicios.ServicioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
@RequestScope
public class ServicioBean implements Serializable {

    @Autowired
    private ServicioServicio servicioServicio;

    @Getter
    @Setter
    private Servicio servicio;

    public ServicioBean(ServicioServicio servicioServicio) {
        this.servicioServicio = servicioServicio;

    }

    @PostConstruct
    public void inicializar() {

    }
}
