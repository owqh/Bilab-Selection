/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sv.edu.bitlab.beans.CandidatoFacade;
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
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.entidades.Sexo;
import sv.edu.bitlab.entidades.TipoUsuario;
import sv.edu.bitlab.entidades.Usuario;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Oscar
 */
@Named(value = "registroManaged")
@Dependent
public class RegistroManaged {

    //Importando EJB para registrar al candidato
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

    //Poner variables para insertar en 
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private String codigo;
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
    
    @PostConstruct
    public void cargarDatos(){
        //Cargando listas desde la base de datos
        listaGenero = sexoFacade.findAll();
        listaNivelAcademico = nivelAcademicoFacade.findAll();
        listaIdioma = idiomaFacade.findAll();
        listaOcupacion = ocupacionFacade.findAll();
    }
    
    public RegistroManaged() {
      
    }
    //Metodo para generar el codigo de cada candidato
    public void codigoPersonas(){
        codigo = papellido.charAt(0) + sapellido.charAt(0) + "123"; 
    }
    
    public void guardarRegistro() {
        //Encotrar todos los id foraneos
        sexo = sexoFacade.find(sexo.getSexId());
        idioma = idiomaFacade.find(idioma.getIdiId());
        nivelAcademico = nivelAcademicoFacade.find(nivelAcademico.getNacId());
        ocupacion = ocupacionFacade.find(ocupacion.getOcuId());
        estadoAplicacion = estadoAplicacionFacade.find(1); //El estado de aplicacion 1 pertenece al estado "Candidato"
        tipoUsuario = tipoUsuarioFacade.find(1); //El codigo 1 pertenece al tipo de usuario "candidato"
        
        //Creando Historial de Aplicacion 
        
        //Creando cuenta de usuario para acceder al sistema
        //Acceder a metodo creado en la clase Usuario Managed
        
        //Creando registro de informacion basica de candidato
        //candidato = new Candidato(Integer.SIZE, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, canFechaNac, nombre, nombre, eapId, genId, idiId, nacId, ocuId, sexo)
        candidato = new Candidato(1, codigo, pnombre, snombre, papellido, sapellido, dui, correo, direccion, telefono, fnacimiento, estadoAplicacion, generalidades, historialAplicacion, idioma, nivelAcademico, ocupacion, sexo);
        candidatoFacade.create(candidato);
        
        //Creando registro de datos complementarios
        
        
        
    }
    
    public void cuentaUsuario(){
        //Validando que el usuario no exista en la base de datos
        
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
