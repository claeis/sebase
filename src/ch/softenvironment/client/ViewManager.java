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
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2004-09-14 16:50:21 $
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
	if (objects.size() > 1) {
ch.softenvironment.util.Tracer.getInstance().nyi(this, "activateView(List)", "Multi-Objects representables are not focused yet!)");

		// difficulty: View may represent one or several of given objects
		//				but may also contain some other objects
		// 				Otherwise what if objects are shown by different View's
		//	=> so either extend different objects in view's object-List 
		//		and point to first of these objects in Toolbar at focusing

		//  => or always force reopen new view (may contain same object's a second time!)
		return false;
	} else {
		return activateView((java.awt.Window)detailViews.get(objects.get(0)));
	}
}
/**
 * Register objects to be represented by the given View.
 * @param objects model-instances to be represented
 * @param view "Decorator" of object
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
	} else if (view instanceof ch.softenvironment.view.SearchView) {
		if (!detailViews.containsKey(view.getClass())) {
			searchViews.put(view.getClass(), view);
		}
	} else {
		// dialogs usually are modal anyway
		ch.softenvironment.util.Tracer.getInstance().developerWarning(this, "checkIn()", "View-type not supported: " + view.getClass().getName());
	}
}
/**
 * Unregister the closing View, to be no more representable
 * for registered objects any more.
 * @param view DetailView representing model-instances
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
	} else if (view instanceof ch.softenvironment.view.SearchView) {
		if (searchViews.containsKey(view.getClass())) {
			searchViews.remove(view.getClass());
		}
	} else {
		// dialogs usually are modal anyway
		ch.softenvironment.util.Tracer.getInstance().developerWarning(this, "checkOut()", "View-type not supported: " + view.getClass().getName());
	}
}
/**
 * Close all View's managed by this GUI-Manager.
 */
public void closeAll() {
	// be awara of ConcurrentModificationException
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
}
