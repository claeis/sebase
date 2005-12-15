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
import ch.softenvironment.client.ResourceManager;
/**
 * Show Developer failures.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.8 $ $Date: 2005-12-15 14:03:51 $
 */
public class DeveloperException extends RuntimeException {
	private String message = null;
	private String title = null;
	private String errorObject = null;
	private String errorMethod = null;
	private Throwable originalException = null;
/**
 * Construct a DeveloperException.
 * @see #DeveloperException(Class, String, String, String)
 */
public DeveloperException(Class type, String method, String message) {
	this(type, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @see #DeveloperException(Class, String, String, String, Throwable)
 */
public DeveloperException(Class type, String method, String message, String title) {
	this(type, method, message, title, null);
}
/**
 * Construct a DeveloperException.
 * @param type Class where error happened
 * @param method producing the error
 * @param title Title for ErrorDialog
 * @param message Message for ErrorDialog
 * @param e Original Exception happened
 */
public DeveloperException(Class type, String method, String message, String title, Throwable e) {
	super();
	this. originalException = e;
	
	String msg = message;
	if (e != null) {
		msg = msg + "[Original fault: " + e.getMessage() + "]";
	}
	
	Tracer.getInstance().developerError(type, method, msg);
	
    if (type != null) {
	   this.errorObject = type.getName();
    }
	this.errorMethod = method;
	this.message = msg;
	if (title == null) {
		this.title = ResourceManager.getResource(DeveloperException.class, "CTDevelopmentError");
	} else {
		this.title = title;
	}
}
/**
 * Construct a DeveloperException.
 * @see #DeveloperException(Object, String, String, String)
 */
public DeveloperException(Object object, String method, String message) {
	this(object, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @see #DeveloperException(Object, String, String, String, Throwable)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title) {
	this(errorObject, errorMethod, message, title, null);
}
/**
 * Construct a DeveloperException.
 * @param errorObject Instance where error happened
 * @see #DeveloperException(Class, String, String, String, Throwable)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title, Throwable e) {
	this(errorObject == null ? null : errorObject.getClass(), errorMethod, message, title, e);
}
/**
 * Return the original message and the source where Error happened.
 */
public String getMessage() {
	return message + "\n" + ResourceManager.getResource(DeveloperException.class, "CISource") + ": " + errorObject + "." + errorMethod;
}
/**
 * Return the original title of error.
 */
public String getTitle() {
	return title;
}
/*
public String toString() {
    String s = getClass().getName();
    return (message != null) ? (s + ": " + message) : s;//$NON-NLS-1$
}
*/
/**
 * Overwrites
 */
public String getLocalizedMessage() {
    StringBuffer buffer = new StringBuffer();
/*    if (message != null) {
        buffer.append("Developer note:\n- " + message);
    }
*/
    if (originalException != null) {
        buffer.append("\nOriginal exception:\n- " + originalException.getLocalizedMessage());
    }
    return buffer.toString();
}
}