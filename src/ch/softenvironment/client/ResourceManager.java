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
 */

import java.util.*;

/**
 * Manage a Map of Resources, resp *.properties for different Classes 
 * dealing with UI.
 * Resources are usually desired by an Application for a specific Locale
 * (for e.g. de_CH), therefore the performance may be enhanced if
 * the mapped *.properties files are cached during runtime.
 * 
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2005-06-05 09:03:20 $
 */
public class ResourceManager {
	private static ResourceManager manager = null;
	private Map resources = new HashMap();
	private Locale currentLocale = null;
	/**
	 * Return an application wide ResourceManager.
	 * Design Pattern: Singleton.
	 * @return ResourceManager
	 */
	public static ResourceManager getInstance() {
		if (manager == null) {
			manager = new ResourceManager();
		}
		return manager;
	}
	/**
	 * Convenience Method.
	 * @see #getResource(java.lang.Class, Locale, String, ClassLoader)
	 */
	public static String getResource(java.lang.Class owner, String propertyName, ClassLoader loader) {
	    return getInstance().getResource(owner, Locale.getDefault(), propertyName, loader);
	}
	/**
	 * Convenience Method.
	 * @see #getResource(java.lang.Class, Locale, String, ClassLoader)
	 */
	public static String getResource(java.lang.Class owner, String propertyName) {
		return getInstance().getResource(owner, Locale.getDefault(), propertyName, null);
	}
	/**
	 * Return NLS-String for a certain Property.
	 * @param holder A <holder-Class>[_<language>].properties file must exist
	 * @param locale
	 * @param propertyName
	 * @param loader null for default ClassLoader
	 * @return String NLS-String
	 * @see ch.ehi.basics.i18n.ResourceBundle
	 */
	public String getResource(java.lang.Class holder, Locale locale, String propertyName, ClassLoader loader) throws MissingResourceException {
		if (!locale.equals(currentLocale)) {
			// reset cached resources
			resources = new HashMap();
			currentLocale = locale;
		}
		if (resources.containsKey(holder)) {
			return ((ResourceBundle)resources.get(holder)).getString(propertyName);
		} else {
			ResourceBundle bundle = null;
			if (loader == null) {
			    bundle = ch.ehi.basics.i18n.ResourceBundle.getBundle(holder, locale);
			} else {
			     bundle = ch.ehi.basics.i18n.ResourceBundle.getBundle(holder, locale, loader);
			}
			resources.put(holder, bundle);
			return bundle.getString(propertyName);
		}
	}
/**
 * Convenience Method.
 * Often a property ressource ends with ":" in case of a label or without ":"
 * in case of a Window-Title, Table-Column etc.
 * Therefore it might be better to add or cut off this character than providing
 * different Translations for likely the same word.
 * @param asLabel true->show ":" at end of String; false->suppress ":" at end of String
 * @see #getResource(java.lang.Class, Locale, String)
 */
public static String getResource(java.lang.Class owner, String propertyName, boolean asLabel) {
	try {
		String ressource = getInstance().getResource(owner, Locale.getDefault(), propertyName, null).trim();
		if (asLabel) {
			if (ressource.charAt(ressource.length()-1) != ':') {
			    // add ":"
				return ressource + ":";
			}
		} else {
			if (ressource.charAt(ressource.length()-1) == ':') {
			    // cut off ":"
				return ressource.substring(0, ressource.length()-1);
			}
		}
		return ressource;
	} catch(NullPointerException e) {
		return null;
	}
}
}
