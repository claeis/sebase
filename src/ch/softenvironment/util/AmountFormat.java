package ch.softenvironment.util;

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
 * Format a number to look like a financial value.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2005-01-06 09:45:18 $
 */
public class AmountFormat /*extends java.text.NumberFormat*/ {

/**
 * 
 */
public static java.text.NumberFormat getAmountInstance() {
	java.text.NumberFormat formatter = java.text.NumberFormat.getNumberInstance();
	//		                           java.text.NumberFormat.getCurrencyInstance();
	formatter.setMinimumFractionDigits(2);
	formatter.setMaximumFractionDigits(2);
//	formatter.setGroupingSize(3);			// separate thousand's
	formatter.setGroupingUsed(true);
//	formatter.setDecimalSeparatorAlwaysShown(true);

	return formatter;
}
/**
 * @deprecated
 */
public static String toString(double amount) {
	return toString(new Double(amount));
}
/**
 * @deprecated
 */
public static String toString(long amount) {
	return toString(new Long(amount));
}
/**
 * Convert given amount into formatted String.
 * This is a convenience Method.
 */
public static String toString(Number amount) {
	if (amount == null) {
		return "";
	} else {
		return getAmountInstance().format(amount);
	}
}
}
