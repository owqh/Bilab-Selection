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
@Table(name = "BIT_NAC_NIVEL_ACADEMICO", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "NivelAcademico.findAll", query = "SELECT n FROM NivelAcademico n"),
    @NamedQuery(name = "NivelAcademico.findByNacId", query = "SELECT n FROM NivelAcademico n WHERE n.nacId = :nacId"),
    @NamedQuery(name = "NivelAcademico.findByNacNombre", query = "SELECT n FROM NivelAcademico n WHERE n.nacNombre = :nacNombre")})
public class NivelAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NAC_ID", nullable = false)
    private Integer nacId;
    @Size(max = 250)
    @Column(name = "NAC_NOMBRE", length = 250)
    private String nacNombre;
    @OneToMany(mappedBy = "nacId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public NivelAcademico() {
    }

    public NivelAcademico(Integer nacId) {
        this.nacId = nacId;
    }

    public Integer getNacId() {
        return nacId;
    }

    public void setNacId(Integer nacId) {
        this.nacId = nacId;
    }

    public String getNacNombre() {
        return nacNombre;
    }

    public void setNacNombre(String nacNombre) {
        this.nacNombre = nacNombre;
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
        hash += (nacId != null ? nacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcademico)) {
            return false;
        }
        NivelAcademico other = (NivelAcademico) object;
        if ((this.nacId == null && other.nacId != null) || (this.nacId != null && !this.nacId.equals(other.nacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.NivelAcademico[ nacId=" + nacId + " ]";
    }
    
}
