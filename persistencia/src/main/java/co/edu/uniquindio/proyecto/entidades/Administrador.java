package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Administrador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACION CON LA ENTIDAD PRODUCTO USUARIO =================================//
    @OneToMany(mappedBy = "administrador")
    @ToString.Exclude
    private List<ProductoUsuario> productosAprobadosUsuarios;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        productos= new ArrayList<>();
        productosAprobadosUsuarios = new ArrayList<>();
    }


}
