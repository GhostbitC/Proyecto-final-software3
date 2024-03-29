package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {

    private final CiudadServicio ciudadServicio;

    public CiudadConverter(CiudadServicio ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    @Override
    public Ciudad getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s!=null && !"".equals(s)){

                int id = Integer.parseInt(s);
                return ciudadServicio.obtenerCiudad(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Ciudad ciudad) {

        if (ciudad!=null){
            return ""+ciudad.getId();
        }
        return "";
    }
}
