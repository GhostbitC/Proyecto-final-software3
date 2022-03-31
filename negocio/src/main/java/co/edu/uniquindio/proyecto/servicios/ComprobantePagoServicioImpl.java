package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.ComprobantePago;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ComprobantePagoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprobantePagoServicioImpl implements ComprobantePagoServicio{

    private final ComprobantePagoRepo comprobanteRepo;
    private final CompraRepo compraRepo;

    public ComprobantePagoServicioImpl(ComprobantePagoRepo comprobanteRepo, CompraRepo compraRepo) {
        this.comprobanteRepo = comprobanteRepo;
        this.compraRepo = compraRepo;
    }

    @Override
    public ComprobantePago registrarComprobante(ComprobantePago com) throws Exception {

        if (com.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return comprobanteRepo.save(com);
    }

    @Override
    public ComprobantePago actualizarComprobante(ComprobantePago com) throws Exception {

        if (com.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return comprobanteRepo.save(com);
    }

    @Override
    public void eliminarComprobante(int id) throws Exception {

        ComprobantePago comprobanteEncontrado = obtenerComprobante(id);

        if(comprobanteEncontrado != null){
            comprobanteRepo.delete(comprobanteEncontrado);
        }else {
            throw new Exception("No se encontro la imagen");
        }
    }

    @Override
    public ComprobantePago obtenerComprobante(int id) throws Exception {

        Optional<ComprobantePago> comprobanteEncontrado = comprobanteRepo.findById(id);

        if(comprobanteEncontrado.isEmpty()){
            throw  new Exception("No se encontro el comprobante de pago");
        }
        return comprobanteEncontrado.get();
    }

    @Override
    public ComprobantePago obtenerImagenComprobanteCompra(int idCompra) throws Exception {

        ComprobantePago comprobante = compraRepo.obtenerComprobante(idCompra);

        if(comprobante == null){
            throw new Exception("No se encontro el comprobante de pago de la compra");
        }

        return comprobante;
    }

    @Override
    public List<ComprobantePago> listarComprobantes() {
        return comprobanteRepo.findAll();
    }

}
