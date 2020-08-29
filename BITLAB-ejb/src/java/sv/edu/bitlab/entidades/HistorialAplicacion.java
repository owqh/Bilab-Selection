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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_HAP_HISTORIAL_APLICACION", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "HistorialAplicacion.findAll", query = "SELECT h FROM HistorialAplicacion h"),
    @NamedQuery(name = "HistorialAplicacion.findByHapId", query = "SELECT h FROM HistorialAplicacion h WHERE h.hapId = :hapId"),
    @NamedQuery(name = "HistorialAplicacion.findByHapFechaInicio", query = "SELECT h FROM HistorialAplicacion h WHERE h.hapFechaInicio = :hapFechaInicio"),
    @NamedQuery(name = "HistorialAplicacion.findByHapFechaFin", query = "SELECT h FROM HistorialAplicacion h WHERE h.hapFechaFin = :hapFechaFin"),
    @NamedQuery(name = "HistorialAplicacion.findByHapComentario", query = "SELECT h FROM HistorialAplicacion h WHERE h.hapComentario = :hapComentario"),
    @NamedQuery(name = "HistorialAplicacion.findByHapPromedio", query = "SELECT h FROM HistorialAplicacion h WHERE h.hapPromedio = :hapPromedio")})
public class HistorialAplicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HAP_ID", nullable = false)
    private Integer hapId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAP_FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hapFechaInicio;
    @Column(name = "HAP_FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date hapFechaFin;
    @Size(max = 600)
    @Column(name = "HAP_COMENTARIO", length = 600)
    private String hapComentario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HAP_PROMEDIO", precision = 22, scale = 0)
    private Double hapPromedio;
    @OneToMany(mappedBy = "hapId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;
    @JoinColumn(name = "NSE_ID", referencedColumnName = "NSE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private NotaSeleccion nseId;

    public HistorialAplicacion() {
    }

    public HistorialAplicacion(Integer hapId) {
        this.hapId = hapId;
    }

    public HistorialAplicacion(Integer hapId, Date hapFechaInicio) {
        this.hapId = hapId;
        this.hapFechaInicio = hapFechaInicio;
    }

    public Integer getHapId() {
        return hapId;
    }

    public void setHapId(Integer hapId) {
        this.hapId = hapId;
    }

    public Date getHapFechaInicio() {
        return hapFechaInicio;
    }

    public void setHapFechaInicio(Date hapFechaInicio) {
        this.hapFechaInicio = hapFechaInicio;
    }

    public Date getHapFechaFin() {
        return hapFechaFin;
    }

    public void setHapFechaFin(Date hapFechaFin) {
        this.hapFechaFin = hapFechaFin;
    }

    public String getHapComentario() {
        return hapComentario;
    }

    public void setHapComentario(String hapComentario) {
        this.hapComentario = hapComentario;
    }

    public Double getHapPromedio() {
        return hapPromedio;
    }

    public void setHapPromedio(Double hapPromedio) {
        this.hapPromedio = hapPromedio;
    }

    public List<Candidato> getCandidatoList() {
        return candidatoList;
    }

    public void setCandidatoList(List<Candidato> candidatoList) {
        this.candidatoList = candidatoList;
    }

    public NotaSeleccion getNseId() {
        return nseId;
    }

    public void setNseId(NotaSeleccion nseId) {
        this.nseId = nseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hapId != null ? hapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialAplicacion)) {
            return false;
        }
        HistorialAplicacion other = (HistorialAplicacion) object;
        if ((this.hapId == null && other.hapId != null) || (this.hapId != null && !this.hapId.equals(other.hapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.HistorialAplicacion[ hapId=" + hapId + " ]";
    }
    
}
