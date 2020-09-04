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
import sv.edu.bitlab.beans.IdiomaFacade;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "idiomaManaged")
@SessionScoped
public class IdiomaManaged implements Serializable {
    
    private static final Logger log = Logger.getLogger(IdiomaManaged.class.getName());
    
    @EJB
    private IdiomaFacade idiomaFacade;
    
    private Idioma idioma;
    private List<Idioma> idiomaLista;

    
    @PostConstruct
    private void encontrarEntidades(){
        idioma = new Idioma();
        idiomaLista = idiomaFacade.findAll();
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException{
        try {
            idioma = Idioma.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(IdiomaManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
     
    public void eliminarEntidad(){
        try {
            idiomaFacade.remove(idioma);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Idioma.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(IdiomaManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }
    
    
    public void guardarEntidad(){
        try {
            idiomaFacade.edit(idioma);
            encontrarEntidades();
            Utilidades.lanzarInfo("Exitoso ", Idioma.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(IdiomaManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public List<Idioma> getIdiomaLista() {
        return idiomaLista;
    }

    public void setIdiomaLista(List<Idioma> idiomaLista) {
        this.idiomaLista = idiomaLista;
    }
    

}
