/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.bitlab.entidades.Tecnologia;

/**
 *
 * @author Oscar
 */
@Stateless
public class TecnologiaFacade extends AbstractFacade<Tecnologia> {

    @PersistenceContext(unitName = "BitlabSelection-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnologiaFacade() {
        super(Tecnologia.class);
    }
    
}
