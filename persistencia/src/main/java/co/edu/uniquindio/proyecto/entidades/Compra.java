package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta", length = 100)
    private Date fechaVenta;

    @Column
    private Boolean estado;

    @Column(name = "medio_pago", nullable = false)
    private String medioPago;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACIÓN CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACIÓN CON LA ENTIDAD DETALLE COMPRA =================================//
    @OneToMany (mappedBy = "compra", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> listaDetallesCompra;

    //================================= RELACIÓN CON LA ENTIDAD ENVÍO =================================//
    @ManyToOne
    @ToString.Exclude
    private Envio envio;

    //================================= RELACIÓN CON LA ENTIDAD COMPROBANTE DE PAGO =================================//
    @OneToOne(cascade = CascadeType.ALL)
    private ComprobantePago comprobantePago;

    public Compra(Date fechaVenta, Usuario usuario) {
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
        this.listaDetallesCompra = new ArrayList<>();
    }

    public Compra(Date fechaVenta, Boolean estado, String medioPago, Usuario usuario, Administrador administrador) {
        this.fechaVenta = fechaVenta;
        this.estado = estado;
        this.medioPago = medioPago;
        this.usuario = usuario;
        this.administrador = administrador;
        this.listaDetallesCompra = new ArrayList<>();
    }
}
