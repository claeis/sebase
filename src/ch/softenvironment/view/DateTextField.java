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
 * Extended JTextField to show a Date.
 * Allows visual connection of Attribute-to-Attribute connection from e.g.
 * a Double-Property of a Model to this Components value-Property. Make sure
 * to trigger <b>propertyChange</b>-Event from this Component towards the Model.
 * The Model will be updated after a focusLost-Event.
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2004-02-14 16:43:37 $
 */
public class DateTextField extends javax.swing.JTextField {
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private java.util.Date fieldDate = null;
	private transient String format = ch.softenvironment.util.NlsUtils.DATE_EUROPE_PATTERN;

class IvjEventHandler implements java.awt.event.KeyListener {
		public void keyPressed(java.awt.event.KeyEvent e) {};
		public void keyReleased(java.awt.event.KeyEvent e) {
			if (e.getSource() == DateTextField.this) 
				connEtoC1(e);
		};
		public void keyTyped(java.awt.event.KeyEvent e) {};
	};
/**
 * DateTextField constructor comment.
 */
public DateTextField() {
	super();
	initialize();
}
/**
 * connEtoC1:  (DateTextField.key.keyReleased(java.awt.event.KeyEvent) --> DateTextField.getDate()Ljava.util.Date;)
 * @return java.util.Date
 * @param arg1 java.awt.event.KeyEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.util.Date connEtoC1(java.awt.event.KeyEvent arg1) {
	java.util.Date connEtoC1Result = null;
	try {
		// user code begin {1}
		// user code end
		connEtoC1Result = this.getDate();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
	return connEtoC1Result;
}
/**
 * @return Date
 */
public java.util.Date getDate() {
	java.util.Date oldValue = fieldDate;
	try {
		if (ch.softenvironment.util.StringUtils.isNullOrEmpty(getText())) {
			fieldDate = null;
		} else {
			// try convertion of current String into Date
			java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(format);
			java.util.Date date = sf.parse(getText());
			// fire date-Event (correctly changed) !
			fieldDate = new java.util.Date(date.getTime());
			setForeground(java.awt.Color.black);
//			setToolTipText(format);
		}
	} catch(java.text.ParseException e) {
		// no valid change yet => don't fire date-Event
//		setDate(null);
		fieldDate = null;
		setForeground(java.awt.Color.red);
		setToolTipText(e.getLocalizedMessage());
	}

	firePropertyChange("date", oldValue, fieldDate);
	return fieldDate;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {
	ch.softenvironment.view.BaseFrame.showException(null, exception);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addKeyListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("DateTextField");
		setSize(4, 20);
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	setToolTipText(format);
	// user code end
}
/**
 * Test part in a Frame.
 */
public static void main(java.lang.String[] args) {
	try {
		javax.swing.JFrame frame = new javax.swing.JFrame();
		DateTextField aDateTextField;
		aDateTextField = new DateTextField();
		frame.setContentPane(aDateTextField);
		frame.setSize(aDateTextField.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		frame.show();
		java.awt.Insets insets = frame.getInsets();
		frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of ch.softenvironment.view.DateTextField");
		exception.printStackTrace(System.out);
	}
}
/**
 * Set new valid date.
 */
public void setDate(java.util.Date date) {

	if (date == null) {
		// ignore String "null"
		setText(null);
	} else {
		// change format as desired
		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(format);
		// only first time
		setText(sf.format(date));
//setText(String.valueOf(date));
	}
}
}
