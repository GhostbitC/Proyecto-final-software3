package co.edu.uniquindio.proyecto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class TipoConverter implements Converter<TipoMascota>, Serializable {

    @Autowired
    private TipoServicio tipoServicio;

    @Override
    public TipoMascota getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try{
            if (s!=null && !"".equals(s)){

                int id= Integer.parseInt(s);
                return tipoServicio.obtenerTipo(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, TipoMascota tipo) {
        if (tipo!= null){
            return ""+tipo.getId();
        }
        return "";
    }

}
