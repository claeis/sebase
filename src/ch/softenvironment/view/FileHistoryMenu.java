package ch.softenvironment.view;

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 */

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import ch.softenvironment.util.ResourceManager;
import ch.softenvironment.util.StringUtils;

/**
 * Generic FileMenu to provide a list of recently used files. 
 * @author: Peter Hirzel <i>soft</i> Environment
 * @version $Revision: 1.1 $ $Date: 2004-05-08 13:29:51 $
 */
public class FileHistoryMenu extends javax.swing.JMenu {
	private FileHistoryListener listener = null;
	private int numberOfEntries = 0;
	private java.util.List history = new ArrayList();
	// use List to keep order (actually SET)
	/**
	 * FileHistoryMenu constructor.
	 * 
	 * @param listener GUI-Class shwing a FileHistoryMenu
	 * @param maxNumberOfEntries max files to be historized
	 * @param currentEntries given history
	 */
	public FileHistoryMenu(FileHistoryListener listener,
							int maxNumberOfEntries,
							java.util.List currentEntries) {
		super();

		this.listener = listener;
		this.numberOfEntries = maxNumberOfEntries;

		setText(ResourceManager.getInstance().getResource(FileHistoryMenu.class, "MnuFileHistory_text"));
		setToolTipText(ResourceManager.getInstance().getResource(FileHistoryMenu.class, "MnuFileHistory_toolTipText"));

		for (int i = 0; (i < currentEntries.size()) && (i < maxNumberOfEntries); i++) {
			String filename = (String)currentEntries.get(i);
			if (!StringUtils.isNullOrEmpty(filename)) {
				history.add(filename);
			}
		}
		buildSubmenu();
	}
	/**
	 * Add most recently opened file to checkIn at top of History.
	 * @param filename (incl. absolute path) to be kept in history
	 */
	public void addRecent(final String filename) {
		removeRecent(filename); //prevent double entries

		history.add(0, filename); // shuffle last at top
		if (history.size() > numberOfEntries) {
			history.remove(numberOfEntries);
		}
		buildSubmenu();
	}
	/**
	 * Create generic JMenuItem's.
	 */
	private void buildSubmenu() {
		removeAll();

		Iterator iterator = history.iterator();
		while (iterator.hasNext()) {
			final String filename = (String)iterator.next();
			JMenuItem menuItem = new JMenuItem(filename);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					listener.openFile(filename);
				}
			});
			add(menuItem);
		}
	}
	/**
	 * Remove a file from History (for e.g. if non existing any more).
	 * @param filename (incl. absolute path) to be removed from history
	 */
	public void removeRecent(String filename) {
		if (history.contains(filename)) {
			history.remove(filename);
		}
		buildSubmenu();
	}
	/**
	 * Return the current history in last used Order.
	 * @return java.util.List
	 */
	public java.util.List getHistory() {
		return history;
	}
}
