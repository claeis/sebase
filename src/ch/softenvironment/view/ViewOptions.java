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
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2004-02-05 11:33:00 $
 * @see DetailView(..)
 * @see SearchView(..)
 */
public class ViewOptions {
	private java.util.Map options = new java.util.HashMap();
/**
 * ViewOptions constructor comment.
 */
public ViewOptions() {
	super();
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
