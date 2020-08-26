/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.beans.ConfiguracionFacade;
import sv.edu.bitlab.beans.UsuarioFacade;
import sv.edu.bitlab.entidades.Configuracion;
import sv.edu.bitlab.entidades.Usuario;
import sv.edu.bitlab.utilidades.EncriptacionTexto;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Oscar
 */
@Named(value = "usuarioManaged")
@SessionScoped
public class UsuarioManaged implements Serializable {

    @EJB
    private ConfiguracionFacade configuracionFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    private String usuario;
    private String codigoRecibido;
    private String codigoGenerado;
    private String contrasena;
    private String rol;
    private String nombre;
    private EncriptacionTexto encriptacionTexto;
    private Usuario usr;
    private String mensajeCorreo;
    private String asunto;
    List<Configuracion> datos;
    private String contrasenaGenerada;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UsuarioFacade.class);

    @PostConstruct
    public void inicializar() {

    }

    //Funcion para validar si el usuario existe en la base de datos
    public void validarUsuario() {
        try {
            //Obteniendo el usuario de la base de datos
            usr = usuarioFacade.ObtenerUsuario(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Encriptando Textos de la base 
        encriptacionTexto = new EncriptacionTexto();
        if (usr != null) {
            //Verificando la contraseña 
            if (contrasena.equals(encriptacionTexto.getTextoDesencriptado(usr.getUsrContrasena()))) {
                try {
                    //Preparando codigo de doble factor de autenticacion
                    codigoGenerado = codigoAcceso();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
                }
                Utilidades.redireccion("validacion");
                //Preparando mensaje para enviar correo electronico
                mensajeCorreo = "<h1>Gracias por utilizar nuestro sistema " + usr.getUsrNombre() + " " + usr.getUsrApellido() + "\n </h1> "
                        + "<p>Hay un intento de inicio de sesión a su ceunta de BITLAB</p>"
                        + "<h3>Su codigo de acceso a la plataforma es: <strong>" + codigoGenerado + "</strong></h3> \n "
                        + "<p>Ha recibido este e-mail por que tiene una cuenta registrada en el sistema de seleccion de becarios BITLAB</p>"
                        + "<p><b>Si usted no solicito este acceso por favor ignore este correo.</b></p>"
                        + "<h4>Por su seguirdad nunca comparta este correo electronico con nadie.</h4>"
                        + "<p><strong>Copyright &copy; 2020 <a href=\"https://bitlab.edu.sv/\">BITLAB</a>.</strong>\n"
                        + " All rights reserved.</p>";
                asunto = "Incio de sesion Bitlab, autenticacion de dos pasos";
                //Funcion para enviar el correo electronico 
                enviarCorreo(usr.getUsrAcceso(), mensajeCorreo, asunto);
            } else {
                Utilidades.lanzarAdvertencia("Usaurio invalido", "El usuario o la contraseña son invalidos");
            }
        } else {
            Utilidades.lanzarAdvertencia("Usaurio invalido", "El usuario o la contraseña son invalidos");
        }
    }

    //Verficia el codigo del doble factor de autenticacion y cierra la sesion si es erroneo
    public void doblefactor() {
        LOG.debug("Validando el codigo de seguridad");
        try {
            usr = usuarioFacade.ObtenerUsuario(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (codigoGenerado.equalsIgnoreCase(codigoRecibido)) {
            //Asignando rol a usaurio (obtenido desde la base)
            rol = usr.getTipId().getTipNombre();
            LOG.debug("Asignandole rol al usuario: " + usr.getUsrAcceso());
            String nombreCompleto = usr.getUsrNombre() + " " + usr.getUsrApellido();
            nombre = nombreCompleto;
            Utilidades.redireccion("index");
        } else {
            Utilidades.lanzarAdvertencia("Codigo invalido", "Por favor ingrese el codigo valido");
            PrimeFaces.current().resetInputs("validar");
            LOG.warn("ERROR, Codigo de acceso incorrecto");
        }
    }

    private String codigoAcceso() throws NoSuchAlgorithmException {
        LOG.debug("Preparando cadena de seguridad");
        //Variables para Generar el ID de Forma Aleatoria
        Random aleatorio = SecureRandom.getInstanceStrong();
        final String CODIGO_TOKEN = "AB7CDE0FG9HIJK2LMN6OPQRST8UVW4XY5Z163@#%+*";
        final byte NIVEL_SEGURIDAD = 7; //cantidad de caracteres para el codigo
        String cadena = ""; //Cadena de seguridad

        //Preparando la cadena
        for (int i = 1; i < NIVEL_SEGURIDAD; i++) {
            //Método para el Cálculo de las letras
            byte caracter = (byte) (aleatorio.nextDouble() * CODIGO_TOKEN.length() - 1 + 0);
            cadena = cadena + CODIGO_TOKEN.charAt(caracter);
        }
        return cadena;

    }

    //funicon para enviar correo electronico con un mensajepreviamente establecido
    private void enviarCorreo(String correo, String mensaje, String asunto) {
        //Encriptando Textos de la base 
        encriptacionTexto = new EncriptacionTexto();
        //Obteniendo la configuracion de la base de datos
        datos = configuracionFacade.findAll();
        //Combirtiendo la lista a tipo configuracion
        Configuracion con = datos.get(0);
        LOG.debug("Preparando envio de correo electronico de seguridad");
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
            LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO.  " + ex.getMessage());
        }
    }

    //Valida si el usuario tiene sesion o no 
    public void validarSesion() {
        //Si el usuario no tiene rol, redireccionamos al login 
        if (rol == null || "".equals(rol)) {
            Utilidades.redireccionLogin();
        }
    }

    //clase para cerrar sesion
    public void cerrarSesion() {
        rol = null;
        Utilidades.redireccionLogin();
    }

    //Este metodo no funciona correctamente aun!!!!
    public void recuperarContraseña() {
        try {
            //Obteniendo el usuario de la base de datos
            usr = usuarioFacade.ObtenerUsuario(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usr != null) {

            try {
                //Preparando contraseña autogenerada
                contrasenaGenerada = codigoAcceso();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Preparando mensaje para enviar correo electronico
            mensajeCorreo = "<h1>Hola, " + usr.getUsrNombre() + " " + usr.getUsrApellido() + "¿Olvidaste tu contraseña? " + "\n </h1> "
                    + "<p></p>"
                    + "<h3>Su codigo de acceso a la plataforma es: <strong>" + codigoGenerado + "</strong></h3> \n "
                    + "<p>Ha recibido este e-mail por que tiene una cuenta registrada en el sistema de seleccion de becarios BITLAB</p>"
                    + "<p><b>Si usted no solicito este acceso por favor ignore este correo.</p>"
                    + "<h4>Por su seguirdad nunca comparta este correo electronico con nadie.</b></h4>"
                    + "<p><strong>Copyright &copy; 2020 <a href=\"https://bitlab.edu.sv/\">BITLAB</a>.</strong>\n"
                    + " All rights reserved.</p>";

            asunto = "Recuperacion de contraseña Bitlab";
            Utilidades.lanzarInfo("Solicitud enviada", "Esta funcion esta en mantenimiento");
            Utilidades.redireccionLogin();

            //Funcion para enviar el correo electronico 
            //enviarCorreo(usr.getUsrAcceso(), mensajeCorreo, asunto);
        } else {
            Utilidades.lanzarAdvertencia("Usaurio invalido", "El usuario no es valido, si no tienes una cuenta puedes crearla en la opcion registro!");
        }

    }

    //Clase para el cambio de contraseña 
    public void cambioContraseña() {

    }

    public ConfiguracionFacade getConfiguracionFacade() {
        return configuracionFacade;
    }

    public void setConfiguracionFacade(ConfiguracionFacade configuracionFacade) {
        this.configuracionFacade = configuracionFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigoRecibido() {
        return codigoRecibido;
    }

    public void setCodigoRecibido(String codigoRecibido) {
        this.codigoRecibido = codigoRecibido;
    }

    public String getCodigoGenerado() {
        return codigoGenerado;
    }

    public void setCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EncriptacionTexto getEncriptacionTexto() {
        return encriptacionTexto;
    }

    public void setEncriptacionTexto(EncriptacionTexto encriptacionTexto) {
        this.encriptacionTexto = encriptacionTexto;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public List<Configuracion> getDatos() {
        return datos;
    }

    public void setDatos(List<Configuracion> datos) {
        this.datos = datos;
    }

}
