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
    private List<Compra> historialServicios;

    @Getter
    @Setter
    private List<Compra> serviciosActivos;

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
            if (usuarioLogin!=null) {

                usuarioServicio.eliminarUsuario(usuarioLogin.getEmail(),usuarioLogin.getPassword());
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



}
