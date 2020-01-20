/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Category;
import entities.Request;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Henrik
 */
public class RequestFacade {
        private static RequestFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private RequestFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RequestFacade getRequestFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RequestFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public List<Category> getAllCategories(){
        EntityManager em = emf.createEntityManager();
        try{
            List<Category> categories = em.createQuery("SELECT c FROM Category c").getResultList();
            for(Category cat : categories)
                cat.setRequests(null);
            return categories;
        }finally{  
            em.close();
        }
        
    }
    
    public Category getCategory(String category) throws NoResultException {
        EntityManager em = getEntityManager();
        try {
            Category c = em.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class).setParameter("name", category).getSingleResult();
            return c;
        } finally {
            em.close();
        }
    }
    
    public void createRequest(List<Category> categories){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            for(Category category : categories){
                em.merge(category);
            }
            Request request = new Request(new Date(), categories);
            em.persist(request);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
