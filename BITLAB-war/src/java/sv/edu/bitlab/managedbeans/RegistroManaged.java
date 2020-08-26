/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.beans.GeneralidadesFacade;
import sv.edu.bitlab.beans.OcupacionFacade;
import sv.edu.bitlab.beans.SexoFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.Sexo;

/**
 *
 * @author Oscar
 */
@Named(value = "registroManaged")
@Dependent
public class RegistroManaged {

    @EJB
    private EstadoAplicacionFacade estadoAplicacionFacade;

    @EJB
    private OcupacionFacade ocupacionFacade;

    @EJB
    private SexoFacade sexoFacade;

    @EJB
    private GeneralidadesFacade generalidadesFacade;

    @EJB
    private CandidatoFacade candidatoFacade;    
    
    //Pner variables del tipo de cada entidad
    private Sexo sexo;
    //...
    private Candidato candidato;
    
    //Poner variables para insertar
    private String nombre;
    
    
    public RegistroManaged() {
    }
 
    
    
    public void guardarRegistro(){
        //Encotrar todos los id foraneos
        //sexo= sexoFacade.find(id) 
        
        //preparar insert a la base
        //candidato = new Candidato(Integer.SIZE, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, canFechaNac, nombre, nombre, eapId, genId, idiId, nacId, ocuId, sexo)
        
        candidatoFacade.create(candidato);
        
                
                }
    
}
