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
 * @version $Revision: 1.4 $ $Date: 2004-09-27 11:11:15 $
 */
public class AmountFormat extends java.text.DecimalFormat {
/**
 * AmountFormat constructor.
 * Set representation to: "1'234'349.30"
 */
public AmountFormat() {
	super();
	setMinimumFractionDigits(2);
	setMaximumFractionDigits(2);
	setGroupingSize(3);			// separate thousand's
	setGroupingUsed(true);
	setDecimalSeparatorAlwaysShown(true);
}
/**
 * Convert Amount into String.
 */
public static String toString(double amount) {
	return toString(new Double(amount));
}
/**
 * Convert Amount into String.
 */
public static String toString(long amount) {
	return toString(new Long(amount));
}
/**
 * Convert given amount into formatted String.
 */
public static String toString(Number amount) {
	if (amount == null) {
		return "";
	} else {
		AmountFormat amountFormat = new AmountFormat();
		return amountFormat.format(amount);
	}
}
}
