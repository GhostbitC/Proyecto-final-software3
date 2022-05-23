package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Imagen implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 300,nullable = false)
    @NotBlank
    private String url;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    private Producto producto;

    //================================= CONSTRUCTOR  =================================//
    public Imagen(String url) {
        this.url = url;
    }

    public Imagen(String url, Producto producto) {
        this.url = url;
        this.producto = producto;
    }
}
