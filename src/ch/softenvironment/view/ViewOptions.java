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
 * Manage a Set of GUI-Options.
 * An instance of the ViewOptions should be initalized as Singleton
 * by an Appplication-Launcher and passed to any View opened from there.
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2004-09-14 17:01:47 $
 * @see DetailView(..)
 * @see SearchView(..)
 */
public class ViewOptions {
	private boolean closeOnSave = true;
	private java.util.Map options = new java.util.HashMap();
	private ch.softenvironment.client.ViewManager viewManager = new ch.softenvironment.client.ViewManager();
/**
 * ViewOptions constructor comment.
 */
public ViewOptions() {
	super();
}
/**
 * @see #setCloseOnSave()
 */
public boolean getCloseOnSave() {
	return closeOnSave;
}
/**
 * Design Pattern: Singleton.
 * @return ViewManager
 */
public ch.softenvironment.client.ViewManager getViewManager() {
	return viewManager;
}
/**
 * Return whether Option with given Name is Configured YES or NO.
 * @return boolean Default: false
 */
public boolean isSet(String name) {
	if (options.containsKey(name)) {
		return ((Boolean)options.get(name)).booleanValue();
	}

	return false;
}
/**
 * Set whether saveObject() in DetailView's shall close GUI if
 * only one object is represented.
 */
public void setCloseOnSave(boolean closeOnSave) {
	this.closeOnSave = closeOnSave;
}
/**
 * Set a certain Option.
 */
public void setOption(String name) {
	setOption(name, true);
}
/**
 * Set a certain Option.
 */
public void setOption(String name, boolean allow) {
	options.put(name, new Boolean(allow));
}
}
