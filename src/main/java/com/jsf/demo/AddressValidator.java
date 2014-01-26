package com.jsf.demo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "addressValidator")
public class AddressValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		MiscUtils.checkForNull(context, "context");
		
		MiscUtils.checkForNull(component, "component");

		String address = (String) value;

		if (!MiscUtils.isValidAddress(address)) {

			FacesMessage message = new FacesMessage();

			String text = "Address is not valid - only letters, numbers, spaces allowed and should not be a 'PO Box' address)";
			message.setDetail(text);
			message.setSummary(text);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(message);
		}
	}

}