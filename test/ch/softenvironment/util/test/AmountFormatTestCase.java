package ch.softenvironment.util.test;

import java.text.NumberFormat;
import java.util.Locale;

import ch.softenvironment.util.AmountFormat;
/**
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2007-02-20 12:57:46 $
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
	Locale.setDefault(new Locale("de", "CH"));
	assertTrue("toString(double)", AmountFormat.toString(1343492.3422).equals("1'343'492.34"));
	assertTrue("toString(Double)", AmountFormat.toString(new Double(1343492.3422)).equals("1'343'492.34"));
//	assertTrue("toString(long)", AmountFormat.toString((long)1343492).equals("1'343'492.00"));
	assertTrue("toString(Long)", AmountFormat.toString(new Long(1343492)).equals("1'343'492.00"));

	assertTrue("toString(null)", AmountFormat.toString(null).equals(""));
//	assertTrue("toString(Double, 3, 5)", AmountFormat.toString(new Double(1343492.3422567), 3, 5).equals("1'343'492.34226"));
//	assertTrue("toString(Double,3 ,3)", AmountFormat.toString(new Double(1343492.3), 3, 3).equals("1'343'492.300"));

	NumberFormat af = AmountFormat.getAmountInstance();
//	assertTrue("format(Double)", af.format(null).equals(""));
	assertTrue("format(Double)", af.format(new Double(12345.563)).equals("12'345.56"));
}
}
