package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario extends Persona implements Serializable {

    @Column(name = "saldo", length = 10)
    private double saldo;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList<>();

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Favorito> favoritos;

    //================================= RELACION CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<ProductoUsuario> productosUsuarios;

    //================================= RELACION CON LA ENTIDAD DIRECCION =================================//
    @OneToOne
    private Direccion direccion;

    //================================= CONSTRUCTOR  =================================//
    public Usuario(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        this.compras= new ArrayList<>();
        this.favoritos= new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

}
