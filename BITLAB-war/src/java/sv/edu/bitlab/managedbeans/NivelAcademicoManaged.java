/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sv.edu.bitlab.beans.NivelAcademicoFacade;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "nivelAcademicoManaged")
@SessionScoped
public class NivelAcademicoManaged implements Serializable {
    
    private static final Logger log = Logger.getLogger(NivelAcademicoManaged.class.getName());
    
    private NivelAcademico nivelAcademico;
    private List<NivelAcademico> nivelAcadList;

    @EJB
    private NivelAcademicoFacade nivelAcademicoFacade;

    
    @PostConstruct
    public void cargarInfo(){
        nivelAcademico = new NivelAcademico();
        nivelAcadList = nivelAcademicoFacade.findAll();
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException, InvocationTargetException{
        try {
            nivelAcademico = NivelAcademico.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(NivelAcademicoManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        try {
            nivelAcademicoFacade.remove(nivelAcademico);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", NivelAcademico.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(NivelAcademicoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }

    
    public void guardarEntidad(){
        try {
            nivelAcademicoFacade.edit(nivelAcademico);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", NivelAcademicoManaged.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(NivelAcademicoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }

    public NivelAcademico getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(NivelAcademico nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public List<NivelAcademico> getNivelAcadList() {
        return nivelAcadList;
    }

    public void setNivelAcadList(List<NivelAcademico> nivelAcadList) {
        this.nivelAcadList = nivelAcadList;
    }


    
}
