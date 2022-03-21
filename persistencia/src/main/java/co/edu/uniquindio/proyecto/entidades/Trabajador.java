package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trabajador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD MASCOTA =================================//
    @OneToMany(mappedBy = "trabajador")
    @ToString.Exclude
    @JsonIgnore
    private List<Mascota> mascotas;


    //================================= RELACION CON LA ENTIDAD SERVICIO =================================//
    @OneToMany(mappedBy = "trabajador")
    @ToString.Exclude
    private List<Servicio> servicios;


    //================================= CONSTRUCTOR  =================================//
    public Trabajador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        this.mascotas = new ArrayList<>();
        this.servicios= new ArrayList<>();

    }

}
