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
import sv.edu.bitlab.beans.HistorialAplicacionFacade;
import sv.edu.bitlab.entidades.HistorialAplicacion;

/**
 *
 * @author carlosGodoy
 */
@FacesConverter (forClass = HistorialAplicacion.class) 
public class HistorialAplicacionConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       HistorialAplicacionFacade historialAplicacionFacade = new HistorialAplicacionFacade();
       return historialAplicacionFacade.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     return ((HistorialAplicacion)value).getHapId().toString();
    }
    
}
