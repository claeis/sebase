package ch.softenvironment.util.test;

import ch.softenvironment.util.DateUtils;

/**
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class DateUtilsTestCase extends junit.framework.TestCase {
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public DateUtilsTestCase(String name) {
	super(name);
}
/**
 * 
 */
public void testCalcTime() {
	java.util.Date date = new java.util.Date();
	assertTrue("DateUtils", "0min.33s".equals(DateUtils.calcTimeDifference(date, new java.util.Date(date.getTime() + (33 * 1000)))));
	assertTrue("DateUtils", "57min.42s".equals(DateUtils.calcTimeDifference(date, new java.util.Date(date.getTime() + ((57*60+42) * 1000)))));
	assertTrue("DateUtils", "2h0min.0s".equals(DateUtils.calcTimeDifference(date, new java.util.Date(date.getTime() + (2 * 60 * 60 * 1000)))));
	assertTrue("DateUtils", "14h13min.5s".equals(DateUtils.calcTimeDifference(date, new java.util.Date(date.getTime() + ((14 * 60 * 60 + 13 * 60 + 5) * 1000)))));
}
}
