package com.agenda.override;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * @author Armando
 */
public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        String currentPage = context.getViewRoot().getViewId();
        
        boolean isLoginPage = currentPage.endsWith("index.xhtml");
        boolean isCreateAccount = currentPage.endsWith("addAccount.xhtml");
        
        HttpSession hs = (HttpSession) context.getExternalContext().getSession(true);
        Object isAuthenticated = hs.getAttribute("isAuthenticated");
        
        if ((!isLoginPage) && isAuthenticated == null && (!isCreateAccount)){
            NavigationHandler nav = context.getApplication().getNavigationHandler();
            nav.handleNavigation(context, null, "/index.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}