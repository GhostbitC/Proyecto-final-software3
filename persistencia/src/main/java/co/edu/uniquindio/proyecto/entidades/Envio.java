package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Envio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "tiempo_aproximado", length = 100)
    private String tiempoAproximado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_envio", nullable = false)
    private Date fechaEnvio;

    @Column
    private float valor;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "envio")
    @ToString.Exclude
    private transient List<Compra> compras;

}
