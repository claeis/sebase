package ch.softenvironment.view;

/**
 * Extended JTextField to show any Number values.
 * Allows visual connection of Attribute-to-Attribute connection from e.g.
 * a Double-Property of a Model to this Components value-Property. Make sure
 * to trigger <b>propertyChange</b>-Event from this Component towards the Model.
 * The Model will be updated after a focusLost-Event.
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class NumberTextField extends javax.swing.JTextField implements java.awt.event.InputMethodListener {
	private static java.util.ResourceBundle resources = ch.ehi.basics.i18n.ResourceBundle.getBundle(NumberTextField.class);
	private java.text.DecimalFormat decFormat = null;
/**
 * Constructor.
 */
public NumberTextField() {
	super();

	addInputMethodListener(this);
}
/**
 * Invoked when the caret within composed text has changed.
 */
public void caretPositionChanged(java.awt.event.InputMethodEvent event) {
}
/**
 * Return the given Text as Double.
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
		Object parent = getRootPane().getParent();
		String title = resources.getString("CTInvalidInput"); //$NON-NLS-1$
		String message = resources.getString("CICorrectInput"); //$NON-NLS-1$
		// reset wrong value
		if (parent instanceof java.awt.Dialog) {
			new WarningDialog((java.awt.Dialog)parent, title, message);
		} else {
			new WarningDialog((java.awt.Frame)parent, title, message);
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
//			setText(getDecimalFormat().format(value.doubleValue()));
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
