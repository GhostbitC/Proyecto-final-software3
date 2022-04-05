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
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int codigoDireccion;

    @Column(name = "calle",length = 50,nullable = false)
    @NotBlank
    private String calle;

    @Column(name = "numero",length = 100,nullable = false)
    @NotBlank
    private String numero;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @OneToOne(mappedBy = "direccion",orphanRemoval = true,cascade = CascadeType.ALL)
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;
}
