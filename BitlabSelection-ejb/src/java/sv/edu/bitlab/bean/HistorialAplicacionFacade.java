/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.bitlab.entidades.HistorialAplicacion;

/**
 *
 * @author Oscar
 */
@Stateless
public class HistorialAplicacionFacade extends AbstractFacade<HistorialAplicacion> {

    @PersistenceContext(unitName = "BitlabSelection-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialAplicacionFacade() {
        super(HistorialAplicacion.class);
    }
    
}
