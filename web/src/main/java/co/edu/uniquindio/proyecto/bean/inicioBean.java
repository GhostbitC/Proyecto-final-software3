package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.servicios.MascotaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class inicioBean implements Serializable {

    @Autowired
    private MascotaServicio mascotaServicio;

    @Getter @Setter
    private List<Mascota> mascotas;

    @PostConstruct
    public void inicializar(){

        this.mascotas = mascotaServicio.listarMascotas();

    }

    public String irADetalle(Integer id){
        return  "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }
}
