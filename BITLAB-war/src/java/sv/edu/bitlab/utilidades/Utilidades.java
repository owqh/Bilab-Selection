/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.utilidades;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.managedbeans.UsuarioManaged;

/**
 *
 * @author Oscar
 */
@Named(value = "utilidades")
@ApplicationScoped
public class Utilidades {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UsuarioManaged.class);



    //Envia un mensaje personalizado
    public static void lanzarMensaje(FacesMessage.Severity severidad, String encabezado, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, encabezado, detalle));
    }

    //Envia un mensaje de informacion
    public static void lanzarInfo(String encabezado, String detalle) {
        lanzarMensaje(FacesMessage.SEVERITY_INFO, encabezado, detalle);
    }

    //Envia un mensaje de error
    public static void lanzarError(String encabezado, String detalle) {
        lanzarMensaje(FacesMessage.SEVERITY_ERROR, encabezado, detalle);
    }

    //Envia un mensaje de error
    public static void lanzarAdvertencia(String encabezado, String detalle) {
        lanzarMensaje(FacesMessage.SEVERITY_WARN, encabezado, detalle);
    }

    public static void redireccion(String pagina) {
        ExternalContext contex = FacesContext.getCurrentInstance().getExternalContext();
        try {
            contex.redirect(contex.getRequestContextPath() + "/" + pagina + ".bitlab");
        } catch (IOException e) {
            LOG.error("Error al hacer redireccion: " + e.getMessage());
        }
    }

    public static void redireccionLogin() {
        ExternalContext contex = FacesContext.getCurrentInstance().getExternalContext();
        try {
            contex.redirect(contex.getRequestContextPath() + "/login.bitlab");
        } catch (IOException e) {
            LOG.error("Error al hacer redireccion: " + e.getMessage());
        }
    }

    public static Object getBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, beanName, Object.class);
    }

}
