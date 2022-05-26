package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends Persona {

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotBlank(message = "Debe seleccionar una fecha de nacimiento")
    private String fechaNacimiento;

    //================================= RELACIÓN CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private transient List<Compra> compras;

    //================================= RELACIÓN CON LA ENTIDAD PRODUCTO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> productos;

    //================================= RELACIÓN CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Favorito> favoritos;

    //================================= RELACIÓN CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comentario> comentarios;

    //================================= RELACIÓN CON LA ENTIDAD DIRECCIÓN =================================//
    @OneToOne
    private Direccion direccion;

    //================================= CONSTRUCTOR =================================//
    public Usuario(String nombre,String apellido,String nickname, String password, String email,String fechaNacimiento) {
        super(nombre,apellido, nickname, password, email);
        this.fechaNacimiento = fechaNacimiento;
        this.compras= new ArrayList<>();
        this.favoritos= new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

}
