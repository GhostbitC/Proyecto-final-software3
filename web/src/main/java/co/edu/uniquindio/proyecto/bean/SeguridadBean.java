package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    private final PersonaServicio personaServicio;

    private final CompraServicio compraServicio;

    private final CiudadServicio ciudadServicio;

    private final DireccionServicio direccionServicio;

    private static final String MENSAJEPERSONALIZADO = "mensajePersonalizado";

    private static final String ALERTA = "Alerta";

    private static final String MENSAJECOMPRA = "msj-compra";

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private  boolean autenticado;

    @Getter @Setter
    private Direccion direccion;

    @Getter @Setter
    @NotBlank
    private String email;

    @Getter @Setter
    @NotBlank
    private String emailR;

    @Getter @Setter
    @NotBlank
    private String password;

    @Getter @Setter
    private String rol;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private List<Compra> listaMisCompras;

    @Getter @Setter
    private double subtotal;

    @Getter @Setter
    private double subTotalF;

    @Getter @Setter
    private String medioPago;

    @Getter @Setter
    private ArrayList<String> mediosPago;

    @Getter @Setter
    private List<Ciudad> ciudades;

    public SeguridadBean(UsuarioServicio usuarioServicio, PersonaServicio personaServicio, CiudadServicio ciudadServicio, DireccionServicio direccionServicio, CompraServicio compraServicio) {
        this.usuarioServicio = usuarioServicio;
        this.personaServicio = personaServicio;
        this.ciudadServicio = ciudadServicio;
        this.direccionServicio = direccionServicio;
        this.compraServicio = compraServicio;
    }

    @PostConstruct
    public void inicializar() {
        autenticado = false;
        this.productosCarrito = new ArrayList<>();
        this.mediosPago = new ArrayList<>();
        mediosPago.add("Consignación");
        this.subtotal = 0.0;
        this.subTotalF = 7.699;
        this.usuario = new Usuario();
        this.direccion = new Direccion();
        listaMisCompras = listarComprasUsuario();
        this.ciudades = ciudadServicio.listarCiudades();
    }

    public String iniciarSesion(){

        if (email!=null && password!=null) {
            try {
                persona = personaServicio.login(email,password);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, ALERTA, "¡Super! ingreso correctamente");
                FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);

                if (persona instanceof Usuario){
                    rol="usuario";
                }else if (persona instanceof Administrador){
                    rol="admin";
                }

                autenticado=true;
               return "/general?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
                FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
            }
        }
        return null;
    }

    public String cerrarSesion(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/general?faces-redirect=true";
    }

    public List<Compra> listarComprasUsuario() {

        List<Compra> comprasU = new ArrayList<>();
        if (persona != null) {
            comprasU = compraServicio.listarComprasUsuario(persona.getId());
        }
        return comprasU;
    }

    public double calcularSubTotal(int indice) {

        listaMisCompras = compraServicio.listarComprasUsuario(persona.getId());
        double total = 0.0;

        for (int i = 0; i < listaMisCompras.get(indice).getListaDetallesCompra().size(); i++) {
            total = listaMisCompras.get(indice).getListaDetallesCompra().get(i).getUnidades() * listaMisCompras.get(indice).getListaDetallesCompra().get(i).getProducto().getPrecio();
        }
        return total;
    }

    public void agregarAlCarrito(Integer codigo, Float precio, String nombre, String imagen) {
        ProductoCarrito pc = new ProductoCarrito(codigo, nombre, imagen, 1, precio);
        if (!productosCarrito.contains(pc)) {
            productosCarrito.add(pc);
            this.subtotal= subtotal + (pc.getPrecio() * pc.getUnidades());
            this.subtotal = Math.round(subtotal*1000.0)/1000.0;

            this.subTotalF= subTotalF + (pc.getPrecio() * pc.getUnidades());
            this.subTotalF = Math.round(subTotalF*1000.0)/1000.0;
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "El producto se ha agregado a carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice) {
        this.subtotal -= productosCarrito.get(indice).getPrecio();
        this.subtotal = Math.round(subtotal*1000.0)/1000.0;
        this.subTotalF -= productosCarrito.get(indice).getPrecio();
        this.subTotalF = Math.round(subTotalF*1000.0)/1000.0;
        productosCarrito.remove(indice);
        if (productosCarrito.isEmpty()){
            this.subtotal = 0.0;
            this.subTotalF =7.699;
        }
    }

    public void actualizarSubtotal() {
        this.subtotal = 0.0;
        this.subTotalF = 7.699;
        for (ProductoCarrito p : productosCarrito) {
            this.subtotal += p.getPrecio() * p.getUnidades();
            this.subtotal = Math.round(subtotal*1000.0)/1000.0;

            this.subTotalF += p.getPrecio() * p.getUnidades();
            this.subTotalF = Math.round(subTotalF*1000.0)/1000.0;
        }
    }

    public void comprar() {
        if (persona!= null && !productosCarrito.isEmpty()) {
            try {
                if (mediosPago.contains(medioPago)) {
                    compraServicio.agregarCompra(productosCarrito, (Usuario) persona, medioPago);
                    productosCarrito.clear();
                    this.subtotal = 0.0;
                    this.subtotal = Math.round(subtotal*1000.0)/1000.0;

                    this.subTotalF = 7.699;
                    this.subTotalF = Math.round(subTotalF*1000.0)/1000.0;

                } else{
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, "Debe seleccionar un medio de pago para efectuar la compra");
                    FacesContext.getCurrentInstance().addMessage(MENSAJECOMPRA, fm);
                }
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, "La compra no se ha podido efectuar correctamente: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(MENSAJECOMPRA, fm);
            }
        }
    }

    public void registrarDireccion(){
        try {
            Usuario usuarioEncontrado = usuarioServicio.obtenerUsuarioNombre(usuario.getNombre());
            usuarioEncontrado.setCedula(usuario.getCedula());
            usuarioEncontrado.setDireccion(direccion);
            direccionServicio.registrarDireccion(direccion);
            usuarioServicio.registrarUsuario(usuarioEncontrado);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ALERTA, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(MENSAJEPERSONALIZADO, facesMsg);
        }
    }

    public String efectuarCompra(){
        registrarDireccion();
        comprar();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "La compra se ha registrado, te avisaremos cuando sea aprobada");
        FacesContext.getCurrentInstance().addMessage(MENSAJECOMPRA, fm);
        return "/usuario/carrito?faces-redirect=true";
    }

}