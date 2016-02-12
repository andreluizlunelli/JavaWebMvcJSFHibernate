/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.dao;

import javax.persistence.EntityManager;
import br.com.andreluizlunelli.webmvc.model.singleton.EntityManagerSingleton;

/**
 *
 * @author Andre
 */
public class BaseDAO {
    
    protected EntityManager getEntityManager() {
        return EntityManagerSingleton.getInstance().getEntityManager();
    }
    
    protected Object executar(Object o, Operacao operacao) {
        
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            
            switch(operacao) {
                
                case INSERIR: em.persist(o); break;
                case SALVAR: o = em.merge(o); break;
                case EXCLUIR: em.remove(em.merge(o)); break;
                    
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
        
        return o;
    }
    
    public void inserir(Object o) {
        executar(o, Operacao.INSERIR);
    }

    public Object salvar(Object o) {
        return executar(o, Operacao.SALVAR);
    }

    public void excluir(Object o) {
        executar(o, Operacao.EXCLUIR);
    }
    
}
