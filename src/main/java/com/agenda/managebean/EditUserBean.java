package com.agenda.managebean;

import com.agenda.dao.UserDaoImpl;
import com.agenda.entities.User;
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
public class EditUserBean {
    
    private UserDaoImpl dao;
    private User usr;
    private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }
    
    @PostConstruct
    public void init(){
        dao = new UserDaoImpl();
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (map != null){
            usr = dao.findUserById(Integer.parseInt(map.get("pId")));
        }
    }
    
    public String save(){
        if (validateFields()) {
            dao.save(usr);
            return "editUser?faces-redirect=true";
        } 
        return null;
    }
    
    private boolean validateFields(){
        if (!usr.getPassword().equals(this.password2)){
            com.agenda.util.JSFUtil.showMessage("Las dos contrasenas no coinciden!",FacesMessage.SEVERITY_ERROR);
            return false;
        }
        return true;
    }
    
}