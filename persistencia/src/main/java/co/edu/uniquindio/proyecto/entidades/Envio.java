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

public class Envio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_envio",nullable = false)
    @EqualsAndHashCode.Include
    private int codigoEnvio;

    @Column(name = "tiempo_aproximado", length = 100)
    private String tiempoAproximado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_envio", nullable = false)
    private Date fechaEnvio;

    @Column(name = "valor")
    private float valor;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "envio")
    @ToString.Exclude
    private List<Compra> compras;

    //================================= RELACION CON LA ENTIDAD VENTA PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "envio")
    @ToString.Exclude
    private List<VentaProductoUsuario> ventaProductosUsuarios;
}
