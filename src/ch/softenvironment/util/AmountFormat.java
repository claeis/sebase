package ch.softenvironment.util;

import java.text.NumberFormat;

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
 * @version $Revision: 1.6 $ $Date: 2005-04-25 15:32:23 $
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
/**
 * Round to 2nd decimal value.
 * @param amount
 * @return rounded amount
 */
public static double round(/*String iso4217Code,*/ double amount) {
    if (amount == 0.0) {
        return amount;
    } else {
        return ((double)(Math.round(amount * 100.0))) / 100.0;
    }
/*    try {
	    if (StringUtils.isNullOrEmpty(iso4217Code)) {
	        NumberFormat format = getAmountInstance();
	        format.setGroupingUsed(false);
	        return Double.parseDouble(format.format(amount));
	    } else {
	        NumberFormat format = DecimalFormat.getNumberInstance();
	        format.setCurrency(Currency.getInstance(iso4217Code));
	        String currency = format.format(amount);
	        return Double.parseDouble(StringUtils.replace(currency, iso4217Code, ""));
	    }
    } catch(NumberFormatException e) {
        Tracer.getInstance().developerWarning(AmountFormat.class, "round()", e.getLocalizedMessage());
        // formatting suppressed
    	return amount;
    }
*/
}
}
