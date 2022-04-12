package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private Persona personaAux;

    @Getter @Setter
    private  boolean autenticado;

    @Autowired
    private PersonaServicio personaServicio;

    @Getter @Setter
    @NotBlank
    private String email,emailR,password;

    @Getter @Setter
    private String rol;

    @Getter
    @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter
    @Setter
    private List<Compra> listaMisCompras;

    @Getter
    @Setter
    private Float subtotal;

    @Autowired
    private CompraServicio compraServicio;

    @Getter
    @Setter
    private String medioPago;

    @Getter
    @Setter
    private ArrayList<String> mediosPago;

    @PostConstruct
    public void inicializar() {
        autenticado = false;
        subtotal = 0F;
        this.productosCarrito = new ArrayList<>();
        this.mediosPago = new ArrayList<>();
        mediosPago.add("Tarjeta de crédito");
        mediosPago.add("Tarjeta débito");
        mediosPago.add("Pago contraentrega");
        mediosPago.add("PSE");
        listaMisCompras = new ArrayList<Compra>();
    }

    public String iniciarSesion(){

        if (email!=null && password!=null) {
            try {
                persona = personaServicio.login(email,password);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! ingreso correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                if (persona instanceof Usuario){
                    rol="usuario";
                }else if (persona instanceof Administrador){
                    rol="admin";
                }

                autenticado=true;
               return "/general?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
        return null;
    }

    public String cerrarSesion(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/general?faces-redirect=true";
    }

    public List<Compra> listarComprasUsuario() {
        System.out.println(persona.getId());
        if (persona != null) {
            listaMisCompras = compraServicio.listarComprasUsuario(String.valueOf(persona.getId()));
            return listaMisCompras;
        }
        return null;
    }

    public Double calcularSubTotal(int indice) {

        listaMisCompras = compraServicio.listarComprasUsuario(String.valueOf(persona.getId()));
        Double total = 0.0;

        for (int i = 0; i < listaMisCompras.get(indice).getListaDetallesCompra().size(); i++) {
            total = listaMisCompras.get(indice).getListaDetallesCompra().get(i).getUnidades() * listaMisCompras.get(indice).getListaDetallesCompra().get(i).getProducto().getPrecio();
        }
        return total;
    }

    public void agregarAlCarrito(Integer codigo, Float precio, String nombre, String imagen) {
        ProductoCarrito pc = new ProductoCarrito(codigo, nombre, imagen, 1, precio);
        if (!productosCarrito.contains(pc)) {
            System.out.print("Hola2");
            System.out.println(pc.toString());
            productosCarrito.add(pc);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "El producto se ha agregado a carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice) {
        subtotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }

    public void actualizarSubtotal() {
        subtotal = 0F;
        for (ProductoCarrito p : productosCarrito) {
            subtotal += p.getPrecio() * p.getUnidades();
        }
    }

    public String comprar() {
        if (persona!= null && !productosCarrito.isEmpty()) {
            try {
                if (mediosPago.contains(medioPago)) {
                    Compra compraRealizada = compraServicio.agregarCompra(productosCarrito, (Usuario) persona, medioPago);
                    productosCarrito.clear();
                    subtotal = 0F;
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "La compra se ha efectuado correctamente, revisa tu correo para visualizar tu compra");
                    FacesContext.getCurrentInstance().addMessage("msj-compra", fm);
                    return "/usuario/carrito?faces-redirect=true";
                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Debe seleccionar un medio de pago para efectuar la compra");
                    FacesContext.getCurrentInstance().addMessage("msj-compra", fm);
                }
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "La compra no se ha podido efectuar correctamente: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-compra", fm);
                return null;
            }
        }
        return null;
    }




}
