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

import java.awt.*;

import ch.softenvironment.client.ResourceManager;
import ch.softenvironment.util.Tracer;
/**
 * Template-Dialog defining minimal functionality.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.6 $ $Date: 2004-09-14 16:56:57 $
 */
public abstract class BaseDialog extends javax.swing.JDialog {
	private javax.swing.JPanel ivjJDialogContentPane = null;
	private boolean saved = false;
/**
 * BaseDialog constructor comment.
 * @param owner java.awt.Frame
 * @param title java.lang.String
 * @param modal boolean
 */
public BaseDialog(java.awt.Dialog owner, String title, boolean modal) {
	super(owner, title, modal);
	setRelativeLocation(owner);
	initialize();
}
/**
 * BaseDialog constructor comment.
 * @param owner java.awt.Frame
 * @param title java.lang.String
 * @param modal boolean
 */
public BaseDialog(Frame owner, String title, boolean modal) {
	super(owner, title, modal);
	setRelativeLocation(owner);
	initialize();
}
/**
 * BaseDialog constructor comment.
 * @param owner java.awt.Frame
 * @param modal boolean
 */
public BaseDialog(Frame owner, boolean modal) {
	super(owner, modal);
	setRelativeLocation(owner);
	initialize();
}
/**
 * Overwrite for specific adaptions.
 * @see BaseFrame#adaptSelection(..)
 */
protected void adaptSelection(java.awt.event.MouseEvent event, javax.swing.JPopupMenu popupMenu) {
}
/**
 * Typical Apply-Action.
 * 
 * @see #save()
 */
protected void applyPressed() {
	save();
}
/**
 * Undo changes and close Dialog.
 * @see #undo()
 */
protected void cancelPressed() {
	undo();
	dispose();
}
/**
 * Ask user whether the remove action shall be proceeded or not.
 * @see BaseFrame#checkDeletion()
 */
protected boolean checkDeletion() {
	return checkDeletion(getResourceString(BaseDialog.class, "CTDeletion"), getResourceString(BaseDialog.class, "CIQueryForDeletion")); //$NON-NLS-2$ //$NON-NLS-1$
}
/**
 * Ask user whether the remove action shall be proceeded or not.
 * @param title String (of Deletion Message Box)
 * @param question String (of Deletion question)
 * @see BaseFrame#checkDeletion()
 */
protected boolean checkDeletion(String title, String question) {
	QueryDialog dialog = new QueryDialog(this, title, question);
	dialog.dispose();
	return dialog.isYes();
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
 * Return Apply-Button Label-String.
 */
protected String getApplyString() {
	return getResourceString(BaseDialog.class, "BtnApply_text");
}
/**
 * Return Button Label-String.
 */
protected String getAssignString() {
	return getResourceString(BaseDialog.class, "BtnAssign_text");
}
/**
 * Return Cancel-Button Label-String.
 */
protected String getCancelString() {
	return getResourceString(BaseDialog.class, "BtnCancel_text");
}
/**
 * Return New-Button Label-String.
 */
protected String getChangeWindowString() {
	return getResourceString(BaseDialog.class, "BtnChangeWindow_text");
}
/**
 * Return Description Label-String.
 */
protected String getDescriptionString() {
	return getResourceString(BaseDialog.class, "CIDescription");
}
/**
 * Return Detail-String.
 */
protected String getDetailString() {
	return getResourceString(BaseDialog.class, "CIDetail");
}
/**
 * Return the JDialogContentPane property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJDialogContentPane() {
	if (ivjJDialogContentPane == null) {
		try {
			ivjJDialogContentPane = new javax.swing.JPanel();
			ivjJDialogContentPane.setName("JDialogContentPane");
			ivjJDialogContentPane.setLayout(null);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJDialogContentPane;
}
/**
 * Return New-Button Label-String.
 * @see DataPanel
 */
protected String getNewString() {
	return getResourceString(BaseDialog.class, "BtnNew_text");
}
/**
 * Return New-Button Label-String.
 */
protected String getNewWindowString() {
	return getResourceString(BaseDialog.class, "BtnNewWindow_text");
}
/**
 * Return OK-Button Label-String.
 */
protected String getOKString() {
	return getResourceString(BaseDialog.class, "BtnOK_text");
}
/**
 * Return Remove-Button Label-String.
 */
protected String getRemoveString() {
	return getResourceString(BaseDialog.class, "BtnRemove_text");
}
/**
 * @see BaseFrame#getResourceString(String)
 */
protected static String getResourceString(java.lang.Class resourceClass, String propertyName) {
	return ResourceManager.getInstance().getResource(resourceClass, propertyName);
}
/**
 * @see BaseFrame#getResourceString(String)
 */
protected String getResourceString(String propertyName) {
	return ResourceManager.getInstance().getResource(this.getClass(), propertyName);
}
/**
 * Return Button Label-String.
 */
protected String getSearchWindowString() {
	return getResourceString(BaseDialog.class, "BtnSearchWindow_text");
}
/**
 * Popup an error Dialog.
 * @param exception java.lang.Throwable
 */
protected void handleException(java.lang.Throwable exception) {
	BaseFrame.showException(this, exception);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("BaseDialog");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(426, 240);
		setTitle("<Abstract/Template Dialog>");
		setContentPane(getJDialogContentPane());
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * Overwrite this method for other OK-Reasons.
 * @see #okPressed()
 * @see #applyPressed()
 * @return boolean whether saving was successful or not
 */
public boolean isSaved() {
	// do nothing by default
	return saved;
}
/**
 * Typical OK-Action. Save Contents and close Dialog.
 * Trigger an OK-Buttons actionPerformed-Event to this method.
 * @see #save()
 */
protected void okPressed() {
	if (save()) {
		dispose();
	}
}
/**
 * Overwrite this method for other OK-Reasons.
 * @see #okPressed()
 * @see #applyPressed()
 * @return boolean whether saving was successful or not
 */
protected boolean save() {
	// do nothing by default
	saved = true;
	return true;
}
/**
 * Show the Dialog in the center of the screen.
 */
public void setCenterLocation() {
	setCenterLocation(BaseFrame.getScreenSize());
}
/**
 * Show the Dialog in the center of the outerDimension.
 */
private void setCenterLocation(Dimension outerSize) {
	Dimension frameSize = getSize();

	if (frameSize.height > outerSize.height) {
		frameSize.height = outerSize.height;
	}
	if (frameSize.width > outerSize.width) {
		frameSize.width = outerSize.width;
	}

	setLocation(
		new Point(
			(outerSize.width - frameSize.width) / 2,
			(outerSize.height - frameSize.height) / 2));
}
/**
 * Set this Dialog relative to parent Window.
 * @see BaseFrame#setRelativeLocation()
 */
protected void setRelativeLocation(java.awt.Window parent) {
	if (parent != null) {
/*		setLocation(new Point(parent.getX() + BaseFrame.X_CHILD_OFFSET,
								parent.getY() + BaseFrame.Y_CHILD_OFFSET));
*/
//		setCenterLocation(parent.getSize());
		setLocationRelativeTo(parent);
	}
}
/**
 * Trace the exception.
 * @param exception java.lang.Throwable
 */
protected void traceOnly(java.lang.Throwable exception) {
	Tracer.getInstance().uncaughtException(this, "traceOnly(..)", exception);//$NON-NLS-1$
}
/**
 * Overwrite this method for other Undo-Reasons.
 * @see #cancelPressed()
 */
protected void undo() {
	// do nothing by default
}
}
