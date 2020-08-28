/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.beans.HistorialAplicacionFacade;
import sv.edu.bitlab.beans.NotaSeleccionFacade;
import sv.edu.bitlab.beans.PruebasFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.NotaSeleccion;
import sv.edu.bitlab.entidades.Pruebas;

/**
 *
 * @author carlosGodoy
 */
@Named(value = "candidatomanaged")
@SessionScoped
public class candidatoManaged implements Serializable {

    @EJB
    private CandidatoFacade candidatoFacade1;
    
    @EJB
    private EstadoAplicacionFacade estadoAplicacionFacade1;
//    @EJB
//    private CandidatoFacade candidatoFacade;
    @EJB
    private NotaSeleccionFacade notaSeleccionFacade;
    @EJB
    private PruebasFacade pruebasFacade;
    @EJB
    private HistorialAplicacionFacade historialFacade;

    private String Psicometrica;
    private String tecnica;
    private Candidato candidato;
    private NotaSeleccion notaSeleccion = new NotaSeleccion();
    private String logica;

    //Listas de candidatos
    private List<Candidato> candidatoLista;
    private List<Candidato> candidatoListPo; //
    private List<Candidato> candidatoListRegularAlto; //
    private List<Candidato> candidatoListRegularBajo; //
    private List<Candidato> candidatoSinOportu;
    private List<Candidato> candidatoSeleccionado;
    private List<Candidato> aplicanteGeneral; //
    private List<Candidato> candidatoListPreseleccionado; //

    private EstadoAplicacion estadoSeleccion;
    private String entrevista;
    //Variables de tipo Prueba
    private Pruebas pruebasE;
    private Pruebas pruebasT;
    private Pruebas pruebasL;
    private Pruebas pruebasP;

    private HistorialAplicacion hApp;
    private HistorialAplicacion historialAplicacion;
    private List<HistorialAplicacion> historialLista;

