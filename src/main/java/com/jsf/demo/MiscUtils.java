package com.jsf.demo;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiscUtils {

	/**
     * Should allow letters and numbers and also some spaces
     * No 'PO Box 1234' allowed.
     */
	private static final String ADDRESS_REGEX = "(^[A-Za-z0-9 ]+$)";

	/**
	 * Check and confirm if the address is a 'PO Box'
	 *  
	 * @param address input address to be checked.
	 * @return true if the address contain the word 'PO Box' ignore case.
	 */
	public static boolean isPoBoxAddress(String address) {
		
		if (address == null || address.trim().equals("")) {
			throw new IllegalArgumentException("Input address must not be null or empty.");
		}
		
		return Pattern.compile("PO BOX", Pattern.CASE_INSENSITIVE).matcher(address).find();
	}

	public static boolean isValidAddress(String address) {
		Pattern mask = Pattern.compile(ADDRESS_REGEX);
		Matcher matcher = mask.matcher(address);
        return matcher.matches();
    }	

	/**
	 * Check address and also PoBox address if applicable
	 * 
	 * @param address
	 * @param excludePoBoxAddress
	 * @return
	 */
	public static boolean isValidAddress(String address, boolean excludePoBoxAddress) {
		
		if (excludePoBoxAddress) {
			return isValidAddress(address) && !isPoBoxAddress(address);
		}
		
		return isValidAddress(address);
	}
	
	public static boolean isValidPhone(PhoneType phoneType, String areaCode,
			String phoneNumber) {

		if (phoneType == null || MiscUtils.isNullOrEmpty(phoneNumber)) {
			return false;
		}

		String toBeMatched;
		String regExpString;

		switch (phoneType) {
		case HOME:
		case WORK:
			// '02','03','07' or '08' for Home and Work no
			regExpString = "0[1|3|7|8]\\d{8}";
			toBeMatched = areaCode + phoneNumber;
			break;
		default:
			regExpString = "\\d{10}";
			toBeMatched = phoneNumber;
			break;
		}

		Pattern pattern = Pattern.compile(regExpString);
		Matcher matcher = pattern.matcher(toBeMatched);

		return matcher.matches();
	}

	/**
	 * 
	 * @param dateOfBirth
	 * 
	 * @return the age in year as of today
	 */
	public static int calculateAgeInYear(Date dateOfBirth) {
		
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();
		
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		
		if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		// Note: if we input the value which is less than one year from current date, the result is -1
		// we thus want to just return zero instead of negative value to indicate that the person is less than one year old.
		if (age <= 0) {
			age = 0;
		}

		return age;
	}
	
	/**
	 * Helper method that we can use to access the shared message bundle from regular Java class
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {

		ResourceBundle bundle = ResourceBundle.getBundle("com.jsf.demo.messages");
		
		if (bundle == null) {
			throw new RuntimeException("Problem initializing the resource bundle.");
		}
		
		return bundle.getString(key);
	}
	
	/**
	 * Make it possible for use to use the resource bundle with parameter
	 *  
	 * @param format the message format place holder string with "{0}", "{1}"
	 * @param args the argument that would be substituted at runtime
	 * @return
	 */
	public static String getMessage(String format, Object[] args) {
		MessageFormat form = new MessageFormat(format);
		return form.format(args);
	}
	
	/**
	 * Helper method to make our code DRY. 
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
	
	/**
	 * Helper method to make our code more DRY.
	 */
	public static void checkForNull(Object object, String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}
	}

}