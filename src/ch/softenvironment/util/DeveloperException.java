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
 * @version $Revision: 1.9 $ $Date: 2006-01-17 19:10:15 $
 */
public class DeveloperException extends RuntimeException {
	private String title = null;
	private String errorObject = null;
	private String errorMethod = null;
/**
 * @see #DeveloperException(Class, String, String, String)
 */
public DeveloperException(Class type, String method, String message) {
	this(type, method, message, null);
}
/**
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
 * @param cause Original Exception happened
 */
public DeveloperException(Class type, String method, String message, String title, Throwable cause) {
	super(message, cause);
	
	String msg = "";
	if (cause != null) {
		msg = "[" + ResourceManager.getResource(DeveloperException.class, "CIOriginalException") + ": " + cause.getMessage() + "]";
	}
	
	Tracer.getInstance().developerError(type, method, message + " " + msg);
	
    if (type != null) {
	   this.errorObject = type.getName();
    }
	this.errorMethod = method;
	if (title == null) {
		this.title = ResourceManager.getResource(DeveloperException.class, "CTDevelopmentError");
	} else {
		this.title = title;
	}
}
/**
 * @see #DeveloperException(Object, String, String, String)
 */
public DeveloperException(Object object, String method, String message) {
	this(object, method, message, null);
}
/**
 * @see #DeveloperException(Object, String, String, String, Throwable)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title) {
	this(errorObject, errorMethod, message, title, null);
}
/**
 * @param errorObject Instance where error happened
 * @see #DeveloperException(Class, String, String, String, Throwable)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title, Throwable e) {
	this(errorObject == null ? null : errorObject.getClass(), errorMethod, message, title, e);
}
/**
 * Return the original title of error,
 * for e.g. to use in ErrorDialog as Title.
 */
public String getTitle() {
	return title;
}
/**
 * Overwrites.
 * Print also source of failure and causing Exception if available.
 */
public String getLocalizedMessage() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(getMessage());
    // source of failure
    buffer.append("\n" + ResourceManager.getResource(DeveloperException.class, "CISource") + ": " + errorObject + "#" + errorMethod);
    if (getCause() != null) {
        // initiating Exception
        buffer.append("\n" + ResourceManager.getResource(DeveloperException.class, "CIOriginalException") + "=[" + getCause().getLocalizedMessage() + "]");
    }
    return buffer.toString();
}
}