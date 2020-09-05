/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.ConfiguracionFacade;
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.beans.GeneralidadesFacade;
import sv.edu.bitlab.beans.HistorialAplicacionFacade;
import sv.edu.bitlab.beans.IdiomaFacade;
import sv.edu.bitlab.beans.NivelAcademicoFacade;
import sv.edu.bitlab.beans.OcupacionFacade;
import sv.edu.bitlab.beans.SexoFacade;
import sv.edu.bitlab.beans.TipoUsuarioFacade;
import sv.edu.bitlab.beans.UsuarioFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.Configuracion;
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.entidades.Sexo;
import sv.edu.bitlab.entidades.TipoUsuario;
import sv.edu.bitlab.entidades.Usuario;
import sv.edu.bitlab.utilidades.EncriptacionTexto;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Oscar
 */
@Named(value = "registroManaged")
@SessionScoped
public class RegistroManaged implements Serializable {
    @Inject
    private PerfilManaged perfilManaged;
    
    //Importando EJB para registrar al candidato
    @EJB
    private ConfiguracionFacade configuracionFacade;

    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private IdiomaFacade idiomaFacade;

    @EJB
    private NivelAcademicoFacade nivelAcademicoFacade;

    @EJB
    private HistorialAplicacionFacade historialAplicacionFacade;

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

    //Creando variables del tipo de cada entidad
    private EstadoAplicacion estadoAplicacion;
    private Idioma idioma;
    private NivelAcademico nivelAcademico;
    private HistorialAplicacion historialAplicacion;
    private Sexo sexo;
    private Generalidades generalidades;
    private Candidato candidato;
    private Ocupacion ocupacion;
    private Usuario usuario;
    private TipoUsuario tipoUsuario;
    private EncriptacionTexto encriptacionTexto;

    //Poner variables para insertar en 
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private Date fnacimiento;
    private String dui;
    private String telefono;
    private String correo;
    private String contrasena;
    private String direccion;
    private String linkedin;
    private String aspiracionLaboral;
    private int aspiracionSalarial;
    private String internt;
    private String computadora;
    private String tiempo;
    private String aspiracionCurso;
    private String enterado;
    private String otrosConocimientos;

