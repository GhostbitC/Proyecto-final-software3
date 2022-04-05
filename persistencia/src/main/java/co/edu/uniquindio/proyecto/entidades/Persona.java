package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//================================= RELACION DE HERENCIA =================================//

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Persona implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACIÓN =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "cedula",length = 100)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String cedula;

    @Column(name = "nombre",length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String nombre;

    @Column(name = "apellido",length = 100,nullable = false)
    @Size(max = 100,message = "El valor ingresado excede los 100 caracteres")
    private String apellido;

    @Column(name = "nickname",length = 100,nullable = false,unique = true)
    private String nickname;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona(String nombre, String apellido,String nickname, String password, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }


}
