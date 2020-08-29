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
@Table(name = "BIT_GEN_GENERALIDADES", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Generalidades.findAll", query = "SELECT g FROM Generalidades g"),
    @NamedQuery(name = "Generalidades.findByGenId", query = "SELECT g FROM Generalidades g WHERE g.genId = :genId"),
    @NamedQuery(name = "Generalidades.findByGenInternet", query = "SELECT g FROM Generalidades g WHERE g.genInternet = :genInternet"),
    @NamedQuery(name = "Generalidades.findByGenComputadora", query = "SELECT g FROM Generalidades g WHERE g.genComputadora = :genComputadora"),
    @NamedQuery(name = "Generalidades.findByGenAspiracionLab", query = "SELECT g FROM Generalidades g WHERE g.genAspiracionLab = :genAspiracionLab"),
    @NamedQuery(name = "Generalidades.findByGenAspiracionSal", query = "SELECT g FROM Generalidades g WHERE g.genAspiracionSal = :genAspiracionSal"),
    @NamedQuery(name = "Generalidades.findByGenTiempo", query = "SELECT g FROM Generalidades g WHERE g.genTiempo = :genTiempo"),
    @NamedQuery(name = "Generalidades.findByGenAspiracionCurso", query = "SELECT g FROM Generalidades g WHERE g.genAspiracionCurso = :genAspiracionCurso"),
    @NamedQuery(name = "Generalidades.findByGenEnterado", query = "SELECT g FROM Generalidades g WHERE g.genEnterado = :genEnterado"),
    @NamedQuery(name = "Generalidades.findByGenOtrosConocimientos", query = "SELECT g FROM Generalidades g WHERE g.genOtrosConocimientos = :genOtrosConocimientos"),
    @NamedQuery(name = "Generalidades.findByGenLinkedin", query = "SELECT g FROM Generalidades g WHERE g.genLinkedin = :genLinkedin")})
public class Generalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GEN_ID", nullable = false)
    private Integer genId;
    @Size(max = 2)
    @Column(name = "GEN_INTERNET", length = 2)
    private String genInternet;
    @Size(max = 2)
    @Column(name = "GEN_COMPUTADORA", length = 2)
    private String genComputadora;
    @Size(max = 600)
    @Column(name = "GEN_ASPIRACION_LAB", length = 600)
    private String genAspiracionLab;
    @Column(name = "GEN_ASPIRACION_SAL")
    private Integer genAspiracionSal;
    @Size(max = 50)
    @Column(name = "GEN_TIEMPO", length = 50)
    private String genTiempo;
    @Size(max = 600)
    @Column(name = "GEN_ASPIRACION_CURSO", length = 600)
    private String genAspiracionCurso;
    @Size(max = 300)
    @Column(name = "GEN_ENTERADO", length = 300)
    private String genEnterado;
    @Size(max = 600)
    @Column(name = "GEN_OTROS_CONOCIMIENTOS", length = 600)
    private String genOtrosConocimientos;
    @Size(max = 300)
    @Column(name = "GEN_LINKEDIN", length = 300)
    private String genLinkedin;
    @OneToMany(mappedBy = "genId", fetch = FetchType.LAZY)
    private List<Candidato> candidatoList;

    public Generalidades() {
    }

    public Generalidades(Integer genId, String genInternet, String genComputadora, String genAspiracionLab, Integer genAspiracionSal, String genTiempo, String genAspiracionCurso, String genEnterado, String genOtrosConocimientos, String genLinkedin) {
        this.genId = genId;
        this.genInternet = genInternet;
        this.genComputadora = genComputadora;
        this.genAspiracionLab = genAspiracionLab;
        this.genAspiracionSal = genAspiracionSal;
        this.genTiempo = genTiempo;
        this.genAspiracionCurso = genAspiracionCurso;
        this.genEnterado = genEnterado;
        this.genOtrosConocimientos = genOtrosConocimientos;
        this.genLinkedin = genLinkedin;
    }

    
   
    public Generalidades(Integer genId) {
        this.genId = genId;
    }

 

    public Integer getGenId() {
        return genId;
    }

    public void setGenId(Integer genId) {
        this.genId = genId;
    }

    public String getGenInternet() {
        return genInternet;
    }

    public void setGenInternet(String genInternet) {
        this.genInternet = genInternet;
    }

    public String getGenComputadora() {
        return genComputadora;
    }

    public void setGenComputadora(String genComputadora) {
        this.genComputadora = genComputadora;
    }

    public String getGenAspiracionLab() {
        return genAspiracionLab;
    }

    public void setGenAspiracionLab(String genAspiracionLab) {
        this.genAspiracionLab = genAspiracionLab;
    }

    public Integer getGenAspiracionSal() {
        return genAspiracionSal;
    }

    public void setGenAspiracionSal(Integer genAspiracionSal) {
        this.genAspiracionSal = genAspiracionSal;
    }

    public String getGenTiempo() {
        return genTiempo;
    }

    public void setGenTiempo(String genTiempo) {
        this.genTiempo = genTiempo;
    }

    public String getGenAspiracionCurso() {
        return genAspiracionCurso;
    }

    public void setGenAspiracionCurso(String genAspiracionCurso) {
        this.genAspiracionCurso = genAspiracionCurso;
    }

    public String getGenEnterado() {
        return genEnterado;
    }

    public void setGenEnterado(String genEnterado) {
        this.genEnterado = genEnterado;
    }

    public String getGenOtrosConocimientos() {
        return genOtrosConocimientos;
    }

    public void setGenOtrosConocimientos(String genOtrosConocimientos) {
        this.genOtrosConocimientos = genOtrosConocimientos;
    }

    public String getGenLinkedin() {
        return genLinkedin;
    }

    public void setGenLinkedin(String genLinkedin) {
        this.genLinkedin = genLinkedin;
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
        hash += (genId != null ? genId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generalidades)) {
            return false;
        }
        Generalidades other = (Generalidades) object;
        if ((this.genId == null && other.genId != null) || (this.genId != null && !this.genId.equals(other.genId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Generalidades[ genId=" + genId + " ]";
    }
    
}
