/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "BIT_SEX_SEXO", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findBySexId", query = "SELECT s FROM Sexo s WHERE s.sexId = :sexId"),
    @NamedQuery(name = "Sexo.findBySexNombre", query = "SELECT s FROM Sexo s WHERE s.sexNombre = :sexNombre")})
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEX_ID", nullable = false)
    private Integer sexId;
    @Size(max = 25)
    @Column(name = "SEX_NOMBRE", length = 25)
    private String sexNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public Sexo() {
    }

    public Sexo(Integer sexId) {
        this.sexId = sexId;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getSexNombre() {
        return sexNombre;
    }

    public void setSexNombre(String sexNombre) {
        this.sexNombre = sexNombre;
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
        hash += (sexId != null ? sexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.sexId == null && other.sexId != null) || (this.sexId != null && !this.sexId.equals(other.sexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Sexo[ sexId=" + sexId + " ]";
    }
    
}
