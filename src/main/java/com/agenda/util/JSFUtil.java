package com.agenda.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * @author Armando
 */
public class JSFUtil {
    
    public static void showMessage(String msj, Severity type){
        FacesMessage msg = new FacesMessage(msj);
        msg.setSeverity(type);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
