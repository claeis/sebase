package ch.softenvironment.util;

import java.io.File;

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
 * Set of reusable String Utilities.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2004-05-08 13:34:09 $
 */
public abstract class StringUtils {
/**
 * Return a String representation of a boolean.
 * @return X => true; <emptyString> => false
 */
public static String getBooleanString(boolean value) {
	if (value) {
		return "Ja";
	} else {
		return "Nein";
	}
}
/**
 * Return PackageName of given class.
 */
public static String getPackageName(java.lang.Class type) {
	String className = type.getName();
	int index = className.lastIndexOf('.');
	return className.substring(0, index);	
}
/**
 * Return PackageName of an Instance.
 */
public static String getPackageName(Object object) {
	return getPackageName(object.getClass());	
}
/**
 * Return ClassName of given class without package path.
 */
public static String getPureClassName(java.lang.Class type) {
	String className = type.getName();
	int index = className.lastIndexOf('.');
	return className.substring(index + 1, className.length());
}
/**
 * Return ClassName of given class without package path.
 */
public static String getPureFileName(String absolutePath) {
	int index = absolutePath.lastIndexOf(File.separator);
	// 1) strip expected Path-separator
	String tmp = absolutePath.substring(index + 1, absolutePath.length());
	// 2) strip evtl. hardcoded DOS-separator on Unix-Plattforms
	index = tmp.lastIndexOf("\\");
	tmp = tmp.substring(index + 1, tmp.length());
	// 3) strip evtl. hardcoded Unix-separator on DOS-Plattforms
	index = tmp.lastIndexOf("/");
	return tmp.substring(index + 1, tmp.length());
}
/**
 * Return ClassName of an Instance without package path.
 */
public static String getPureClassName(Object object) {
	return getPureClassName(object.getClass());
}
/**
 * Return either String of value or empty String if nothing contained.
 * @return String
 */
public static String getString(Object value) {
	return value == null ? new String() : value.toString();
}
/**
 * Return either String or empty String if nothing contained.
 * @return String
 */
public static String getString(String value) {
	return value == null ? new String() : value;
}
/**
 * Return whether String is null or contains nothing.
 * @return boolean
 */
public static boolean isNullOrEmpty(String value) {
	return ((value == null) || (value.trim().length() == 0));
}
/**
 * Replace all occurences of searchTerm by replacement in source.
 * @return String
 */
public static String replace(String source, String searchTerm, String replacement) {
	// test: StringUtils.replace("X AS C, Attr1 AS Dummy", " AS ", " ");
	if (source == null) {
		return null;
	}
	String result = source;
	int index = source.indexOf(searchTerm);
	if (index > -1) {
		result = result.substring(0, index)	+ replacement + result.substring(index + searchTerm.length(), result.length());
		return replace(result, searchTerm, replacement);
	}
	return result;
}
}
