package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Envio implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 100)
    long tiempoAproximado;


    @Column(nullable = false)
    private LocalDate fechaEnvio;


    private LocalDate fechaAproximadaLlegada;

    @Column
    private float valor;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "envio",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    public Envio(long tiempoAproximado, LocalDate fechaEnvio, float valor) {
        this.tiempoAproximado = tiempoAproximado;
        this.fechaEnvio = fechaEnvio;
        this.valor = valor;
        this.compras = new ArrayList<>();
    }
}
