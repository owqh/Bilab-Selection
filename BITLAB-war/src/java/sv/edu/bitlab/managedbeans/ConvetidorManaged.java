/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import sv.edu.bitlab.beans.ActividadesFacade;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.beans.ConfiguracionFacade;
import sv.edu.bitlab.beans.CursoFacade;
import sv.edu.bitlab.beans.DocenteFacade;
import sv.edu.bitlab.beans.EstadoAplicacionFacade;
import sv.edu.bitlab.beans.GeneralidadesFacade;
import sv.edu.bitlab.beans.HistorialAplicacionFacade;
import sv.edu.bitlab.beans.IdiomaFacade;
import sv.edu.bitlab.beans.NivelAcademicoFacade;
import sv.edu.bitlab.beans.NotaCursoFacade;
import sv.edu.bitlab.beans.NotaSeleccionFacade;
import sv.edu.bitlab.beans.OcupacionFacade;
import sv.edu.bitlab.beans.PruebasFacade;
import sv.edu.bitlab.beans.SexoFacade;
import sv.edu.bitlab.beans.TecnologiaFacade;
import sv.edu.bitlab.beans.TipoUsuarioFacade;
import sv.edu.bitlab.beans.UsuarioFacade;
import sv.edu.bitlab.entidades.Actividades;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.entidades.Configuracion;
import sv.edu.bitlab.entidades.Curso;
import sv.edu.bitlab.entidades.Docente;
import sv.edu.bitlab.entidades.EstadoAplicacion;
import sv.edu.bitlab.entidades.Generalidades;
import sv.edu.bitlab.entidades.HistorialAplicacion;
import sv.edu.bitlab.entidades.Idioma;
import sv.edu.bitlab.entidades.NivelAcademico;
import sv.edu.bitlab.entidades.NotaCurso;
import sv.edu.bitlab.entidades.NotaSeleccion;
import sv.edu.bitlab.entidades.Ocupacion;
import sv.edu.bitlab.entidades.Pruebas;
import sv.edu.bitlab.entidades.Sexo;
import sv.edu.bitlab.entidades.Tecnologia;
import sv.edu.bitlab.entidades.TipoUsuario;
import sv.edu.bitlab.entidades.Usuario;

/**
 *
 * @author Oscar
 */
@Named(value = "convetidorManaged")
@SessionScoped
public class ConvetidorManaged implements Serializable {

    //Importacion de EJB para realizar convertidores
    @EJB
    private ActividadesFacade actividadesFacade;
    
    @EJB
    private ConfiguracionFacade configuracionFacade;
    
    @EJB
    private CandidatoFacade candidatoFacade;

    @EJB
    private CursoFacade cursoFacade;

    @EJB
    private DocenteFacade docenteFacade;

    @EJB
    private EstadoAplicacionFacade estadoAplicacionFacade;

    @EJB
    private GeneralidadesFacade generalidadesFacade;

    @EJB
    private HistorialAplicacionFacade historialAplicacionFacade;

    @EJB
    private IdiomaFacade idiomaFacade;

    @EJB
    private NivelAcademicoFacade nivelAcademicoFacade;

    @EJB
    private NotaCursoFacade notaCursoFacade;

    @EJB
    private NotaSeleccionFacade notaSeleccionFacade;

    @EJB
    private OcupacionFacade ocupacionFacade;

    @EJB
    private PruebasFacade pruebasFacade;

    @EJB
    private SexoFacade sexoFacade;

    @EJB
    private TecnologiaFacade tecnologiaFacade;

    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    
    //Buscando los id para convertidores
    public Actividades getActividadesId(String id){
        return actividadesFacade.find(Integer.parseInt(id));
    }
    
    public Configuracion getConfiguracionId(String id){
        return configuracionFacade.find(Integer.parseInt(id));
    }
    
    public Candidato getCandidatoId(String id){
        return candidatoFacade.find(Integer.parseInt(id));
    }
    
    public Curso getCursoId(String id){
        return cursoFacade.find(Integer.parseInt(id));
    }
    
    public Docente getDocenteId(String id){
        return docenteFacade.find(Integer.parseInt(id));
    }
    
    public EstadoAplicacion getEstadoAplicacionId(String id){
        return estadoAplicacionFacade.find(Integer.parseInt(id));
    }
    
    public Generalidades getGeneralidadesId(String id){
        return generalidadesFacade.find(Integer.parseInt(id));
    }
    
    public HistorialAplicacion getHistorialAplicacionId(String id){
        return historialAplicacionFacade.find(Integer.parseInt(id));
    }
    
    public Idioma getIdiomaId(String id){
        return idiomaFacade.find(Integer.parseInt(id));
    }
    
    public NivelAcademico getNivelAcademicoId(String id){
        return nivelAcademicoFacade.find(Integer.parseInt(id));
    }
    
    public NotaCurso getNotaCursoId(String id){
        return notaCursoFacade.find(Integer.parseInt(id));
    }
    
    public NotaSeleccion getNotaSeleccionId(String id){
        return notaSeleccionFacade.find(Integer.parseInt(id));
    }
    
    public Ocupacion getOcupacionId(String id){
        return ocupacionFacade.find(Integer.parseInt(id));
    }
    
    public Pruebas getPruebasId(String id){
        return pruebasFacade.find(Integer.parseInt(id));
    }
    
    public Sexo getSexoId(String id){
        return sexoFacade.find(Integer.parseInt(id));
    }
    
    public Tecnologia getTecnologiaId(String id){
        return tecnologiaFacade.find(Integer.parseInt(id));
    }
    
    public TipoUsuario getTipoUsuarioId(String id){
        return tipoUsuarioFacade.find(Integer.parseInt(id));
    }
    
    public Usuario getUsuarioId(String id){
        return usuarioFacade.find(Integer.parseInt(id));
    }
}
