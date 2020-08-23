/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.utilidades;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.bean.ConfiguracionFacade;
import sv.edu.bitlab.entidades.Configuracion;
import sv.edu.bitlab.managed.UsuarioManaged;

/**
 *
 * @author Oscar
 */
@Named(value = "utilidades")
@ApplicationScoped
public class Utilidades {

    @EJB
    private ConfiguracionFacade configuracionFacade;
    private final EncriptacionTexto encriptacionTexto = new EncriptacionTexto();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UsuarioManaged.class);
    //Obteniendo la configuracion de la base de datos
    List<Configuracion> datos;
        
        
    public Utilidades() {
    }
    
    @PostConstruct
    public void Cargar(){
        //Cargando la configuracion de la base de datos
        datos = configuracionFacade.findAll();
    }
    
    
     //Envia un mensaje personalizado
    public static void lanzarMensaje(FacesMessage.Severity severidad, String encabezado, String detalle){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, encabezado, detalle));
    }
    
    //Envia un mensaje de informacion
    public static void lanzarInfo(String encabezado, String detalle){
        lanzarMensaje(FacesMessage.SEVERITY_INFO, encabezado, detalle);
    }
    
    //Envia un mensaje de error
    public static void lanzarError(String encabezado, String detalle){
        lanzarMensaje(FacesMessage.SEVERITY_ERROR, encabezado, detalle);
    }
    
    //Envia un mensaje de error
    public static void lanzarAdvertencia(String encabezado, String detalle){
        lanzarMensaje(FacesMessage.SEVERITY_WARN, encabezado, detalle);
    }
    
    public static void redireccion(String pagina){
        ExternalContext contex = FacesContext.getCurrentInstance().getExternalContext();
        try {
            contex.redirect(contex.getRequestContextPath()+"/pages/"+pagina+".bitlab");
        } catch (IOException e) {
            Logger.getLogger(UsuarioManaged.class.getName());
        }
    }
    
    public static void redireccionLogin(){
        ExternalContext contex = FacesContext.getCurrentInstance().getExternalContext();
            try {
                contex.redirect(contex.getRequestContextPath() + "/login.bitlab");
            } catch (IOException e) {
                Logger.getLogger(UsuarioManaged.class.getName());
            }
    }
    
    //Funcion para enviar un correo electronico
    public void enviarCorreo(String asunto, String mensaje, String correo){
        //Combirtiendo la lista a tipo configuracion
        Configuracion con = datos.get(0);
        LOG.debug("Preparando envio de correo electronico");
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(encriptacionTexto.getTextoDesencriptado(con.getConServer()));
            email.setSmtpPort(Integer.parseInt(encriptacionTexto.getTextoDesencriptado(con.getConPort())));
            email.setAuthenticator(new DefaultAuthenticator(
                    encriptacionTexto.getTextoDesencriptado(con.getConCorreo()),
                    encriptacionTexto.getTextoDesencriptado(con.getConPcorreo())));
            email.setSSLOnConnect(true);
            email.setFrom(encriptacionTexto.getTextoDesencriptado(con.getConCorreo()));
            email.setSubject(asunto);
            email.setHtmlMsg(mensaje);
            email.addTo(correo);
            LOG.debug("Enviando correo electronico");
            email.send();
        } catch (EmailException ex) {
            LOG.debug("Error al enviar el correo a "+correo +"error generado: "+ex.getMessage() );
        }
    }
    
}
