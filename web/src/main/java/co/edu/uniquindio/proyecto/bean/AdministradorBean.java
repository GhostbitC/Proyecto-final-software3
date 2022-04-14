package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class AdministradorBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private Administrador administrador;

    @Getter
    @Setter
    private List<Producto>productosSinAprobarUsuarios;

    @Getter
    @Setter
    private List<Compra>comprasUsuariosSinAprobar;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @PostConstruct
    public void inicializar() {
        this.administrador = obtenerAdministrador();
        this.comprasUsuariosSinAprobar = compraServicio.listarComprasSinAprobarUsuarios();
        this.productosSinAprobarUsuarios = productoServicio.listarProductosSinAprobarUsuarios();
    }

    /***
     * Metodo para obtener un administrador
     * @return El administrador que se requiere
     */
    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());
                //personaLogin.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return administradorEncontrado;
    }

    public void aprobarProductoUsuario(int idProducto) throws Exception {

        administradorServicio.aprobarProductoUsuario(idProducto, personaLogin.getId());
    }

    public void rechazarProductoUsuario(int idProducto) throws Exception {

        administradorServicio.rechazarProductoUsuario(idProducto, personaLogin.getId());
    }

    public void aprobarCompra(int idCompra){

        administradorServicio.aprobarCompra(idCompra, personaLogin.getId());
    }

    public void rechazarCompra(int idCompra){

        administradorServicio.rechazarCompra(idCompra, personaLogin.getId());
    }

}
