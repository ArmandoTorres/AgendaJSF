package com.agenda.managebean;

import java.util.Map;
import java.util.HashMap;
import com.agenda.dao.ReasonsDao;
import com.agenda.entities.Reasons;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import com.agenda.dao.CloseAccountDao;
import com.agenda.dao.UserDaoImpl;
import com.agenda.entities.CloseAccount;
import com.agenda.entities.Status;
import com.agenda.entities.User;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
@ManagedBean
@ViewScoped
public class CloseAccountBean implements Serializable {
    
    //Dao usado para conseguir las razones de cancelacion.
    private ReasonsDao reasonsDao;
    //Lista de razones de Cancelacion.
    private Map<String, Integer> reasonsList;
    //Razon seleccionada
    private int selectedReasonsId;
    //Registro de cuenta a cancelar
    private CloseAccount account;
    //Dao para guardar el registro de cuenta cancelada
    private CloseAccountDao accountDao;
    
    @PostConstruct
    public void init(){
        reasonsDao = new ReasonsDao();
        reasonsList = new HashMap<String, Integer>();
        for (Reasons a : reasonsDao.findAll(Reasons.class)){
            reasonsList.put(a.getDescripcion(), a.getId());
        }
        account = new CloseAccount();
        accountDao = new CloseAccountDao();
    }    
    
    public String cancelAccount(){
        //Inactivar cuenta de usuario
        if (selectedReasonsId > 0 && account.getFeedback() != null) {
            //Conseguimos al usuario para inactivarlo
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession hs = (HttpSession) ctx.getExternalContext().getSession(false);
            User user = (User) hs.getAttribute("CurrentUser");
            //Setiamos los valores por defecto de la cuenta cancelada.
            account.setReason(reasonsDao.findById(selectedReasonsId));
            account.setUser(user);
            account.setCloseDate(new Date());
            //Grabamos el registro que indica que la cuenta se cancelo
            accountDao.save(account);
            //Inactiamos al usuario
            user.setStatus(Status.I);
            new UserDaoImpl().update(user);
            //Invalidamos al usuario
            hs.invalidate();
            com.agenda.util.JSFUtil.showMessage("Su cuenta ha sido inactivada exitosamente!", FacesMessage.SEVERITY_INFO);
            return "/index?faces-redirect=true";
        } else {
            com.agenda.util.JSFUtil.showMessage("Todos los Campo son abligatorios.", FacesMessage.SEVERITY_INFO);
        }     
        return null;
    }

    public Map<String, Integer> getReasonsList() {
        return reasonsList;
    }

    public void setReasonsList(Map<String, Integer> reasonsList) {
        this.reasonsList = reasonsList;
    }

    public CloseAccount getAccount() {
        return account;
    }

    public void setAccount(CloseAccount account) {
        this.account = account;
    }

    public int getSelectedReasonsId() {
        return selectedReasonsId;
    }

    public void setSelectedReasonsId(int selectedReasonsId) {
        this.selectedReasonsId = selectedReasonsId;
    }
    
}