package com.agenda.managebean;

import com.agenda.dao.UserDaoImpl;
import com.agenda.entities.Status;
import com.agenda.entities.User;
import com.agenda.util.Paginacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Armando
 */
@ManagedBean
@ViewScoped
public class UserDataTableBean implements Serializable {
    
    private UserDaoImpl userDao;
    private List<User> userList;
    private Paginacion paginacion;
    private String nextLink;
    private String previousLink;
    
    public String getNextLink() {
        nextLink = paginacion.hasNext() ? "" : "disabled";
        return nextLink;
    }

    public String getPreviousLink() {
        previousLink = paginacion.hasPrevious() ? "" : "disabled";
        return previousLink;
    }
    
    public List<User> getUserList() {
        return userList;
    }

    @PostConstruct
    public void init(){
        userDao = new UserDaoImpl();
        paginacion = new Paginacion(userDao.findAllActive(),5);
        userList = (List<User>) paginacion.getFirtRowSet();
    }
    
    public void next() {
        userList = (List<User>) paginacion.next();
    }
    
    public void previous(){
        userList = (List<User>) paginacion.previous();
    }
    
    public String deleteUser(String email){
        User usr = userDao.findUserByEmail(email);
        usr.setStatus(Status.I);
        userDao.update(usr);
        return "editUser?faces-redirect=true";
    }
    
    public String editUserForm(int id){
        return "editUserTable?faces-redirect=true&pId="+id;
    }
}