/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.convertidores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.entidades.Candidato;


/**
 *
 * @author carlosGodoy
 */
@FacesConverter (forClass = Candidato.class) 
public class CandidatoConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CandidatoFacade candidatoFacede = new CandidatoFacade();
        return candidatoFacede.find(Integer.parseInt(value));
       
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Candidato)value).getCanCodigo().toString();
    }

   
    
}
