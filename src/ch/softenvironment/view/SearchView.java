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
 * Method-Set for a SearchView
 * @see ApplicationFrame (as a Parent-Class)
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2004-02-05 11:33:00 $
 */
public interface SearchView {
/**
 * Assign the selected Objects in a SearchTable.
 * @see DbTableModel
 * @exception Throwable Handled by this GUI-Method.
 */
public void assignObjects();
/**
 * Create a new Object and open it in a DetailView.
 * @exception Throwable Handled by this GUI-Method.
 */
public void newObject();
/**
 * Open the selected Objects.
 * @see DbTableModel
 * @exception Throwable Handled by this GUI-Method.
 */
public void openObjects();
/**
 * Remove the selected Object's (for e.g. DbSessionBeans) from a SearchTable.
 * @see DbTableModel remove()
 * @exception Throwable Handled by this GUI-Method.
 */
void removeObjects();
/**
 * A SearchView usually offers a set of Query-Fields to
 * make the searching of objects more accurate and performant.
 * Therefore a reset of all SearchArguments may be initialized here.
 */
void resetSearchArguments();
/**
 * Search for Objects.
 * @see DbTableModel#setQuery(..)
 * @exception Throwable Handled by this GUI-Method.
 */
public void searchObjects();
}
