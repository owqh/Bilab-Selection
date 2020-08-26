/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.bitlab.entidades.HistorialAplicacion;

/**
 *
 * @author Mario
 */
@Stateless
public class HistorialAplicacionFacade extends AbstractFacade<HistorialAplicacion> {

    @PersistenceContext(unitName = "BITLAB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialAplicacionFacade() {
        super(HistorialAplicacion.class);
    }
    
     public Object buscarPorIdCandidato(Object id) {
        Query q = em.createQuery("Select c from HistorialAplicacion c where c.canId.canId= :id");
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    
}
