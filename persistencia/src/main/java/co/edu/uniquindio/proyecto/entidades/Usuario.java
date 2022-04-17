package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends Persona implements Serializable {
    @Column(length = 10)
    private double saldo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private String fechaNacimiento;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Favorito> favoritos;

    //================================= RELACION CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD DIRECCION =================================//
    @OneToOne
    private Direccion direccion;

    //================================= CONSTRUCTOR  =================================//
    public Usuario(String nombre,String apellido,String nickname, String password, String email,String fechaNacimiento) {
        super(nombre,apellido, nickname, password, email);
        this.fechaNacimiento = fechaNacimiento;
        this.compras= new ArrayList<>();
        this.favoritos= new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

}
