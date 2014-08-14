package com.agenda.dao;

import com.agenda.entities.Agenda;
import com.agenda.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Armando
 */
public class AgendaDaoImpl extends GenericDao<Agenda> {

    public AgendaDaoImpl() {
        super();
    }
    
    public List<Agenda> findByUser(User user){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("P1", user);
        return super.findAllByCriteria(Agenda.FIND_BY_USER, parameters);
    }
    
    public List<Agenda> findByParameter(String param, User usr){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("P2","%"+param+"%");
        parameters.put("P3",usr);
        return super.findAllByCriteria(Agenda.FIND_BY_PARAMETER, parameters);
    }
    
    public Agenda findById(int id){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("P4",id);
        return super.findOne(Agenda.FIND_BY_ID, parameters);
    }
}
