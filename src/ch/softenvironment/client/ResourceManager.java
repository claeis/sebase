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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.softenvironment.util.DeveloperException;
import ch.softenvironment.util.Tracer;

/**
 * Manage a Map of Resources, resp *.properties for different Classes 
 * dealing with UI.
 * Resources are usually desired by an Application for a specific Locale
 * (for e.g. de_CH), therefore the performance may be enhanced if
 * the mapped *.properties files are cached during runtime.
 * 
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.7 $ $Date: 2006-06-29 22:24:57 $
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
		return getResource(owner, propertyName, Locale.getDefault());
	}
    public static String getResource(java.lang.Class owner, String propertyName, Locale locale) {
        return getInstance().getResource(owner, locale, propertyName, null);
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
	    java.util.ResourceBundle bundle = getBundle(holder, locale, loader);
	    if (bundle == null) {
	        throw new DeveloperException("no bundle for holder=" + holder.getName());
	    }
		return bundle.getString(propertyName);
	}
	/**
	 * Return cached bundle or instantiate it otherwise.
	 * @param holder
	 * @param locale
	 * @param loader
	 * @return
	 */
	private java.util.ResourceBundle getBundle(Class holder, Locale locale, ClassLoader loader) {
		if (!locale.equals(currentLocale)) {
			// reset cached resources
			resources = new HashMap();
			currentLocale = locale;
		}
		if (resources.containsKey(holder)) {
			return ((java.util.ResourceBundle)resources.get(holder));
		} else {
			java.util.ResourceBundle bundle = null;
			if (loader == null) {
			    bundle = ch.ehi.basics.i18n.ResourceBundle.getBundle(holder, locale);
			} else {
			    bundle = ch.ehi.basics.i18n.ResourceBundle.getBundle(holder, locale, loader);
			}
			resources.put(holder, bundle);
			return bundle;
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
		String resource = getInstance().getResource(owner, Locale.getDefault(), propertyName, null).trim();
		return convertLabel(resource, asLabel);
	} catch(NullPointerException e) {
		return null;
	}
}
/**
 * Return the first translation found for key matching given pattern.
 * This might be useful for generic mappings for e.g. from an Object properties
 * to a Label corresponding in a GUI.
 * @param owner
 * @param pattern (for e.g. "LblMy[a-zA-Z0-9]*_text")
 * @param asLabel
 * @return
 * @throws MissingResourceException
 * @see http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html
 */
public static String getResourceMatch(java.lang.Class owner, String pattern, boolean asLabel) throws MissingResourceException {
    java.util.ResourceBundle bundle = getInstance().getBundle(owner, Locale.getDefault(), null);
    Enumeration keys = bundle.getKeys();
    Pattern p = Pattern.compile(pattern); //"Lbl*myKey*_text"
    while (keys.hasMoreElements()) {
        String key = (String)keys.nextElement();
        Matcher m = p.matcher(key);
        if (m.matches()) {
            return convertLabel(bundle.getString(key), asLabel);
        }
    }
    throw new MissingResourceException("No key matching pattern: " + pattern, owner.getName(), pattern);
}
/**
 * Add or cut off ':'.
 * @param resource
 * @param asLabel
 * @return
 */
private static String convertLabel(String resource, boolean asLabel) {
    if (resource == null) {
        return resource;
    }
    
    if (asLabel) {
		if (resource.charAt(resource.length()-1) != ':') {
		    // add ":"
			return resource + ":";
		}
	} else {
		if (resource.charAt(resource.length()-1) == ':') {
		    // cut off ":"
			return resource.substring(0, resource.length()-1);
		}
	}
    return resource;
}
}
