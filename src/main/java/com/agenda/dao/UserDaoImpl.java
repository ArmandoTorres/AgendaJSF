package com.agenda.dao;

import com.agenda.entities.Status;
import com.agenda.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Armando
 */
public class UserDaoImpl extends GenericDao<User> {

    public UserDaoImpl() {
        super();
    }
    
    public User findUserById(int id){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        return super.findOne(User.FIND_BY_ID, parameters);
    }
    
    public User findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("P4", email);
        parameters.put("status", Status.A);
        return super.findOne(User.FIND_BY_EMAIL, parameters);
    }    
    
    public boolean authenticate(String email, String pass) {
       Map<String, Object> param = new HashMap<String, Object>();
       param.put("P2", email);
       param.put("P3", pass);
       param.put("status", Status.A);
       Object obj = super.findOne(User.AUTHENTICATE, param);
       return !(obj == null);
    }
    
    public List<User> findAllActive(){
       Map<String, Object> param = new HashMap<String, Object>();
       param.put("status", Status.A);
       return super.findAllByCriteria(User.FIND_ALL_ACTIVE, param);
    }
    
}