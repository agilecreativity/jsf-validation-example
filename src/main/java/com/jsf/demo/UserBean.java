package com.jsf.demo;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	final Logger logger = LoggerFactory.getLogger(UserBean.class);

	private static final long serialVersionUID = -3086578657948705344L;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	/**
	 * Default to home phone
	 */
	private PhoneType phoneType = PhoneType.HOME;

	/**
	 * Optional for mobile phone
	 */
	private String areaCode;

	/**
	 * 8 digits for home or work phone, 10 digits for mobile phone
	 */
	private String phoneNumber;

	/**
	 * Residential address line 1
	 */
	private String addressOne;

	/**
	 * Residential address line 2
	 */
	private String addressTwo;

	/**
	 * Initial value should be to residential unless changed by the user
	 */
	private boolean mailToResidential = true;

	/**
	 * Mailing address line 1
	 */
	private String mailingAddressOne;

	/**
	 * Mailing address line 2
	 */
	private String mailingAddressTwo;

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public boolean isMailToResidential() {
		return mailToResidential;
	}

	public void setMailToResidential(boolean mailToResidential) {
		this.mailToResidential = mailToResidential;
	}

	public String getMailingAddressOne() {
		return mailingAddressOne;
	}

	public void setMailingAddressOne(String mailingAddressOne) {
		this.mailingAddressOne = mailingAddressOne;
	}

	public String getMailingAddressTwo() {
		return mailingAddressTwo;
	}

	public void setMailingAddressTwo(String mailingAddressTwo) {
		this.mailingAddressTwo = mailingAddressTwo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Clear the value of the residential address
	 * 
	 * @param event
	 */
	public void resetResidentialData(AjaxBehaviorEvent event) {
		// Note: reset the value of mailing address just in case.
		if (isMailToResidential()) {
			this.setMailingAddressOne(null);
			this.setMailingAddressTwo(null);
		}
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void validatePhoneNumber(ComponentSystemEvent event) {

		UIComponent source = event.getComponent();

		UIInput phoneTypeInput = (UIInput) source.findComponent(MiscUtils
				.getMessage("id_phone_type"));

		UIInput phoneAreaInput = (UIInput) source.findComponent(MiscUtils
				.getMessage("id_phone_area"));

		UIInput phoneNumberInput = (UIInput) source.findComponent(MiscUtils
				.getMessage("id_phone_number"));

		PhoneType phoneType = (PhoneType) phoneTypeInput.getLocalValue();
		String phoneArea = (String) phoneAreaInput.getLocalValue();
		String phoneNumber = (String) phoneNumberInput.getLocalValue();

		if (!MiscUtils.isValidPhone(phoneType, phoneArea, phoneNumber)) {

			String messageText;

			if (phoneType != PhoneType.MOBILE) {
				messageText = MiscUtils.getMessage(
						MiscUtils.getMessage("valid_phone_no"),
						new Object[] { phoneType.getLabel().toLowerCase() });
			} else {
				messageText = MiscUtils.getMessage(
						MiscUtils.getMessage("valid_mobile_phone_no"),
						new Object[] { phoneType.getLabel().toLowerCase() });
			}

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, messageText, messageText);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(source.getClientId(), message);
			context.renderResponse();
		}
	}

	/**
	 * Remove the value for mobile phone
	 * 
	 * @param event
	 */
	public void resetAreaCode(AjaxBehaviorEvent event) {
		if (isMobilePhoneInUse()) {
			this.setAreaCode(null);
		}
	}

	/**
	 * Use in the list
	 * 
	 * @return
	 */
	public PhoneType[] getPhoneTypes() {
		return PhoneType.values();
	}

	/**
	 * Helper method to make it cleaner in the view.
	 */
	public boolean isMobilePhoneInUse() {
		return this.getPhoneType().equals(PhoneType.MOBILE);
	}

}