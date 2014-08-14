package com.agenda.managebean;

import com.agenda.dao.AgendaDaoImpl;
import com.agenda.entities.Agenda;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * @author Armando
 */
@ManagedBean
@ViewScoped
public class EditContactBean implements Serializable {
    
    private Agenda contacto;
    private AgendaDaoImpl impl;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agenda getContacto() {
        return contacto;
    }

    public void setContacto(Agenda contacto) {
        this.contacto = contacto;
    }
    
    @PostConstruct
    public void init(){
        impl = new AgendaDaoImpl();
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!map.isEmpty()){
            String id = map.get("selectedUser");
            this.id = Integer.parseInt(id);
            if (id != null){
                contacto = impl.findById(this.id);
            }
        }
    }
    
    public String save(){
        try {
            impl.update(contacto);
        } catch (Exception e){
            com.agenda.util.JSFUtil.showMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        return "findContact?faces-redirect=true";
    }
}