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
 * @version $Revision: 1.1 $ $Date: 2004-04-27 18:49:23 $
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
 * Adapt the given PopupMenu before displaying it (for e.g. disable Items).
 * Overwrite this method.
 * @see #genericPopupDisplay()
 */
protected javax.swing.JPopupMenu adaptPopupMenu(javax.swing.JPopupMenu popupMenu) {
	// overwrite
	return null;
}
/**
 * @see BaseFrame
 */
protected void genericPopupDisplay(java.awt.event.MouseEvent event, javax.swing.JPopupMenu popupMenu) {
	if (event.isPopupTrigger()) {
		adaptPopupMenu(popupMenu);
		popupMenu.show(event.getComponent(), event.getX(), event.getY());
	}
}
/**
 * @see BaseFrame#getResourceString(Class, String)
 */
protected static String getResourceString(java.lang.Class resource, String propertyName) {
	return ch.softenvironment.util.ResourceManager.getInstance().getResource(resource, propertyName);
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
	new ErrorDialog(this, null, exception.toString(), exception);
}
}
