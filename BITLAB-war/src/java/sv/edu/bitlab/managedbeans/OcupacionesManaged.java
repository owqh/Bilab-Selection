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
import sv.edu.bitlab.beans.OcupacionFacade;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "ocupacionesManaged")
@SessionScoped
public class OcupacionesManaged implements Serializable {
    private static final Logger log = Logger.getLogger(CursoManaged.class.getName());

    private Ocupacion ocupacion;
    private List<Ocupacion> ocupacionList;
    
    @EJB
    private OcupacionFacade ocupacionFacade;
    
    
    @PostConstruct
    public void cargarInfo(){
        ocupacion = new Ocupacion();
        ocupacionList = ocupacionFacade.findAll();
    }
    
    
    public void nuevaEntidad() throws NoSuchMethodException{
        try {
            ocupacion = Ocupacion.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(OcupacionesManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    public void eliminarEntidad(){
        try {
            ocupacionFacade.remove(ocupacion);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Ocupacion.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(OcupacionesManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }

    
    public void guardarEntidad(){
        try {
            ocupacionFacade.edit(ocupacion);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Ocupacion.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(OcupacionesManaged.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }
    
    
    

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public List<Ocupacion> getOcupacionList() {
        return ocupacionList;
    }

    public void setOcupacionList(List<Ocupacion> ocupacionList) {
        this.ocupacionList = ocupacionList;
    }


    
}
