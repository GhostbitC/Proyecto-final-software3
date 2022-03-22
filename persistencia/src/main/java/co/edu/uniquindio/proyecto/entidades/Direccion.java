package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_direccion",nullable = false)
    @EqualsAndHashCode.Include
    private int codigo_direccion;

    @Column(name = "calle",length = 50,nullable = false)
    @NotBlank
    private String calle;

    @Column(name = "numero",length = 100,nullable = false)
    @NotBlank
    private String numero;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @OneToOne(mappedBy = "direccion")
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;
}
