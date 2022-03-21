package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario extends Persona implements Serializable {

    @Column(name = "saldo")
    private float saldo;

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    @JsonIgnore
    private Ciudad ciudad;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Favorito> favoritos;

    //================================= RELACION CON LA ENTIDAD DIRECCION =================================//
    @ManyToOne
    private Direccion direccion;


    //================================= CONSTRUCTOR  =================================//
    public Usuario(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        compras= new ArrayList<>();
        favoritos= new ArrayList<>();

    }

}
