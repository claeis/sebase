package ch.softenvironment.client;

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
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import java.io.*;
import java.util.ArrayList;
import ch.softenvironment.util.ParserCSV;

/**
 * Manage the Application Settings by Properties file.
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2004-05-08 15:21:31 $
 */
public class ApplicationOptions extends java.util.Properties implements UserSettings {
	// values for Key-Values
	private final static String TRUE = "TRUE";
	private final static String FALSE = "FALSE";
	private final static String SEPARATOR = ";";
	private final static String HOME_DIRECTORY = "user.home";

	// Property Keys (non-NLS)
	// @see getKeySet()
	private final static String LOOK_AND_FEEL = "LOOK_AND_FEEL";
	private final static String BACKGROUND_COLOR = "BACKGROUND_COLOR";
	private final static String FONT = "FONT";
	private final static String FOREGROUND_COLOR = "FOREGROUND_COLOR";
	private final static String IMPORT_DIRECTORY = "IMPORT_DIRECTORY";
	private final static String LANGUAGE = "LANGUAGE";
	private final static String COUNTRY = "COUNTRY";
	private final static String SHOW_STATUS_BAR = "SHOW_STATUS_BAR";
	private final static String SHOW_TOOLBAR = "SHOW_TOOLBAR";
	private final static String WORKING_DIRECTORY = "WORKING_DIRECTORY";
	private final static String LAST_FILES = "LAST_FILES";
	private final static String QUERY_DELETION = "QUERY_DELETION";
	private final static String WINDOW_HEIGHT = "WINDOW_HEIGHT";
	private final static String WINDOW_WIDTH = "WINDOW_WIDTH";
	private final static String WINDOW_X = "WINDOW_X";
	private final static String WINDOW_Y = "WINDOW_Y";

