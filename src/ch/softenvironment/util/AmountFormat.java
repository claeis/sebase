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
 * Format a number to certain criterias.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2004-02-05 11:30:42 $
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
 * Format Amount.
 */
public String format(Double amount) {
	if (amount == null) {
		return "";
	} else {
		return format(amount.doubleValue());
	}
}
/**
 * Convert Amount into String.
 */
public static String toString(double amount) {
	AmountFormat amountFormat = new AmountFormat();
	return amountFormat.format(amount);
}
/**
 * Convert Amount into String.
 */
public static String toString(Double amount) {
	AmountFormat amountFormat = new AmountFormat();
	return amountFormat.format(amount);
}
/**
 * Return the value as Decimal-String with given range of digits after dot.
 * @deprecated
 */
public static String toString(Double value, int minDigits, int maxDigits) {
	AmountFormat decFormat = new AmountFormat();
	decFormat.setMinimumFractionDigits(minDigits);
	decFormat.setMaximumFractionDigits(maxDigits);
	return decFormat.format(value);
}
}