    //Listas para rrecorrer las entidades externas
    private List<Sexo> listaGenero;
    private List<NivelAcademico> listaNivelAcademico;
    private List<Idioma> listaIdioma;
    private List<Ocupacion> listaOcupacion;
    List<Configuracion> datos;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RegistroManaged.class);

    @PostConstruct
    public void cargarDatos() {
        //Cargando listas desde la base de datos
        listaGenero = sexoFacade.findAll();
        listaNivelAcademico = nivelAcademicoFacade.findAll();
        listaIdioma = idiomaFacade.findAll();
        listaOcupacion = ocupacionFacade.findAll();
        sexo = new Sexo();
        idioma = new Idioma();
        nivelAcademico = new NivelAcademico();
        ocupacion = new Ocupacion();
    }

    //Metodo para generar el codigo de cada candidato
    public String codigoPersonas() {
        String codigoGenerado = papellido.substring(0, 1) + sapellido.substring(0, 1) + dui.substring(5, 8) + dui.substring(9);
        return codigoGenerado;
    }

    public void guardarRegistro() {
        String codigo = codigoPersonas();
        //Creando cuenta de usuario para acceder al sistema
        cuentaUsuario();

        try {
            //Creando Historial de Aplicacion 
            crearHistorial();

            //Creando generalidades
            crearGeneralidades();

            //Encotrar todos los id foraneos
            estadoAplicacion = estadoAplicacionFacade.find(9); //El estado de aplicacion 9 pertenece al estado "aplicante"
            tipoUsuario = tipoUsuarioFacade.find(5); //El codigo  pertenece al tipo de usuario "candidato"
            historialAplicacion = historialAplicacionFacade.find(historialAplicacion.getHapId());
            generalidades = generalidadesFacade.find(generalidades.getGenId());

            //Creando registro de informacion basica de candidato
            candidato = new Candidato(1, codigo, pnombre, snombre, papellido, sapellido, dui, correo, direccion, telefono, fnacimiento, estadoAplicacion, generalidades, historialAplicacion, idioma, nivelAcademico, ocupacion, sexo);
            candidatoFacade.create(candidato);

            //Enviando correo de registro exitoso    
            correoRegistro();

            Utilidades.lanzarInfo("Aplicacion recibida exitosamente", "Su aplicacion ha sido recibida por favor revise su correo electronico para obtener mas información. ¡Ya puedes iniciar sesion en el sistema!");
        } catch (Exception e) {
            Utilidades.lanzarError("Solicitud no recibida", "Su aplicacion no ha sido recibida, motivo: " + e.toString());

        }

    }

    public void correoRegistro() {
        String asuntoCorreo = "Aplicacion a curos BITLAB";
        String mensajeCorreo = "<h1>Estimad@  " + pnombre + " " + papellido + ", tu solicitud se ha procesado con éxito. \n </h1> "
                + "<p>Hemos recibido tu solicitud para una de nuestras becas en Bitlab, te pedimos estés pendiente de tu número de contacto y correo electrónico, nos pondremos en contacto en los próximos días.</p>"
                + "<p>¡Recuerda que puedes seguir tu proceso de selección a través de nuestra plataforma! Utiliza tu dirección de correo y contraseña proporcionada en el formulario de aplicación</p><br/>"
                + "<p>Ha recibido este e-mail por que se ha registrado una cuenta en el sistema de seleccion de becarios BITLAB.</p><br/>"
                + "<p>Preguntas frecuentes: "
                + "<ul><li><a href=\"https://blog.elaniin.com/enterate-aplicar-beca-bitlab/\">¿Cual es el proceso de aplicacion? </a></li>"
                + "<li><a href=\"https://bitlab.edu.sv/about\">¿Que es bitlab?</a></li></ul>"
                + "<br/><p><b>Si usted no solicito este acceso por favor ignore este correo.</b></p>"
                + "<h4>Por su seguirdad nunca comparta este correo electronico con nadie.</h4>"
                + "<p>Preserve el Medio Ambiente NO imprimiendo este correo si no es realmente indispensable, recuerde que cada uno puede hacer la diferencia.</p>"
                + "<p><strong>Copyright &copy; 2020 <a href=\"https://bitlab.edu.sv/\">BITLAB</a>.</strong>\n"
                + " All rights reserved.</p>";

        enviarCorreo(correo, mensajeCorreo, asuntoCorreo);
    }

    public void cuentaUsuario() {
        //Validando que el usuario no exista en la base de datos
        try {
            //Buscando el correo en la base de datos
            usuario = usuarioFacade.ObtenerUsuario(correo);
            //Buscando el Dui en la base de datos
            candidato = candidatoFacade.candidatoPorDui(dui);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
        //preparando para encriptar contraseña
        encriptacionTexto = new EncriptacionTexto();
        if (usuario == null && candidato == null) {
            //Buscando el tipo de usuario candidato 
            tipoUsuario = tipoUsuarioFacade.find(5);

            //Encriptando contraseña para guardar en base de datos
            String contrasenaSegura = encriptacionTexto.getTextoEncriptado(contrasena);

            //perparando y creando el registro
            Usuario registro = new Usuario(1, correo, pnombre, papellido, contrasenaSegura, tipoUsuario);
            usuarioFacade.create(registro);
            LOG.info("Se ha creado correctamente el usuario: " + correo);
        } else {
            LOG.error("El correo: " + correo + " o el " + dui + " ya existe en la base de datos");
            Utilidades.lanzarError("Error en registrar aplicación", "El usuario ya se encuentra registrado. ¡Si ya tienes una cuenta incia sesion!");
        }
    }

    public void crearHistorial() {
        try {
            historialAplicacion = new HistorialAplicacion(1, new Date());
            historialAplicacionFacade.create(historialAplicacion);
        } catch (Exception e) {
            LOG.error("No se pudo crear el historial de aplicacion. " + e);
        }

    }

    public void crearGeneralidades() {
        try {
            //Creando registro de datos complementarios
            generalidades = new Generalidades(1, internt, computadora, aspiracionLaboral, aspiracionSalarial, tiempo, aspiracionCurso, enterado, otrosConocimientos, linkedin);
            generalidadesFacade.create(generalidades);
        } catch (Exception e) {
            LOG.error("No se pudo crear el historial de aplicacion. " + e);
        }
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

    public IdiomaFacade getIdiomaFacade() {
        return idiomaFacade;
    }

    public void setIdiomaFacade(IdiomaFacade idiomaFacade) {
        this.idiomaFacade = idiomaFacade;
    }

    public NivelAcademicoFacade getNivelAcademicoFacade() {
        return nivelAcademicoFacade;
    }

    public void setNivelAcademicoFacade(NivelAcademicoFacade nivelAcademicoFacade) {
        this.nivelAcademicoFacade = nivelAcademicoFacade;
    }

    public HistorialAplicacionFacade getHistorialAplicacionFacade() {
        return historialAplicacionFacade;
    }

    public void setHistorialAplicacionFacade(HistorialAplicacionFacade historialAplicacionFacade) {
        this.historialAplicacionFacade = historialAplicacionFacade;
    }

    public EstadoAplicacionFacade getEstadoAplicacionFacade() {
        return estadoAplicacionFacade;
    }

    public void setEstadoAplicacionFacade(EstadoAplicacionFacade estadoAplicacionFacade) {
        this.estadoAplicacionFacade = estadoAplicacionFacade;
    }

    public OcupacionFacade getOcupacionFacade() {
        return ocupacionFacade;
    }

    public void setOcupacionFacade(OcupacionFacade ocupacionFacade) {
        this.ocupacionFacade = ocupacionFacade;
    }

    public SexoFacade getSexoFacade() {
        return sexoFacade;
    }

    public void setSexoFacade(SexoFacade sexoFacade) {
        this.sexoFacade = sexoFacade;
    }

    public GeneralidadesFacade getGeneralidadesFacade() {
        return generalidadesFacade;
    }

    public void setGeneralidadesFacade(GeneralidadesFacade generalidadesFacade) {
        this.generalidadesFacade = generalidadesFacade;
    }

    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade = candidatoFacade;
    }

    public EstadoAplicacion getEstadoAplicacion() {
        return estadoAplicacion;
    }

    public void setEstadoAplicacion(EstadoAplicacion estadoAplicacion) {
        this.estadoAplicacion = estadoAplicacion;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public NivelAcademico getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(NivelAcademico nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public HistorialAplicacion getHistorialAplicacion() {
        return historialAplicacion;
    }

    public void setHistorialAplicacion(HistorialAplicacion historialAplicacion) {
        this.historialAplicacion = historialAplicacion;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Generalidades getGeneralidades() {
        return generalidades;
    }

    public void setGeneralidades(Generalidades generalidades) {
        this.generalidades = generalidades;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getAspiracionLaboral() {
        return aspiracionLaboral;
    }

    public void setAspiracionLaboral(String aspiracionLaboral) {
        this.aspiracionLaboral = aspiracionLaboral;
    }

    public int getAspiracionSalarial() {
        return aspiracionSalarial;
    }

    public void setAspiracionSalarial(int aspiracionSalarial) {
        this.aspiracionSalarial = aspiracionSalarial;
    }

    public String getInternt() {
        return internt;
    }

    public void setInternt(String internt) {
        this.internt = internt;
    }

    public String getComputadora() {
        return computadora;
    }

    public void setComputadora(String computadora) {
        this.computadora = computadora;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getAspiracionCurso() {
        return aspiracionCurso;
    }

    public void setAspiracionCurso(String aspiracionCurso) {
        this.aspiracionCurso = aspiracionCurso;
    }

    public String getEnterado() {
        return enterado;
    }

    public void setEnterado(String enterado) {
        this.enterado = enterado;
    }

    public String getOtrosConocimientos() {
        return otrosConocimientos;
    }

    public void setOtrosConocimientos(String otrosConocimientos) {
        this.otrosConocimientos = otrosConocimientos;
    }

    public List<Sexo> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<Sexo> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public List<NivelAcademico> getListaNivelAcademico() {
        return listaNivelAcademico;
    }

    public void setListaNivelAcademico(List<NivelAcademico> listaNivelAcademico) {
        this.listaNivelAcademico = listaNivelAcademico;
    }

    public List<Idioma> getListaIdioma() {
        return listaIdioma;
    }

    public void setListaIdioma(List<Idioma> listaIdioma) {
        this.listaIdioma = listaIdioma;
    }

    public List<Ocupacion> getListaOcupacion() {
        return listaOcupacion;
    }

    public void setListaOcupacion(List<Ocupacion> listaOcupacion) {
        this.listaOcupacion = listaOcupacion;
    }

}
