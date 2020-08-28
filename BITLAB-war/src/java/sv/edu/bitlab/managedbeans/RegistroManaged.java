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
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.Sexo;

/**
 *
 * @author Oscar
 */
@Named(value = "registroManaged")
@Dependent
public class RegistroManaged {

    //Importando EJB para registrar al participante

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
    
    //Listas para rrecorrer las entidades externas
    private List<Sexo> listaGenero;
    
    @PostConstruct
    public void cargarDatos(){
        listaGenero = sexoFacade.findAll();
    }
    
    public RegistroManaged() {
    }

    public void guardarRegistro() {
        //Encotrar todos los id foraneos
        //sexo= sexoFacade.find(id) 

        //preparar insert a la base
        //candidato = new Candidato(Integer.SIZE, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, nombre, canFechaNac, nombre, nombre, eapId, genId, idiId, nacId, ocuId, sexo)
        candidatoFacade.create(candidato);

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

    public void setSapellido(String sapeliido) {
        this.sapellido = sapeliido;
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

    public List<Sexo> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<Sexo> listaGenero) {
        this.listaGenero = listaGenero;
    }
    
    
    

}
