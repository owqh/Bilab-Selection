/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.bitlab.entidades.EstadoAplicacion;

/**
 *
 * @author Mario
 */
@Stateless
public class EstadoAplicacionFacade extends AbstractFacade<EstadoAplicacion> {

    @PersistenceContext(unitName = "BITLAB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoAplicacionFacade() {
        super(EstadoAplicacion.class);
    }
    
}
