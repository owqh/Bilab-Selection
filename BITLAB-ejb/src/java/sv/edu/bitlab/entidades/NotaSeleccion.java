/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
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

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_NSE_NOTA_SELECCION", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "NotaSeleccion.findAll", query = "SELECT n FROM NotaSeleccion n"),
    @NamedQuery(name = "NotaSeleccion.findByNseId", query = "SELECT n FROM NotaSeleccion n WHERE n.nseId = :nseId"),
    @NamedQuery(name = "NotaSeleccion.findByNseNota", query = "SELECT n FROM NotaSeleccion n WHERE n.nseNota = :nseNota"),
    @NamedQuery(name = "NotaSeleccion.findByNsePromedio", query = "SELECT n FROM NotaSeleccion n WHERE n.nsePromedio = :nsePromedio")})
public class NotaSeleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NSE_ID", nullable = false)
    private Integer nseId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NSE_NOTA", precision = 22, scale = 0)
    private Double nseNota;
    @Column(name = "NSE_PROMEDIO", precision = 22, scale = 0)
    private Double nsePromedio;
    @OneToMany(mappedBy = "nseId", fetch = FetchType.LAZY)
    private List<HistorialAplicacion> historialAplicacionList;
    @JoinColumn(name = "PRU_ID", referencedColumnName = "PRU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pruebas pruId;

    public NotaSeleccion() {
    }
     public NotaSeleccion(Double nseNota, Double nsePromedio, Pruebas pruId) {
        this.nseNota = nseNota;
        this.nsePromedio = nsePromedio;
        this.pruId = pruId;
    }
    

    public NotaSeleccion(Integer nseId) {
        this.nseId = nseId;
    }

    public Integer getNseId() {
        return nseId;
    }

    public void setNseId(Integer nseId) {
        this.nseId = nseId;
    }

    public Double getNseNota() {
        return nseNota;
    }

    public void setNseNota(Double nseNota) {
        this.nseNota = nseNota;
    }

    public Double getNsePromedio() {
        return nsePromedio;
    }

    public void setNsePromedio(Double nsePromedio) {
        this.nsePromedio = nsePromedio;
    }

    public List<HistorialAplicacion> getHistorialAplicacionList() {
        return historialAplicacionList;
    }

    public void setHistorialAplicacionList(List<HistorialAplicacion> historialAplicacionList) {
        this.historialAplicacionList = historialAplicacionList;
    }

    public Pruebas getPruId() {
        return pruId;
    }

    public void setPruId(Pruebas pruId) {
        this.pruId = pruId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nseId != null ? nseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaSeleccion)) {
            return false;
        }
        NotaSeleccion other = (NotaSeleccion) object;
        if ((this.nseId == null && other.nseId != null) || (this.nseId != null && !this.nseId.equals(other.nseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.NotaSeleccion[ nseId=" + nseId + " ]";
    }
    
}
