package ch.softenvironment.client;

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
import ch.softenvironment.util.DeveloperException;
import ch.softenvironment.view.ListMenuChoice;

/**
 * Manage individual Rights for a certain Model-Class which might be
 * represented by a DetailView and/or SearchView.
 * The defined rights match with User-Actions defined by
 * ListMenuChoice interface.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2006-02-22 07:21:33 $
 * @see ListMenuChoice#adaptUserAction(java.util.EventObject, Object)
 */
public class UserActionRights {
    public final static int NONE = 99;      // no rights at all
    public final static int ALL = 100;      // all rights
    public final static int READONLY = 101; // changeable but not saveable
    
    private boolean newObjectAllowed = false;
    private boolean changeObjectAllowed = false;
    private boolean removeObjectsAllowed = false;
    private boolean saveObjectAllowed = false;
    
    public UserActionRights(final int rights) {
        super();
        if (rights == ALL) {
            newObjectAllowed = true;
            changeObjectAllowed = true;
            removeObjectsAllowed = true;
            saveObjectAllowed = true;
        } else if (rights == READONLY) {
            changeObjectAllowed = true;  
        } else if (rights != NONE) {
            throw new DeveloperException(this, "UserActionRights(int)", "Unknown rights");
        }
    }
    /**
     * Allow creation of new Model objects.
     * @return
     */
    public boolean isNewObjectAllowed() {
        return newObjectAllowed;
    }
    /**
     * Allow reading of existings Model objects.
     * @return
     */
    public boolean isChangeObjectAllowed() {
        return changeObjectAllowed;
    }
    /**
     * Allow removing of existing Model objects.
     * @return
     */
    public boolean isRemoveObjectsAllowed() {
        return removeObjectsAllowed;
    }
    /**
     * Allow writing of existing Model objects.
     * @return
     */
    public boolean isSaveObjectAllowed() {
        return saveObjectAllowed;
    }
}
