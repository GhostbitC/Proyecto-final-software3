package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaProductoServicio;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categoria>, Serializable {

    private final CategoriaProductoServicio categoriaServicio;

    public CategoriaConverter(CategoriaProductoServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }


    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            if (s!=null && !"".equals(s)){

                int id = Integer.parseInt(s);
                return categoriaServicio.obtenerCategoria(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {

        if (categoria!=null){
            return ""+categoria.getId();
        }
        return "";
    }
}
