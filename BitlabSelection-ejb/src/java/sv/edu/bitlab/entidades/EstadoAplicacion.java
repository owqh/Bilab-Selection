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
@Table(name = "BIT_EAP_ESTADO_APLICACION", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "EstadoAplicacion.findAll", query = "SELECT e FROM EstadoAplicacion e"),
    @NamedQuery(name = "EstadoAplicacion.findByEapId", query = "SELECT e FROM EstadoAplicacion e WHERE e.eapId = :eapId"),
    @NamedQuery(name = "EstadoAplicacion.findByEapNombre", query = "SELECT e FROM EstadoAplicacion e WHERE e.eapNombre = :eapNombre")})
public class EstadoAplicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EAP_ID", nullable = false)
    private Integer eapId;
    @Size(max = 50)
    @Column(name = "EAP_NOMBRE", length = 50)
    private String eapNombre;
    @OneToMany(mappedBy = "eapId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public EstadoAplicacion() {
    }

    public EstadoAplicacion(Integer eapId) {
        this.eapId = eapId;
    }

    public Integer getEapId() {
        return eapId;
    }

    public void setEapId(Integer eapId) {
        this.eapId = eapId;
    }

    public String getEapNombre() {
        return eapNombre;
    }

    public void setEapNombre(String eapNombre) {
        this.eapNombre = eapNombre;
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
        hash += (eapId != null ? eapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoAplicacion)) {
            return false;
        }
        EstadoAplicacion other = (EstadoAplicacion) object;
        if ((this.eapId == null && other.eapId != null) || (this.eapId != null && !this.eapId.equals(other.eapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.EstadoAplicacion[ eapId=" + eapId + " ]";
    }
    
}
