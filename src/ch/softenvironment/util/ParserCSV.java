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
 
import java.io.*;

/**
 * Parser-Tool to parse comma separated files (*.CSV).
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.6 $ $Date: 2004-08-18 09:28:13 $
 */
public class ParserCSV {
	private String line = null;
	private String separator = null;
	private int lastIndex = -1;
	
/**
 * Transform a String with separated items into a List.
 * @param serializedList
 * @return java.util.List
 */
public static java.util.List stringToArray(String serializedList, String separator) {
	ParserCSV parser = new ParserCSV(serializedList, separator);
	
	java.util.List list = new java.util.ArrayList();
	String item = parser.getNextString();
	while (item != null) {
//		if (!StringUtils.isNullOrEmpty(item)) {
			list.add(item);
//Tracer.getInstance().debug(ParserCSV.class, "stringToArray(..)", "adding: " + item);
//		}
		item = parser.getNextString();
	}
	return list;
}
/**
 * Transform a List into a String with items separated by a Separator.
 * @param items
 * @return String
 */
public static String arrayToString(java.util.List items, String separator) {
	String serializedList = "";
	java.util.Iterator iterator = items.iterator();
	while (iterator.hasNext()) {
		serializedList = serializedList + iterator.next() + separator;
	}
	return serializedList;
}
/**
 * ParserCSV constructor comment.
 * @param line (for e.g. "field1;field2;...")
 * @param separator (for e.g. ";")
 */
public ParserCSV(String line, String separator) {
	super();
	this.line = line;
	this.separator = separator;
}
/**
 * Return next Integer between to Separators.
 */
public Integer getNextInteger() throws NumberFormatException {
	String s = getNextString();
	if (StringUtils.isNullOrEmpty(s)) {
		return null;
	} else {
		return Integer.valueOf(s);
	}
}
/**
 * Return a Substring between to Separators.
 * @return null if no next or empty
 */
public String getNextString() {
	String result = null;
	int to = line.indexOf(separator, lastIndex + 1);
	
	if (to > -1) {
		result = line.substring(lastIndex + 1, to);
		lastIndex = to;

	} else if (lastIndex < line.length()) {
		// treat end of line as Separator
		result = line.substring(lastIndex + 1, line.length());
		lastIndex = line.length();
	} else {
		// end of line reached
		return result;
	}
/*
	// eliminate "double-quotes" around String => quotes
	// Excel introduces such quotes in case a cell contains separator-like Characters
	if (result.startsWith("\"", 0) && result.endsWith("\"")) {
		result = result.substring(1, result.length() - 1);
	}

	// strip falsely generated double quotes
	int from = 0;
	while ((to = result.indexOf("\"\"", from)) > -1) {
		// always eliminate 2nd '\"'
		result = result.substring(0, to + 1) + result.substring(to + 2, result.length());
		from = to + 1;
	}
*/
	return result.trim();
}
/**
 * Replace separator in text.
 */
public static String maskSeparator(Object object, char sep) {
	if (object == null) {
		return "";
	}
	
	String text = null;
	char replacement = ',';
	if (object instanceof String) {
		text = (String)object;
	} else {
		text = object.toString();
	}
	return text.replace(sep, replacement).replace('\n', replacement);
}

/**
 * Read a File where fields/cells are separated by a given separator and return a List
 * where each item contains a line of the given Stream.
 *
 * @param stream
 * @param separator
 * @return list of lines
 */
public static java.util.List readFile(InputStream stream, String separator) {
	java.util.List arrayList = new java.util.ArrayList();
	BufferedReader inFile = new BufferedReader(new InputStreamReader(stream));

	try {
		String line = "";
		
		while ((line = inFile.readLine()) != null) {
			if (line.indexOf(separator) >= 0) {
				// only add if at least on "cell" is contained
				arrayList.add(line);
			}
		}

/*
 		// try if a Cell may contain LF as possible character
 		int c;
		while ((c = inFile.read()) != -1) {
			if (c == '\r') { //CR
  				c = inFile.read();
  				if (c == -1) {
  					// EOF reached
	  				if (line.length() > 0) {
		  				arrayList.add(line);
	  				}
	  				return arrayList;
  				} else if (c == '\n') { // LF
	  				// Windows: EndOfLine reached
  					if (line.indexOf(separator) >= 0) {
  						// only add if "cells" contained
  						arrayList.add(line);
  					}
  					line = "";
  				} else {
  					// Linux evtl. other EndOfLine
//					throw new DeveloperException(ParserCSV.class, "readFile(..)", "'\n' was expected!");
  					arrayList.add(line);
  					line = "" + (char)c;
  				}
	   		} else {
		   		line = line + (char)c;
	   		}
		}
*/
		inFile.close();
	} catch(IOException e) {
		Tracer.getInstance().runtimeWarning(ParserCSV.class, "readFile(..)", "Could not read from Stream: " + e.toString());
	}
	return arrayList;
}
/**
 * Skip over Separators.
 * @param nrOfFields to be overread
 */
public void skip(int nrOfFields) {
	for (int i=0; i<nrOfFields; i++) {
		int to = line.indexOf(separator, lastIndex + 1);
		if (to > -1) {
			lastIndex = to;
		} else {
			Tracer.getInstance().runtimeWarning(this, "skip(int)", "no more separators after: " + i + "/" + nrOfFields);
			break;
		}
	}
}
}
