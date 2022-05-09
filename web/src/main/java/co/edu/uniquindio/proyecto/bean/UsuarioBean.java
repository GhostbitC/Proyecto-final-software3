package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    @Getter @Setter
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private String correo;

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

    @Autowired
    private EmailService emailService;

    @Getter
    @Setter
    private List<Producto>productosPublicados;

    @Getter
    @Setter
    private List<Compra> comprasSinComprobante;

    @Value("${comprobantes.url}")
    private String urlComprobantes;

    @Getter
    @Setter
    private ComprobantePago comprobantePago;


    @PostConstruct
    public void inicializar() {
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
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public String eliminarUsuario(){

        System.out.println("Entro");
        try {
            if (personaLogin!=null) {

                    usuarioServicio.eliminarUsuario(personaLogin.getEmail(),personaLogin.getPassword());

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario ha sido eliminado con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                    return "/index?faces-redirect=true";

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

        return null;
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                this.usuarioLogin= obtenerUsuario();
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                return "/usuario/perfilUsuario?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
        return null;
    }

    public void subirComprobante(FileUploadEvent event) {

        UploadedFile imagen = event.getFile();
        String nombreComprobante = subirImagen(imagen);

        if (nombreComprobante != null) {

            comprobantePago = new ComprobantePago();
            comprobantePago.setUrl(nombreComprobante);

        }
    }

    public String subirImagen(UploadedFile file) {

        try {
            InputStream input = file.getInputStream();
            String fileName = FilenameUtils.getName(file.getFileName());
            String baseName = FilenameUtils.getBaseName(fileName) + "_";
            String extension = "." + FilenameUtils.getExtension(fileName);
            File fileDest = File.createTempFile(baseName, extension, new File(urlComprobantes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);

            return fileDest.getName();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void unirComprobanteCompra(int idCompra){

        try {
            compraServicio.añadirComprobanteCompra(idCompra, comprobantePago);
            this.comprasSinComprobante = obtenerComprasSinComprobante();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
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

    public void eliminarProducto(int idProducto) throws Exception {

        if (personaLogin!=null){
            productoServicio.eliminarProducto(idProducto);
            this.productosPublicados = obtenerProductosPublicados();
        }
    }

    public void enviarFactura(int idCompra){

        try {
            Compra compra = compraServicio.obtenerCompraUsuario(personaLogin.getId(),idCompra);

            String mensaje = "<span style=\"color:#542551;font-size: 25px\"><b>Factura Amazing Store</b></span>";
            mensaje += "<br><br>Usted ha realizado una compra en Amazing Store";
            mensaje += "<br><br><span style=\"color:#542551;font-size: 12px\"><b>Su factura contiene los siguientes datos: </b></span><br><br>";
            String emailCliente = personaLogin.getEmail();
            Double totalCompra = 0.0;

            String asunto = "Factura";

            mensaje += "<span style=\"color:#542551\"><b>Fecha de compra: </b></span>" + "<span style=\"color:black\">"+compra.getFechaVenta().toString()+ "</span><br><br>";

            mensaje += "<span style=\"color:#542551;font-size: 18.5px\"><b>Productos comprados</b></span><br>";

            for (int i = 0; i < compra.getListaDetallesCompra().size(); i++) {

                mensaje += "<ul>";
                mensaje += "<li>" + compra.getListaDetallesCompra().get(i).getProducto().getNombre() + "<span style=\"color:black\">&nbsp;&nbsp;&nbsp;<b>    Unidades:      </b></span>" + "<span style=\"color:blue\">" + compra.getListaDetallesCompra().get(i).getUnidades() + "</span>" + "</li>";
                mensaje += "</ul>";

                totalCompra += compra.getListaDetallesCompra().get(i).getProducto().getPrecio();
            }

            mensaje += "<br><span style=\"color:#542551\"><b>Total cancelado: </b></span>" + "<span style=\"color:black\">"+ totalCompra +" COP </span><br>";

            mensaje += "<br><span style=\"color:#542551\"><b>Método de pago seleccionado: </b></span>" + "<span style=\"color:black\">"+compra.getMedioPago()+"</span>";

            emailService.enviarEmail(asunto,mensaje,emailCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "La factura se ha enviado al correo electrónico vinculado a esta cuenta");

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
                String asunto = "Cambio de contraseña";
                String emailCliente = u.getEmail();
                String mensaje = "https://amazing-store-uq.herokuapp.com/cambiarPassword.xhtml";
                emailService.enviarEmail(asunto,mensaje,emailCliente);
            }else{
                throw new Exception("No hemos encontrado tu cuenta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessagePassword() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Revisa tu correo electrónico, te hemos enviado un email.");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void metodsPassword(){

        enviarEmailPassword();
        showMessagePassword();
    }

}
