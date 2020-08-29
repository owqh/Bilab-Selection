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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_PRU_PRUEBAS", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pruebas.findAll", query = "SELECT p FROM Pruebas p"),
    @NamedQuery(name = "Pruebas.findByPruId", query = "SELECT p FROM Pruebas p WHERE p.pruId = :pruId"),
    @NamedQuery(name = "Pruebas.findByPruNombre", query = "SELECT p FROM Pruebas p WHERE p.pruNombre = :pruNombre"),
    @NamedQuery(name = "Pruebas.findByPruPorcentaje", query = "SELECT p FROM Pruebas p WHERE p.pruPorcentaje = :pruPorcentaje")})
public class Pruebas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRU_ID", nullable = false)
    private Integer pruId;
    @Size(max = 150)
    @Column(name = "PRU_NOMBRE", length = 150)
    private String pruNombre;
    @Column(name = "PRU_PORCENTAJE")
    private Integer pruPorcentaje;
    @OneToMany(mappedBy = "pruId", fetch = FetchType.LAZY)
    private List<NotaSeleccion> notaSeleccionList;

    public Pruebas() {
    }

    public Pruebas(Integer pruId) {
        this.pruId = pruId;
    }

    public Integer getPruId() {
        return pruId;
    }

    public void setPruId(Integer pruId) {
        this.pruId = pruId;
    }

    public String getPruNombre() {
        return pruNombre;
    }

    public void setPruNombre(String pruNombre) {
        this.pruNombre = pruNombre;
    }

    public Integer getPruPorcentaje() {
        return pruPorcentaje;
    }

    public void setPruPorcentaje(Integer pruPorcentaje) {
        this.pruPorcentaje = pruPorcentaje;
    }

    public List<NotaSeleccion> getNotaSeleccionList() {
        return notaSeleccionList;
    }

    public void setNotaSeleccionList(List<NotaSeleccion> notaSeleccionList) {
        this.notaSeleccionList = notaSeleccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruId != null ? pruId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebas)) {
            return false;
        }
        Pruebas other = (Pruebas) object;
        if ((this.pruId == null && other.pruId != null) || (this.pruId != null && !this.pruId.equals(other.pruId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Pruebas[ pruId=" + pruId + " ]";
    }
    
}
