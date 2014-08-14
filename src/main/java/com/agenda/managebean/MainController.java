
package com.agenda.managebean;

import com.agenda.dao.UserDaoImpl;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
@ManagedBean
@SessionScoped
public class MainController implements Serializable {
    
    private UserDaoImpl usrImpl;
    
    private String email;
    private String password;

    public UserDaoImpl getUsrImpl() {
        return usrImpl;
    }

    public void setUsrImpl(UserDaoImpl usrImpl) {
        this.usrImpl = usrImpl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @PostConstruct
    public void init() {
        usrImpl = new UserDaoImpl();        
    }
    
    public String login() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
        if (email != null && password != null) {
            if (usrImpl.authenticate(email, password)){
                session.setAttribute("CurrentUser", usrImpl.findUserByEmail(email));
                session.setAttribute("isAuthenticated", true);
                session.setAttribute("UserRolType", usrImpl.findUserByEmail(email).getRole());
                return "pages/welcome";
            } else {
                FacesMessage msj = new FacesMessage("Usuario y/o contrasena no son validos!");
                msj.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } 
        return "index";
    }
    
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    
}