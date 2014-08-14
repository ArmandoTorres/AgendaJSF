package com.agenda.dao;

import com.agenda.entities.CloseAccount;
import com.agenda.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Armando
 */
public class CloseAccountDao extends GenericDao<CloseAccount> {
    
    public CloseAccountDao(){
        super();
    }
    
    public List<CloseAccount> findByUser(User user) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("P1",user);
        return super.findAllByCriteria(CloseAccount.FIND_BY_USER, param);
    }
    
    public CloseAccount findById(int id){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("P2",id);
        return super.findOne(CloseAccount.FIND_BY_ID, param);
    }
}