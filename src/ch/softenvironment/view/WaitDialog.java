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
 * Wait-Dialog for user.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2004-04-27 09:14:58 $
 */
public class WaitDialog extends BaseDialog {
	private javax.swing.JPanel ivjBaseDialogContentPane = null;
	private javax.swing.JLabel ivjLblText = null;
	private javax.swing.JLabel ivjLblImage = null;
	private javax.swing.JProgressBar ivjPrgBar = null;

	private String title = null;
	private javax.swing.ImageIcon imageIcon = null;
/**
 * WaitDialog constructor comment.
 * @param owner java.awt.Frame
 * @param modal boolean
 */
public WaitDialog(java.awt.Frame owner, String title, String imagePath) {
	super(owner, false /* otherwise will not terminate */);

	if (title == null) {
		this.title = getResourceString("DlgTitle");
	} else {
		this.title = title;
	}

	if (imagePath == null) {
		this.imageIcon = ch.ehi.basics.i18n.ResourceBundle.getImageIcon(WaitDialog.class, "traffic_redlight.png");
	} else {
		this.imageIcon = new javax.swing.ImageIcon(getClass().getResource(imagePath));
	}

	initialize();
}
/**
 * Constructor
 * @param owner Symbol
 * @param modal Symbol
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public WaitDialog(java.awt.Frame owner, boolean modal) {
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
			getBaseDialogContentPane().add(getLblImage(), getLblImage().getName());
			getBaseDialogContentPane().add(getLblText(), getLblText().getName());
			getBaseDialogContentPane().add(getPrgBar(), getPrgBar().getName());
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
 * Return the JLabel1 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblImage() {
	if (ivjLblImage == null) {
		try {
			ivjLblImage = new javax.swing.JLabel();
			ivjLblImage.setName("LblImage");
			ivjLblImage.setText("JLabel1");
			ivjLblImage.setBounds(16, 14, 120, 232);
			// user code begin {1}
			ivjLblImage.setText("");
			ivjLblImage.setIcon(imageIcon);
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblImage;
}
/**
 * Return the LblText property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getLblText() {
	if (ivjLblText == null) {
		try {
			ivjLblText = new javax.swing.JLabel();
			ivjLblText.setName("LblText");
			ivjLblText.setText("Bitte gedulden Sie sich einen Moment...");
			ivjLblText.setBounds(162, 176, 254, 14);
			// user code begin {1}
			ivjLblText.setText(getResourceString("LblText_text"));
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLblText;
}
/**
 * Return the bar property value.
 * @return javax.swing.JProgressBar
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public javax.swing.JProgressBar getPrgBar() {
	if (ivjPrgBar == null) {
		try {
			ivjPrgBar = new javax.swing.JProgressBar();
			ivjPrgBar.setName("PrgBar");
			ivjPrgBar.setBounds(162, 93, 167, 14);
			ivjPrgBar.setValue(0);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjPrgBar;
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
		setLocation(100, 100);
		// user code end
		setName("WaitDialog");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(426, 289);
		setTitle("Kurze Pause");
		setContentPane(getBaseDialogContentPane());
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	getPrgBar().setVisible(false);
	setTitle(title);
	// user code end
}
/**
 * Let User know what happens
 */
public final void updateProgress(int percentage, String currentActivity) {
	getPrgBar().setValue(percentage);
	if (currentActivity != null) {
		getLblText().setText(currentActivity);
	}
}
}
