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
 * Method-Set for a DetailView
 * @see BaseFrame (as a Parent-Class)
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2004-02-05 11:32:59 $
 */
public interface DetailView {
/**
 * Assign a set of aggregates.
 */
void assignObjects(java.util.List objects);
/**
 * Do anything at DoubleClick-Event.
 * @return boolean true if event was consumed
 * @see BaseFrame#generatePopupDisplay(..)
 */
boolean defaultDoubleClickAction(java.awt.event.MouseEvent event);
/**
 * Save an Object represented by DetailView.
 * @see for e.g. DbEntityBean#save()
 */
void saveObject();
/**
 * Make the View represent the given Object.
 * @see Toolbar#getNext()
 */
void setCurrentObject(Object currentObject);
/**
 * Undo the changes of an Object represented by this GUI.
 * @see for e.g. DbEntityBean#refresh()
 */
void undoObject();
}
