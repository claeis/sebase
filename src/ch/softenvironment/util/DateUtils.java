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
 * Utilities for Date calculations.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2004-02-05 11:30:43 $
 */
public class DateUtils {
/**
 * Return the difference between end- and start-Time.
 * @return String ("[3h]15min.27s")
 */
public static String calcTimeDifference(java.util.Date start, java.util.Date end) {
	if ((start == null) || (end == null)) {
		throw new DeveloperException(DateUtils.class, "calcTimeDifference(..)", "start nor end must be null!");
	}
	if (end.getTime() < start.getTime()) {
		throw new DeveloperException(DateUtils.class, "calcTimeDifference(..)", "start must be earlier end!");
	}
	
	long seconds = (end.getTime() - start.getTime()) / 1000;
	String value = "";

	long hours = seconds / 3600;
	if (hours > 0) {
		// hours are optional
		value = value + hours + "h";
		seconds = seconds % 3600;
	}
	
	long minutes = seconds / 60;
	value = value + minutes + "min. ";
	seconds = seconds % 60;
	
	return value + seconds + "s";
}
/**
 * Return the last Monday.
 * @return java.util.Date
 */
public static java.util.Date getBeginingOfWeek() {
	java.util.Date now = new java.util.Date();
	int day = now.getDay(); // 1 for Monday
	int lastMondayDiff = day + 1 - java.util.Calendar.MONDAY;
	return new java.util.Date(now.getTime() - lastMondayDiff * 24 * 60 * 60 * 1000);
}
/**
 * Return the first day of current month.
 * @return java.util.Date
 */
public static java.util.Date getEndOfMonth() {
	java.util.Date now = new java.util.Date();
	java.util.Date firstNextMonth = new java.util.Date(now.getYear(), now.getMonth() + 1, 1);
	return new java.util.Date(firstNextMonth.getTime() - 24 * 60 * 60 * 1000);
}
/**
 * Return the last Monday.
 * @return java.util.Date
 */
public static java.util.Date getEndOfWeek() {
	java.util.Date monday = getBeginingOfWeek();
	return new java.util.Date(monday.getTime() + (java.util.Calendar.DAY_OF_WEEK - 1) * 24 * 60 * 60 * 1000);
}
/**
 * Return the first day of current month.
 * @return java.util.Date
 */
public static java.util.Date getFirstOfMonth() {
	java.util.Date now = new java.util.Date();
	return new java.util.Date(now.getYear(), now.getMonth(), 1);
}
}
