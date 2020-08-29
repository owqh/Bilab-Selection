/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "BIT_TEC_TECNOLOGIA", catalog = "BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tecnologia.findAll", query = "SELECT t FROM Tecnologia t"),
    @NamedQuery(name = "Tecnologia.findByTecId", query = "SELECT t FROM Tecnologia t WHERE t.tecId = :tecId"),
    @NamedQuery(name = "Tecnologia.findByTecNombre", query = "SELECT t FROM Tecnologia t WHERE t.tecNombre = :tecNombre")})
public class Tecnologia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TEC_ID", nullable = false)
    private Integer tecId;
    @Size(max = 200)
    @Column(name = "TEC_NOMBRE", length = 200)
    private String tecNombre;
    @JoinColumn(name = "CUR_ID", referencedColumnName = "CUR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curId;

    public Tecnologia() {
    }

    public Tecnologia(Integer tecId) {
        this.tecId = tecId;
    }

    public Integer getTecId() {
        return tecId;
    }

    public void setTecId(Integer tecId) {
        this.tecId = tecId;
    }

    public String getTecNombre() {
        return tecNombre;
    }

    public void setTecNombre(String tecNombre) {
        this.tecNombre = tecNombre;
    }

    public Curso getCurId() {
        return curId;
    }

    public void setCurId(Curso curId) {
        this.curId = curId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tecId != null ? tecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnologia)) {
            return false;
        }
        Tecnologia other = (Tecnologia) object;
        if ((this.tecId == null && other.tecId != null) || (this.tecId != null && !this.tecId.equals(other.tecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.bitlab.entidades.Tecnologia[ tecId=" + tecId + " ]";
    }
    
}
