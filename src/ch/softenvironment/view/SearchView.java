package ch.softenvironment.view;

/**
 * Method-Set for a SearchView
 * @see ApplicationFrame (as a Parent-Class)
 * @author: Peter Hirzel <i>soft</i>Environment
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
