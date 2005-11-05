package ch.softenvironment.view;
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
import java.util.EventObject; 
/**
 * Method-Set for typical List-Actions for e.g. in a JTable's PopupMenu.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2005-11-05 20:00:24 $
 */
public interface ListMenuChoice {
    /**
     * Enable/disable any user relevant Controls (for e.g. MenuItems in a
     * PopupMenu) depending on triggering event.
     * @param event
     * @param control
     */
    public void adaptUserAction(EventObject event, /*java.awt.Component*/Object control);
    /**
     * Change the selected Objects (for e.g. in a DetailView).
     * This might also be triggered as double-click action by
     * BaseFrame.
     * @param source (triggering control for e.g. a Popup-MenuItem)
     * @see BaseFrame#genericPopupDisplay(java.awt.event.MouseEvent, javax.swing.JPopupMenu) 
     * @see BaseDialog#genericPopupDisplay(java.awt.event.MouseEvent, javax.swing.JPopupMenu) 
     */
    public void changeObjects(Object source);
    /**
     * Create a new Object as a "copy" of a selected one (and open 
     * it for e.g. in a DetailView).
     * @param source (triggering control for e.g. a Popup-MenuItem)
     */
    public void copyObject(Object source);
    /**
     * Create a new Object (and open it for e.g. in a DetailView).
     * @param source (triggering control for e.g. a Popup-MenuItem)
     */
    public void newObject(Object source);
    /**
     * Remove the selected Object's (for e.g from a JTable).
     * @param source (triggering control for e.g. a Popup-MenuItem)
     */
    void removeObjects(Object source);
}