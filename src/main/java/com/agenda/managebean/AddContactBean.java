package com.agenda.managebean;

import com.agenda.dao.AgendaDaoImpl;
import com.agenda.entities.Agenda;
import com.agenda.entities.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
@ManagedBean
@ViewScoped
public class AddContactBean implements Serializable {
    
    private AgendaDaoImpl impl;
    private Agenda contacto;
    private String msgStyle = "alert alert-danger";
    private User currentUser;

    public String getMsgStyle() {
        return msgStyle;
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
        contacto = new Agenda();
        HttpSession hs = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        currentUser = (User) hs.getAttribute("CurrentUser");
    }
    
    public void save(){
        if (contacto != null){
            contacto.setUser(currentUser);
            impl.save(contacto);
            msgStyle = "alert alert-success";
            com.agenda.util.JSFUtil.showMessage("El contacto se ha creado correctamente", FacesMessage.SEVERITY_INFO);
            contacto = null;
        }
    }
}