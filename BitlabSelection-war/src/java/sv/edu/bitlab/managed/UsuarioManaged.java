/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managed;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.edu.bitlab.bean.UsuarioFacade;
import sv.edu.bitlab.entidades.Usuario;

/**
 *
 * @author Oscar
 */
@Named(value = "usuario")
@ViewScoped
public class UsuarioManaged implements Serializable  {

    @EJB
    private UsuarioFacade usuarioFacade;
    private List<Usuario> listaUsuario;
    
    @PostConstruct
    public void Cargar(){
        listaUsuario = usuarioFacade.findAll();
    }
    
    
    public UsuarioManaged() {
    }
    
}
