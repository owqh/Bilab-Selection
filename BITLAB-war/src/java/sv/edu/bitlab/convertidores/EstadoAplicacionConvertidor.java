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
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.entidades.EstadoAplicacion;

/**
 *
 * @author carlosGodoy
 */
@FacesConverter (forClass = EstadoAplicacion.class) 
public class EstadoAplicacionConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       EstadoAplicacionFacade estadoAplicacionFacade = new EstadoAplicacionFacade();
       return estadoAplicacionFacade.find(Integer.parseInt(value));
       
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     return ((EstadoAplicacion)value).getEapId().toString();
    }
    
}
