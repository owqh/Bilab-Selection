/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.GeneralidadesFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.entidades.Sexo;

/**
 *
 * @author Mario
 */
@Named(value = "perfilManaged")
@ViewScoped
public class PerfilManaged implements Serializable {
    private static final Logger log = Logger.getLogger(PerfilManaged.class.getName());
    
    @EJB
    private GeneralidadesFacade generalidadesFacade;

    @EJB
    private CandidatoFacade candidatoFacade;

    @Inject
    UsuarioManaged usuarioManaged;

    private Candidato perfilUsuario;

    //Campos simples de candidato
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

    //Campos compuestos
    private int sexo;
    private int nivelAcademico;
    private int idioma;
    private int ocupacion;

    //Generalidades
    private int genId;
    private String linkedIn;
    private String internet;
    private String computadora;
    private String tiempo;
    private int expectativaSal;
    private String alaboral;
    private String acurso;
    private String enterado;
    private String otros;

    public PerfilManaged() {
    }

    @PostConstruct
    public void encontrarCandidato() {
        try {
            perfilUsuario = candidatoFacade.candidatoPorCorreo(usuarioManaged.getUsr().getUsrAcceso());

            //Campos simples de candidato
            pnombre = perfilUsuario.getCanPrimerNombre();
            snombre = perfilUsuario.getCanSegundoNombre();
            papellido = perfilUsuario.getCanPrimerApellido();
            sapellido = perfilUsuario.getCanSegundoApellido();
            fnacimiento = perfilUsuario.getCanFechaNac();
            dui = perfilUsuario.getCanDui();
            telefono = perfilUsuario.getCanTelefono();
            correo = perfilUsuario.getCanCorreo();
            direccion = perfilUsuario.getCanDireccion();

            //Campos compuestos
            sexo = perfilUsuario.getSexId().getSexId();
            nivelAcademico = perfilUsuario.getNacId().getNacId();
            idioma = perfilUsuario.getIdiId().getIdiId();
            ocupacion = perfilUsuario.getOcuId().getOcuId();

            //Generalidades
            genId = perfilUsuario.getGenId().getGenId();
            linkedIn = perfilUsuario.getGenId().getGenLinkedin();
            internet = perfilUsuario.getGenId().getGenInternet();
            computadora = perfilUsuario.getGenId().getGenComputadora();
            tiempo = perfilUsuario.getGenId().getGenTiempo();
            expectativaSal = perfilUsuario.getGenId().getGenAspiracionSal();
            alaboral = perfilUsuario.getGenId().getGenAspiracionLab();
            acurso = perfilUsuario.getGenId().getGenAspiracionCurso();
            enterado = perfilUsuario.getGenId().getGenEnterado();
            otros = perfilUsuario.getGenId().getGenOtrosConocimientos();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "No se encontro al candidato, error: {0}", ex);
        }
    }
    
    public void updatePicture(){
        
    }

    public void updateAccount(){
        
    }
    public void updateGeneral(){
        perfilUsuario.setCanPrimerNombre(pnombre);
        perfilUsuario.setCanSegundoNombre(snombre);
        perfilUsuario.setCanPrimerApellido(papellido);
        perfilUsuario.setCanSegundoApellido(sapellido);
        perfilUsuario.setCanFechaNac(fnacimiento);
        perfilUsuario.setSexId(new Sexo(sexo));
        perfilUsuario.setCanDui(dui);
        perfilUsuario.setCanTelefono(telefono);
        perfilUsuario.setCanDireccion(direccion);
        
        candidatoFacade.edit(perfilUsuario);
    }
    public void updateComplement(){
        perfilUsuario.setNacId(new NivelAcademico(nivelAcademico));
        perfilUsuario.setOcuId(new Ocupacion(ocupacion));
        perfilUsuario.setIdiId(new Idioma(idioma));
        Generalidades genUsuario = new Generalidades(genId, internet, computadora, alaboral, expectativaSal, tiempo, acurso, enterado, otros, linkedIn);
        generalidadesFacade.edit(genUsuario);
        candidatoFacade.edit(perfilUsuario);
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

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(int nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
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

    public int getExpectativaSal() {
        return expectativaSal;
    }

    public void setExpectativaSal(int expectativaSal) {
        this.expectativaSal = expectativaSal;
    }

    public String getAlaboral() {
        return alaboral;
    }

    public void setAlaboral(String alaboral) {
        this.alaboral = alaboral;
    }

    public String getAcurso() {
        return acurso;
    }

    public void setAcurso(String acurso) {
        this.acurso = acurso;
    }

    public String getEnterado() {
        return enterado;
    }

    public void setEnterado(String enterado) {
        this.enterado = enterado;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }
}
