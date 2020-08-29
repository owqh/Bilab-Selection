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
@Table(name = "BIT_ACT_ACTIVIDADES", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a"),
    @NamedQuery(name = "Actividades.findByActId", query = "SELECT a FROM Actividades a WHERE a.actId = :actId"),
    @NamedQuery(name = "Actividades.findByActNombre", query = "SELECT a FROM Actividades a WHERE a.actNombre = :actNombre"),
    @NamedQuery(name = "Actividades.findByActPorcentaje", query = "SELECT a FROM Actividades a WHERE a.actPorcentaje = :actPorcentaje")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACT_ID", nullable = false)
    private Integer actId;
    @Size(max = 100)
    @Column(name = "ACT_NOMBRE", length = 100)
    private String actNombre;
    @Column(name = "ACT_PORCENTAJE")
    private Integer actPorcentaje;
    @OneToMany(mappedBy = "actId", fetch = FetchType.LAZY)
    private List<NotaCurso> notaCursoList;

    public Actividades() {
    }

    public Actividades(Integer actId) {
        this.actId = actId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }

    public Integer getActPorcentaje() {
        return actPorcentaje;
    }

    public void setActPorcentaje(Integer actPorcentaje) {
        this.actPorcentaje = actPorcentaje;
    }

    public List<NotaCurso> getNotaCursoList() {
        return notaCursoList;
    }

    public void setNotaCursoList(List<NotaCurso> notaCursoList) {
        this.notaCursoList = notaCursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actId != null ? actId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.actId == null && other.actId != null) || (this.actId != null && !this.actId.equals(other.actId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Actividades[ actId=" + actId + " ]";
    }
    
}
