package com.agenda.managebean;

import com.agenda.dao.UserDaoImpl;
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
public class ChangueUserDataBean implements Serializable {
    
    private User currentUser;
    private UserDaoImpl userDao;
    HttpSession hs;
    
    private String newEmail;
    private String confirmEmail;
    private String newPassword;
    private String confirmPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String updateUserEmail(){
        if (newEmail.equals(confirmEmail)){
            currentUser.setEmail(newEmail);
            userDao.update(currentUser);
            com.agenda.util.JSFUtil.showMessage("El correo ha sido actualizado correctamente debe volver a iniciar session!", FacesMessage.SEVERITY_INFO);
            hs.invalidate();
            return "/index";
        }
        com.agenda.util.JSFUtil.showMessage("Los correos introducidos no concuerdan!", FacesMessage.SEVERITY_ERROR);
        return null;
    }
    
    public String updateUserPassword(){
        if (newPassword.equals(confirmPassword)){
            currentUser.setPassword(newPassword);
            userDao.update(currentUser);
            com.agenda.util.JSFUtil.showMessage("La contraseña ha sido actualizado correctamente debe volver a iniciar session!", FacesMessage.SEVERITY_INFO);
            hs.invalidate();
            return "/index";
        }
        com.agenda.util.JSFUtil.showMessage("Las contraseñas introducidas no concuerdan!", FacesMessage.SEVERITY_ERROR);
        return null;
    }
    
    @PostConstruct
    public void init(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        hs = (HttpSession) ctx.getExternalContext().getSession(false);
        currentUser = (User) hs.getAttribute("CurrentUser");
        userDao = new UserDaoImpl();
    }
}