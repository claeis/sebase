package ch.softenvironment.util.test;

/**
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class StringUtilsTestCase extends junit.framework.TestCase {
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public StringUtilsTestCase(String name) {
	super(name);
}
/**
 * 
 */
public void testPackageName() {
	assertTrue("StringUtils", "java.util".equals(ch.softenvironment.util.StringUtils.getPackageName(new java.util.ArrayList())));
	assertTrue("StringUtils", "java.util".equals(ch.softenvironment.util.StringUtils.getPackageName(java.util.ArrayList.class)));
}
/**
 * 
 */
public void testPureClassname() {
	assertTrue("StringUtils", "Object".equals(ch.softenvironment.util.StringUtils.getPureClassName(new Object())));
	assertTrue("StringUtils", "Statistic".equals(ch.softenvironment.util.StringUtils.getPureClassName(new ch.softenvironment.util.Statistic("test"))));
	assertTrue("StringUtils", "Statistic".equals(ch.softenvironment.util.StringUtils.getPureClassName(ch.softenvironment.util.Statistic.class)));
}
/**
 * 
 */
public void testReplace() {
	assertTrue("StringUtils", "X C".equals(ch.softenvironment.util.StringUtils.replace("X AS C", " AS ", " ")));
	assertTrue("StringUtils", "X C, Attr1 Dummy".equals(ch.softenvironment.util.StringUtils.replace("X AS C, Attr1 AS Dummy", " AS ", " ")));
}
}
