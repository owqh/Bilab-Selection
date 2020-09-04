/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_CAN_CANDIDATO", catalog = "BITLAB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CAN_CODIGO"}),
    @UniqueConstraint(columnNames = {"CAN_DUI"}),
    @UniqueConstraint(columnNames = {"CAN_CORREO"})})
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c"),
    @NamedQuery(name = "Candidato.findByCanId", query = "SELECT c FROM Candidato c WHERE c.canId = :canId"),
    @NamedQuery(name = "Candidato.findByCanCodigo", query = "SELECT c FROM Candidato c WHERE c.canCodigo = :canCodigo"),
    @NamedQuery(name = "Candidato.findByCanPrimerNombre", query = "SELECT c FROM Candidato c WHERE c.canPrimerNombre = :canPrimerNombre"),
    @NamedQuery(name = "Candidato.findByCanSegundoNombre", query = "SELECT c FROM Candidato c WHERE c.canSegundoNombre = :canSegundoNombre"),
    @NamedQuery(name = "Candidato.findByCanPrimerApellido", query = "SELECT c FROM Candidato c WHERE c.canPrimerApellido = :canPrimerApellido"),
    @NamedQuery(name = "Candidato.findByCanSegundoApellido", query = "SELECT c FROM Candidato c WHERE c.canSegundoApellido = :canSegundoApellido"),
    @NamedQuery(name = "Candidato.findByCanDui", query = "SELECT c FROM Candidato c WHERE c.canDui = :canDui"),
    @NamedQuery(name = "Candidato.findByCanCorreo", query = "SELECT c FROM Candidato c WHERE c.canCorreo = :canCorreo"),
    @NamedQuery(name = "Candidato.findByCanDireccion", query = "SELECT c FROM Candidato c WHERE c.canDireccion = :canDireccion"),
    @NamedQuery(name = "Candidato.findByCanTelefono", query = "SELECT c FROM Candidato c WHERE c.canTelefono = :canTelefono"),
    @NamedQuery(name = "Candidato.findByCanFechaNac", query = "SELECT c FROM Candidato c WHERE c.canFechaNac = :canFechaNac"),
    @NamedQuery(name = "Candidato.findByCanCv", query = "SELECT c FROM Candidato c WHERE c.canCv = :canCv"),
    @NamedQuery(name = "Candidato.findByCanFoto", query = "SELECT c FROM Candidato c WHERE c.canFoto = :canFoto"),
    @NamedQuery(name = "Candidato.findByCanPromedioCurso", query = "SELECT c FROM Candidato c WHERE c.canPromedioCurso = :canPromedioCurso"),
    @NamedQuery(name = "Candidato.findByCanPromedioSeleccion", query = "SELECT c FROM Candidato c WHERE c.canPromedioSeleccion = :canPromedioSeleccion")})
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CAN_ID", nullable = false)
    private Integer canId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CAN_CODIGO", nullable = false, length = 6)
    private String canCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CAN_PRIMER_NOMBRE", nullable = false, length = 20)
    private String canPrimerNombre;
    @Size(max = 20)
    @Column(name = "CAN_SEGUNDO_NOMBRE", length = 20)
    private String canSegundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CAN_PRIMER_APELLIDO", nullable = false, length = 20)
    private String canPrimerApellido;
    @Size(max = 20)
    @Column(name = "CAN_SEGUNDO_APELLIDO", length = 20)
    private String canSegundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CAN_DUI", nullable = false, length = 10)
    private String canDui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "CAN_CORREO", nullable = false, length = 150)
    private String canCorreo;
    @Size(max = 350)
    @Column(name = "CAN_DIRECCION", length = 350)
    private String canDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CAN_TELEFONO", nullable = false, length = 9)
    private String canTelefono;
    @Column(name = "CAN_FECHA_NAC")
    @Temporal(TemporalType.DATE)
    private Date canFechaNac;
    @Size(max = 300)
    @Column(name = "CAN_CV", length = 300)
    private String canCv;
    @Size(max = 300)
    @Column(name = "CAN_FOTO", length = 300)
    private String canFoto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAN_PROMEDIO_CURSO", precision = 22, scale = 0)
    private Double canPromedioCurso;
    @Column(name = "CAN_PROMEDIO_SELECCION", precision = 22, scale = 0)
    private Double canPromedioSeleccion;
    @JoinColumn(name = "EAP_ID", referencedColumnName = "EAP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoAplicacion eapId;
    @JoinColumn(name = "GEN_ID", referencedColumnName = "GEN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Generalidades genId;
    @JoinColumn(name = "HAP_ID", referencedColumnName = "HAP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private HistorialAplicacion hapId;
    @JoinColumn(name = "IDI_ID", referencedColumnName = "IDI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Idioma idiId;
    @JoinColumn(name = "NAC_ID", referencedColumnName = "NAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private NivelAcademico nacId;
    @JoinColumn(name = "OCU_ID", referencedColumnName = "OCU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ocupacion ocuId;
    @JoinColumn(name = "SEX_ID", referencedColumnName = "SEX_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sexo sexId;
    @JoinColumn(name = "DOC_ID", referencedColumnName = "DOC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Docente docId;
    @OneToMany(mappedBy = "canId", fetch = FetchType.LAZY)
    private List<NotaCurso> notaCursoList;

    public Candidato() {
    }

    public Candidato(Integer canId) {
        this.canId = canId;
    }

    public Candidato(Integer canId, String canCodigo, String canPrimerNombre, String canPrimerApellido, String canDui, String canCorreo, String canTelefono) {
        this.canId = canId;
        this.canCodigo = canCodigo;
        this.canPrimerNombre = canPrimerNombre;
        this.canPrimerApellido = canPrimerApellido;
        this.canDui = canDui;
        this.canCorreo = canCorreo;
        this.canTelefono = canTelefono;
    }

    public Candidato(Generalidades genId) {
        this.genId = genId;
    }
    

    public Candidato(Integer canId, String canCodigo, String canPrimerNombre, String canSegundoNombre, String canPrimerApellido, String canSegundoApellido, String canDui, String canCorreo, String canDireccion, String canTelefono, Date canFechaNac, EstadoAplicacion eapId, Generalidades genId, HistorialAplicacion hapId, Idioma idiId, NivelAcademico nacId, Ocupacion ocuId, Sexo sexId) {
        this.canId = canId;
        this.canCodigo = canCodigo;
        this.canPrimerNombre = canPrimerNombre;
        this.canSegundoNombre = canSegundoNombre;
        this.canPrimerApellido = canPrimerApellido;
        this.canSegundoApellido = canSegundoApellido;
        this.canDui = canDui;
        this.canCorreo = canCorreo;
        this.canDireccion = canDireccion;
        this.canTelefono = canTelefono;
        this.canFechaNac = canFechaNac;
        this.eapId = eapId;
        this.genId = genId;
        this.hapId = hapId;
        this.idiId = idiId;
        this.nacId = nacId;
        this.ocuId = ocuId;
        this.sexId = sexId;
    }

    public Integer getCanId() {
        return canId;
    }

    public void setCanId(Integer canId) {
        this.canId = canId;
    }

    public String getCanCodigo() {
        return canCodigo;
    }

    public void setCanCodigo(String canCodigo) {
        this.canCodigo = canCodigo;
    }

    public String getCanPrimerNombre() {
        return canPrimerNombre;
    }

    public void setCanPrimerNombre(String canPrimerNombre) {
        this.canPrimerNombre = canPrimerNombre;
    }

    public String getCanSegundoNombre() {
        return canSegundoNombre;
    }

    public void setCanSegundoNombre(String canSegundoNombre) {
        this.canSegundoNombre = canSegundoNombre;
    }

    public String getCanPrimerApellido() {
        return canPrimerApellido;
    }

    public void setCanPrimerApellido(String canPrimerApellido) {
        this.canPrimerApellido = canPrimerApellido;
    }

    public String getCanSegundoApellido() {
        return canSegundoApellido;
    }

    public void setCanSegundoApellido(String canSegundoApellido) {
        this.canSegundoApellido = canSegundoApellido;
    }

    public String getCanDui() {
        return canDui;
    }

    public void setCanDui(String canDui) {
        this.canDui = canDui;
    }

    public String getCanCorreo() {
        return canCorreo;
    }

    public void setCanCorreo(String canCorreo) {
        this.canCorreo = canCorreo;
    }

    public String getCanDireccion() {
        return canDireccion;
    }

    public void setCanDireccion(String canDireccion) {
        this.canDireccion = canDireccion;
    }

    public String getCanTelefono() {
        return canTelefono;
    }

    public void setCanTelefono(String canTelefono) {
        this.canTelefono = canTelefono;
    }

    public Date getCanFechaNac() {
        return canFechaNac;
    }

    public void setCanFechaNac(Date canFechaNac) {
        this.canFechaNac = canFechaNac;
    }

    public String getCanCv() {
        return canCv;
    }

    public void setCanCv(String canCv) {
        this.canCv = canCv;
    }

    public String getCanFoto() {
        return canFoto;
    }

    public void setCanFoto(String canFoto) {
        this.canFoto = canFoto;
    }

    public Double getCanPromedioCurso() {
        return canPromedioCurso;
    }

    public void setCanPromedioCurso(Double canPromedioCurso) {
        this.canPromedioCurso = canPromedioCurso;
    }

    public Double getCanPromedioSeleccion() {
        return canPromedioSeleccion;
    }

    public void setCanPromedioSeleccion(Double canPromedioSeleccion) {
        this.canPromedioSeleccion = canPromedioSeleccion;
    }

    public EstadoAplicacion getEapId() {
        return eapId;
    }

    public void setEapId(EstadoAplicacion eapId) {
        this.eapId = eapId;
    }

    public Generalidades getGenId() {
        return genId;
    }

    public void setGenId(Generalidades genId) {
        this.genId = genId;
    }

    public HistorialAplicacion getHapId() {
        return hapId;
    }

    public void setHapId(HistorialAplicacion hapId) {
        this.hapId = hapId;
    }

    public Idioma getIdiId() {
        return idiId;
    }

    public void setIdiId(Idioma idiId) {
        this.idiId = idiId;
    }

    public NivelAcademico getNacId() {
        return nacId;
    }

    public void setNacId(NivelAcademico nacId) {
        this.nacId = nacId;
    }

    public Ocupacion getOcuId() {
        return ocuId;
    }

    public void setOcuId(Ocupacion ocuId) {
        this.ocuId = ocuId;
    }

    public Sexo getSexId() {
        return sexId;
    }

    public void setSexId(Sexo sexId) {
        this.sexId = sexId;
    }

    public Docente getDocId() {
        return docId;
    }

    public void setDocId(Docente docId) {
        this.docId = docId;
    }

    public List<NotaCurso> getNotaCursoList() {
        return notaCursoList;
    }

    public void setNotaCursoList(List<NotaCurso> notaCursoList) {
        this.notaCursoList = notaCursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (canId != null ? canId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.canId == null && other.canId != null) || (this.canId != null && !this.canId.equals(other.canId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Candidato[ canId=" + canId + " ]";
    }
    
}
