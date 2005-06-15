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
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.tree.*;

import ch.softenvironment.view.BaseFrame;

/**
 * Tool for Mouse-Drop within a JTree.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2005-06-15 11:33:35 $
 */
class TreeDropTarget implements DropTargetListener {
//	private DropTarget target = null;
	private AutoScrollingTree targetTree = null;

	public TreeDropTarget(AutoScrollingTree tree) {
		targetTree = tree;
		
		// register tree as DropTarget @see #drop()
		/*target =*/ new DropTarget(targetTree, this);
	}

  // Drop Event Handlers
  private Object getNodeForEvent(DropTargetDragEvent dtde) {
    Point p = dtde.getLocation();
    DropTargetContext dtc = dtde.getDropTargetContext();
    JTree tree = (JTree)dtc.getComponent();
    TreePath path = tree.getClosestPathForLocation(p.x, p.y);
    return /*(TreeNode)*/path.getLastPathComponent();
  }
  public void dragEnter(DropTargetDragEvent dtde) {
    Object /*TreeNode*/ node = getNodeForEvent(dtde);
    if (targetTree.getUtility().isLeaf(node)) {
      dtde.rejectDrag();
    } else {
      // start by supporting move operations
      dtde.acceptDrag(dtde.getDropAction() /*DnDConstants.ACTION_MOVE*/);
    }
  }
  public void dragOver(DropTargetDragEvent dtde) {
    Object /*TreeNode*/ node = getNodeForEvent(dtde);
    if (targetTree.getUtility().isLeaf(node)) {
      dtde.rejectDrag();
    } else {
      // start by supporting move operations
      dtde.acceptDrag(dtde.getDropAction() /*DnDConstants.ACTION_MOVE*/);
    }
  }
  public void dragExit(DropTargetEvent dte) {}
  public void dropActionChanged(DropTargetDragEvent dtde) {}
  /**
   * @see TreeDragSource#dragGestureRecognized()
   */
  public void drop(DropTargetDropEvent dtde) {
    Point pt = dtde.getLocation();
//  DropTargetContext dtc = dtde.getDropTargetContext();
//  JTree tree = (JTree)dtc.getComponent();
    TreePath parentpath = targetTree.getClosestPathForLocation(pt.x, pt.y);
    Object /*DefaultMutableTreeNode*/ parent = /*(DefaultMutableTreeNode)*/ parentpath.getLastPathComponent();
    if (targetTree.getUtility().isLeaf(parent)) {
      dtde.rejectDrop();
      return;
    }

    try {
      Transferable tr = dtde.getTransferable();
      DataFlavor[] flavors = tr.getTransferDataFlavors();
      for (int i = 0; i < flavors.length; i++) {
		if (tr.isDataFlavorSupported(flavors[i])) {
		  dtde.acceptDrop(dtde.getDropAction());
		  TreePath p = (TreePath)tr.getTransferData(flavors[i]);
		  Object /*DefaultMutableTreeNode*/ node = /*(DefaultMutableTreeNode)*/ p.getLastPathComponent();
//		  NavigationTreeModel /*DefaultTreeModel*/ model = (NavigationTreeModel)targetTree.getModel();
		  targetTree.getUtility().relocateElement(node, parent); //model.insertNodeInto(node, parent, 0);
		  dtde.dropComplete(true);
		  return;
		}
      }
      dtde.rejectDrop();
    } catch(Throwable e) {
//      dtde.rejectDrop();
        BaseFrame.showException(targetTree, e); // part of view mechanism => Dialog is ok here
    }
  }
}