    @PostConstruct
    public void cargarInfo() {

        try {
            candidatoListPreseleccionado = candidatoFacade1.candidatosPreseleccionados();
            aplicanteGeneral = candidatoFacade1.aplicanteGeneral(); //
            candidatoSeleccionado = candidatoFacade1.aplicanteSeleccionado();
            candidatoListPo = candidatoFacade1.candidatoPotencial(); //
            candidatoListRegularAlto = candidatoFacade1.candidatoRegularAlto(); //
            candidatoListRegularBajo = candidatoFacade1.candidatoRegularBajo(); //
            candidatoSinOportu = candidatoFacade1.candidatoSinOportunidad();
        } catch (Exception ex) {
            Logger.getLogger(candidatoManaged.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarNotas() {
        //Llenado de objetos por id de tipo prueba
        pruebasE = pruebasFacade.find(1);
        pruebasT = pruebasFacade.find(2);
        pruebasL = pruebasFacade.find(3);
        pruebasP = pruebasFacade.find(4);
        //calculo del promedio para cada prueba 
        double promedioE = (Double.parseDouble(entrevista) * (pruebasE.getPruPorcentaje()) / 100);
        double promedioT = (Double.parseDouble(tecnica) * (pruebasT.getPruPorcentaje()) / 100);
        double promedioL = (Double.parseDouble(logica) * (pruebasL.getPruPorcentaje()) / 100);
        double promedioP = (Double.parseDouble(Psicometrica) * (pruebasP.getPruPorcentaje()) / 100);
        Double promedio = (promedioE + promedioL + promedioP + promedioT);
        //Ingreso de notas 
        notaSeleccion = new NotaSeleccion((Double.parseDouble(entrevista)), promedioE, pruebasE);
        notaSeleccionFacade.edit(notaSeleccion);
        notaSeleccion = new NotaSeleccion((Double.parseDouble(logica)), promedioL, pruebasL);
        notaSeleccionFacade.edit(notaSeleccion);
        notaSeleccion = new NotaSeleccion((Double.parseDouble(tecnica)), promedioT, pruebasT);
        notaSeleccionFacade.edit(notaSeleccion);
        notaSeleccion = new NotaSeleccion((Double.parseDouble(Psicometrica)), promedioP, pruebasP);
        notaSeleccionFacade.edit(notaSeleccion);
        //Ingreso del promedio 
        double promedioSeleccion = Math.round(promedio * 100D) / 100D;
        candidato.setCanPromedioSeleccion(promedioSeleccion);
        candidatoFacade1.edit(candidato);
        
        cargarInfo();
    }

    public void aceptarPreSeleccionado() {
        estadoSeleccion = estadoAplicacionFacade1.find(1);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade1.edit(candidato);
        cargarInfo();
    }

    public void aceptarASeleccionado() {
        estadoSeleccion = estadoAplicacionFacade1.find(2);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade1.edit(candidato);
        cargarInfo();
    }

    public void addAlumn() {
        estadoSeleccion = estadoAplicacionFacade1.find(3);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade1.edit(candidato);
        cargarInfo();
    }

    public void rechazarCandidato() {
        estadoSeleccion = estadoAplicacionFacade1.find(7);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade1.edit(candidato);
        cargarInfo();
    }

    public EstadoAplicacionFacade getEstadoAplicacionFacade1() {
        return estadoAplicacionFacade1;
    }

    public void setEstadoAplicacionFacade1(EstadoAplicacionFacade estadoAplicacionFacade1) {
        this.estadoAplicacionFacade1 = estadoAplicacionFacade1;
    }

    public NotaSeleccion getNotaSeleccion() {
        return notaSeleccion;
    }

    public void setNotaSeleccion(NotaSeleccion notaSeleccion) {
        this.notaSeleccion = notaSeleccion;
    }

    public List<Candidato> getCandidatoListRegularAlto() {
        return candidatoListRegularAlto;
    }

    public void setCandidatoListRegularAlto(List<Candidato> candidatoListRegularAlto) {
        this.candidatoListRegularAlto = candidatoListRegularAlto;
    }

    public List<Candidato> getCandidatoListRegularBajo() {
        return candidatoListRegularBajo;
    }

    public void setCandidatoListRegularBajo(List<Candidato> candidatoListRegularBajo) {
        this.candidatoListRegularBajo = candidatoListRegularBajo;
    }

    public List<Candidato> getCandidatoSinOportu() {
        return candidatoSinOportu;
    }

    public void setCandidatoSinOportu(List<Candidato> candidatoSinOportu) {
        this.candidatoSinOportu = candidatoSinOportu;
    }

    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade1;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade1 = candidatoFacade;
    }

    public List<Candidato> getCandidatoLista() {
        return candidatoLista;
    }

    public void setCandidatoLista(List<Candidato> candidatoLista) {
        this.candidatoLista = candidatoLista;
    }

    public List<Candidato> getCandidatoListPo() {
        return candidatoListPo;
    }

    public void setCandidatoListPo(List<Candidato> candidatoListPo) {
        this.candidatoListPo = candidatoListPo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public EstadoAplicacion getEstadoSeleccion() {
        return estadoSeleccion;
    }

    public void setEstadoSeleccion(EstadoAplicacion estadoSeleccion) {
        this.estadoSeleccion = estadoSeleccion;
    }

    public NotaSeleccionFacade getNotaSeleccionFacade() {
        return notaSeleccionFacade;
    }

    public void setNotaSeleccionFacade(NotaSeleccionFacade notaSeleccionFacade) {
        this.notaSeleccionFacade = notaSeleccionFacade;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getLogica() {
        return logica;
    }

    public void setLogica(String logica) {
        this.logica = logica;
    }

    public String getPsicometrica() {
        return Psicometrica;
    }

    public void setPsicometrica(String Psicometrica) {
        this.Psicometrica = Psicometrica;
    }

    public String getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(String entrevista) {
        this.entrevista = entrevista;
    }

    public HistorialAplicacion gethApp() {
        return hApp;
    }

    public void sethApp(HistorialAplicacion hApp) {
        this.hApp = hApp;
    }

    public HistorialAplicacion getHistorialAplicacion() {
        return historialAplicacion;
    }

    public void setHistorialAplicacion(HistorialAplicacion historialAplicacion) {
        this.historialAplicacion = historialAplicacion;
    }

    public List<Candidato> getAplicanteGeneral() {
        return aplicanteGeneral;
    }

    public void setAplicanteGeneral(List<Candidato> aplicanteGeneral) {
        this.aplicanteGeneral = aplicanteGeneral;
    }

    public HistorialAplicacionFacade getHistorialFacade() {
        return historialFacade;
    }

    public void setHistorialFacade(HistorialAplicacionFacade historialFacade) {
        this.historialFacade = historialFacade;
    }

    public List<HistorialAplicacion> getHistorialLista() {
        return historialLista;
    }

    public void setHistorialLista(List<HistorialAplicacion> historialLista) {
        this.historialLista = historialLista;
    }

    public List<Candidato> getCandidatoSeleccionado() {
        return candidatoSeleccionado;
    }

    public void setCandidatoSeleccionado(List<Candidato> candidatoSeleccionado) {
        this.candidatoSeleccionado = candidatoSeleccionado;
    }

    public List<Candidato> getCandidatoListPreseleccionado() {
        return candidatoListPreseleccionado;
    }

    public void setCandidatoListPreseleccionado(List<Candidato> candidatoListPreseleccionado) {
        this.candidatoListPreseleccionado = candidatoListPreseleccionado;
    }

}
