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
 
/**
 * Method-Set for List-Actions for e.g. in a JTable's PopupMenu.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2004-05-09 17:25:55 $
 */
public interface ListMenuChoice {
/**
 * Do anything at DoubleClick-Event for e.g. open selected
 * Object(s) in a JTable.
 * @see BaseFrame#genericPopupDisplay(..)
 */
//void defaultDoubleClickAction(java.awt.event.MouseEvent event);
/**
 * Change the selected Objects (for e.g. in a DetailView).
 * @param source (for e.g. a Popup-MenuItem)
 */
public void changeObjects(Object source);
/**
 * Create a new Object as a "copy" of a selected one (and open 
 * it for e.g. in a DetailView).
 * @param source (for e.g. a Popup-MenuItem)
 */
public void copyObject(Object source);
/**
 * Create a new Object (and open it for e.g. in a DetailView).
 * @param source (for e.g. a Popup-MenuItem)
 */
public void newObject(Object source);
/**
 * Remove the selected Object's (for e.g from a JTable).
 * @param source (for e.g. a Popup-MenuItem)
 */
void removeObjects(Object source);
}
