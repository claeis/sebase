package ch.softenvironment.client.test;

import java.awt.Color;
import java.awt.Font;

import ch.softenvironment.client.ApplicationOptions;

import junit.framework.TestCase;

/**
 * <Description>
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-09-16 14:59:49 $
 */
public class ApplicationOptionsTestCase extends TestCase {
    private String fileName = "ApplicationOptions.test";
	private ApplicationOptions settings = null;
    public void setUp() {
//        super.setUp();
        settings = new ApplicationOptions(fileName);
    }
    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        super.tearDown();
// remove "fileName";
    }
    
    public void testFont() {
        Font font = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);
        settings.setFont(font);
        Font fontGet = settings.getFont();
        assertTrue("Font-Family", font.getFamily().equals(fontGet.getFamily()));
        assertTrue("Font-Style", font.getStyle() == fontGet.getStyle());
        assertTrue("Font-Size", font.getSize() == fontGet.getSize());
    }
    public void testColor() {
        Color color = Color.MAGENTA;
        settings.setBackgroundColor(color);
        Color colorGet = settings.getBackgroundColor();
        assertTrue("BackgroundColor", color.equals(colorGet));
        
        color = new Color(10, 99, 87);
        settings.setForegroundColor(color);
        colorGet = settings.getForegroundColor();
        assertTrue("ForegroundColor", color.equals(colorGet));
    }
}
