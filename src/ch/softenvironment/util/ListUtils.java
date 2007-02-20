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

import java.util.Iterator;
/**
 * Provides some often used features in using java.util.List.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.3 $ $Date: 2007-02-20 12:49:15 $
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
/**
 * Convert elements of the list to a String expression by given method,
 * where elements are separated by given separator.
 * <example>
 * list.add(myObject); // which has a method #getMyListRepresentation()
 * ListUtils.convertToString(list, "myListRepresentation", ';')
 * </example>
 * @param method non-argument method which must evaluate to String
 * @param list
 * @return
 */
public static String convertToString(java.util.List list, String method, final char separator) {
    if ((list == null) || (list.size() == 0)) {
        return "";
    }
    if (StringUtils.isNullOrEmpty(method)) {
        throw new IllegalArgumentException("method must not be null");
    }
    
    StringBuffer contents = new StringBuffer();
    Iterator it = list.iterator();
    while (it.hasNext()) {
        Object element = it.next();
/*      if (method == null) {
            contents.append(element.toString());
        } else {
*/
            BeanReflector reflector = new BeanReflector(element, method);
            try {
                contents.append(reflector.getValue());
            } catch (Throwable e) {
                Tracer.getInstance().developerWarning("method buggy: " + element + "#" + method + "=>" + e.getLocalizedMessage());
                contents.append(element.toString());
            }
//        }
        contents.append(separator);
    }
    return contents.toString();
}
    /**
     * Build intersection between given sets.
     * @param set0
     * @param set1
     * @return
     */
    public static java.util.Set createIntersection(java.util.Set set0, java.util.Set set1) {
        if ((set0 == null) || (set1 == null)) {
            throw new IllegalArgumentException("given sets must not be null");
        }
        java.util.Set intersection = new java.util.HashSet();
        Iterator it = set0.iterator();
        while (it.hasNext()) {
            Object element = it.next();
            if (set1.contains(element)) {
                intersection.add(element);
            }
        }
        return intersection;
    }
}
