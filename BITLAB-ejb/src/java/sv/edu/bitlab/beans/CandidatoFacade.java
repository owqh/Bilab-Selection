/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.bitlab.entidades.Candidato;

/**
 *
 * @author Mario
 */
@Stateless
public class CandidatoFacade extends AbstractFacade<Candidato> {
    // varibales y lista de tipo candidatos.
    List<Candidato> listaCandidado = new ArrayList<>();
    private Candidato candidato = new Candidato();
    @PersistenceContext(unitName = "BITLAB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidatoFacade() {
        super(Candidato.class);
    }
     public List<Candidato> candidatoPorEstadoAplicacion (String estadoAplicacion) throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.eapId.eapNombre= :estadoAplicacion");
            q.setParameter("estadoAplicacion", estadoAplicacion);
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
    
    
    public List<Candidato> candidatosPotencialList() {
        List<Candidato> listaCandidado = new ArrayList<>();

        return listaCandidado = CandidatoFacade.super.findAll();

    }
    
    public List<Candidato> CandidatoTipoPRe(){
        if (candidato.getEapId().equals(1)){
           
           return listaCandidado = CandidatoFacade.super.findAll();
        }
        
        return null;
    }
    
      public List<Candidato> candidatoPre () throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.eapId.eapId = 1");
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
      
      //lista de candidatos potenciales
     public List<Candidato> candidatoPotencial () throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.genId.genInternet='si' and c.genId.genComputadora='si' and c.genId.genTiempo='si' and c.eapId.eapId=9");
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
     //lista de candidatos Regular alto
       public List<Candidato> candidatoRegularAlto () throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.genId.genInternet='si' and c.genId.genComputadora='si' and c.genId.genTiempo='no' and c.eapId.eapId=9");
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
       
        public List<Candidato> candidatoRegularBajo () throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.genId.genInternet='si' and c.genId.genComputadora='no' and c.genId.genTiempo='no' and c.eapId.eapId=9");
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
        
           public List<Candidato> candidatoSinOportunidad () throws Exception {
      try {
            Query q = em.createQuery("select c from Candidato c where c.genId.genInternet='no' and c.genId.genComputadora='no' and c.genId.genTiempo='no' and c.eapId.eapId=9");
           return q.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
       
}
