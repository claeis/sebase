import ch.softenvironment.client.test.ApplicationOptionsTestCase;
import ch.softenvironment.client.test.ResourceManagerTestCase;
import ch.softenvironment.math.test.MathTestSuite;
import ch.softenvironment.util.test.AmountFormatTestCase;
import ch.softenvironment.util.test.BeanReflectorTestCase;
import ch.softenvironment.util.test.DateUtilsTestCase;
import ch.softenvironment.util.test.NlsUtilsTestCase;
import ch.softenvironment.util.test.StringUtilsTestCase;

import junit.framework.*;
/**
 * Run this class for all <b>seBase</b> TestCases.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:18:03 $
 */
public class SeBaseTestSuite extends junit.framework.TestSuite {
/**
 * DemoTestSuite constructor comment.
 */
public SeBaseTestSuite() {
	super();
}
/**
 * DemoTestSuite constructor comment.
 * @param arg1 java.lang.Class
 */
public SeBaseTestSuite(Class arg1) {
	super(arg1);
}
/**
 * DemoTestSuite constructor comment.
 * @param arg1 java.lang.String
 */
public SeBaseTestSuite(String arg1) {
	super(arg1);
}
/**
 * Insert the method's description here.
 * Creation date: (10.05.2001 12:14:54)
 * @return junit.framework.Test
 */
public static void main(String[] args) {
	//junit.textui.TestRunner.run(suite());
	junit.swingui.TestRunner.run(SeBaseTestSuite.class);
}
/**
 * @return junit.framework.Test
 */
public static junit.framework.Test suite() {
	TestSuite suite =  new TestSuite("Testing: ch.softenvironment.util.*");

	suite.addTest(new TestSuite(BeanReflectorTestCase.class));
	suite.addTest(new TestSuite(DateUtilsTestCase.class));
	suite.addTest(new TestSuite(StringUtilsTestCase.class));
	suite.addTest(new TestSuite(NlsUtilsTestCase.class));
	suite.addTest(new TestSuite(AmountFormatTestCase.class));
	suite.addTest(new TestSuite(ApplicationOptionsTestCase.class));
	suite.addTest(new TestSuite(ResourceManagerTestCase.class));
	
	suite.addTest(MathTestSuite.suite());

	/* in non VAJ environments:
		suite.addTest(new TestSuite(TestTestCaseClassLoader.class));
	*/
	
	return(suite);
}
}
