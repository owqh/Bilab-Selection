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
@Table(name = "BIT_IDI_IDIOMA", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
    @NamedQuery(name = "Idioma.findByIdiId", query = "SELECT i FROM Idioma i WHERE i.idiId = :idiId"),
    @NamedQuery(name = "Idioma.findByIdiNivel", query = "SELECT i FROM Idioma i WHERE i.idiNivel = :idiNivel")})
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDI_ID", nullable = false)
    private Integer idiId;
    @Size(max = 50)
    @Column(name = "IDI_NIVEL", length = 50)
    private String idiNivel;
    @OneToMany(mappedBy = "idiId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public Idioma() {
    }

    public Idioma(Integer idiId) {
        this.idiId = idiId;
    }

    public Integer getIdiId() {
        return idiId;
    }

    public void setIdiId(Integer idiId) {
        this.idiId = idiId;
    }

    public String getIdiNivel() {
        return idiNivel;
    }

    public void setIdiNivel(String idiNivel) {
        this.idiNivel = idiNivel;
    }

    public List<Candidato> getCandidatoList() {
        return candidatoList;
    }

    public void setCandidatoList(List<Candidato> candidatoList) {
        this.candidatoList = candidatoList;
    }

    @Override
    public int hashCode() {
        return ( idiId != null)
                ? (getClass().hashCode()+idiId.hashCode()) 
                : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (object != null && getClass() == object.getClass() && idiId != null)
                ? idiId.equals(((Idioma) object).idiId)
                : (object == this);
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Idioma[ idiId=" + idiId + " ]";
    }
    
}
