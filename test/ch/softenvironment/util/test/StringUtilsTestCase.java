package ch.softenvironment.util.test;

/**
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:16:19 $
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
public void testGetString() {
	// getString(String)
	assertTrue("StringUtils.getString(null)", "".equals(ch.softenvironment.util.StringUtils.getString(null)));
	assertTrue("StringUtils.getString(empty)", "".equals(ch.softenvironment.util.StringUtils.getString("")));
	assertTrue("StringUtils.getString(blaBla)", "blaBla".equals(ch.softenvironment.util.StringUtils.getString("blaBla")));

	// getString(Object)
	assertTrue("StringUtils.getString(null)", "".equals(ch.softenvironment.util.StringUtils.getString((Object)null)));
	assertTrue("StringUtils.getString(17)", "17".equals(ch.softenvironment.util.StringUtils.getString(new Integer(17))));
	assertTrue("StringUtils.getString(-23.45)", "-23.45".equals(ch.softenvironment.util.StringUtils.getString(new Double(-23.45))));

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
public void testPureClassName() {
	assertTrue("StringUtils", "Object".equals(ch.softenvironment.util.StringUtils.getPureClassName(new Object())));
	assertTrue("StringUtils", "Statistic".equals(ch.softenvironment.util.StringUtils.getPureClassName(new ch.softenvironment.util.Statistic("test"))));
	assertTrue("StringUtils", "Statistic".equals(ch.softenvironment.util.StringUtils.getPureClassName(ch.softenvironment.util.Statistic.class)));
}
public void testPureFileName() {
	assertTrue("no Path", "MyFile.xml".equals(ch.softenvironment.util.StringUtils.getPureFileName("MyFile.xml")));
	assertTrue("DOS-Path", "MyFile.xml".equals(ch.softenvironment.util.StringUtils.getPureFileName("C:\\tmp\\dummy\\MyFile.xml")));
	assertTrue("Unix-Path", "MyFile.xml".equals(ch.softenvironment.util.StringUtils.getPureFileName("/usr/local/MyFile.xml")));
}
/**
 * 
 */
public void testReplace() {
	assertTrue("StringUtils", "X C".equals(ch.softenvironment.util.StringUtils.replace("X AS C", " AS ", " ")));
	assertTrue("StringUtils", "X C, Attr1 Dummy".equals(ch.softenvironment.util.StringUtils.replace("X AS C, Attr1 AS Dummy", " AS ", " ")));
}
}
