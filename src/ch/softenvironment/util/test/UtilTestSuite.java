package ch.softenvironment.util.test;

import junit.framework.*;
/**
 * Run this class for all these TestCases.
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class UtilTestSuite extends junit.framework.TestSuite {
/**
 * DemoTestSuite constructor comment.
 */
public UtilTestSuite() {
	super();
}
/**
 * DemoTestSuite constructor comment.
 * @param arg1 java.lang.Class
 */
public UtilTestSuite(Class arg1) {
	super(arg1);
}
/**
 * DemoTestSuite constructor comment.
 * @param arg1 java.lang.String
 */
public UtilTestSuite(String arg1) {
	super(arg1);
}
/**
 * Insert the method's description here.
 * Creation date: (10.05.2001 12:14:54)
 * @return junit.framework.Test
 */
public static void main(String[] args) {
	//junit.textui.TestRunner.run(suite());
	//junit.awtui.TestRunner.run(SandfishTestCase.class);
	junit.swingui.TestRunner.run(UtilTestSuite.class);
}
/**
 * @return junit.framework.Test
 */
public static junit.framework.Test suite() {
	TestSuite suite =  new TestSuite("Testing: ch.softenvironment.util.*");

	suite.addTest(new TestSuite(DateUtilsTestCase.class));
	suite.addTest(new TestSuite(StringUtilsTestCase.class));
	suite.addTest(new TestSuite(NlsUtilsTestCase.class));
	suite.addTest(new TestSuite(AmountFormatTestCase.class));

	/* in non VAJ environments:
		suite.addTest(new TestSuite(TestTestCaseClassLoader.class));
	*/
	
	return(suite);
}
}
