package ch.softenvironment.view;

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
 * @version $Revision: 1.5 $ $Date: 2005-03-01 15:31:51 $
 */
public class BasePanel extends javax.swing.JPanel {
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
}
