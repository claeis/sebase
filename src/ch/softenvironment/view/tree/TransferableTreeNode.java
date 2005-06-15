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
import java.awt.datatransfer.*;
import javax.swing.tree.*;
/**
 * Tool for Mouse-Drag withing a JTree.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-06-15 08:39:21 $
 */
class TransferableTreeNode implements Transferable {

  public static final DataFlavor TREE_PATH_FLAVOR = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType,
							     									"Tree Path");
  private DataFlavor flavors[] = { TREE_PATH_FLAVOR };
  private TreePath path = null;

  public TransferableTreeNode(TreePath tp) {
      super();
      path = tp;
  }
  public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
    if (isDataFlavorSupported(flavor)) {
      return path;
    } else {
      throw new UnsupportedFlavorException(flavor);
    }
  }
  public synchronized DataFlavor[] getTransferDataFlavors() {
    return flavors;
  }
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    //return (flavor.getRepresentationClass() == TreePath.class);
    return true;
  }
}