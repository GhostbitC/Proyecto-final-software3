package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface CompraServicio {

    Compra crearCompra(Usuario usuario) throws Exception;

    Compra agregarDetalleCompra(Compra compra, DetalleCompra detalle);

    Compra efectuarCompra(Compra compra) throws Exception;

    Compra agregarCompra(ArrayList<ProductoCarrito> productoCarrito, Usuario usuario, String medioPago) throws Exception;

    List<Compra> listarComprasUsuario (String idUsuario);

}
