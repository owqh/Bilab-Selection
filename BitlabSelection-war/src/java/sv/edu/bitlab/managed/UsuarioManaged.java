/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managed;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.bean.ConfiguracionFacade;
import sv.edu.bitlab.bean.UsuarioFacade;
import sv.edu.bitlab.entidades.Configuracion;
import sv.edu.bitlab.entidades.Usuario;
import sv.edu.bitlab.utilidades.EncriptacionTexto;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Oscar
 */
@Named(value = "usuario")
@ViewScoped
public class UsuarioManaged implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private ConfiguracionFacade configuracionFacade;
    private String usuario;
    private String codigoRecibido;
    private String codigoGenerado;
    private String contrasena;
    private Integer rol;
    private String nombre;

    private Usuario usr;
    List<Configuracion> datos;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UsuarioFacade.class);

    @PostConstruct
    public void inicializar() {
        usuarioFacade = new UsuarioFacade();
        
    }

    //Funcion para validar si el usuario existe en la base de datos
    public void validarUsuario() throws NoSuchAlgorithmException {
        //Encriptando Textos de la base 
        EncriptacionTexto encriptacionTexto = new EncriptacionTexto();
        //Obteniendo el usuario de la base de datos
        usr = usuarioFacade.ObtenerUsuario(usuario);
        if (usr != null) {

            //Verificando la contraseña 
            if (contrasena.equals(encriptacionTexto.getTextoDesencriptado(usr.getUsrContrasena()))) {
                //Preparando codigo de doble factor de autenticacion
                codigoGenerado = codigoAcceso();
                Utilidades.redireccion("validacion");
                //Funcion para enviar el correo electronico 
                enviarCorreo(usr.getUsrNombre(), usr.getUsrAcceso(), codigoGenerado);
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
        usr = usuarioFacade.ObtenerUsuario(usuario);
        if (codigoGenerado.equalsIgnoreCase(codigoRecibido)) {
            //Asignando rol a usaurio (obtenido desde la base)
            rol = usr.getTipId().getTipId();
            LOG.debug("Asignandole rol al usuario: " + usr.getUsrAcceso());
            String nombreCompleto = "Bienvenido/a " + usr.getUsrNombre() + " " + usr.getUsrApellido();
            nombre = nombreCompleto;
            Utilidades.redireccion("index");
        } else {
            Utilidades.lanzarAdvertencia("Codigo invalido", "Por favor ingrese el codigo valido");
            PrimeFaces.current().resetInputs("form");
            LOG.warn("ERROR, Codigo de acceso incorrecto");
        }
    }

    public String codigoAcceso() throws NoSuchAlgorithmException {
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

    //funicon para enviar correo electronico del codigo de seguridad previamente establecido
    public void enviarCorreo(String nombre, String correo, String codigo) {
        //Encriptando Textos de la base 
        EncriptacionTexto encriptacionTexto = new EncriptacionTexto();
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
            email.setSubject("Autenticacion de dos pasos");
            email.setHtmlMsg("<h1>Gracias por utilizar nuestros sistemas " + nombre + "\n </h1> "
                    + "<p>Hay un intento de inicio de sesión a su sistema de Human Resources</p>"
                    + "<h3>Su codigo de acceso a la plataforma es: <strong>" + codigo + "</strong></h3> \n "
                    + "<p><b>Si usted no solicito este acceso porfavor ignore este correo.</b></p>"
                    + "<p>Has recibido este e-mail porque tienes una cuenta registrada en Human Resources System</p>"
                    + "<h4>Por su seguirdad nunca comparta este correo electronico con nadie.</h4>"
                    + "<p>&copy; 2020 Human Resources System<p>");
            email.addTo(correo);
            LOG.debug("Enviando correo electronico");
            email.send();
        } catch (EmailException ex) {
            LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO.  " + ex.getMessage());
        }
    }
    //esto es un comentraio para realizar pruebas de commit

    //Valida si el usuario tiene sesion o no 
    public void validarSesion() {
        //Si el usuario no tiene rol, redireccionamos al login 
        if (rol == null || rol == 0) {
            Utilidades.redireccionLogin();
        }
    }

    public void cerrarSesion() {
        rol = null;
        Utilidades.redireccionLogin();
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

}
