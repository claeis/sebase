package ch.softenvironment.view.tree;
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
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;
import java.util.List;
import java.util.Iterator;


/**
 * Implements an adapter to
 * the metamodel as required by the NavigationView's JTree.
 *
 * @author ce
 * @author Peter Hirzel <i>soft</i>Environment 
 * @version $Revision: 1.1 $ $Date: 2005-06-15 08:39:21 $
 */
public class NavigationTreeModel implements javax.swing.tree.TreeModel, java.beans.PropertyChangeListener {
	private TreeNodeUtility utility = null;

	public static final int SORT_AS_IS = 10;
    public static final int SORT_BY_NAME = 11;
    public static final int SORT_BY_KIND_NAME = 12;
    private int order = SORT_AS_IS;

    private java.util.Vector treeModelListeners = new java.util.Vector();
    private boolean showOnlyPackages = true;

  class Compare implements java.util.Comparator {
    private int order;

	public Compare(final int order) {
	    this.order = order;
	}
	public int compare(Object o1, Object o2) {
	    if (order  == NavigationTreeModel.SORT_BY_KIND_NAME) {
	        int def = utility.compareDefinition(o1.getClass(), o2.getClass());
	        if (def != 0) {
	       		return def;
	        }
	        // ASSERT: same type
	    } else if (order == SORT_BY_NAME) {
		    String name1 = utility.getName(o1);
		    if (name1 == null) {
		        name1 = "";
		    }
		    String name2 = utility.getName(o2);
		    if (name2 == null) {
		        name2 = "";
		    }
		    return name1.compareToIgnoreCase(name2);
	    } //else if (order == NavigationTreeModel.SORT_AS_IS) {
	        return 0;
	    //}
	}
  }

    public NavigationTreeModel(boolean showOnlyPackages, TreeNodeUtility utility) {
        this.showOnlyPackages = showOnlyPackages;
        this.utility = utility;
    }
    /**
     * Adds a listener for the TreeModelEvent posted after the tree changes.
     */
    public void addTreeModelListener(TreeModelListener l) {
	    if (!treeModelListeners.contains(l)) {
	        treeModelListeners.addElement(l);
	    }
    }
    /** 
     * Invoked after a node (or a set of siblings) has changed in some way.
     */
    protected void fireTreeNodesChanged(Object node){
//ch.softenvironment.util.Tracer.getInstance().debug(this, "fireTreeNodesChanged(Element)", "node CHANGED");
        int len = treeModelListeners.size();
        TreeModelEvent e;
        if (node.equals(utility.getRoot())) {
          e = new TreeModelEvent(this, getTreePath(node));
        } else {
          e = new TreeModelEvent(this, getTreePath(utility.findParent(node)));
        }
        
        for (int i = 0; i < len; i++) {
	        if (e.getPath()[0] != null) {
            	((TreeModelListener)treeModelListeners.elementAt(i)).treeNodesChanged(e);
	        }
        }
    }
    /** 
     * Invoked after nodes have been inserted into the tree.
     */
    protected void fireTreeNodesInserted(Object e){
//ch.softenvironment.util.Tracer.getInstance().debug(this, "fireTreeNodesInserted(Element)", "node INSERTED");
    }
    /**
     * Invoked after nodes have been removed from the tree.
     */
    protected void fireTreeNodesRemoved(Object e){
//ch.softenvironment.util.Tracer.getInstance().debug(this, "fireTreeNodesRemoved(Element)", "node REMOVED");
    }
    /**
     * Invoked after the tree has drastically changed structure from a given node down.
     */
    protected void fireTreeStructureChanged(Object node) {
//ch.softenvironment.util.Tracer.getInstance().debug(this, "fireTreeStructureChanged(Element)", "node STRUCTURE CHANGED");
        int len = treeModelListeners.size();
        TreeModelEvent e = new TreeModelEvent(this, getTreePath(node));
        for (int i = 0; i < len; i++) {
            ((TreeModelListener)treeModelListeners.elementAt(i)).
                    treeStructureChanged(e);
        }
    }
/**
 * Returns the child of parent at index index in the parent's child array.
 */
public Object getChild(Object parent, int index) {
    List children = getChildren(parent);
    return children.get(index);
}
/**
 * Returns the number of children of parent.
 */
public int getChildCount(Object parent) {
    List children = getChildren(parent);
    return children.size();
}
/**
 * Return all visible children of parent.
 */
private List getChildren(Object parent) {
    List ret = new java.util.ArrayList();

    // add ownedElements
    Iterator iterator = utility.iteratorChildren(parent);
    while (iterator.hasNext()) {
        Object next = iterator.next();
        if (showOnlyPackages) {
            if (!utility.isLeaf(next)) {
                ret.add(next);
            }
        } else {
            ret.add(next);
        }
    }
    // sort according to user settings
	java.util.Collections.sort(ret, new Compare(getOrder()));

    return ret;
}
/**
 * Returns the index of child in parent.
 */
public int getIndexOfChild(Object parent, Object child) {
    List children = getChildren(parent);
    return children.indexOf(child);
}
  public int getOrder(){
    return order;
  }
/**
 * @see javax.swing.tree.TreeModel
 */
public Object getRoot() {
	return utility.getRoot();
}
/**
 * @see javax.swing.tree.TreeModel
 */
public TreePath getTreePath(Object node) {
    java.util.List path = new java.util.ArrayList();
    path.add(node);
    Object current = node;
    Object next = null;
    while ((next = utility.findParent(current)) != null) {
        // insert parent in front of child
        path.add(0, next);
        current = next;
    }
    return new TreePath(path.toArray());
}
/**
 * @see javax.swing.tree.TreeModel
 */
public boolean isLeaf(Object node) {
    return utility.isLeaf(node);
}
/**
 * This method gets called when a bound property is changed.
 * @param evt A PropertyChangeEvent object describing the event source 
 *   	and the property that has changed.
 */
public void propertyChange(java.beans.PropertyChangeEvent event) {
	if (utility.isNodeStructureChanged(event)) {
		fireTreeStructureChanged(event.getSource());
    } else if (utility.isNodeChanged(event)) {
	   	fireTreeNodesChanged(event.getSource());
//TODO width of text not correctly updated in Tree
    }
}
  public void refresh(){
    fireTreeStructureChanged(utility.getRoot());
  }
    /**
     * Removes a listener previously added with addTreeModelListener().
     */
    public void removeTreeModelListener(TreeModelListener l) {
	    if (treeModelListeners.contains(l)) {
	        treeModelListeners.removeElement(l);
	    }
    }
/** 
 * Doesn't fire a refresh event; should be done by caller. That way, the caller
 * may keep nodes expanded.
 */
public void setOrder(final int order) {
	this.order = order;
}
/**
 * This method is called as a result of the user editing a value in
 * the tree.
 *
 * Triggered if JTree's attribute invokesStopCellEditing is set to true only.
 * @see #propertyChange(..)
 */
public void valueForPathChanged(TreePath path, Object newValue) {
    Object node = path.getLastPathComponent();
    utility.setName(node, (String)newValue);
}
public TreeNodeUtility getUtility() {
    return utility;
}
}
