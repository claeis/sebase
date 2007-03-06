package ch.softenvironment.view.swingext;

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

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import ch.softenvironment.util.BeanReflector;
import ch.softenvironment.util.DeveloperException;
import ch.softenvironment.util.Evaluator;
import ch.softenvironment.util.ListUtils;
/**
 * JComboBox utility to display sorted objects in items by its
 * property determined by evaluator.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2007-03-06 12:56:19 $
 */
public class JComboBoxUtility {
    /**
     * Default generic generic Evaluator assuming getter-method.
     * @see BeanReflector
     */
    private static class BeanEvaluator implements Evaluator {
        public Object evaluate(Object owner, final String property) {
            try {
                if (owner != null) {
                    ch.softenvironment.util.BeanReflector br = new ch.softenvironment.util.BeanReflector(owner, property);
                    return br.getValue();
                }
            } catch(Throwable e) {
                throw new DeveloperException("cannot determine value: " + e.getLocalizedMessage());
            }
            return null;
        }
    }
    /**
     * Display the value of a given public property of the represented object.
     */
    private static class ObjectComboBoxRenderer extends BasicComboBoxRenderer {
        private Evaluator evaluator = null;
        private String property = null;
        /**
         * Set the evaluator to return display value fo given property.
         * @param evaluator
         * @param property
         */
        private ObjectComboBoxRenderer(Evaluator evaluator, final String property) {
            super();
            this.evaluator = evaluator;
            this.property = property;
        }
        /**
         * Overwrites
         */
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {        
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
            if (value instanceof Icon) {
                // @see super
                setIcon((Icon)value);
            } else {
                if (value == null) {
                    setText("");
                } else {
                    Object result = evaluator.evaluate(value, property);
                    if (result == null) {
                        setText("");
                    } else {
                        setText(result.toString());
                    }
                }
            }
            return this;
        }
    }
    /**
     * Extended DefaultComboBoxModel to sort ascendingly by displayable value of
     * given by an object's property.
     */
    private static class SortedComboBoxModel extends DefaultComboBoxModel {
        /**
         * Initialize the list.
         * @param items
         * @param addNullElement add a null-element as very first element
         * @param evaluator
         * @param property
         */
        private SortedComboBoxModel(java.util.List items, Evaluator evaluator, final String property, boolean addNullElement) {
            super();        
            if (addNullElement) {
                // insert an empty object as very first element
                addElement(null);
            }
            java.util.Iterator iterator = ListUtils.sort(items, evaluator, property).iterator();
            while (iterator.hasNext()) {
                addElement(iterator.next());
            }
        }
    }
    /**
     * @see #initComboBox(JComboBox, java.util.List, String, boolean, Evaluator)
     */
    public static void initComboBox(JComboBox comboBox, java.util.List items, final String property) {
        initComboBox(comboBox, items, property, false);    
    }
    /**
     * @see #initComboBox(JComboBox, java.util.List, String, boolean, Evaluator)
     */
    public static void initComboBox(JComboBox comboBox, java.util.List items, final String property, boolean addNullElement) {
        initComboBox(comboBox, items, property, addNullElement, new BeanEvaluator());    
    }
    /**
     * Setup a JComboBox with a sorted list of items and a
     * renderer which shows value of each object#property of
     * each element in items.
     * @param comboBox
     * @param items
     * @param property contained in each object within items
     * @param addNullItem whether items list shall have a very first null-Element
     * @param evaluator uses default BeanEvaluator if null
     */
    public static void initComboBox(JComboBox comboBox, java.util.List items, final String property, boolean addNullItem, Evaluator evaluator) {
        Evaluator eval = evaluator;
        if (eval == null) {
            eval = new BeanEvaluator();
        }
        comboBox.setRenderer(new ObjectComboBoxRenderer(eval, property));
        comboBox.setModel(new SortedComboBoxModel(items, eval, property, addNullItem));
    }    
}
