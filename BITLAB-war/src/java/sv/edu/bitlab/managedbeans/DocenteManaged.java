/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sv.edu.bitlab.beans.DocenteFacade;
import sv.edu.bitlab.entidades.Docente;


/**
 *
 * @author Manuel
 */
@Named(value = "docenteManaged")
@SessionScoped
public class DocenteManaged implements Serializable {
    private static final Logger log = Logger.getLogger(DocenteManaged.class.getName());
    
    private Docente entidadSeleccion = new Docente();
    private List<Docente> docenteList;
    
    @EJB
    private DocenteFacade docenteFacade = new DocenteFacade();
    
    public DocenteManaged() {
    }
    
    @PostConstruct
    public void encontrarEntidades(){
        docenteList = docenteFacade.findAll();
        log.info("Entidades cargadas en @constructor");
    }
    
    
    public void nuevaEntidad(){
        log.info("Creando nueva entidad...");
        try {
            docenteFacade.create(entidadSeleccion);
            encontrarEntidades();
            UtilidadesManejador.lanzarInfo("Exitoso ", Docente.class.getSimpleName() + " ha sido creado");
        } catch (Exception ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesManejador.lanzarError("Error al crear ", ex.getMessage());
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        log.info("Eliminando docente...");
        try {
            docenteFacade.remove(entidadSeleccion);
            encontrarEntidades();
            UtilidadesManejador.lanzarInfo("Exitoso ", Docente.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesManejador.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }
    
    
    public void guardarEntidad(){
        log.info("Editando entidad...");
        try {
            docenteFacade.edit(entidadSeleccion);
            encontrarEntidades();
            UtilidadesManejador.lanzarInfo("Exitoso ", Docente.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
            UtilidadesManejador.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }
    
    
    
    public List<Docente> getDocenteList() {
        return docenteList;
    }
    
    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }
    
    public Docente getEntidadSeleccion() {
        return entidadSeleccion;
    }
    
    public void setEntidadSeleccion(Docente entidadSeleccion) {
        this.entidadSeleccion = entidadSeleccion;
    }
    
    public DocenteFacade getDocenteFacade() {
        return docenteFacade;
    }
    
    public void setDocenteFacade(DocenteFacade docenteFacade) {
        this.docenteFacade = docenteFacade;
    }
    
}
