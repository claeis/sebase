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
 
/**
 * Interface to specifiy a Formatter for a BeanReflector.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2004-09-27 11:09:07 $
 */
public interface ValueFormatter {
/**
 * Visitor-Pattern.
 * The given owner's property will be formatted,
 * for e.g. to be displayed by a table-Cell.
 * @return Object the formatted value
 */
Object format(Object owner, String property);
}
