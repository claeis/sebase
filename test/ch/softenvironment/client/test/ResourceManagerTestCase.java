package ch.softenvironment.client.test;

/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import java.util.Locale;

import ch.softenvironment.client.ResourceManager;

import junit.framework.TestCase;

/**
 * TestCase for ResourceManager.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2006-05-07 14:53:51 $
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
