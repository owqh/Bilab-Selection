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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.beans.GeneralidadesFacade;
import sv.edu.bitlab.beans.HistorialAplicacionFacade;
import sv.edu.bitlab.beans.IdiomaFacade;
import sv.edu.bitlab.beans.NivelAcademicoFacade;
import sv.edu.bitlab.beans.OcupacionFacade;
import sv.edu.bitlab.beans.SexoFacade;
import sv.edu.bitlab.beans.UsuarioFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.entidades.Sexo;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Mario
 */
@Named(value = "perfilManaged")
@ViewScoped
public class PerfilManaged implements Serializable {

    @EJB
    private EstadoAplicacionFacade estadoAplicacionFacade;

    @EJB
    private HistorialAplicacionFacade historialAplicacionFacade;

    @EJB
    private GeneralidadesFacade generalidadesFacade;

    @EJB
    private CandidatoFacade candidatoFacade;

    @EJB
    private OcupacionFacade ocupacionFacade;

    @EJB
    private SexoFacade sexoFacade;

    @EJB
    private IdiomaFacade idiomaFacade;

    @EJB
    private NivelAcademicoFacade nivelAcademicoFacade;
    
    

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
    private Idioma idioma;
    private NivelAcademico nivelAcademico;
    private Sexo sexo;
    private Ocupacion ocupacion;
    private EstadoAplicacion estadoAplicacion;
    private HistorialAplicacion historialAplicacion;

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

    //Listas de campos compuestos
    private List<Sexo> listaGenero;
    private List<NivelAcademico> listaNivelAcademico;
    private List<Idioma> listaIdioma;
    private List<Ocupacion> listaOcupacion;

      private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PerfilManaged.class);
    public PerfilManaged() {
        //Constructor necesario para el POJO
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
            sexo = perfilUsuario.getSexId();
            nivelAcademico = perfilUsuario.getNacId();
            idioma = perfilUsuario.getIdiId();
            ocupacion = perfilUsuario.getOcuId();
            estadoAplicacion = perfilUsuario.getEapId();

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

            //Cargando listas desde la base de datos
            listaGenero = sexoFacade.findAll();
            listaNivelAcademico = nivelAcademicoFacade.findAll();
            listaIdioma = idiomaFacade.findAll();
            listaOcupacion = ocupacionFacade.findAll();
        } catch (Exception ex) {
            Logger.getLogger(PerfilManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateGeneral() {
        perfilUsuario.setCanPrimerNombre(pnombre);
        perfilUsuario.setCanSegundoNombre(snombre);
        perfilUsuario.setCanPrimerApellido(papellido);
        perfilUsuario.setCanSegundoApellido(sapellido);
        perfilUsuario.setCanFechaNac(fnacimiento);
        perfilUsuario.setSexId(sexo);
        perfilUsuario.setCanDui(dui);
        perfilUsuario.setCanTelefono(telefono);
        perfilUsuario.setCanDireccion(direccion);

        candidatoFacade.edit(perfilUsuario);
        Utilidades.lanzarInfo("Exitoso ", "Los campos de la tabla se han actualizado correctamente");

    }

    public void updateComplement() {
        perfilUsuario.setNacId(nivelAcademico);
        perfilUsuario.setOcuId(ocupacion);
        perfilUsuario.setIdiId(idioma);
        Generalidades genUsuario = new Generalidades(genId, internet, computadora, alaboral, expectativaSal, tiempo, acurso, enterado, otros, linkedIn);
        generalidadesFacade.edit(genUsuario);

        candidatoFacade.edit(perfilUsuario);
        Utilidades.lanzarInfo("Exitoso ", "Los campos de la tabla se han actualizado correctamente");
    }

    public int progressBarValue() {
        int value;
        switch (estadoAplicacion.getEapId()) {
            case 1:
                value = 50;
                break;
            case 2:
                value = 75;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                value = 100;
                break;
            case 7:
            case 8:
            case 9:
                value = 0;
                break;
            default:
                value = 0;
        }
        return value;
    }

    public void nuevaAplicacion() {
        try {
            if ("Rechazado".equals(perfilUsuario.getEapId().getEapNombre()) || "Abandono".equals(perfilUsuario.getEapId().getEapNombre())) {
               //Creando Historial de aplicacion
                try {
                    historialAplicacion = new HistorialAplicacion(1, new Date());
                    historialAplicacionFacade.create(historialAplicacion);
                } catch (Exception e) {
                    LOG.error("No se pudo crear el historial de aplicacion. " + e);
                }
                
                historialAplicacion = historialAplicacionFacade.find(historialAplicacion.getHapId());
                perfilUsuario.setHapId(historialAplicacion);
                
                estadoAplicacion = estadoAplicacionFacade.find(9); //El estado de aplicacion 9 pertenece al estado "aplicante"
                perfilUsuario.setEapId(estadoAplicacion);
                
               
                candidatoFacade.edit(perfilUsuario);
                Utilidades.lanzarInfo("Solicitud aceptada", "Hemos recibido tu solicitud, nos pondremos en contacto en los próximos días. ");
            } else {
                Utilidades.lanzarError("Error ", "No se ha podido registrar su aplicacion, solo se permite un proceso a la vez.");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            Utilidades.lanzarError("Error ", "No se ha podido registrar su nueva aplicacion, intentelo de nuevo mas tarde.");
        }

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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public EstadoAplicacion getEstadoAplicacion() {
        return estadoAplicacion;
    }

    public void setEstadoAplicacion(EstadoAplicacion estadoAplicacion) {
        this.estadoAplicacion = estadoAplicacion;
    }
}
