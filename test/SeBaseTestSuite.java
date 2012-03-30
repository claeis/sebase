import ch.softenvironment.client.test.ApplicationOptionsTestCase;
import ch.softenvironment.client.test.ResourceManagerTestCase;
import ch.softenvironment.client.test.ViewManagerTestCase;
import ch.softenvironment.controller.test.DataBrowserTestCase;
import ch.softenvironment.math.test.FinancialUtilsTestCase;
import ch.softenvironment.math.test.MathUtilsTestCase;
import ch.softenvironment.util.test.AmountFormatTestCase;
import ch.softenvironment.util.test.BeanReflectorTestCase;
import ch.softenvironment.util.test.DOMUtilsTestCase;
import ch.softenvironment.util.test.DateUtilsTestCase;
import ch.softenvironment.util.test.DeveloperExceptionTestCase;
import ch.softenvironment.util.test.ImageUtilsTest;
import ch.softenvironment.util.test.ListUtilsTestCase;
import ch.softenvironment.util.test.NlsUtilsTestCase;
import ch.softenvironment.util.test.ParserCSVTestCase;
import ch.softenvironment.util.test.StringUtilsTestCase;
import ch.softenvironment.util.test.UserExceptionTestCase;

import junit.framework.*;

/**
 * Run this class for all <b>seBase</b> TestCases.
 * 
 * @author Peter Hirzel, softEnvironment GmbH
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
	 * 
	 * @param arg1
	 *            java.lang.Class
	 * 
	 *            public SeBaseTestSuite(Class arg1) { super(arg1); }
	 */

	/**
	 * DemoTestSuite constructor comment.
	 * 
	 * @param arg1
	 *            java.lang.String
	 */
	public SeBaseTestSuite(String arg1) {
		super(arg1);
	}

	/**
	 * @return junit.framework.Test
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite()); // JUnit 3 runner
		// junit.swingui.TestRunner.run(SeBaseTestSuite.class);
	}

	/**
	 * @return junit.framework.Test
	 */
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite("Testing: seBase");

		// *.client.test
		suite.addTest(new TestSuite(ApplicationOptionsTestCase.class));
		suite.addTest(new TestSuite(ResourceManagerTestCase.class));
		suite.addTest(new TestSuite(ViewManagerTestCase.class));

		// *.controller.test
		suite.addTest(new TestSuite(DataBrowserTestCase.class));

		// *.math.test
		suite.addTest(new TestSuite(MathUtilsTestCase.class));
		suite.addTest(new TestSuite(FinancialUtilsTestCase.class));

		// *.util.test
		suite.addTest(new TestSuite(AmountFormatTestCase.class));
		suite.addTest(new TestSuite(BeanReflectorTestCase.class));
		suite.addTest(new TestSuite(DateUtilsTestCase.class));
		suite.addTest(new TestSuite(DeveloperExceptionTestCase.class));
		suite.addTest(new JUnit4TestAdapter(ImageUtilsTest.class));
		suite.addTest(new TestSuite(ListUtilsTestCase.class));
		suite.addTest(new TestSuite(UserExceptionTestCase.class));
		suite.addTest(new TestSuite(DOMUtilsTestCase.class));
		suite.addTest(new TestSuite(NlsUtilsTestCase.class));
		suite.addTest(new TestSuite(ParserCSVTestCase.class));
		suite.addTest(new TestSuite(StringUtilsTestCase.class));

		return (suite);
	}
}
