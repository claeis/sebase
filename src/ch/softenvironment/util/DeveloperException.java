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
 
/**
 * Show Developer failures.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2004-05-28 19:52:13 $
 */
public class DeveloperException extends RuntimeException {
	private String message = null;
	private String title = null;
	private String errorObject = null;
	private String errorMethod = null;
/**
 * Construct a DeveloperException.
 */
public DeveloperException(Class aClass, String method, String message) {
	this(aClass, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @param aClass (Class where error happened)
 * @param method (producing the error)
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public DeveloperException(Class aClass, String method, String message, String title) {
	this(aClass, method, message, title, null);
}
/**
 * Construct a DeveloperException.
 * @param aClass Class where error happened
 * @param method producing the error
 * @param title Title for ErrorDialog
 * @param message Message for ErrorDialog
 * @param e Original Exception happened
 */
public DeveloperException(Class aClass, String method, String message, String title, Throwable e) {
	super();

	String msg = message;
	if (e != null) {
		msg = msg + "[Original fault: " + e.getMessage() + "]";
	}
	
	Tracer.getInstance().developerError(aClass, method, msg);
	
	this.errorObject = aClass.getName();
	this.errorMethod = method;
	this.message = msg;
	if (title == null) {
		this.title = ResourceManager.getInstance().getResource(DeveloperException.class, "CTDevelopmentError");
	} else {
		this.title = title;
	}
}
/**
 * Construct a DeveloperException.
 */
public DeveloperException(Object object, String method, String message) {
	this(object, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title) {
	this(errorObject, errorMethod, message, title, null);
}
/**
 * Construct a DeveloperException.
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public DeveloperException(Object errorObject, String errorMethod, String message, String title, Throwable e) {
	this(errorObject.getClass(), errorMethod, message, title, e);
}
public String getMessage() {
	return message + "\n" + ResourceManager.getInstance().getResource(DeveloperException.class, "CISource") + ": " + errorObject + "." + errorMethod;
}
public String getTitle() {
	return title;
}
public String toString() {
    String s = getClass().getName();
    return (message != null) ? (s + ": " + message) : s;//$NON-NLS-1$
}
}
