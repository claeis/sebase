package ch.softenvironment.util;

/**
 * Some useful sort-Tools.
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public class SortUtils {
	class GenericComparator implements java.util.Comparator {
	private String propertyToSort = null;
	
	GenericComparator(String propertyToSort) {
		super();
		this.propertyToSort = propertyToSort;
	}
	/**
	 * Compare by propertyToSort.
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
				ch.softenvironment.util.Tracer.getInstance().nyi(this, "compare(..)", "PropertToSort <" + propertyToSort + "> with Returntype <" + returnType + "> not implemented yet");
			}
		} catch(Throwable e) {
			ch.softenvironment.util.Tracer.getInstance().developerError(this, "compare(..)", "PropertToSort <" + propertyToSort + "> missing or failed");
		}
		// do not sort
		return 0;
	};
}
/**
 * SortUtils constructor comment.
 * @param list to be sorted
 * @param propertyToSort Property-Name to be sorted by (for e.g. "Value" for #getValue())
 */
public SortUtils(java.util.List list, String propertyToSort) {
	super();
	java.util.Collections.sort(list, new GenericComparator(propertyToSort));
}
}
