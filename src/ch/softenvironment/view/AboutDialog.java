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
 * Product Info Dialog.
 * @author: @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2004-08-25 16:00:33 $
 */
public class AboutDialog extends BaseDialog {
	private javax.swing.JPanel ivjBaseDialogContentPane = null;
	private javax.swing.JPanel ivjJPanel1 = null;
	private javax.swing.JLabel ivjLblAbout = null;
	private javax.swing.JLabel ivjLblCopyright = null;
	private javax.swing.JLabel ivjLblLicencedFor = null;
	private javax.swing.JLabel ivjLblTitle = null;
	private javax.swing.JTextArea ivjTxaLicence = null;
/**
 * AboutDialog constructor comment.
 * @param owner java.awt.Frame
 * @param title java.lang.String
 * @param modal boolean
 */
public AboutDialog(java.awt.Frame owner, String application, String version, String copyrightPeriod, String licence) {
	super(owner, null, true);
	initialize();

	setTitle(getResourceString("DlgTitle") + " " + application);
	getLblTitle().setText(application + " " + version);
	getLblCopyright().setText("Copyright (c) softEnvironment " + copyrightPeriod);
	getTxaLicence().setText(licence);

	show();
}
/**
 * Constructor
 * @param owner Symbol
 * @param modal Symbol
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public AboutDialog(java.awt.Frame owner, boolean modal) {
	super(owner, modal);
	initialize();
}
/**
 * Return the BaseDialogContentPane property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getBaseDialogContentPane() {
	if (ivjBaseDialogContentPane == null) {
		try {
			ivjBaseDialogContentPane = new javax.swing.JPanel();
			ivjBaseDialogContentPane.setName("BaseDialogContentPane");
			ivjBaseDialogContentPane.setLayout(null);
			getBaseDialogContentPane().add(getLblTitle(), getLblTitle().getName());
			getBaseDialogContentPane().add(getLblLicencedFor(), getLblLicencedFor().getName());
			getBaseDialogContentPane().add(getJPanel1(), getJPanel1().getName());
			getBaseDialogContentPane().add(getLblCopyright(), getLblCopyright().getName());
			getBaseDialogContentPane().add(getLblAbout(), getLblAbout().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjBaseDialogContentPane;
}
/**
 * Return the JPanel1 property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJPanel1() {
	if (ivjJPanel1 == null) {
		try {
			ivjJPanel1 = new javax.swing.JPanel();
			ivjJPanel1.setName("JPanel1");
			ivjJPanel1.setBorder(new javax.swing.border.EtchedBorder());
			ivjJPanel1.setLayout(null);
			ivjJPanel1.setBounds(64, 351, 383, 105);
			getJPanel1().add(getTxaLicence(), getTxaLicence().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJPanel1;
}
/**
 * Return the LblAbout property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblAbout() {
	if (ivjLblAbout == null) {
		try {
			ivjLblAbout = new javax.swing.JLabel();
			ivjLblAbout.setName("LblAbout");
			ivjLblAbout.setText("");
			ivjLblAbout.setBounds(33, 73, 462, 240);
			// user code begin {1}
			ivjLblAbout.setIcon(ch.ehi.basics.i18n.ResourceBundle.getImageIcon(AboutDialog.class, "about.png"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblAbout;
}
/**
 * Return the LblCopyright property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblCopyright() {
	if (ivjLblCopyright == null) {
		try {
			ivjLblCopyright = new javax.swing.JLabel();
			ivjLblCopyright.setName("LblCopyright");
			ivjLblCopyright.setText("Copyright (c) softEnvironment 2001-2003");
			ivjLblCopyright.setBounds(127, 461, 280, 27);
			// user code begin {1}
			ivjLblCopyright.setText("Copyright (c) softEnvironment 2001-2004");
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblCopyright;
}
/**
 * Return the LblLicencedFor property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblLicencedFor() {
	if (ivjLblLicencedFor == null) {
		try {
			ivjLblLicencedFor = new javax.swing.JLabel();
			ivjLblLicencedFor.setName("LblLicencedFor");
			ivjLblLicencedFor.setFont(new java.awt.Font("Arial", 1, 14));
			ivjLblLicencedFor.setText("Dieses Produkt ist lizenziert für:");
			ivjLblLicencedFor.setBounds(64, 320, 285, 26);
			// user code begin {1}
			ivjLblLicencedFor.setText(getResourceString("LblLicencedFor_text"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblLicencedFor;
}
/**
 * Return the LblTitle property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblTitle() {
	if (ivjLblTitle == null) {
		try {
			ivjLblTitle = new javax.swing.JLabel();
			ivjLblTitle.setName("LblTitle");
			ivjLblTitle.setFont(new java.awt.Font("Arial", 1, 18));
			ivjLblTitle.setText("Lepra-Projektadministration Vx.x.x");
			ivjLblTitle.setBounds(15, 16, 497, 38);
			// user code begin {1}
			ivjLblTitle.setText("<your product name>");
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblTitle;
}
/**
 * Return the TxaLicence property value.
 * @return javax.swing.JTextArea
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JTextArea getTxaLicence() {
	if (ivjTxaLicence == null) {
		try {
			ivjTxaLicence = new javax.swing.JTextArea();
			ivjTxaLicence.setName("TxaLicence");
			ivjTxaLicence.setFont(new java.awt.Font("Arial", 1, 12));
			ivjTxaLicence.setBounds(13, 12, 354, 78);
			ivjTxaLicence.setEditable(false);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjTxaLicence;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
protected void handleException(java.lang.Throwable exception) {
	super.handleException(exception);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("AboutDialog");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(534, 553);
		setTitle("Info zu ");
		setContentPane(getBaseDialogContentPane());
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	setTitle(getResourceString("DlgTitle"));
	// user code end
}
}
