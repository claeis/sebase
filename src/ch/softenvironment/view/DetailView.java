package ch.softenvironment.view;

/**
 * Method-Set for a DetailView
 * @see BaseFrame (as a Parent-Class)
 * @author: Peter Hirzel <i>soft</i>Environment
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
