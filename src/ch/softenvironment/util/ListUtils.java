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
 * Provides some often used features in using java.util.List.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-03-12 17:45:50 $
 */
public abstract class ListUtils {
/**
 * Return an ArrayList containing the given item.
 * @return java.util.ArrayList
 */
public static java.util.List createList(Object item) {
	java.util.List list = new java.util.ArrayList(1);
	list.add(item);
	return list;
}
}
