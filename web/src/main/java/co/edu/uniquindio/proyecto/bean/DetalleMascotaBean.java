package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.MascotaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleMascotaBean implements Serializable {

    @Value("#{param['mascota']}")
    private String idMascota;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Getter
    @Setter
    private Mascota mascota;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter
    @Setter
    private String icono;

    @Getter
    @Setter
    private List<String> urlImagenes;

    @PostConstruct
    public void inicializar() {

        if (idMascota != null && !"".equals(idMascota)) {
            try {
                int id = Integer.parseInt(idMascota);

                this.mascota = mascotaServicio.obtenerMascota(id);
                this.urlImagenes = new ArrayList<>();

                List<Imagen> imagenes = mascota.getImagenes();

                if (imagenes.size() > 0) {

                    for (Imagen i : imagenes) {

                        urlImagenes.add(i.getUrl());
                    }
                } else {

                    urlImagenes.add("default.png");
                }

                List<Mascota> mascotas = new ArrayList<>();
                mascotas.add(mascota);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}