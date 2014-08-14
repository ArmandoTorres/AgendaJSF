package com.agenda.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** * @author Armando */
public class Connexion {
    
    private static EntityManagerFactory EMF;
    
    public static EntityManagerFactory getPersistUnit(){
        
        if (EMF == null){
            EMF = Persistence.createEntityManagerFactory("AgendaPU");
        }
        
        return EMF;
    }
    
}
