package ch.softenvironment.view.swingext;
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
import ch.softenvironment.client.ResourceManager;
/**
 * Extended JTextField to show any jva.lang.Number values.
 * Allows visual connection of Attribute-to-Attribute connection from e.g.
 * a Double-Property of a Model to this Components value-Property. Make sure
 * to trigger <b>propertyChange</b>-Event from this Component towards the Model.
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2005-03-12 17:48:42 $
 */
public class NumberTextField extends javax.swing.JTextField implements java.awt.event.InputMethodListener {
	private java.text.DecimalFormat decFormat = null;
/**
 * Constructor.
 * By Default align Numbers on the right in the TextField.
 */
public NumberTextField() {
	super();

	addInputMethodListener(this);
	setHorizontalAlignment(javax.swing.JTextField.RIGHT);
}
/**
 * Invoked when the caret within composed text has changed.
 */
public void caretPositionChanged(java.awt.event.InputMethodEvent event) {
}
/**
 * Return the given Text as Double.
 * @throws NumberFormatException
 */
public java.lang.Double getDoubleValue() throws NumberFormatException {
	if (ch.softenvironment.util.StringUtils.isNullOrEmpty(getText())) {
		return null;
	} else {
		return new Double(Double.parseDouble(getText()));
	}
}
/**
 * Return the given Text as Integer.
 * @throws NumberFormatException
 */
public java.lang.Integer getIntegerValue() throws NumberFormatException {
	if (ch.softenvironment.util.StringUtils.isNullOrEmpty(getText())) {
		return null;
	} else {
		return new Integer(Integer.parseInt(getText()));
	}
}
/**
 * Return the given Text as Long.
 * @throws NumberFormatException
 */
public java.lang.Long getLongValue() throws NumberFormatException {
	if (ch.softenvironment.util.StringUtils.isNullOrEmpty(getText())) {
		return null;
	} else {
		return new Long(Long.parseLong(getText()));
	}
}
/**
 * Overwrites.
 */
public String getText() {
	if (ch.softenvironment.util.StringUtils.isNullOrEmpty(super.getText())) {
		return null;
	} else {
		return super.getText();
	}
}
/**
 * Invoked when the text entered through an input method has changed.
 */
public void inputMethodTextChanged(java.awt.event.InputMethodEvent event) {
	char lastChar = event.getText().last();
	if (!(((lastChar >= '0') && (lastChar <= '9')) || (lastChar == '-') || (lastChar == '+') || (lastChar == '.'))) {
		if (isEditable() && isEnabled()) {
			Object parent = getRootPane().getParent();
			String title = ResourceManager.getResource(NumberTextField.class, "CTInvalidInput"); //$NON-NLS-1$
			String message = ResourceManager.getResource(NumberTextField.class, "CICorrectInput"); //$NON-NLS-1$
			// reset wrong value
			if (parent instanceof java.awt.Dialog) {
				ch.softenvironment.view.BaseDialog.showWarning((java.awt.Component)parent, title, message);
			} else {
				ch.softenvironment.view.BaseDialog.showWarning((java.awt.Component)parent, title, message);
			}
		}

		// ignore last Char Input
		event.consume();
	}
}
/**
 * Set the number of digits after comma to be displayed.
 */
public void setFractionDigits(int numberOfDigits) {
	if (numberOfDigits >= 0) {
		decFormat = new java.text.DecimalFormat();
		decFormat.setMinimumFractionDigits(numberOfDigits);
		decFormat.setMaximumFractionDigits(numberOfDigits);
		decFormat.setGroupingSize(3);			// separate thousand's
		decFormat.setGroupingUsed(true);
		decFormat.setDecimalSeparatorAlwaysShown(numberOfDigits > 0);
	}
}
/**
 * Overwrites.
 */
public void setText(String t) {
	if ((t == null) || (t.indexOf("null") > -1)) {
		// ignore String "null"
		super.setText("");
	} else {
//		setText(getDecimalFormat().format(value.doubleValue()));
/*
	try {
		if (getText().length() > 0) {
			setValue(new Double(getDecimalFormat().parse(getText()).doubleValue()));
		} else {
			setValue(null);
		}
	} catch (Throwable exception) {
		Object parent = getRootPane().getParent();
		String title = resDecimalTextField.getString("CTInvalidInput"); //$NON-NLS-1$
		String message = resDecimalTextField.getString("CICorrectInput"); //$NON-NLS-1$
		setValue(null);
		if (parent instanceof java.awt.Dialog) {
			new ErrorDialog((java.awt.Dialog)parent, title, message, exception);
		} else {
			new ErrorDialog((java.awt.Frame)parent, title, message, exception);
		}
	}
*/
			super.setText(t);
		}
	}
}
