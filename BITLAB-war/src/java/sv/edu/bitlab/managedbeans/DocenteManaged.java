/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sv.edu.bitlab.beans.DocenteFacade;
import sv.edu.bitlab.entidades.Docente;
import sv.edu.bitlab.utilidades.Utilidades;


/**
 *
 * @author Manuel
 */
@Named(value = "docenteManaged")
@SessionScoped
public class DocenteManaged implements Serializable {
    private static final Logger log = Logger.getLogger(DocenteManaged.class.getName());
    
    private Docente entidadSeleccion;
    private List<Docente> docenteList;
    private List<String> estadoDocList;
    
    @EJB
    private DocenteFacade docenteFacade;
    
    public DocenteManaged() {
    }
    
    @PostConstruct
    private void encontrarEntidades(){
        entidadSeleccion = new Docente();
        docenteList = docenteFacade.findAll();
        
        estadoDocList = new ArrayList<>();
        estadoDocList.add("A");
        estadoDocList.add("I");
        log.info("Lista de entidades cargada");
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException{
        log.info("Creando nueva entidad...");
        try {
            entidadSeleccion = Docente.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        log.info("Eliminando docente...");
        try {
            docenteFacade.remove(entidadSeleccion);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Docente.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }
    
    
    public void guardarEntidad(){
        log.info("Editando entidad...");
        try {
            docenteFacade.edit(entidadSeleccion);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Docente.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(DocenteManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
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

    public List<String> getEstadoDocList() {
        return estadoDocList;
    }

    public void setEstadoDocList(List<String> estadoDocList) {
        this.estadoDocList = estadoDocList;
    }
    
}
