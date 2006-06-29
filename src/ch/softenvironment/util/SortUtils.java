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
 * Some useful sort-Tools.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.6 $ $Date: 2006-06-29 22:26:47 $
 */
public class SortUtils {
	private static class GenericComparator implements java.util.Comparator {
    	private String propertyToSort = null;
    	
    	GenericComparator(String propertyToSort) {
    		super();
    		this.propertyToSort = propertyToSort;
    	}
    	/**
    	 * Compare by propertyToSort.
         * If comparation fails, then no sorting is done at all.
    	 */
    	public int compare(Object o1, Object o2) {
    		try {
    			Class types[] = {};
    			Object params[] = {};
    			java.lang.reflect.Method method = o1.getClass().getMethod("get" + propertyToSort, types);
    			Class returnType = method.getReturnType();
    			
    			if (returnType.equals(java.lang.String.class)) {
    				return ((String)(method).invoke(o1, params)).compareTo((String)(method).invoke(o2, params));
    			} else if (returnType.equals(java.lang.Long.class)) {
    				return ((Long)(method).invoke(o1, params)).compareTo((Long)(method).invoke(o2, params));
    			} if (returnType.equals(java.lang.Integer.class)) {
    				return ((Integer)(method).invoke(o1, params)).compareTo((Integer)(method).invoke(o2, params));
    			} else if (returnType.equals(java.lang.Double.class)) {
    				return ((Double)(method).invoke(o1, params)).compareTo((Double)(method).invoke(o2, params));
    			} else {
//TODO NYI Returntype generic sort
                    throw new DeveloperException("SortUtils.GenericComparator#compare(..)=>Cannot sort generically: PropertyToSort <" + propertyToSort + "> with Returntype <" + returnType + ">");
    			}
    		} catch(Throwable e) {
    			ch.softenvironment.util.Tracer.getInstance().developerError("SortUtils.GenericComparator#compare(..)=>PropertToSort <" + propertyToSort + "> missing or failed: " + e.getLocalizedMessage());
    		}
    		// do not sort
    		return 0;
    	};
    }
    /**
     * Sort given list generically by list-elements#get<propertyToSort>() return values.
     * @param list to be sorted
     * @param propertyToSort Property-Name to be sorted by (for e.g. "Value" for #getValue())
     * @see java.util.Collections#sort()
     */
    public static void sortByProperty(java.util.List list, String propertyToSort) {
    	java.util.Collections.sort(list, new GenericComparator(propertyToSort));
    }
}
