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
 * Show failures within Application a User must know.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2004-05-28 19:52:43 $
 */
public class UserException extends DeveloperException {
/**
 * Construct a DeveloperException.
 */
public UserException(Class aClass, String method, String message) {
	this(aClass, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @param aClass (Class where error happened)
 * @param method (producing the error)
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public UserException(Class aClass, String method, String message, String title) {
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
public UserException(Class aClass, String method, String message, String title, Throwable e) {
	super(aClass, method, message, (title==null? "Anwendungsfehler": title), e);
}
/**
 * Construct a DeveloperException.
 */
public UserException(Object object, String method, String message) {
	this(object, method, message, null);
}
/**
 * Construct a DeveloperException.
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public UserException(Object errorObject, String errorMethod, String message, String title) {
	this(errorObject, errorMethod, message, title, null);
}
/**
 * Construct a DeveloperException.
 * @param title (Title for ErrorDialog)
 * @param message (Message for ErrorDialog)
 */
public UserException(Object errorObject, String errorMethod, String message, String title, Throwable e) {
	this(errorObject.getClass(), errorMethod, message, title, e);
}
}
