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
import sv.edu.bitlab.beans.DocenteFacade;
import sv.edu.bitlab.entidades.Docente;


/**
 *
 * @author Manuel
 */
@FacesConverter(forClass = Docente.class)
public class DocenteConvertidor implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {   
        DocenteFacade docenteFacade = new DocenteFacade();
        return docenteFacade.find(Integer.parseInt(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Docente)value).getDocId().toString();
    }
    
}
