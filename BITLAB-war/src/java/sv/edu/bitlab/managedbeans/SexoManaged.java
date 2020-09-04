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
import sv.edu.bitlab.beans.SexoFacade;
import sv.edu.bitlab.entidades.Sexo;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "sexoManaged")
@SessionScoped
public class SexoManaged implements Serializable {
    private static final Logger log = Logger.getLogger(CursoManaged.class.getName());
    
    private List<Sexo> sexoList = null;
    private Sexo sexo;
    
    @EJB
    private SexoFacade sexoFacade;

    
    @PostConstruct
    public void cargarInfo(){
        sexo = new Sexo();
        sexoList = sexoFacade.findAll();
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException{
        try {
            sexo = Sexo.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(SexoManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        try {
            sexoFacade.remove(sexo);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Sexo.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(SexoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }

    
    public void guardarEntidad(){
        try {
            sexoFacade.edit(sexo);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Sexo.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(SexoManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }
    
    public List<Sexo> getSexoList() {
        return sexoList;
    }

    public void setSexoList(List<Sexo> sexoList) {
        this.sexoList = sexoList;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public SexoFacade getSexoFacade() {
        return sexoFacade;
    }

    public void setSexoFacade(SexoFacade sexoFacade) {
        this.sexoFacade = sexoFacade;
    }
    
    
    
}
