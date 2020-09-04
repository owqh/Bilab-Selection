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
 * @author Manuel
 */
@Entity
@Table(name = "BIT_DOC_DOCENTE", catalog = "BITLAB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DOC_DUI"})})
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByDocId", query = "SELECT d FROM Docente d WHERE d.docId = :docId"),
    @NamedQuery(name = "Docente.findByDocCodigo", query = "SELECT d FROM Docente d WHERE d.docCodigo = :docCodigo"),
    @NamedQuery(name = "Docente.findByDocPrimerNombre", query = "SELECT d FROM Docente d WHERE d.docPrimerNombre = :docPrimerNombre"),
    @NamedQuery(name = "Docente.findByDocSegundoNombre", query = "SELECT d FROM Docente d WHERE d.docSegundoNombre = :docSegundoNombre"),
    @NamedQuery(name = "Docente.findByDocPrimerApellido", query = "SELECT d FROM Docente d WHERE d.docPrimerApellido = :docPrimerApellido"),
    @NamedQuery(name = "Docente.findByDocSegundoApellido", query = "SELECT d FROM Docente d WHERE d.docSegundoApellido = :docSegundoApellido"),
    @NamedQuery(name = "Docente.findByDocDui", query = "SELECT d FROM Docente d WHERE d.docDui = :docDui"),
    @NamedQuery(name = "Docente.findByDocDireccion", query = "SELECT d FROM Docente d WHERE d.docDireccion = :docDireccion"),
    @NamedQuery(name = "Docente.findByDocTelefono", query = "SELECT d FROM Docente d WHERE d.docTelefono = :docTelefono"),
    @NamedQuery(name = "Docente.findByDocCorreo", query = "SELECT d FROM Docente d WHERE d.docCorreo = :docCorreo"),
    @NamedQuery(name = "Docente.findByDocFechaNac", query = "SELECT d FROM Docente d WHERE d.docFechaNac = :docFechaNac"),
    @NamedQuery(name = "Docente.findByDocEstado", query = "SELECT d FROM Docente d WHERE d.docEstado = :docEstado")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DOC_ID", nullable = false)
    private Integer docId;
    @Size(max = 6)
    @Column(name = "DOC_CODIGO", length = 6)
    private String docCodigo;
    @Size(max = 20)
    @Column(name = "DOC_PRIMER_NOMBRE", length = 20)
    private String docPrimerNombre;
    @Size(max = 20)
    @Column(name = "DOC_SEGUNDO_NOMBRE", length = 20)
    private String docSegundoNombre;
    @Size(max = 20)
    @Column(name = "DOC_PRIMER_APELLIDO", length = 20)
    private String docPrimerApellido;
    @Size(max = 20)
    @Column(name = "DOC_SEGUNDO_APELLIDO", length = 20)
    private String docSegundoApellido;
    @Size(max = 10)
    @Column(name = "DOC_DUI", length = 10)
    private String docDui;
    @Size(max = 350)
    @Column(name = "DOC_DIRECCION", length = 350)
    private String docDireccion;
    @Size(max = 9)
    @Column(name = "DOC_TELEFONO", length = 9)
    private String docTelefono;
    @Size(max = 150)
    @Column(name = "DOC_CORREO", length = 150)
    private String docCorreo;
    @Column(name = "DOC_FECHA_NAC")
    @Temporal(TemporalType.DATE)
    private Date docFechaNac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DOC_ESTADO", nullable = false, length = 8)
    private String docEstado;
    @OneToMany(mappedBy = "docId", fetch = FetchType.LAZY)
    private List<Curso> cursoList;

    public Docente() {
    }

    public Docente(Integer docId) {
        this.docId = docId;
    }

    public Docente(Integer docId, String docEstado) {
        this.docId = docId;
        this.docEstado = docEstado;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocCodigo() {
        return docCodigo;
    }

    public void setDocCodigo(String docCodigo) {
        this.docCodigo = docCodigo;
    }

    public String getDocPrimerNombre() {
        return docPrimerNombre;
    }

    public void setDocPrimerNombre(String docPrimerNombre) {
        this.docPrimerNombre = docPrimerNombre;
    }

    public String getDocSegundoNombre() {
        return docSegundoNombre;
    }

    public void setDocSegundoNombre(String docSegundoNombre) {
        this.docSegundoNombre = docSegundoNombre;
    }

    public String getDocPrimerApellido() {
        return docPrimerApellido;
    }

    public void setDocPrimerApellido(String docPrimerApellido) {
        this.docPrimerApellido = docPrimerApellido;
    }

    public String getDocSegundoApellido() {
        return docSegundoApellido;
    }

    public void setDocSegundoApellido(String docSegundoApellido) {
        this.docSegundoApellido = docSegundoApellido;
    }

    public String getDocDui() {
        return docDui;
    }

    public void setDocDui(String docDui) {
        this.docDui = docDui;
    }

    public String getDocDireccion() {
        return docDireccion;
    }

    public void setDocDireccion(String docDireccion) {
        this.docDireccion = docDireccion;
    }

    public String getDocTelefono() {
        return docTelefono;
    }

    public void setDocTelefono(String docTelefono) {
        this.docTelefono = docTelefono;
    }

    public String getDocCorreo() {
        return docCorreo;
    }

    public void setDocCorreo(String docCorreo) {
        this.docCorreo = docCorreo;
    }

    public Date getDocFechaNac() {
        return docFechaNac;
    }

    public void setDocFechaNac(Date docFechaNac) {
        this.docFechaNac = docFechaNac;
    }

    public String getDocEstado() {
        return docEstado;
    }

    public void setDocEstado(String docEstado) {
        this.docEstado = docEstado;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Docente[ docId=" + docId + " ]";
    }
    
}
