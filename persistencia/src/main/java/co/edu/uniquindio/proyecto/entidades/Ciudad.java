package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    @NotBlank
    private String nombre;

    //================================= RELACION CON LA ENTIDAD DIRECCIÓN =================================//
    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Direccion> direcciones;

    //================================= CONSTRUCTOR  =================================//
    public Ciudad( String nombre) {
        this.nombre = nombre;
        this.direcciones = new ArrayList<>();
    }
}
