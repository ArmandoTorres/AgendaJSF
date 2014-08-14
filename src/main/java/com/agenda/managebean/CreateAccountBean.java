package com.agenda.managebean;

import com.agenda.dao.UserDaoImpl;
import com.agenda.entities.Roles;
import com.agenda.entities.Status;
import com.agenda.entities.User;
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
public class CreateAccountBean implements Serializable {
    
    private UserDaoImpl usrImpl;
    private User user;
    private String password2;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @PostConstruct
    public void init(){
        usrImpl = new UserDaoImpl();
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (map.get("pId") == null) {
            user    = new User();
            user.setStatus(Status.A);
        } else {
            user = usrImpl.findUserById(Integer.parseInt(map.get("pId")));
        }
    }
    
    public String save(){
        if (user.getPassword().equals(password2)){
            user.setRole(Roles.PARTICIPANTE);
            usrImpl.save(user);
            return "/index?faces-redirect=true";
        } 
        com.agenda.util.JSFUtil.showMessage("Las constraseñas no coinciden.", FacesMessage.SEVERITY_ERROR);
        return null;
    }
    
    public String saveAdminUser(){
        if (role.equals("ADMIN")){
            user.setRole(Roles.ADMIN);
        } else if (role.equals("PARTICIPANTE")) {
            user.setRole(Roles.PARTICIPANTE);
        }
        usrImpl.save(user);
        com.agenda.util.JSFUtil.showMessage("El usuario ha sido guardado correctamente.",FacesMessage.SEVERITY_INFO);
        return "addUser?faces-redirect=true";
    }
}