package ch.softenvironment.util.test;

import ch.softenvironment.util.AmountFormat;
/**
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class AmountFormatTestCase extends junit.framework.TestCase {
/**
 * ApplicationFrameTestCase constructor comment.
 * @param arg1 java.lang.String
 */
public AmountFormatTestCase(String arg1) {
	super(arg1);
}
public void testFormat() {
	assertTrue("toString(double)", (AmountFormat.toString(1343492.3422)).equals("1'343'492.34"));
	assertTrue("toString(Double)", (AmountFormat.toString(new Double(1343492.3422))).equals("1'343'492.34"));
	assertTrue("toString(Double, 3, 5)", (AmountFormat.toString(new Double(1343492.3422567), 3, 5)).equals("1'343'492.34226"));
	assertTrue("toString(Double,3 ,3)", (AmountFormat.toString(new Double(1343492.3), 3, 3)).equals("1'343'492.300"));

	AmountFormat af = new AmountFormat();
	assertTrue("format(Double)", af.format(null).equals("0.00"));
	assertTrue("format(Double)", af.format(new Double(12345.563)).equals("12'345.56"));
}
}
