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
import java.awt.dnd.*;

import javax.swing.tree.*;

/**
 * Tool for Mouse-Drag withing a JTree.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-06-15 08:39:21 $
 */
class TreeDragSource implements DragSourceListener, DragGestureListener {
    private DragSource source = null;
    private DragGestureRecognizer recognizer = null;
//    private TransferableTreeNode transferable = null;
//    private Object /*DefaultMutableTreeNode*/ oldNode = null;
    private AutoScrollingTree sourceTree = null;

    public TreeDragSource(AutoScrollingTree tree, final int actions) {
        super();
        sourceTree = tree;
      	source = new DragSource();
      	recognizer = source.createDefaultDragGestureRecognizer(sourceTree, actions, this);
    }
    /*
     * Drag Gesture Handler
     */
    public void dragGestureRecognized(DragGestureEvent dge) {
      TreePath path = sourceTree.getSelectionPath();
      if ((path == null) || (path.getPathCount() <= 1)) {
        // We can't move the root node or an empty selection
        return;
      }
      Object oldNode = /*(DefaultMutableTreeNode)*/path.getLastPathComponent();
      TransferableTreeNode transferable = new TransferableTreeNode(path);
	  // If you support dropping the node anywhere, you should probably
      // start with a valid move cursor:
      source.startDrag(dge, DragSource.DefaultMoveDrop /*DragSource.DefaultMoveDrop*/, transferable, this);
    }

    // Drag Event Handlers
    public void dragEnter(DragSourceDragEvent dsde) {}
    public void dragExit(DragSourceEvent dse) {}
    public void dragOver(DragSourceDragEvent dsde) {}
    public void dropActionChanged(DragSourceDragEvent dsde) {}
    /**
     * @see TreeDropTarget#drop()
     */
    public void dragDropEnd(DragSourceDropEvent dsde) {
      // to support move or copy, we have to check which occurred:
//    if (dsde.getDropSuccess() && (dsde.getDropAction() == DnDConstants.ACTION_MOVE)) {
//        ((DefaultTreeModel)sourceTree.getModel()).removeNodeFromParent(oldNode);
//      }
    }
}