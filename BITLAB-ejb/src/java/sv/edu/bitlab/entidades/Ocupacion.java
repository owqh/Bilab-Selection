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
@Table(name = "BIT_OCU_OCUPACION", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o"),
    @NamedQuery(name = "Ocupacion.findByOcuId", query = "SELECT o FROM Ocupacion o WHERE o.ocuId = :ocuId"),
    @NamedQuery(name = "Ocupacion.findByOcuNombre", query = "SELECT o FROM Ocupacion o WHERE o.ocuNombre = :ocuNombre")})
public class Ocupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OCU_ID", nullable = false)
    private Integer ocuId;
    @Size(max = 75)
    @Column(name = "OCU_NOMBRE", length = 75)
    private String ocuNombre;
    @OneToMany(mappedBy = "ocuId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public Ocupacion() {
    }

    public Ocupacion(Integer ocuId) {
        this.ocuId = ocuId;
    }

    public Integer getOcuId() {
        return ocuId;
    }

    public void setOcuId(Integer ocuId) {
        this.ocuId = ocuId;
    }

    public String getOcuNombre() {
        return ocuNombre;
    }

    public void setOcuNombre(String ocuNombre) {
        this.ocuNombre = ocuNombre;
    }

    public List<Candidato> getCandidatoList() {
        return candidatoList;
    }

    public void setCandidatoList(List<Candidato> candidatoList) {
        this.candidatoList = candidatoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ocuId != null ? ocuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupacion)) {
            return false;
        }
        Ocupacion other = (Ocupacion) object;
        if ((this.ocuId == null && other.ocuId != null) || (this.ocuId != null && !this.ocuId.equals(other.ocuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Ocupacion[ ocuId=" + ocuId + " ]";
    }
    
}
