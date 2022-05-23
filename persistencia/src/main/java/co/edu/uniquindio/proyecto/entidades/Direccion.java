package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 50,nullable = false)
    @NotBlank
    private String calle;

    @Column(length = 100,nullable = false)
    @NotBlank
    private String numero;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @OneToOne(mappedBy = "direccion",orphanRemoval = true,cascade = CascadeType.ALL)
    private Usuario usuario;

    //================================= RELACIÓN CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;

    public Direccion(String calle, String numero, Usuario usuario, Ciudad ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.usuario = usuario;
        this.ciudad = ciudad;
    }
}
