package ch.softenvironment.client.test;

import java.util.Locale;

import ch.softenvironment.client.ResourceManager;

import junit.framework.TestCase;

/**
 * <Description>
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-09-16 14:59:49 $
 */
public class ResourceManagerTestCase extends TestCase {
	public void testGetRessourceAsIs() {
	    Locale.setDefault(Locale.US);
	    assertTrue("en Translation", "My Label:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text")));
	    assertTrue("en Translation", "My Panel".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text")));
	    
	    Locale.setDefault(Locale.GERMAN);
	    assertTrue("de Translation", "Mein Label:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text")));
	    assertTrue("de Translation", "Mein Panel".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text")));
	}
	public void testGetResourceLabel() {
	    Locale.setDefault(Locale.US);
	    // as is
	    assertTrue("en Translation", "My Label:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text", true)));
	    assertTrue("en Translation", "My Panel".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text", false)));
	    // should deal with ending
	    assertTrue("en Translation", "My Label".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text", false)));
	    assertTrue("en Translation", "My Panel:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text", true)));
	    
	    Locale.setDefault(Locale.GERMAN);
	    // as is
	    assertTrue("de Translation", "Mein Label:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text", true)));
	    assertTrue("de Translation", "Mein Panel".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text", false)));
	    // should deal with ending
	    assertTrue("de Translation", "Mein Label".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "LblTest_text", false)));
	    assertTrue("de Translation", "Mein Panel:".equals(ResourceManager.getResource(ResourceManagerTestCase.class, "PnlTest_text", true)));
	}
	public void testGetResourceMatch() {
	    Locale.setDefault(Locale.US);
	    // as is
	    assertTrue("en Translation", "Xy-Text (en):".equals(ResourceManager.getResourceMatch(ResourceManagerTestCase.class, "Lbl[a-zA-Z0-9]*Xy_text", true)));
	    assertTrue("en Translation", "Xy-Text (en)".equals(ResourceManager.getResourceMatch(ResourceManagerTestCase.class, "Lbl[a-zA-Z0-9]*y_text", false)));
	}
}
