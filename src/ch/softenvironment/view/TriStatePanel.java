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
 * Panel allowing entry of 3 different States:
 * - true
 * - false
 * - undefined
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2004-10-26 19:21:21 $
 */
public class TriStatePanel extends BasePanel {
	private javax.swing.JRadioButton ivjRbtFalse = null;
	private javax.swing.JRadioButton ivjRbtTrue = null;
	private javax.swing.JRadioButton ivjRbtAll = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private java.lang.Boolean fieldValue = new Boolean(false);

class IvjEventHandler implements java.awt.event.ItemListener {
		public void itemStateChanged(java.awt.event.ItemEvent e) {
			if (e.getSource() == TriStatePanel.this.getRbtAll()) 
				connEtoC1(e);
			if (e.getSource() == TriStatePanel.this.getRbtTrue()) 
				connEtoC2(e);
			if (e.getSource() == TriStatePanel.this.getRbtFalse()) 
				connEtoC3(e);
		};
	};
/**
 * TriStatePanel constructor comment.
 */
public TriStatePanel() {
	super();
	initialize();
}
/**
 * TriStatePanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public TriStatePanel(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * TriStatePanel constructor comment.
 * @param layout java.awt.LayoutManager
 * @param isDoubleBuffered boolean
 */
public TriStatePanel(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
}
/**
 * TriStatePanel constructor comment.
 * @param isDoubleBuffered boolean
 */
public TriStatePanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
}
/**
 * Comment
 */
private void changed(java.awt.event.ItemEvent itemEvent) {
	if (getRbtFalse().isSelected()) {
		setValue(new Boolean(false));
	} else if (getRbtTrue().isSelected()) {
		setValue(new Boolean(true));
	} else {
		setValue(null);
	}
}
/**
 * connEtoC1:  (RbtAll.item.itemStateChanged(java.awt.event.ItemEvent) --> TriStatePanel.changed(Ljava.awt.event.ItemEvent;)V)
 * @param arg1 java.awt.event.ItemEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.ItemEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.changed(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (RbtTrue.item.itemStateChanged(java.awt.event.ItemEvent) --> TriStatePanel.changed(Ljava.awt.event.ItemEvent;)V)
 * @param arg1 java.awt.event.ItemEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.ItemEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.changed(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (RbtFalse.item.itemStateChanged(java.awt.event.ItemEvent) --> TriStatePanel.changed(Ljava.awt.event.ItemEvent;)V)
 * @param arg1 java.awt.event.ItemEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.ItemEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.changed(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Return !getValue().
 */
public java.lang.Boolean getNotValue() {
	if (fieldValue != null) {
		return new Boolean(!fieldValue.booleanValue());
	} else {
		return null;
	}
}
/**
 * Return the RbtNone property value.
 * @return javax.swing.JRadioButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JRadioButton getRbtAll() {
	if (ivjRbtAll == null) {
		try {
			ivjRbtAll = new javax.swing.JRadioButton();
			ivjRbtAll.setName("RbtAll");
			ivjRbtAll.setText("Alle");
			ivjRbtAll.setBounds(0, 0, 93, 22);
			// user code begin {1}
			ivjRbtAll.setText(getResourceString("CI_All_text"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjRbtAll;
}
/**
 * Return the RbtFalse property value.
 * @return javax.swing.JRadioButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JRadioButton getRbtFalse() {
	if (ivjRbtFalse == null) {
		try {
			ivjRbtFalse = new javax.swing.JRadioButton();
			ivjRbtFalse.setName("RbtFalse");
			ivjRbtFalse.setText("Nein");
			ivjRbtFalse.setBounds(200, 0, 93, 22);
			// user code begin {1}
			ivjRbtFalse.setText(getResourceString(ch.softenvironment.util.StringUtils.class, "CI_No_text"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjRbtFalse;
}
/**
 * Return the RbtTrue property value.
 * @return javax.swing.JRadioButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JRadioButton getRbtTrue() {
	if (ivjRbtTrue == null) {
		try {
			ivjRbtTrue = new javax.swing.JRadioButton();
			ivjRbtTrue.setName("RbtTrue");
			ivjRbtTrue.setText("Ja");
			ivjRbtTrue.setBounds(100, 0, 93, 21);
			// user code begin {1}
			ivjRbtTrue.setText(getResourceString(ch.softenvironment.util.StringUtils.class, "CI_Yes_text"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjRbtTrue;
}
/**
 * Gets the value property (java.lang.Boolean) value.
 * @return The value property value.
 * @see #setValue
 */
public java.lang.Boolean getValue() {
	return fieldValue;
}
/**
 * Overwrites.
 */
protected void handleException(java.lang.Throwable exception) {
	super.handleException(exception);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	getRbtAll().addItemListener(ivjEventHandler);
	getRbtTrue().addItemListener(ivjEventHandler);
	getRbtFalse().addItemListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("TriStatePanel");
		setLayout(null);
		setSize(300, 22);
		add(getRbtAll(), getRbtAll().getName());
		add(getRbtTrue(), getRbtTrue().getName());
		add(getRbtFalse(), getRbtFalse().getName());
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
	group.add(getRbtAll());
	group.add(getRbtTrue());
	group.add(getRbtFalse());
	getRbtAll().setSelected(true);
	// user code end
}
/**
 * Sets the value property (java.lang.Boolean) value.
 * @param value The new value for the property.
 * @see #getValue
 */
public void setValue(java.lang.Boolean value) {
	if (((value == null) && (fieldValue != null)) ||
		((value != null) && (!value.equals(fieldValue)))) {
			// prevent pinging around
			Boolean oldValue = fieldValue;
			fieldValue = value;
			firePropertyChange("value", oldValue, value);
		
			if (value != null) {
				if (value.booleanValue()) {
					getRbtTrue().setSelected(true);
				} else {
					getRbtFalse().setSelected(true);
				}
			}
	}
}
}
