/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andre
 */
public class EntityManagerSingleton {
    
    private static EntityManagerSingleton instance;
    private EntityManager entityManager;
    
    private EntityManagerSingleton() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TranspoBrasilPU");
        entityManager = emf.createEntityManager();
    }
    
    public static EntityManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerSingleton();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
