package com.agenda.dao;

import com.agenda.entities.Reasons;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Armando
 */
public class ReasonsDao extends GenericDao<Reasons>{

    public ReasonsDao() {
        super();
    }
    
    public List<Reasons> findByDescription(String description){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("P1", description);
        return super.findAllByCriteria(Reasons.FIND_BY_DESCRIPTION, param);
    }
    
    public Reasons findById (int id){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("P2", id);
        return super.findOne(Reasons.FIND_BY_ID, param);
    }
    
}
