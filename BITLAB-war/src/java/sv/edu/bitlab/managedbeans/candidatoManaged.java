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
    private EstadoAplicacionFacade estadoAplicacionFacade1;
    @EJB
    private CandidatoFacade candidatoFacade;
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
    //private NotaSeleccion notaSeleccionP;
    private String logica;
    private List<Candidato> candidatoLista;
    private List<Candidato> candidatoListPo;
    private List<Candidato> candidatoListPre;
    private List<Candidato> candidatoListRegularAlto;
    private List<Candidato> candidatoListRegularBajo;
    private List<Candidato> candidatoSinOportu;
    private List<Candidato> candidatoPreseleccionado;
    private List<Candidato> aplicanteGeneral;
    private List<EstadoAplicacion> estadoAplicacionlist;
    private List<NotaSeleccion> notaSelList;
    private EstadoAplicacion estadoSeleccion;
    private String entrevista;
    private Pruebas pruebasE;
    private Pruebas pruebasT;
    private Pruebas pruebasL;
    private Pruebas pruebasP;
    private HistorialAplicacion hApp;
    private HistorialAplicacion historialAplicacion;

    @PostConstruct
    public void cargarInfo() {
        candidatoLista = candidatoFacade.findAll();

//        estadoAplicacionlist = estadoAplicacionFacade1.findAll();
//        estadoSeleccion = estadoAplicacionFacade.find(2);
        try {
            aplicanteGeneral = candidatoFacade.aplicanteGeneral();
            candidatoListPre = candidatoFacade.candidatoPre();
            candidatoListPo = candidatoFacade.candidatoPotencial();
            candidatoListRegularAlto = candidatoFacade.candidatoRegularAlto();
            candidatoListRegularBajo = candidatoFacade.candidatoRegularBajo();
            candidatoSinOportu = candidatoFacade.candidatoSinOportunidad();
        } catch (Exception ex) {
            Logger.getLogger(candidatoManaged.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarNotas() {

        pruebasE = pruebasFacade.find(1);
        pruebasT = pruebasFacade.find(2);
        pruebasL = pruebasFacade.find(3);
        pruebasP = pruebasFacade.find(4);
        double promedioE = (Double.parseDouble(entrevista) * (pruebasE.getPruPorcentaje()) / 100);
        double promedioT = (Double.parseDouble(tecnica) * (pruebasT.getPruPorcentaje()) / 100);
        double promedioL = (Double.parseDouble(logica) * (pruebasL.getPruPorcentaje()) / 100);
        double promedioP = (Double.parseDouble(Psicometrica) * (pruebasP.getPruPorcentaje()) / 100);
        hApp = (HistorialAplicacion) historialFacade.buscarPorIdCandidato(candidato.getCanId());
        Double promedio = (promedioE + promedioL + promedioP + promedioT);
//        notaSeleccion.setNseNota(Double.parseDouble(entrevista));
//        notaSeleccion.setNsePromedio(promedioE);
//        notaSeleccion.setPruId(pruebasE);
//        notaSeleccion.setHapId(hApp);
//        notaSeleccionFacade.create(notaSeleccion);

        notaSeleccion = new NotaSeleccion((Double.parseDouble(entrevista)), promedioE, hApp, pruebasE);
        notaSeleccionFacade.edit(notaSeleccion);

        notaSeleccion = new NotaSeleccion((Double.parseDouble(tecnica)), promedioT, hApp, pruebasT);
        notaSeleccionFacade.edit(notaSeleccion);

        notaSeleccion = new NotaSeleccion((Double.parseDouble(logica)), promedioL, hApp, pruebasL);
        notaSeleccionFacade.edit(notaSeleccion);

        notaSeleccion = new NotaSeleccion((Double.parseDouble(Psicometrica)), promedioP, hApp, pruebasP);
        notaSeleccionFacade.edit(notaSeleccion);
        
//        
        //System.out.println(" probando para ingresar el promedio." + notaSeleccionP.getNsePromedio().toString());
//        notaSeleccionFacade.edit(notaSeleccion);
//        
        System.out.println(candidato.getCanCodigo());
        System.out.println(candidato.getCanPrimerNombre());
        System.out.println(candidato.getCanPrimerApellido());
        System.out.println(candidato.getCanDui());
        System.out.println(this.entrevista);
        System.out.println(this.logica);
        System.out.println(this.tecnica);
        System.out.println(this.Psicometrica);
        System.out.println(pruebasE.getPruNombre());
        System.out.println(candidato.getCanId());
        System.out.println("la nota de: " + candidato.getCanPrimerNombre() + " El promedio de la" + pruebasE.getPruNombre() + " es:" + promedioE);
        System.out.println("la nota de: " + candidato.getCanPrimerNombre() + " El promedio de la Prueba" + pruebasT.getPruNombre() + " es es:" + promedioT);
        System.out.println("la nota de: " + candidato.getCanPrimerNombre() + " El promedio de la Prueba " + pruebasL.getPruNombre() + " es es: " + promedioL);
        System.out.println("la nota de: " + candidato.getCanPrimerNombre() + " El promedio de la Prueba " + pruebasP.getPruNombre() + " es es:" + promedioP);
        System.out.println("El promedio general es de: " + (promedioE + promedioL + promedioP + promedioT));
        //no reconocer el id, este debe ser un integer
        //System.out.println("el ID de Hap_id es: " + historialFacade.buscarPorIdCandidato(candidato.getCanId()));

        System.out.println(hApp);

    }

    public void aceptarPreSeleccionado() {
        estadoSeleccion = estadoAplicacionFacade1.find(1);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade.edit(candidato);
        cargarInfo();
    }
    
     public void aceptarASeleccionado() {
        estadoSeleccion = estadoAplicacionFacade1.find(2);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade.edit(candidato);
        cargarInfo();
    }
   

    public void rechazarCandidato() {
        estadoSeleccion = estadoAplicacionFacade1.find(7);
        candidato.setEapId(estadoSeleccion);
        candidatoFacade.edit(candidato);
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

    public List<Candidato> getCandidatoListPre() {
        return candidatoListPre;
    }

    public void setCandidatoListPre(List<Candidato> candidatoListPre) {
        this.candidatoListPre = candidatoListPre;
    }

    public CandidatoFacade getCandidatoFacade() {
        return candidatoFacade;
    }

    public void setCandidatoFacade(CandidatoFacade candidatoFacade) {
        this.candidatoFacade = candidatoFacade;
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

    public List<EstadoAplicacion> getEstadoAplicacionlist() {
        return estadoAplicacionlist;
    }

    public void setEstadoAplicacionlist(List<EstadoAplicacion> estadoAplicacionlist) {
        this.estadoAplicacionlist = estadoAplicacionlist;
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

}