	// variables
	private String filename = null;
/**
 * UserSettings constructor comment.
 */
private ApplicationOptions() {
	super();
}
/**
 * Instantiates and loads the UserSettings.
 * @see getKeySet()
 */
protected static ApplicationOptions createDefault() {
	ApplicationOptions userSettings = new ApplicationOptions();

	userSettings.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
	userSettings.setBackgroundColor(java.awt.Color.white);
	userSettings.setFont("Monospaced-BOLD-9");
	userSettings.setForegroundColor(java.awt.Color.black);
	userSettings.setImportDirectory(System.getProperty(HOME_DIRECTORY));
	userSettings.setLanguage(java.util.Locale.GERMAN.getLanguage());
	userSettings.setCountry("CH");
	
	userSettings.setShowStatusBar(new Boolean(true));
	userSettings.setShowToolBar(new Boolean(true));
	userSettings.setWorkingDirectory(System.getProperty(HOME_DIRECTORY));
	userSettings.setLastFiles(new ArrayList());

	userSettings.setWindowHeight(new Integer(570));
	userSettings.setWindowWidth(new Integer(850));
	userSettings.setWindowX(new Integer(10));
	userSettings.setWindowY(new Integer(10));

    return userSettings;
}
/**
 * Return whether the User is allowed to use Application or not.
 * @return boolean
 */
public boolean getActive() {
	return true;
}
/**
 * Return whether the User is the Administrator himself.
 * @return boolean
 */
public boolean getAdmin() {
	return false;
}
/**
 * Gets the backgroundColor property (java.awt.Color) value.
 * @return The backgroundColor property value.
 * @see #setBackgroundColor
 */
public java.awt.Color getBackgroundColor() {
	return new java.awt.Color(new Integer(getProperty(BACKGROUND_COLOR)).intValue());
}
/**
 * Gets the Country property (java.lang.String) value.
 * @return The Country String
 * @see #setCountry
 */
public java.lang.String getCountry() {
	return getProperty(COUNTRY);
}
/**
 * Gets the font property (java.lang.String) value.
 * @return The font property value.
 * @see #setFont
 */
public String getFont() {
	return getProperty(FONT);
}
/**
 * Gets the foregroundColor property (java.awt.Color) value.
 * @return The foregroundColor property value.
 * @see #setForegroundColor
 */
public java.awt.Color getForegroundColor() {
	return new java.awt.Color(new Integer(getProperty(FOREGROUND_COLOR)).intValue());
}
/**
 * Gets the importDirectory property (java.lang.String) value.
 * @return The importDirectory property value.
 * @see #setImportDirectory
 */
public java.lang.String getImportDirectory() {
	return getProperty(IMPORT_DIRECTORY);
}
/**
 * @return all keys managed by Settings.
 * @see class definition
 * @see createDefault()
 */
private static java.util.Set getKeySet() {
	java.util.Set set = new java.util.HashSet();
	set.add(LOOK_AND_FEEL);
	set.add(BACKGROUND_COLOR);
	set.add(FONT);
	set.add(FOREGROUND_COLOR);
	set.add(IMPORT_DIRECTORY);
	set.add(LANGUAGE);
	set.add(COUNTRY);
	set.add(SHOW_STATUS_BAR);
	set.add(SHOW_TOOLBAR);
	set.add(WORKING_DIRECTORY);
	set.add(LAST_FILES);
	set.add(QUERY_DELETION);
	set.add(WINDOW_HEIGHT);
	set.add(WINDOW_WIDTH);
	set.add(WINDOW_X);
	set.add(WINDOW_Y);

	return set;
}
/**
 * Gets the language property (java.lang.String) value.
 * @return The language property value.
 * @see #setLanguage
 */
public java.lang.String getLanguage() {
	return getProperty(LANGUAGE);
}
/**
 * Gets the lastFiles opened property (java.lang.String) value.
 * @see #setWorkingDirectory
 */
public java.util.List getLastFiles() {
	return ParserCSV.stringToArray((String)getProperty(LAST_FILES), SEPARATOR);
}

/**
 * Gets the 'Look & Feel' property (java.lang.String) value.
 * @return The language property value.
 * @see #setLookAndFeel(String)
 */
public java.lang.String getLookAndFeel() {
	return getProperty(LOOK_AND_FEEL);
}
/**
 * Return the e-Mail Provider host to send e-Mails.
 * @return java.lang.String (for e.g. "mail.bluewin.ch")
 */
public java.lang.String getProviderSMTP() {
	return null;
}
/**
 * Gets the showLogView property (java.lang.Boolean) value.
 * @return The showLogView property value.
 * @see #setShowLogView
 * @deprecated
 */
public java.lang.Boolean getShowLogView() {
//	return Boolean.valueOf(getProperty(SHOW_LOG_VIEW));
	return Boolean.TRUE;
}
/**
 * Gets the showStatusBar property (java.lang.Boolean) value.
 * @return The showStatusBar property value.
 * @see #setShowStatusBar
 */
public java.lang.Boolean getShowStatusBar() {
	return Boolean.valueOf(getProperty(SHOW_STATUS_BAR));
}
/**
 * Gets the showToolBar property (java.lang.Boolean) value.
 * @return The showToolBar property value.
 * @see #setShowToolBar
 */
public java.lang.Boolean getShowToolBar() {
	return Boolean.valueOf(getProperty(SHOW_TOOLBAR));
}
/**
 * Return the User's id, by means the login Id to the current application.
 * @return String (for e.g. "phirzel")
 * @see java.util.Locale
 */
public java.lang.String getUserId() {
	// there is no specific User or Login-Id
	return "<NONE>";
}
/**
 * Return property.
 */
public java.lang.Integer getWindowHeight() {
	return new Integer(getProperty(WINDOW_HEIGHT));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowWidth() {
	return new Integer(getProperty(WINDOW_WIDTH));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowX() {
	return new Integer(getProperty(WINDOW_X));
}
/**
 * Return property.
 */
public java.lang.Integer getWindowY() {
	return new Integer(getProperty(WINDOW_Y));
}
/**
 * Gets the workingDirectory property (java.lang.String) value.
 * @return The workingDirectory property value.
 * @see #setWorkingDirectory
 */
public java.lang.String getWorkingDirectory() {
	return getProperty(WORKING_DIRECTORY);
}
/**
 * Instantiates and loads the UserSettings.
 */
public static ApplicationOptions load(String filename) {
	ApplicationOptions userSettings = createDefault();
	userSettings.filename = filename;
	
	try {
	    FileInputStream inputStream = new FileInputStream(filename);
		ApplicationOptions tmp = new ApplicationOptions();
		tmp.load(inputStream);

		// try to reuse given keys
		// copy mechanism makes sure newer Versions of this Class
		// with additional keys cause no problems
		java.util.Iterator keys = getKeySet().iterator();
		while (keys.hasNext()) {
			String key = (String)keys.next();
			if (tmp.containsKey(key)) {
				userSettings.setProperty(key, tmp.getProperty(key));
			}
		}

		// set the Locale
                // doesn't belong to UserSettings
                // ce2003-03-06 don't change Locale!!!
		// java.util.Locale.setDefault(new java.util.Locale(tmp.getLanguage(), tmp.getCountry()));
		// Tracer.getInstance().runtimeInfo("Locale is: " + java.util.Locale.getDefault().toString());
	} catch(FileNotFoundException fe) {
		
	} catch(IOException ioe) {
		
	}
	
    return userSettings;
}
/**
 * Saves the UserSettings.
 */
public void save() throws IOException, FileNotFoundException {
    FileOutputStream outputStream = new FileOutputStream(filename);
    super.store(outputStream, "TCO-Tool User Properties");
}
/**
 * Sets the backgroundColor property (java.awt.Color) value.
 * @param backgroundColor The new value for the property.
 * @see #getBackgroundColor
 */
public void setBackgroundColor(java.awt.Color backgroundColor) {
	setProperty(BACKGROUND_COLOR, (new Integer(backgroundColor.getRGB())).toString());
}
/**
 * Sets the Country property (java.lang.String) value.
 * @param language (for e.g. "CH"; "FR", etc)
 * @see #getCountry
 */
public void setCountry(java.lang.String country) {
	setProperty(COUNTRY, country);
}
/**
 * Sets the font property (java.lang.String) value.
 * This Font is used for graphical nodes and edges.
 * @param font The new value for the property.
 * @see #getFont
 */
public void setFont(String font) {
	setProperty(FONT, font);
}
/**
 * Sets the foregroundColor property (java.awt.Color) value.
 * @param foregroundColor The new value for the property.
 * @see #getForegroundColor
 */
public void setForegroundColor(java.awt.Color foregroundColor) {
	setProperty(FOREGROUND_COLOR, (new Integer(foregroundColor.getRGB())).toString());
}
/**
 * Sets the importDirectory property (java.lang.String) value.
 * @param importDirectory The new value for the property.
 * @see #getImportDirectory
 */
public void setImportDirectory(java.lang.String importDirectory) {
	setProperty(IMPORT_DIRECTORY, importDirectory);
}
/**
 * Sets the language property (java.lang.String) value.
 * @param language (for e.g. "de"; "fr", etc)
 * @see #getLanguage
 */
public void setLanguage(java.lang.String language) {
	setProperty(LANGUAGE, language);
}
/**
 * Sets the lastFiles opened property (java.lang.String) value.
 * @param 0..n Files separated by Semikolon ';'.
 * @see #getLastFiles
 */
public void setLastFiles(java.util.List lastFiles) {
	setProperty(LAST_FILES, ParserCSV.arrayToString(lastFiles, SEPARATOR));
}
/**
 * Sets the 'Look & Feel' property (java.lang.String) value.
 * This Font is used for graphical nodes and edges.
 * @param font The new value for the property.
 * @see #getLookAndFeel
 */
public void setLookAndFeel(String string) {
	setProperty(LOOK_AND_FEEL, string);
}
/**
 * Sets the showStatusBar property (java.lang.Boolean) value.
 * @param showStatusBar The new value for the property.
 * @see #getShowStatusBar
 */
public void setShowStatusBar(java.lang.Boolean showStatusBar) {
	setProperty(SHOW_STATUS_BAR, showStatusBar.toString());
}
/**
 * Sets the showToolBar property (java.lang.Boolean) value.
 * @param showToolBar The new value for the property.
 * @see #getShowToolBar
 */
public void setShowToolBar(java.lang.Boolean showToolBar) {
	setProperty(SHOW_TOOLBAR, showToolBar.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowHeight(java.lang.Integer value) {
	setProperty(WINDOW_HEIGHT, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowWidth(java.lang.Integer value) {
	setProperty(WINDOW_WIDTH, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowX(java.lang.Integer value) {
	setProperty(WINDOW_X, value.toString());
}
/**
 * Sets a property (java.lang.String) value.
 * @param value
 */
public void setWindowY(java.lang.Integer value) {
	setProperty(WINDOW_Y, value.toString());
}
/**
 * Sets the workingDirectory property (java.lang.String) value.
 * @param workingDirectory The new value for the property.
 * @see #getWorkingDirectory
 */
public void setWorkingDirectory(java.lang.String workingDirectory) {
	setProperty(WORKING_DIRECTORY, workingDirectory);
}
}
