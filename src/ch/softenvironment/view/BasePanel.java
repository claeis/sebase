package ch.softenvironment.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

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

/**
 * Basic javax.swing.JPanel.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.6 $ $Date: 2005-06-30 07:27:43 $
 */
public class BasePanel extends javax.swing.JPanel {
    private Image image = null;
/**
 * BasePanel constructor comment.
 */
public BasePanel() {
	super();
}
/**
 * BasePanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public BasePanel(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * BasePanel constructor comment.
 * @param layout java.awt.LayoutManager
 * @param isDoubleBuffered boolean
 */
public BasePanel(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
}
/**
 * BasePanel constructor comment.
 * @param isDoubleBuffered boolean
 */
public BasePanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
}
/**
 * @see BaseFrame#genericPopupDisplay(..)
 */
protected void genericPopupDisplay(java.awt.event.MouseEvent event, javax.swing.JPopupMenu popupMenu) {
	try {
	 	adaptSelection(event, popupMenu);

	 	if (event.getClickCount() == 2) {
		 	// case: double-click
			if (this instanceof ListMenuChoice) {
//				((ListMenuChoice)this).defaultDoubleClickAction(event);
				((ListMenuChoice)this).changeObjects(event.getSource());
			}
	 	} else if (event.isPopupTrigger() && (popupMenu != null)) {
			popupMenu.show(event.getComponent(), event.getX(), event.getY());
		}
   	} catch(Throwable e) {
	   	handleException(e);
   	}
}
/**
 * @see BaseFrame#getResourceString(Class, String)
 */
protected static String getResourceString(java.lang.Class resource, String propertyName) {
	return ch.softenvironment.client.ResourceManager.getInstance().getResource(resource, propertyName);
}
/**
 * @see BaseFrame#getResourceString(String)
 */
protected String getResourceString(String propertyName) {
	return getResourceString(this.getClass(), propertyName);
}
/**
 * Popup an error Dialog.
 * @param exception java.lang.Throwable
 */
protected void handleException(java.lang.Throwable exception) {
	BaseFrame.showException(null, exception);
}

/**
 * Overwrite for specific adaptions.
 * @see BaseFrame#adaptSelection(..)
 */
protected void adaptSelection(java.awt.event.MouseEvent event, javax.swing.JPopupMenu popupMenu) {
}

/**
 * @see WaitDialog#showBusy()
 */
protected final void showBusy(final Runnable block) {
	WaitDialog.showBusy(this, block);
}

/**
 * @see WaitDialog#updateProgress()
 */
protected final void showProgress(final int percentage, final String currentActivity) {
	WaitDialog.updateProgress(percentage, currentActivity);
}
/**
 * Display given image as Background-Image on Panel.
 * @param path
 * @see #paintComponent()
 */
public void setImage(Image image) {
//    image = Toolkit.getDefaultToolkit().createImage(path);
    this.image = image;
}
/**
 * Overwrites.
 * @see #setImage()
 */
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
/*
        int w = getWidth();
        int h = getHeight();
        int x = (w - imageWidth)/2;
        int y = (h - imageHeight)/2;
*/
	    g.drawImage(image,
	    		new Double(g.getClipBounds().getCenterX() - (image.getWidth(this) / 2)).intValue(),
	    		new Double(g.getClipBounds().getCenterY() - (image.getHeight(this) / 2)).intValue(),
	    		this);
    }
}
}
