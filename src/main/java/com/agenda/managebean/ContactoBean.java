package com.agenda.managebean;

import com.agenda.dao.AgendaDaoImpl;
import com.agenda.entities.Agenda;
import com.agenda.entities.User;
import com.agenda.util.Paginacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
@ManagedBean
@ViewScoped
public class ContactoBean implements Serializable {
    
    private AgendaDaoImpl contacto;
    private List<Agenda> lista;
    private String param;
    private User currentUser;
    private Paginacion paginacion;

    public Paginacion getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Paginacion paginacion) {
        this.paginacion = paginacion;
    }
    
    public List<Agenda> getLista() {
        return lista;
    }

    public void setlista(List<Agenda> lista) {
        this.lista = lista;
    }

    public void setParam(String param){
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    @PostConstruct
    public void init(){
        contacto = new AgendaDaoImpl();
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession hs   = (HttpSession) ctx.getExternalContext().getSession(false);
        currentUser      = (User) hs.getAttribute("CurrentUser");
        paginacion = new Paginacion(contacto.findAll(Agenda.class),7);
        lista = paginacion.getFirtRowSet();
    }
    
    public String buscarContacto(){
        if (param != null){
            if (contacto.findByParameter(param, currentUser) != null) {
                paginacion.setSubList( contacto.findByParameter(param, currentUser) );
            }
        } else {
            paginacion.setSubList(contacto.findByUser(currentUser));
        }
        refrestTable();
        return null;
    }
    
    public void delete(Agenda agd){
        int position = paginacion.getCurrentPosition();
        contacto.delete(agd);
        lista = contacto.findByParameter(param,currentUser);
        paginacion = new Paginacion(contacto.findAll(Agenda.class),7);
        refrestTable();
        lista = paginacion.setCurrentPosition(position);
    }
    
    public String edit(int id){
        return "editContact?faces-redirect=true&selectedUser="+id;
    }
    
    public void next(){
        lista = (List<Agenda>) paginacion.next();
    }
    
    public void previous(){
        lista = (List<Agenda>) paginacion.previous();
    }
    
    public String hasPrevious(){
        return paginacion.hasPrevious() ? "" : " disabled";
    }
    
    public String hasNext(){
        return paginacion.hasNext() ? "" : " disabled";
    }
    
    public void refrestTable(){
        lista = paginacion.getFirtRowSet();
    }
    
    public void setTablePosition(int i){
        lista = paginacion.setCurrentPosition(i);
    }
    
}