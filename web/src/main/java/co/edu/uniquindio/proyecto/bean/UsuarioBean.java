package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.util.*;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    private final CompraServicio compraServicio;

    private final ProductoServicio productoServicio;

    @Getter
    private final CiudadServicio ciudadServicio;

    private static final String MENSAJEPERSONALIZADO = "mensajePersonalizado";

    private static final String MENSAJECONFIRMACION = "Confirmación";

    private static final String ALERTA = "Alerta";

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private String correo;

    @Getter @Setter
    private String passwordN;

    @Getter @Setter
    private Direccion direccionUsuario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private Producto productoEstrella;

    @Getter @Setter
    private Usuario usuarioLogin;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Compra> historial;

    @Getter
    @Setter
    private List<Compra> serviciosActivos;

    private final EmailService emailService;

    @Getter
    @Setter
    private List<Producto>productosPublicados;

    @Getter
    @Setter
    private List<Compra> comprasSinComprobante;

    @Getter @Setter
    private String linkImagen;

    @Getter
    @Setter
    private ComprobantePago comprobantePago;

    public UsuarioBean(UsuarioServicio usuarioServicio, CompraServicio compraServicio, ProductoServicio productoServicio, CiudadServicio ciudadServicio, EmailService emailService) {
        this.usuarioServicio = usuarioServicio;
        this.compraServicio = compraServicio;
        this.productoServicio = productoServicio;
        this.ciudadServicio = ciudadServicio;
        this.emailService = emailService;
    }


    @PostConstruct
    public void inicializar() {
        this.linkImagen = "";
        this.usuario  = new Usuario();
        this.usuarioLogin = obtenerUsuario();
        this.direccionUsuario = new Direccion();
        this.productosPublicados = obtenerProductosPublicados();
        this.comprasSinComprobante = obtenerComprasSinComprobante();
        this.historial = obtenerHistorialTransacciones();
        this.productoEstrella = obtenerProductoEstrella();
        this.ciudades = ciudadServicio.listarCiudades();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
        }
    }

    public String eliminarUsuario(){

        try {
            if (personaLogin!=null) {

                    usuarioServicio.eliminarUsuario(personaLogin.getEmail(),personaLogin.getPassword());

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! el usuario ha sido eliminado con exito");
                    FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                    return "/index?faces-redirect=true";

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
        }

        return null;
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                this.usuarioLogin= obtenerUsuario();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

                return "/usuario/perfilUsuario?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

        }
        return null;
    }

    public void subirComprobante() {

        if(linkImagen!=null && linkImagen.length()!=0){
            comprobantePago = new ComprobantePago();
            comprobantePago.setUrl(linkImagen);
        }
    }

    public void unirComprobanteCompra(int idCompra){

        try {
            compraServicio.agregarComprobanteCompra(idCompra, comprobantePago);
            this.comprasSinComprobante = obtenerComprasSinComprobante();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
        }
    }

    public Producto obtenerProductoEstrella(){

        Producto productoAux = new Producto();

        if(personaLogin!=null){
            productoAux=productoServicio.obtenerProductoEstrella(personaLogin.getId());
        }

        return productoAux;
    }

    public Usuario obtenerUsuario(){

        Usuario usuarioEncontrado = new Usuario();

        if(personaLogin!=null){

            try{
                usuarioEncontrado = usuarioServicio.obtenerUsuario(personaLogin.getId());

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return usuarioEncontrado;

    }

    public List<Compra> obtenerComprasSinComprobante(){

        List<Compra> compras= new ArrayList<>();

        if (personaLogin!=null){
            compras = compraServicio.listarComprasUsuarioSinComprobante(personaLogin.getId());
        }

        return compras;
    }

    public List<Compra> obtenerHistorialTransacciones(){

        List<Compra> compras= new ArrayList<>();

        if (personaLogin!=null){
            compras = compraServicio.listarComprasUsuario(personaLogin.getId());

        }

        return compras;
    }

    public List<Producto> obtenerProductosPublicados(){

        List<Producto> productos = new ArrayList<>();

        if (personaLogin!=null){
            productos =productoServicio.listarProductosUsuario(personaLogin.getId());
        }

        return productos;
    }

    public void eliminarProducto(int idProducto) {

        if (personaLogin!=null){
            productoServicio.eliminarProducto(idProducto);
            this.productosPublicados = obtenerProductosPublicados();
        }
    }

    public void enviarFactura(int idCompra){

        try {
            Compra compra = compraServicio.obtenerCompraUsuario(personaLogin.getId(),idCompra);

            StringBuilder mensaje = new StringBuilder("<span style=\"color:#542551;font-size: 25px\"><b>Factura Amazing Store</b></span>");
            mensaje.append("<br><br>Usted ha realizado una compra en Amazing Store");
            mensaje.append("<br><br><span style=\"color:#542551;font-size: 12px\"><b>Su factura contiene los siguientes datos: </b></span><br><br>");
            String emailCliente = personaLogin.getEmail();
            double totalCompra = 0.0;

            String asunto = "Factura";

            mensaje.append("<span style=\"color:#542551\"><b>Fecha de compra: </b></span>" + "<span style=\"color:black\">").append(compra.getFechaVenta().toString()).append("</span><br><br>");

            mensaje.append("<span style=\"color:#542551;font-size: 18.5px\"><b>Productos comprados</b></span><br>");

            for (int i = 0; i < compra.getListaDetallesCompra().size(); i++) {

                mensaje.append("<ul>");
                mensaje.append("<li>").append(compra.getListaDetallesCompra().get(i).getProducto().getNombre()).append("<span style=\"color:black\">&nbsp;&nbsp;&nbsp;<b>    Unidades:      </b></span>").append("<span style=\"color:blue\">").append(compra.getListaDetallesCompra().get(i).getUnidades()).append("</span>").append("</li>");
                mensaje.append("</ul>");

                totalCompra += compra.getListaDetallesCompra().get(i).getProducto().getPrecio();
            }

            mensaje.append("<br><span style=\"color:#542551\"><b>Total cancelado: </b></span>" + "<span style=\"color:black\">").append(totalCompra).append(" COP </span><br>");

            mensaje.append("<br><span style=\"color:#542551\"><b>Método de pago seleccionado: </b></span>" + "<span style=\"color:black\">").append(compra.getMedioPago()).append("</span>");

            emailService.enviarEmail(asunto, mensaje.toString(),emailCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, MENSAJECONFIRMACION, "La factura se ha enviado al correo electrónico vinculado a esta cuenta");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void metods(int idCompra){

        enviarFactura(idCompra);
        showMessage();
    }

    public void enviarEmailPassword(){

        try {

            Usuario u = usuarioServicio.obtenerUsuarioEmail(correo);

            if (u!=null){

                String mensaje = "<span style=\"color:#542551;font-size: 25px\"><b>Recuperar contraseña</b></span>";
                mensaje += "<br><br> Para cambiar tu contraseña por favor ingresa al siguiente enlace:";
                mensaje += "<br><br><span style=\"color:#542551\"><b>https://amazing-store-uq.herokuapp.com/cambiarPassword.xhtml</b></span><br><br>";
                String emailCliente = u.getEmail();
                String asunto = "Cambio de contraseña";
                emailService.enviarEmail(asunto,mensaje,emailCliente);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, "No hemos encontrado tu cuenta");
                FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessagePassword() {

        try {
            Usuario u= usuarioServicio.obtenerUsuarioEmail(correo);

            FacesMessage message;
            if (u!=null){
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, MENSAJECONFIRMACION, "Revisa tu correo electrónico, te hemos enviado un email.");

            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, MENSAJECONFIRMACION, "No se encontró el usuario");

            }
            PrimeFaces.current().dialog().showMessageDynamic(message);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, MENSAJECONFIRMACION, e.getMessage());

            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void metodsPassword(){

        enviarEmailPassword();
        showMessagePassword();
    }

    public String cambiarPassword(){

        try {
            Usuario u = usuarioServicio.obtenerUsuarioEmail(correo);

            usuarioServicio.cambiarPassword(u.getEmail(),passwordN);
            String mensaje = "<span style=\"color:#542551;font-size: 25px\"><b>Cambio de contraseña exitoso</b></span>";
            mensaje += "<br><br>¡Tu contraseña fue actualizada!";
            mensaje += "<br><br>Disfruta de nuestros servicios en <span style=\"color:#542551\"><b>https://amazing-store-uq.herokuapp.com/</b></span>";
            mensaje += "<br><br>¡Te esperamos!";
            emailService.enviarEmail("Confirmación cambio de contraseña",mensaje,u.getEmail());

            return "/loginUsuario?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
