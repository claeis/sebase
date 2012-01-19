package ch.softenvironment.util.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

import ch.softenvironment.util.ImageUtils;

/**
 * Test ImageUtils.
 * 
 * @author Peter Hirzel, softEnvironment GmbH
 */
public class ImageUtilsTest {

    @Test
    public void testWriteImage() {
	// TestCase 1
	BufferedImage bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = (Graphics2D)bufferedImage.getGraphics();
	g2.setColor(new Color(204, 204, 255));
	g2.fillRect(0, 0, 50, 50);
	try {
	    ImageUtils.writeImage("C:/Temp/ImageUtilsTestCase_writeImage.png", bufferedImage);
	} catch (IOException e) {
	    fail(e.getMessage());
	}
    }

    @Test
    public void testScreenShot() {
	try {
	ImageUtils.screenShot("C:/Temp/ImageUtilsTestCase_screenShot.png");
	} catch (Throwable e) {
	    fail(e.getMessage());
	}
    }
}
