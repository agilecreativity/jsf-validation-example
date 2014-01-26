package com.jsf.demo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "mailingAddressValidator")
public class MailingAddressValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object objectValue) throws ValidatorException {

		MiscUtils.checkForNull(context, "context");
		
		MiscUtils.checkForNull(component, "component");

    	String address = (String) objectValue;

    	if (!MiscUtils.isValidAddress(address)) {
    		String messageText = MiscUtils.getMessage("valid_mailing_address");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText,messageText));
        }
    }

}