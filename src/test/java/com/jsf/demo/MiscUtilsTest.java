package com.jsf.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsf.demo.MiscUtils;
import com.jsf.demo.PhoneType;

public class MiscUtilsTest extends MiscUtils {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidAddress() {
		assertTrue(MiscUtils.isValidAddress("123 Main St"));
		assertTrue("Contain no special character", MiscUtils.isValidAddress("123 Main St"));
		assertFalse("Contain special character", MiscUtils.isValidAddress("123 Main St."));
		assertTrue("PO Box allowed by default", MiscUtils.isValidAddress("PO Box 1234"));
		assertTrue("PO Box allowed by default", MiscUtils.isValidAddress("My PO Box 1234 etc"));
		assertTrue("PO Box allowed by default", MiscUtils.isValidAddress("PO Box 1234"));
		assertTrue("PO Box allowed", MiscUtils.isValidAddress("PO Box 1234", false));
		assertFalse("PO Box not allowed", MiscUtils.isValidAddress("PO Box 1234", true));
		assertFalse(MiscUtils.isValidAddress("Any Po Box 1234 etc", true));		
	}

	@Test
	public void testIsPoBoxAddress() {
		assertFalse(MiscUtils.isPoBoxAddress("123 Main St"));
		assertFalse(MiscUtils.isPoBoxAddress("123 Main St."));
		assertTrue(MiscUtils.isPoBoxAddress("PO Box 1234"));
		assertTrue(MiscUtils.isPoBoxAddress("po box 1234"));
		assertTrue(MiscUtils.isPoBoxAddress("Any pO bOx 1234 etc"));		
	}

	@Test
	public void testIsValidPhone() {

		String[] VALID_AREA_CODES = new String[] { "01","03","07","08" };
		
		String VALID_HOME_OR_WORK_NUMBER = "12345678";
		String VALID_MOBILE_NUMBER = "0123456789";
		
		for (String areaCode : VALID_AREA_CODES) {
			assertTrue(MiscUtils.isValidPhone(PhoneType.HOME, areaCode, VALID_HOME_OR_WORK_NUMBER));
			assertTrue(MiscUtils.isValidPhone(PhoneType.WORK, areaCode, VALID_HOME_OR_WORK_NUMBER));
		}
		
		assertTrue(MiscUtils.isValidPhone(PhoneType.MOBILE, null, VALID_MOBILE_NUMBER));
		assertTrue(MiscUtils.isValidPhone(PhoneType.MOBILE, "N/A", VALID_MOBILE_NUMBER));
		
		// invalid numbers
		assertFalse("Invalid phone type", MiscUtils.isValidPhone(null, "01", VALID_HOME_OR_WORK_NUMBER));
		assertFalse("Invalid area code for home or work", MiscUtils.isValidPhone(PhoneType.HOME, "99", VALID_HOME_OR_WORK_NUMBER));
		assertFalse("Invalid area code for home or work", MiscUtils.isValidPhone(PhoneType.WORK, "99", VALID_HOME_OR_WORK_NUMBER));

		assertFalse("Not a number", MiscUtils.isValidPhone(PhoneType.HOME, "01", "AAAAAAAA"));
		assertFalse("Not a number", MiscUtils.isValidPhone(PhoneType.WORK, "01", "BBBBBBBB"));
		assertFalse("Invalid length", MiscUtils.isValidPhone(PhoneType.HOME, "01", VALID_HOME_OR_WORK_NUMBER + "123"));

	}
	
	@Test
	public void testCalculateAgeInYear() {
		// we don't allow negative value, only zero or positive value
		assertTrue(MiscUtils.calculateAgeInYear(new Date()) == 0);

		Calendar rightNow = Calendar.getInstance();
		rightNow.roll(Calendar.YEAR, -16);
		Date fifteenYearAgo = rightNow.getTime();
		assertEquals(15, MiscUtils.calculateAgeInYear(fifteenYearAgo));
	}

	@Test
	public void testGetMessage() {
		
		try {
			MiscUtils.getMessage("BAD_KEY");
			fail("Should get exception." );
		} catch (Exception e) {
			//expected.
		}
		
		assertEquals("First Name:", MiscUtils.getMessage("label_first_name"));
	}

	@Test
	public void testGetMessageWithParameters() {
		
		try {
			MiscUtils.getMessage("This is {0} and {1}", new Object[] {});
		} catch (Exception e) {
			//expected.
		}

		String expectedHolder = "I love to program in both %s also %s.";
		String resultHolder = "I love to program in both {0} also {1}.";
		
		assertEquals(String.format(expectedHolder, "Java", "Ruby"), MiscUtils.getMessage(resultHolder, new Object[] { "Java", "Ruby" }));
	}
	
}