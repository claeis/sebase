package ch.softenvironment.util.test;

import ch.softenvironment.util.NlsUtils;

/**
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class NlsUtilsTestCase extends junit.framework.TestCase {
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public NlsUtilsTestCase(String name) {
	super(name);
}
/**
 * 
 */
public void testDate() {
	assertTrue("NlsUtils", "25.01.2003".equals(NlsUtils.getEuropeanDateString(new java.util.GregorianCalendar(2003, java.util.Calendar.JANUARY, 25))));
}
/**
 * 
 */
public void testFormatMessage() {
	assertTrue("NlsUtils", "This is 17".equals(NlsUtils.formatMessage("This is {0}", 17)));
	assertTrue("NlsUtils", "The langugage is english".equals(NlsUtils.formatMessage("The langugage is {0}", "english")));
	Object[] tokens = { new Integer(3), "messages" };
	assertTrue("NlsUtils", "These are 3 messages".equals(NlsUtils.formatMessage("These are {0} {1}", tokens)));
}
}
