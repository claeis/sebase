package ch.softenvironment.view;

/**
 * Manage a Set of GUI-Options.
 * @author: Peter Hirzel <i>soft</i>Environment
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
