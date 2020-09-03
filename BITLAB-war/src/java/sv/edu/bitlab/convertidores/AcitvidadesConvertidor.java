/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.convertidores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sv.edu.bitlab.entidades.Actividades;
import sv.edu.bitlab.managedbeans.ConvetidorManaged;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Oscar
 */
@FacesConverter(forClass = Actividades.class)
public class AcitvidadesConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       return ((ConvetidorManaged)Utilidades.getBean("#{convetidorManaged}")).getActividadesId(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Actividades)value).getActId().toString();
    }
    
}
