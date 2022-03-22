package co.edu.uniquindio.proyecto.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Compra implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "valor")
    private float valor;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta", length = 100)
    private Date fechaVenta;

    @Column(name = "cantidad_productos")
    private int cantidadProductos;

    @Column(name = "estado")
    private Boolean estado;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACION CON LA ENTIDAD ENVIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Envio envio;

}
