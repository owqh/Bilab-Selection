/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.bitlab.entidades.Usuario;

/**
 *
 * @author Oscar
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BitlabSelection-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario ObtenerUsuario(String usuario){
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.usrAcceso = :usuario");
        q.setParameter("usuario", usuario);
        return (Usuario) q.getSingleResult();
    }
    
    
}
