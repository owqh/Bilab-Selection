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
import sv.edu.bitlab.beans.CursoFacade;
import sv.edu.bitlab.beans.DocenteFacade;
import sv.edu.bitlab.entidades.Curso;
import sv.edu.bitlab.entidades.Docente;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "cursoManaged")
@SessionScoped
public class CursoManaged implements Serializable {
    
    private static final Logger log = Logger.getLogger(CursoManaged.class.getName());
    
    private Curso cursoObj;
    private List<Curso> cursoList;
    private List<Docente> docentesList;
    
    private Docente docId;
    
    @EJB
    private DocenteFacade docenteFacade;
    
    @EJB
    private CursoFacade cursoFacade;
    
    @PostConstruct
    public void encontrarEntidades(){
        docentesList = docenteFacade.findAll();
        cursoList = cursoFacade.findAll();
        log.info("Lista de entidades cargada");
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException{
        log.info("Creando nueva entidad...");
        try {
            cursoObj = Curso.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CursoManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        log.info("Eliminando entidad...");
        try {
            cursoFacade.remove(cursoObj);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Curso.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(CursoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }
    
    
    public void guardarEntidad(){
        log.info("Editando entidad...");
        try {
            cursoFacade.edit(cursoObj);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Curso.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(CursoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }
    
    
    
    /**
     * Creates a new instance of CursoManaged
     */
    public CursoManaged() {
    }

    public Curso getCursoObj() {
        return cursoObj;
    }

    public void setCursoObj(Curso cursoObj) {
        this.cursoObj = cursoObj;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public List<Docente> getDocentesList() {
        return docentesList;
    }

    public void setDocentesList(List<Docente> docentesList) {
        this.docentesList = docentesList;
    }

    public Docente getDocId() {
        return docId;
    }

    public void setDocId(Docente docId) {
        this.docId = docId;
    }

    public DocenteFacade getDocenteFacade() {
        return docenteFacade;
    }

    public void setDocenteFacade(DocenteFacade docenteFacade) {
        this.docenteFacade = docenteFacade;
    }

    public CursoFacade getCursoFacade() {
        return cursoFacade;
    }

    public void setCursoFacade(CursoFacade cursoFacade) {
        this.cursoFacade = cursoFacade;
    }
    
    
    
}