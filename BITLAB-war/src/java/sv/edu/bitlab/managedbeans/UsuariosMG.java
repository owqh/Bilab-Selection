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
import sv.edu.bitlab.beans.TipoUsuarioFacade;
import sv.edu.bitlab.beans.UsuarioFacade;
import sv.edu.bitlab.entidades.TipoUsuario;
import sv.edu.bitlab.entidades.Usuario;
import sv.edu.bitlab.utilidades.EncriptacionTexto;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Manuel
 */
@Named(value = "usuariosMG")
@SessionScoped
public class UsuariosMG implements Serializable {
    private static final Logger log = Logger.getLogger(UsuariosMG.class.getName());
    
    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuario;
    private List<Usuario> usuarioList = null;
    private String contrasena;
    private List<TipoUsuario> tipoUsuarioslist;
     
    
    @PostConstruct
    public void cargarInfo() {
        usuario = new Usuario();
        usuarioList = usuarioFacade.findAll();
        tipoUsuarioslist = tipoUsuarioFacade.findAll();
    }
    
    public void nuevaEntidad() throws NoSuchMethodException{
        try {
            usuario = Usuario.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(UsuariosMG.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Entidad creada exitosamente!!");
    }
    
    
    private void guardarEntity(){
        try {
            usuarioFacade.edit(usuario);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Usuario.class.getSimpleName() + " ha sido guardado");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosMG.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al guardar ", ex.getMessage());
        }
        log.info("Entidad editada y guardada");
    }
    
    
    public void eliminarEntidad(){
        try {
            usuarioFacade.remove(usuario);
            cargarInfo();
            Utilidades.lanzarInfo("Exitoso ", Usuario.class.getSimpleName() + " ha sido eliminado");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosMG.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.lanzarError("Error al eliminar ", ex.getMessage());
        }
        log.info("Entidad eliminada");
    }
    
    
    //encriptando la contrasena de usuarios hacia la base de datos
    public void guardarEntidad() {
        if(getUsuario().getUsrContrasena() == null || !getUsuario().getUsrContrasena().equals(contrasena)){
            EncriptacionTexto encriptacionTexto = new  EncriptacionTexto();
            getUsuario().setUsrContrasena(encriptacionTexto.getTextoEncriptado(contrasena));
        }
        guardarEntity();
    }
    
    public void completarInformacion(Usuario usr){
        setUsuario(usr);
        this.contrasena = getUsuario().getUsrContrasena();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TipoUsuario> getTipoUsuarioslist() {
        return tipoUsuarioslist;
    }

    public void setTipoUsuarioslist(List<TipoUsuario> tipoUsuarioslist) {
        this.tipoUsuarioslist = tipoUsuarioslist;
    }
    
}
