package ch.softenvironment.view.table;

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

/**
 * Format a Number value as Percent-String in a JTable-Cell.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.4 $ $Date: 2006-06-29 22:30:07 $
 */
public class PercentTableCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
	java.text.NumberFormat percentFormat = null;
public PercentTableCellRenderer() {
	super();
	percentFormat = java.text.NumberFormat.getNumberInstance();
	//				java.text.NumberFormat.getPercentInstance();
	percentFormat.setMinimumFractionDigits(1);
}
/**
 * Overwrites.
 */
public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
						  boolean isSelected, boolean hasFocus, int row, int column) {
    setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
	return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
}
/**
 * Overwrites.
 */
protected void setValue(Object value) {
	try {
		setText((value == null) ? "" : percentFormat.format((Number)value));
	} catch(ClassCastException e) {
        ch.softenvironment.util.Tracer.getInstance().developerError("Number value expected for: " + value);
    }
}
}
