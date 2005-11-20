package ch.softenvironment.util;
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
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.Node;


/**
 * Image encoding Utility.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-11-20 15:48:18 $
 */
public class ImageUtils {
    /**
     * Save current Screen-Contents to given filename in PNG-Format 
     * (suffix will be set if missing in filename).
     * @param filename
     * @see http://schmidt.devlib.org/java/save-screenshot.html
     */
    public static void screenShot(String filename) {
        try {
            // wait for a user-specified time
//            long time = 500; // ms to wait until shooting
            // Thread.sleep(time);

            // determine current screen size
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            // create screen shot
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            // save captured image to PNG file
            ImageIO.write(image, "png", new File(checkSuffixPNG(filename)));

            // use System.exit if the program hangs after writing the file;
            // that's an old bug which got fixed only recently
            // System.exit(0);
        } catch (Throwable e) {
            System.out.println("screenshot failed: " + e.getLocalizedMessage());
        }
    }
    /**
     * @see StringUtls#tryAppendSuffix()
     */
    private static String checkSuffixPNG(String filename) {
        return StringUtils.tryAppendSuffix(filename, ".png");
    }
    /**
     * Write given image into given file.
     * 
     * @param filename
     * @param bufferedImage
     * @see http://forum.java.sun.com/thread.jspa?threadID=653302&messageID=3840311
     */
    public static void writeImage(String filename, BufferedImage bufferedImage) throws IOException {
        Iterator iterator = ImageIO.getImageWritersBySuffix("png");
        if (!iterator.hasNext()) {
            throw new IllegalStateException("no writers found");
        }
        ImageWriter imageWriter = (ImageWriter)iterator.next();
        FileOutputStream outputStream = new FileOutputStream(checkSuffixPNG(filename));
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        ImageTypeSpecifier imageTypeSpecifier = new ImageTypeSpecifier(bufferedImage);
        IIOMetadata metadata = imageWriter.getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);
        String sFormat = "javax_imageio_png_1.0";
        Node node = metadata.getAsTree(sFormat);
        IIOMetadataNode gammaNode = new IIOMetadataNode("gAMA");
        String sGamma = "55556";
        gammaNode.setAttribute("value", sGamma);
        node.appendChild(gammaNode);
        metadata.setFromTree(sFormat, node);
        imageWriter.write(new IIOImage(bufferedImage, null, metadata));
    }

    /**
     * TestCase.
     * @param args
     * @deprecated
     */
    public static void main(String[] args) {
        // TestCase 1
        BufferedImage bufferedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setColor(new Color(204, 204, 255));
        g2.fillRect(0, 0, 50, 50);
        try {
            writeImage("C:/Temp/test_ph1.png", bufferedImage);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        // TestCase 2
        screenShot("C:/Temp/test_ps_sc1.png");
    }
}