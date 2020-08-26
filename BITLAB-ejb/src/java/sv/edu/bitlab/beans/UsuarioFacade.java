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
import sv.edu.bitlab.entidades.Usuario;

/**
 *
 * @author Mario
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BITLAB-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario ObtenerUsuario(String usuario) throws Exception {
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usrAcceso = :usuario");
            q.setParameter("usuario", usuario);
             return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception(e);
        }

       
    }

}
