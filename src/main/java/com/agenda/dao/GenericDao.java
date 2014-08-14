package com.agenda.dao;

import com.agenda.util.Connexion;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Armando
 */
public abstract class GenericDao<A> {
    
    private EntityManagerFactory EMF;
    private EntityManager        EM;
    private EntityTransaction    ET;

    public GenericDao() {
        EMF = Connexion.getPersistUnit();
        EM  = EMF.createEntityManager();
        ET  = EM.getTransaction();
    }
    
    public void save(A obj) {
        ET.begin();
        EM.persist(obj);
        ET.commit();
    }

    public void update(A obj) {
        ET.begin();
        EM.merge(obj);
        ET.commit();
    }

    public void delete(A obj) {
        ET.begin();
        EM.remove(EM.merge(obj));
        ET.commit();
    }
    
    public List<A> findAll(Class<A> entity ){
        CriteriaQuery cq = EM.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return EM.createQuery(cq).getResultList();
    }
    
    protected A findOne(String namedQuery, Map<String, Object> param){
        
        A result = null;
        
        try {
            Query q = EM.createNamedQuery(namedQuery);
            if (param != null && !param.isEmpty()){
                for(Entry<String, Object> entry : param.entrySet()){
                    q.setParameter(entry.getKey(), entry.getValue());
                }
            }
            
            result = (A) q.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No result found at find one by namedquery "+namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query find one. "+ e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    protected List<A> findAllByCriteria(String namedQuery, Map<String, Object> param){
        List<A> result = null;
        
        try {
            Query q = EM.createNamedQuery(namedQuery);
            if (param != null && !param.isEmpty()){
                for(Entry<String, Object> entry : param.entrySet()){
                    q.setParameter(entry.getKey(), entry.getValue());
                }
            }
            result = (List<A>) q.getResultList();
        } catch (NoResultException ex) {
            System.out.println("No result found at find one by namedquery "+namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query find one. "+ e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
}