package com.jsf.demo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "residentialAddressValidator")
public class ResidentialAddressValidator extends MailingAddressValidator {

	public void validate(FacesContext context, UIComponent component, Object objectValue) throws ValidatorException {

		MiscUtils.checkForNull(context, "context");
		
		MiscUtils.checkForNull(component, "component");

		String address = (String) objectValue;
    	
		//Note: include the check for 'PO Box' type of address
    	boolean excludePoBoxAddress = true;
    	if (!MiscUtils.isValidAddress(address, excludePoBoxAddress)) {
    		String messageText = MiscUtils.getMessage("valid_residential_address");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText,messageText));
    	}
    }

}