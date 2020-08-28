/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.entidades.Candidato;

/**
 *
 * @author Mario
 */
@Named(value = "perfilManaged")
@ViewScoped
public class PerfilManaged implements Serializable{
    /**
     * Creates a new instance of PerfilManaged
     */

    @EJB
    private CandidatoFacade candidatoFacade;

    @Inject
    UsuarioManaged usuarioManaged;
    
    private Candidato perfilUsuario;
    
    public PerfilManaged() {
    }
    
    @PostConstruct
    public void inicializar(){
//        perfilUsuario = candidatoFacade.candidatoPorEmail(usuarioManaged.getUsr().getUsrAcceso());
    }
    
}
