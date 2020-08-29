/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
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

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_NCU_NOTA_CURSO", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "NotaCurso.findAll", query = "SELECT n FROM NotaCurso n"),
    @NamedQuery(name = "NotaCurso.findByNcuId", query = "SELECT n FROM NotaCurso n WHERE n.ncuId = :ncuId"),
    @NamedQuery(name = "NotaCurso.findByNcuNota", query = "SELECT n FROM NotaCurso n WHERE n.ncuNota = :ncuNota"),
    @NamedQuery(name = "NotaCurso.findByNcuPromedio", query = "SELECT n FROM NotaCurso n WHERE n.ncuPromedio = :ncuPromedio")})
public class NotaCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NCU_ID", nullable = false)
    private Integer ncuId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NCU_NOTA", precision = 22, scale = 0)
    private Double ncuNota;
    @Column(name = "NCU_PROMEDIO", precision = 22, scale = 0)
    private Double ncuPromedio;
    @JoinColumn(name = "ACT_ID", referencedColumnName = "ACT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividades actId;
    @JoinColumn(name = "CAN_ID", referencedColumnName = "CAN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidato canId;
    @JoinColumn(name = "CUR_ID", referencedColumnName = "CUR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curId;

    public NotaCurso() {
    }

    public NotaCurso(Integer ncuId) {
        this.ncuId = ncuId;
    }

    public Integer getNcuId() {
        return ncuId;
    }

    public void setNcuId(Integer ncuId) {
        this.ncuId = ncuId;
    }

    public Double getNcuNota() {
        return ncuNota;
    }

    public void setNcuNota(Double ncuNota) {
        this.ncuNota = ncuNota;
    }

    public Double getNcuPromedio() {
        return ncuPromedio;
    }

    public void setNcuPromedio(Double ncuPromedio) {
        this.ncuPromedio = ncuPromedio;
    }

    public Actividades getActId() {
        return actId;
    }

    public void setActId(Actividades actId) {
        this.actId = actId;
    }

    public Candidato getCanId() {
        return canId;
    }

    public void setCanId(Candidato canId) {
        this.canId = canId;
    }

    public Curso getCurId() {
        return curId;
    }

    public void setCurId(Curso curId) {
        this.curId = curId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ncuId != null ? ncuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaCurso)) {
            return false;
        }
        NotaCurso other = (NotaCurso) object;
        if ((this.ncuId == null && other.ncuId != null) || (this.ncuId != null && !this.ncuId.equals(other.ncuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.NotaCurso[ ncuId=" + ncuId + " ]";
    }
    
}
