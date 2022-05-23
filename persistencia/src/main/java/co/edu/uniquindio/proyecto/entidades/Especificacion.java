package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Especificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false,length = 500)
    @Size(max = 500, message = "La especificación no puede superar los 500 caracteres")
    private String especification;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    private Producto producto;

    public Especificacion(String especification) {
        this.especification = especification;
    }

    public Especificacion(String especification, Producto producto) {
        this.especification = especification;
        this.producto = producto;
    }
}
