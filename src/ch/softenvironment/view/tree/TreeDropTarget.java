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
import ch.softenvironment.view.BaseDialog;
import ch.softenvironment.view.BaseFrame;

/**
 * Listener for Mouse-Drop within a JTree.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2005-12-13 17:25:56 $
 */
class TreeDropTarget implements DropTargetListener {
//	private DropTarget target = null;
	private AutoScrollingTree targetTree = null;

	public TreeDropTarget(AutoScrollingTree tree) {
		targetTree = tree;
		
		// register tree as DropTarget @see #drop()
		/*target =*/ new DropTarget(targetTree, this);
	}

  public void dragEnter(DropTargetDragEvent dtde) {
//Tracer.getInstance().debug("dragEnter");
//    Object /*TreeNode*/ dropTargetNode = getNodeForEvent(dtde);
/*    if (targetTree.getUtility().isAddable(dropTargetNode)) {
        dtde.rejectDrag();
      } else {
        // start by supporting move operations
        dtde.acceptDrag(dtde.getDropAction()); //=DnDConstants.ACTION_MOVE
      }
*/
  }
  /**
   * @see #dragEnter()
   */
  public void dragOver(DropTargetDragEvent dtde) { }
  public void dragExit(DropTargetEvent dte) {}
  public void dropActionChanged(DropTargetDragEvent dtde) {
/*    if (dsde.getDropAction() != DnDConstants.ACTION_MOVE) {
        
      }
*/
  }
  /**
   * Only MOVE supported.
   * @see TreeDragSource#dragGestureRecognized()
   */
  public void drop(DropTargetDropEvent dtde) {
    // find target node to drop source to
    Point pt = dtde.getLocation();
    DropTargetContext dtc = dtde.getDropTargetContext();
    JTree tree = (JTree)dtc.getComponent(); // by default => targetTree
    TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
    Object /*DefaultMutableTreeNode*/ target = /*(DefaultMutableTreeNode)*/ parentpath.getLastPathComponent();

    try {
      Transferable tr = dtde.getTransferable();
      DataFlavor[] flavors = tr.getTransferDataFlavors();
      if ((flavors.length == 1) && tr.isDataFlavorSupported(flavors[0]) && (dtde.getDropAction() == DnDConstants.ACTION_MOVE)) {
		  TreePath p = (TreePath)tr.getTransferData(flavors[0]);
		  Object /*DefaultMutableTreeNode*/ sourceNode = /*(DefaultMutableTreeNode)*/ p.getLastPathComponent();
		  String msg = targetTree.getUtility().isAddable(sourceNode, target);
		  if (msg == null) {
		    dtde.acceptDrop(dtde.getDropAction());
		  	dtde.dropComplete(true);
            targetTree.getUtility().relocateElement(sourceNode, target); //model.insertNodeInto(node, parent, 0);
		  } else {
		  	dtde.rejectDrop();
		  	BaseDialog.showWarning(targetTree, null, msg); // part of view mechanism => Dialog is ok here
		  }
	  } else {
      	dtde.rejectDrop();
	  }
    } catch(Throwable e) {
        dtde.rejectDrop();
        BaseFrame.showException(targetTree, e); // part of view mechanism => Dialog is ok here
    }
  }
}