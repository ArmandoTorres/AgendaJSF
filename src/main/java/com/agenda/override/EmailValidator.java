package com.agenda.override;

import com.agenda.dao.UserDaoImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Armando
 */
@FacesValidator("com.agenda.override.EmailValidator")
public class EmailValidator implements Validator {

    private final UserDaoImpl usrImpl;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private final Pattern pattern;
    private Matcher matcher;
    
    public EmailValidator(){
        usrImpl = new UserDaoImpl();
        pattern = Pattern.compile(EMAIL_REGEX);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null){
            if (usrImpl.findUserByEmail(value.toString()) != null){
                FacesMessage message = new FacesMessage("EL email ingresado ya se encuentra registrado.","EL email ingresado ya se encuentra registrado.");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
            matcher = pattern.matcher(value.toString());
            if (!matcher.matches()){
                FacesMessage message = new FacesMessage("EL email ingresado no cumple con el patron requerido Ejemplo juan@gmail.com","");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
