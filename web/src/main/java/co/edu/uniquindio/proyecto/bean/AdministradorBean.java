package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class AdministradorBean implements Serializable {

    private static final String MENSAJEPERSONALIZADO = "mensajePersonalizado";

    private static final String ALERTA = "Alerta";

    private final AdministradorServicio administradorServicio;

    private final CompraServicio compraServicio;

    private final ProductoServicio productoServicio;

    private final EmailService emailService;

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private List<Producto>productosSinAprobarUsuarios;

    @Getter @Setter
    private List<Producto>productosPublicados;

    @Getter @Setter
    private List<Compra>comprasUsuariosSinAprobar;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public AdministradorBean(AdministradorServicio administradorServicio, CompraServicio compraServicio, ProductoServicio productoServicio, EmailService emailService) {
        this.administradorServicio = administradorServicio;
        this.compraServicio = compraServicio;
        this.productoServicio = productoServicio;
        this.emailService = emailService;
    }

    @PostConstruct
    public void inicializar() {
        this.administrador = obtenerAdministrador();
        this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
        this.productosPublicados = obtenerProductosPublicados();
    }

    /***
     * Método para obtener un administrador
     * @return El administrador que se requiere
     */
    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return administradorEncontrado;
    }

    public void aprobarProductoUsuario(int idProducto) {

        administradorServicio.aprobarProductoUsuario(idProducto, personaLogin.getId());
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
    }

    public void rechazarProductoUsuario(int idProducto) {

        administradorServicio.rechazarProductoUsuario(idProducto, personaLogin.getId());
        this.productosSinAprobarUsuarios = obtenerProductosSinAprobar();
    }

    public void aprobarCompra(int idCompra){

        Compra c = compraServicio.obtenerCompra(idCompra);

        if (c!=null){
            administradorServicio.aprobarCompra(idCompra, personaLogin.getId());

            c = compraServicio.obtenerCompra(idCompra);
            compraServicio.crearEnvio(c);
            enviarConfirmationCompra(idCompra);
            this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
        }
    }

    public void enviarConfirmationCompra(int idCompra){

        try {

            Compra c = compraServicio.obtenerCompra(idCompra);

            Usuario u = c.getUsuario();

            Envio e = c.getEnvio();

            if (u!=null){

                String mensaje = "<span style=\"color:#542551;font-size: 25px\"><b>Confirmación de compra</b></span>";
                mensaje += "<br><br> Tu compra con el código "+c.getId()+" ha sido aprobada";
                mensaje += "<br><br><span style=\"color:#542551;font-size: 18.5px\"><b>Datos de envío</b></span><br><br>";
                mensaje += "<span style=\"color:#542551\"><b>Guía de envío: </b></span>" + e.getId();
                mensaje += "<br><br><span style=\"color:#542551\"><b>Fecha de compra: </b></span>"+ e.getFechaEnvio();
                mensaje += "<br><br><span style=\"color:#542551\"><b>Fecha de Aproximada de llegada: </b></span>"+ e.getFechaAproximadaLlegada();
                mensaje += "<br><br><span style=\"color:#542551\"><b>Tu compra llegara a tu dirección en aproximadamente </b></span>"+ e.getTiempoAproximado() + " días";
                mensaje += "<br><br>¡Muchas gracias por tu compra!";
                String emailCliente = u.getEmail();
                String asunto = "Confirmación";
                emailService.enviarEmail(asunto,mensaje,emailCliente);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, "No hemos encontrado tu cuenta");
                FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int idProducto){

        if (personaLogin!=null){
            productoServicio.eliminarProducto(idProducto);
            this.productosPublicados = obtenerProductosPublicados();
        }
    }

    public void rechazarCompra(int idCompra){

        administradorServicio.rechazarCompra(idCompra, personaLogin.getId());
        this.comprasUsuariosSinAprobar = obtenerComprasSinValidar();
    }


    public List<Producto> obtenerProductosSinAprobar(){

        List<Producto> productos= new ArrayList<>();

        if (personaLogin!=null){
            productos=productoServicio.listarProductosSinAprobarUsuarios();
        }

        return productos;
    }

    public List<Compra> obtenerComprasSinValidar(){

        List<Compra> compras = new ArrayList<>();

        if (personaLogin!=null){
            compras = compraServicio.listarComprasSinAprobarUsuarios();
        }

        return compras;
    }

    public List<Producto> obtenerProductosPublicados(){

        List<Producto> productos = new ArrayList<>();

        if (personaLogin!=null){
            productos =productoServicio.listarProductosAdmin(personaLogin.getId());
        }

        return productos;
    }

}
