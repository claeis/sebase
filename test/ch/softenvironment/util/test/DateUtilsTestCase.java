package ch.softenvironment.util.test;

import ch.softenvironment.util.DateUtils;

/**
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:16:19 $
 */
public class DateUtilsTestCase extends junit.framework.TestCase {
    java.util.Date currentDate = null;
    java.util.GregorianCalendar march15_2004 = null;
    
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public DateUtilsTestCase(String name) {
	super(name);
}
protected void setUp() throws Exception {
    super.setUp();
    
    currentDate = new java.util.Date();
    march15_2004 = new java.util.GregorianCalendar(2004,java.util.Calendar.MARCH, 15);
}
/**
 * 
 */
public void testCalcTime() {
	assertTrue("DateUtils#calcTimeDifference", "0min. 33s".equals(DateUtils.calcTimeDifference(currentDate, new java.util.Date(currentDate.getTime() + (33 * 1000)))));
	assertTrue("DateUtils#calcTimeDifference", "57min. 42s".equals(DateUtils.calcTimeDifference(currentDate, new java.util.Date(currentDate.getTime() + ((57*60+42) * 1000)))));
	assertTrue("DateUtils#calcTimeDifference", "2h0min. 0s".equals(DateUtils.calcTimeDifference(currentDate, new java.util.Date(currentDate.getTime() + (2 * 60 * 60 * 1000)))));
	assertTrue("DateUtils#calcTimeDifference", "14h13min. 5s".equals(DateUtils.calcTimeDifference(currentDate, new java.util.Date(currentDate.getTime() + ((14 * 60 * 60 + 13 * 60 + 5) * 1000)))));
}
public void testGetYear() {
    assertTrue("DateUtils#getYear", DateUtils.getYear(march15_2004.getTime()).intValue() == 2004);
}
public void testGetDayInMonth() {
    assertTrue("DateUtils#getDayInMonth", DateUtils.getDayInMonth(march15_2004.getTime()).intValue() == 15);
}
public void testCalcHours() {
    assertTrue("DateUtils#calcHours", 2.0 == DateUtils.calcHours(currentDate, new java.util.Date(currentDate.getTime() + (2 * 60 * 60 * 1000)), 1).doubleValue());
}
}
