package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    private final ProductoServicio productoServicio;

    @Getter
    @Setter
    private List<Producto> productosDestacados;

    @Getter
    @Setter
    private List<Producto> productos;

    @Getter
    @Setter
    private List<Producto> productosMenorPrecio;

    @Getter
    @Setter
    private List<Producto> productosMayorPrecio;

    public InicioBean(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.productosDestacados = productoServicio.listarProductosDestacados();
        this.productos = productoServicio.listarProductos();
        this.productosMenorPrecio = productoServicio.listarProductosPorMenorPrecio();
        this.productosMayorPrecio = productoServicio.listarProductosPorMayorPrecio();
    }

    public String irADetalle(Integer id){
      return  "/detalleProducto?faces-redirect=true&amp;producto="+id;
    }

}
