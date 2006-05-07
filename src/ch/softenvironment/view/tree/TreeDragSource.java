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
import java.awt.Point;
import java.awt.dnd.*;

import javax.swing.JTree;
import javax.swing.tree.*;

import ch.softenvironment.util.Tracer;

/**
 * Tool for Mouse-Drag withing a JTree.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2006-05-07 14:02:37 $
 */
class TreeDragSource implements DragSourceListener, DragGestureListener {
    private DragSource source = null;
//    private DragGestureRecognizer recognizer = null;
    private AutoScrollingTree sourceTree = null;
    private TransferableTreeNode transferable = null;

    public TreeDragSource(AutoScrollingTree tree, final int actions) {
        super();
        sourceTree = tree;
      	source = new DragSource();
        /*DragGestureRecognizer recognizer =*/ source.createDefaultDragGestureRecognizer(sourceTree, actions, this);
    }
    /*
     * Only MOVE action supported.
     * Drag Gesture Handler.
     * @see TreeDropTarget#drop()
     */
    public void dragGestureRecognized(DragGestureEvent dge) {
      TreePath path = sourceTree.getSelectionPath();
      if ((path == null) || (path.getPathCount() <= 1)) {
        // do NOT move the ROOT node or an empty selection
        return;
      }
//    Object /*DefaultMutableTreeNode*/ selectedNode = /*(DefaultMutableTreeNode)*/path.getLastPathComponent();
      transferable = new TransferableTreeNode(path);
	  // if you support dropping the node anywhere, you should probably start with a valid move cursor
      source.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
    }

    // Drag Event Handlers
    public void dragEnter(DragSourceDragEvent dsde) {
//Tracer.getInstance().debug("DragSource#Enter");
    }
    public void dragExit(DragSourceEvent dse) { }
    public void dragOver(DragSourceDragEvent dsde) {
//Tracer.getInstance().debug("DragSource#Over");
//TODO     setDragDropCursor(dsde); //=> buggy
    }
    public void dropActionChanged(DragSourceDragEvent dsde) { }
    /**
     * @see TreeDropTarget#drop()
     */
    public void dragDropEnd(DragSourceDropEvent dsde) {
//      if (dsde.getDropSuccess()) { "OK" }
		transferable = null;
    }
    /**
     * Verify whether current dropZone is ok for current transferable.
     * @param dtde
     * @return
     */
    private boolean isDropAcceptable(DragSourceDragEvent dsde) {
        try {
	        Point p = dsde.getLocation();
	        DragSourceContext dtc = dsde.getDragSourceContext();
	        JTree tree = (JTree)dtc.getComponent(); // by default => targetTree
	        TreePath path = tree.getClosestPathForLocation(p.x, p.y);
	        Object /*TreeNode*/ dropTargetNode = path.getLastPathComponent();
	        Object source = ((TreePath)transferable.getTransferData(TransferableTreeNode.TREE_PATH_FLAVOR)).getLastPathComponent();

	       	String msg = sourceTree.getUtility().isAddable(source, dropTargetNode);
	       	if (msg == null) {
	       	    return true;
	       	} else {
Tracer.getInstance().debug(this, "isDropAcceptable()->false", msg);
	       	    return false;
	       	}
        } catch(Throwable e) {
            Tracer.getInstance().developerWarning(this, "isDropAcceptable()", e.getLocalizedMessage());
            return false;
        }
    }
    /**
     * Show appropriate cursor depending on drop-zone.
     * @param dsde
     */
    private void setDragDropCursor(DragSourceDragEvent dsde) {
        DragSourceContext dsc = dsde.getDragSourceContext();
	    if (isDropAcceptable(dsde)) {
	        dsc.setCursor(DragSource.DefaultMoveDrop);
	    } else {
	        dsc.setCursor(DragSource.DefaultMoveNoDrop);
	    }
    }
}