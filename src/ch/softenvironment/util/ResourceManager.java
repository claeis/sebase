package ch.softenvironment.util;

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
 * @version $Revision: 1.2 $ $Date: 2004-05-03 20:26:14 $
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
	 * @see getResource(java.lang.Class, Locale, String)
	 */
	public String getResource(java.lang.Class holder, String propertyName) throws MissingResourceException {
		return getResource(holder, Locale.getDefault(), propertyName);
	}
	/**
	 * Return NLS-String for a certain Property.
	 * @param holder A <holder-Class>[_<language>].properties file must exist
	 * @param locale
	 * @param propertyName
	 * @return String NLS-String
	 * @see ch.ehi.basics.i18n.ResourceBundle
	 * @throws MissingResourceException
	 */
	public String getResource(java.lang.Class holder, Locale locale, String propertyName) throws MissingResourceException {
		if (!locale.equals(currentLocale)) {
			// reset cached resources
			Tracer.getInstance().debug(this, "getResource(..)", "Reset resource-properties");
			resources = new HashMap();
			currentLocale = locale;
		}
		if (resources.containsKey(holder)) {
			return ((ResourceBundle)resources.get(holder)).getString(propertyName);
		} else {
			ResourceBundle bundle = ch.ehi.basics.i18n.ResourceBundle.getBundle(holder, locale);
			resources.put(holder, bundle);
			return bundle.getString(propertyName);
		}
	}
}
