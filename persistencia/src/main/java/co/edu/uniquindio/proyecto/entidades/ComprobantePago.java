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
public class ComprobantePago implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 100,nullable = false)
    @NotBlank
    private String url;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToOne
    private Compra compra;

    public ComprobantePago(String url, Compra compra) {
        this.url = url;
        this.compra = compra;
    }
}
