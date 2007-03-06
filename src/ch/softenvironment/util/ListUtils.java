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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 * Provides some often used features in using java.util.List.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2007-03-06 13:00:27 $
 */
public abstract class ListUtils {
    /**
     * Comparator for sorting Object's by a defined property.
     */
    private static class ObjectPropertyComparator implements java.util.Comparator {
        private Evaluator evaluator = null;
        private String property = null;
        private java.text.Collator collator = null;
            
        /**
         * @param property Attribute of Objects in list to sort by.
         */
        protected ObjectPropertyComparator(Evaluator evaluator, final String property) {
            super();
        
            if (evaluator == null) {
                throw new IllegalArgumentException("evaluator must not be null");
            }
            if (property == null) {
                throw new IllegalArgumentException("property must not be null");
            }
            
            this.evaluator = evaluator;
            this.property = property;
            
            collator = java.text.Collator.getInstance(Locale.getDefault());
        }
        /**
         * @see java.util.Comparator
         * @param o1 ValueWrapper
         * @param o1 ValueWrapper
         */
        public int compare(java.lang.Object o1, java.lang.Object o2) {
            try {
                if (o1 == null) {
                    if (o2 == null) {
                        return 0;
                    } else {
                        return -1; // make sure null-element comes first
                    }
                } else if (o2 == null) {
                    return 1;
                } else {
                    Object v1 = evaluator.evaluate(o1, property);
                    Object v2 = evaluator.evaluate(o2, property);
                    if (v1 == null) {
                        if (v2 == null) {
                            return 0;
                        } else {
                            return -1; // make sure null-element comes first
                        }
                    } else if (v2 == null) {
                        return 1;
                    } else {
                        if ((v1 instanceof String) && (v2 instanceof String)) {
                            String s1 = (v1 == null ? "" : v1.toString());                    
                            String s2 = (v1 == null ? "" : v2.toString());
                            return collator.compare(s1, s2);
                        } else {
                            if ((v1 instanceof Long) && (v2 instanceof Long)) {
                                return ((Long)o1).compareTo((Long)o2);
                            } if ((v1 instanceof Integer) && (v2 instanceof Integer)) {
                                return ((Integer)o1).compareTo((Integer)o2);
                            } else if ((v1 instanceof Double) && (v2 instanceof Double)) {
                                return ((Double)v1).compareTo((Double)v2);
                            } else {
//TODO evtl. compare is not correct for any other type                            
                                return v1.toString().compareTo(v2.toString());
                            }
                        }
                    }
                }
            } catch(Throwable e) {
                ch.softenvironment.util.Tracer.getInstance().runtimeWarning("sort failed: " + e.getLocalizedMessage());
                return 0; // as is
            }
        }
    }

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
 * Sort the given items according to their objects property
 * determined by evaluator.
 * @param items list to sorted
 * @param evaluator
 * @param property
 * @return cloned and sorted list
 */
public static java.util.List sort(java.util.List items, Evaluator evaluator, final String property) {
    if ((items == null) || (items.size() == 0)) {
        return items;
    }
    if (StringUtils.isNullOrEmpty(property)) {
        throw new IllegalArgumentException("property must not be null");
    }
    
    // clone the list -> do not influence original items-list
    java.util.List list = new ArrayList(items.size());
    Iterator it = items.iterator();
    while (it.hasNext()) {
        list.add(it.next());
    }
    
    // sort list
    java.util.Collections.sort(list, new ObjectPropertyComparator(evaluator, property));
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
