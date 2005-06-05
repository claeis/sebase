package ch.softenvironment.client;

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
 * Utility to manage opened GUI's for associated Object's.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2005-06-05 09:03:20 $
 */
public class ViewManager {
	java.util.Map searchViews = new java.util.HashMap();
	java.util.Map detailViews = new java.util.HashMap();
/**
 * ViewManager constructor comment.
 */
public ViewManager() {
	super();
}
/**
 * Focus on view if existing.
 */
private boolean activateView(java.awt.Window view) {
	if (view == null) {
		return false;
	} else {
		view.setVisible(true);
		if (view instanceof java.awt.Frame) {
			// deiconize eventually
			((java.awt.Frame)view).setState(java.awt.Frame.NORMAL);
		}
		view.toFront();
		return true;
	}
}
/**
 * If given SearchView type already is represented by a view
 * then focus on it.
 */
public boolean activateView(java.lang.Class searchView) {
	return activateView((java.awt.Window)searchViews.get(searchView));
}
/**
 * If given model-instances already are represented by a view
 * then focus on it.
 */
public boolean activateView(java.util.List objects) {
	if (objects.size() == 1) {
		// return activateView((java.awt.Window)detailViews.get(objects.get(0))); => does NOT compare to overwritten #equals()
		java.awt.Window view = getView(objects.get(0));
		if (view != null) {
			return activateView(view);
		}
		return false;
	} else {
ch.softenvironment.util.Tracer.getInstance().nyi(this, "activateView(List)", "Multi-Objects representables are not focused yet!)");

		// difficulty: View may represent one or several of given objects
		//				but may also contain some other objects
		// 				Otherwise what if objects are shown by different View's
		//	=> so either extend different objects in view's object-List 
		//		and point to first of these objects in Toolbar at focusing

		//  => or always force reopen new view (may contain same object's a second time!)
		return false;
	}
}
/**
 * Register objects to be represented by the given View.
 * @param objects model-instances to be represented (null for SearchView's)
 * @param view DetailView AND/OR SearchView
 */
public void checkIn(java.util.List objects, java.awt.Window view) {
	// some GUI's might be DetailView and SearchView in ONE
	if ((objects != null) && (view instanceof ch.softenvironment.view.DetailView)) {
		java.util.Iterator iterator = objects.iterator();
		while(iterator.hasNext()) {
			Object object = iterator.next();
			if (!detailViews.containsKey(object)) {
				detailViews.put(object, view);
			}
		}
	}
	
	if ((objects == null) && (view instanceof ch.softenvironment.view.SearchView)) {
//		if (!detailViews.containsKey(view.getClass())) {
			searchViews.put(view.getClass(), view);
//		}
	}
}
/**
 * Unregister the closing View, to be no more representable
 * for registered objects any more.
 * @param view DetailView AND/OR SearchView
 */
public void checkOut(java.awt.Window view) {
	if (view instanceof ch.softenvironment.view.DetailView) {
		java.util.List keys = new java.util.ArrayList();
		java.util.Iterator iterator = detailViews.keySet().iterator();
		while(iterator.hasNext()) {
			Object key = iterator.next();
			// remind all model-objects represented by the same view
			if (detailViews.get(key).equals(view)) {
				keys.add(key);
			}
		}
		// remove the model-objects from representables
		iterator = keys.iterator();
		while (iterator.hasNext()) {
			detailViews.remove(iterator.next());
		}
	}
	
	if (view instanceof ch.softenvironment.view.SearchView) {
		if (searchViews.containsKey(view.getClass())) {
			searchViews.remove(view.getClass());
		}
	}
}
/**
 * Close all View's managed by this GUI-Manager.
 */
public void closeAll() {
	// be aware of ConcurrentModificationException
	java.util.List views = new java.util.ArrayList();
	views.addAll(searchViews.values());
	views.addAll(detailViews.values());

	searchViews = new java.util.HashMap();
	detailViews = new java.util.HashMap();
	
	java.util.Iterator iterator = views.iterator();
	while (iterator.hasNext()) {
		((java.awt.Window)iterator.next()).dispose();
	}
}

/**
 * Close all View's representing an Object in given List.
 */
public void closeAll(java.util.List objects) {
	java.util.Iterator iterator = objects.iterator();
	while (iterator.hasNext()) {
		java.awt.Window view = getView(iterator.next());
		if (view != null) {
			java.awt.Window windows[] = view.getOwnedWindows();
			for (int i=0; i<windows.length; i++) {
				windows[i].dispose();
			}
			view.dispose();
		}
	}
}

/**
 * Return view showing given object, null for none.
 */
private java.awt.Window getView(Object object) {
	java.util.Iterator iterator = detailViews.keySet().iterator();
	while (iterator.hasNext()) {
		Object key = iterator.next();
		if (key.equals(object)) {
			return (java.awt.Window)detailViews.get(key);
		}
	}
	return null;
}
}
