/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "BIT_CUR_CURSO", catalog = "BITLAB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CUR_CODIGO"})})
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCurId", query = "SELECT c FROM Curso c WHERE c.curId = :curId"),
    @NamedQuery(name = "Curso.findByCurCodigo", query = "SELECT c FROM Curso c WHERE c.curCodigo = :curCodigo"),
    @NamedQuery(name = "Curso.findByCurNombre", query = "SELECT c FROM Curso c WHERE c.curNombre = :curNombre"),
    @NamedQuery(name = "Curso.findByCurFechaInicio", query = "SELECT c FROM Curso c WHERE c.curFechaInicio = :curFechaInicio"),
    @NamedQuery(name = "Curso.findByCurFechaFin", query = "SELECT c FROM Curso c WHERE c.curFechaFin = :curFechaFin")})
public class Curso implements Serializable {

    @Size(max = 300)
    @Column(name = "CUR_DETALLE", length = 300)
    private String curDetalle;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "CUR_ESTADO", nullable = false, length = 8)
    private String curEstado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUR_ID", nullable = false)
    private Integer curId;
    @Size(max = 8)
    @Column(name = "CUR_CODIGO", length = 8)
    private String curCodigo;
    @Size(max = 250)
    @Column(name = "CUR_NOMBRE", length = 250)
    private String curNombre;
    @Column(name = "CUR_FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date curFechaInicio;
    @Column(name = "CUR_FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date curFechaFin;
    @JoinColumn(name = "DOC_ID", referencedColumnName = "DOC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Docente docId;

    public Curso() {
    }

    public Curso(Integer curId) {
        this.curId = curId;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public String getCurCodigo() {
        return curCodigo;
    }

    public void setCurCodigo(String curCodigo) {
        this.curCodigo = curCodigo;
    }

    public String getCurNombre() {
        return curNombre;
    }

    public void setCurNombre(String curNombre) {
        this.curNombre = curNombre;
    }

    public Date getCurFechaInicio() {
        return curFechaInicio;
    }

    public void setCurFechaInicio(Date curFechaInicio) {
        this.curFechaInicio = curFechaInicio;
    }

    public Date getCurFechaFin() {
        return curFechaFin;
    }

    public void setCurFechaFin(Date curFechaFin) {
        this.curFechaFin = curFechaFin;
    }

    public Docente getDocId() {
        return docId;
    }

    public void setDocId(Docente docId) {
        this.docId = docId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curId != null ? curId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.curId == null && other.curId != null) || (this.curId != null && !this.curId.equals(other.curId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Curso[ curId=" + curId + " ]";
    }

    public String getCurEstado() {
        return curEstado;
    }

    public void setCurEstado(String curEstado) {
        this.curEstado = curEstado;
    }

    public String getCurDetalle() {
        return curDetalle;
    }

    public void setCurDetalle(String curDetalle) {
        this.curDetalle = curDetalle;
    }
    
}
