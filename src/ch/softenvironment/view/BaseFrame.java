package ch.softenvironment.view;

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
 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.MissingResourceException;

import ch.ehi.basics.view.*;
import ch.softenvironment.util.*;
import ch.softenvironment.util.Tracer;
/**
 * TemplateFrame defining minimal functionality.
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.10 $ $Date: 2004-06-26 11:18:56 $
 */
public abstract class BaseFrame extends javax.swing.JFrame {
	// Relative Offset to Child Window
	public final static int X_CHILD_OFFSET = 20;
	public final static int Y_CHILD_OFFSET = 15;
	private WaitDialog waitDialog = null;
	private volatile int waitCounter = 0;
	private ViewOptions viewOptions = null;

/**
 * Simply allow forking the display of a WaitDialog.
 * @see WaitDialog
 */
class WaitBlock extends Thread {
	/**
	 * Constructor.
	 */
	WaitBlock() {
		super();
	}
	/**
	 * Popup the WaitDialog if not already displayed.
	 */
	public void run() {
		try {
			if (waitDialog != null) {
				waitDialog.show();
				waitDialog.paint(waitDialog.getGraphics());
			}
		} catch(Throwable e) {
			Tracer.getInstance().runtimeWarning(this, "WaitBlock#run()", "Ignore: " + e.getLocalizedMessage());
		}
	}
}

/**
 * Execute a method as a threaded Block and show Busy-Cursor meanwhile.
 */
class Block extends Thread {
	private Class parameterTypes[];
	private Object parameters[];
	private String methodName = null;
	private Object instance = null;
	/**
	 * @param methodName a public method to be threaded
	 */
	Block(Class parameterTypes[], Object parameters[], String methodName, Object instance) {
		super();
		this.parameterTypes = parameterTypes;
		this.parameters = parameters;
		this.methodName = methodName;
		this.instance = instance;
	}
	/**
	 * Execute the Method given by Block-Constructor and
	 * show BusyCursor while executing the Method.
	 */
	public void run() {
//		java.awt.Cursor cursor = getCursor();
		WaitDialog dialog = null;
		try {
			setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
			
			dialog = new WaitDialog((java.awt.Frame)instance, null, null);
			dialog.show();
			dialog.paint(dialog.getGraphics());
			
			java.lang.reflect.Method method = instance.getClass().getMethod(methodName, parameterTypes);
			method.invoke(instance, parameters);
		} catch(Throwable e) {
			Tracer.getInstance().runtimeError(this, "Block#run()", "Thread failed: " + e.toString());
			handleException(e);
		} finally {
			try {
				if (dialog != null) {
					dialog.dispose();
				}
			} catch(NullPointerException e) {
				// waitDialog may be null'ed -> not Thread-save
				Tracer.getInstance().developerWarning(this, "run()", "NullPointerException ignored");
			}
 			setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
		}
	}
}
/**
 * BaseFrame constructor comment.
 */
public BaseFrame() {
	super();
}
/**
 * BaseFrame constructor comment.
 */
public BaseFrame(ViewOptions viewOptions) {
	super();

	this.viewOptions = viewOptions;
}
/**
 * BaseFrame constructor comment.
 * @param title java.lang.String
 */
public BaseFrame(String title) {
	super(title);
}
/**
 * Adapt the dependendencies in relation to a mouseEvent.
 *
 * For e.g. Adapt PopupMenuItems after a selection of a SearchTable-Row.
 * Overwrite this method.
 * @see genericPopupDisplay(..)
 */
protected void adaptSelection(MouseEvent event, JPopupMenu popupMenu) {
}
/**
 * Set the Listener for windowClosed-Events.
 */
protected final void addDefaultClosedListener() {
	/* Add a windowListener for the windowClosedEvent */
	addWindowListener(new java.awt.event.WindowAdapter() {
		public void windowClosed(java.awt.event.WindowEvent e) {
			System.exit(0);
		};
		//  public void windowClosing(java.awt.event.WindowEvent e) {
		//      System.exit(0);
		//  };
	});
}
/**
 * Set the Listener for windowClosing-Events.
 */
protected final void addDefaultClosingListener() {
	/* Add a windowListener for the windowClosedEvent */
	addWindowListener(new java.awt.event.WindowAdapter() {
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		};
	});
}
/**
 * Ask user whether the remove action shall be proceeded or not.
 * @see BaseDialog#checkDeletion()
 */
protected final boolean checkDeletion() {
	return checkDeletion(getResourceString(BaseFrame.class, "CTDeletion"), getResourceString(BaseFrame.class, "CQAcceptDeletion")); //$NON-NLS-2$ //$NON-NLS-1$
}
/**
 * Ask user whether the remove action shall be proceeded or not.
 * @param title String (of Deletion Message Box)
 * @param question String (of Deletion question)
 * @see BaseDialog#checkDeletion()
 */
protected final boolean checkDeletion(String title, String question) {
	try {
		QueryDialog dialog = new QueryDialog(this, title, question);
		dialog.dispose();
		return dialog.isYes();
	} catch(Throwable e) {
		handleException(e);
		return false;
	}
}
/**
 * Extend the given menu generically with plattform dependent Look&Feel styles.
 * @see #setLookAndFeel(String)
 */
protected final void createLookAndFeelMenu(JMenu lookAndFeelMenu) {
	UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
	JMenuItem menuItem = null;

	ButtonGroup buttons = new ButtonGroup();
	for (int i = 0; i < lafs.length; i++) {
		// create generic MenuItem-Button
		menuItem = new JRadioButtonMenuItem(lafs[i].getName());
		buttons.add(menuItem);
		final String lnfClassName = lafs[i].getClassName();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setLookAndFeel(lnfClassName);
			}
		});
		lookAndFeelMenu.add(menuItem);
	}
}
/**
 * Overwrite the #dispose() method for Launcher's of any Application extending this
 * BaseFrame.
 *
 * // Overwrites
 * public void dispose() {
 *  // super.dispose(); => WILL BE CALLED BY #disposeApplication()
 *	disposeApplication();
 * }
 *
 * @see LauncherView#dispose()
 */
protected void disposeApplication() {
	Tracer.getInstance().stop();
	super.dispose();
	System.exit(0);
}
/**
 * Converts the stack trace into a string.
 */
public static String exceptionToString(Throwable exception) {
    java.io.StringWriter stringWriter = new java.io.StringWriter();
    java.io.PrintWriter writer = new java.io.PrintWriter(stringWriter);
   	exception.printStackTrace(writer);
    return stringWriter.toString();
}
/**
 * Execute a <b>public Method</b> in an <b>own Thread</b> and show Busy-Cursor 
 * and WaitDialog meanwhile.
 * Each threaded Block has its own WaitDialog.
 *
 * Ex. public void doAnything(Integer c, String s) {..}
 * @param parameterTypes	Class parameterTypes[] = { Integer.class, String.class }
 * @param parameters		Object parameters[] = { new Integer(3), "Hello" }
 * @param methodName		methodName "doAnything" must be <b>public</b>
 * @param instance			instance allowing doAnything(..)
 * @see Block.run()			inner Class here
 * @see #updateProgress(..)
 */
protected final void executeBlock(Class parameterTypes[], Object parameters[], String methodName, Object instance) {
	Block block = new Block(parameterTypes, parameters, methodName, instance);
	block.start(); 
}
/**
 * (Short cut.)
 * @see #executeChangeObjects(Object).
 */
protected final void executeChangeObjects() {
	executeChangeObjects(null);
}
/**
 * @see ListMenuChoice#changeObjects(Object).
 */
protected final void executeChangeObjects(Object source) {
	Class types[] = { Object.class };
	Object parameters[] = { source };
	showBusy(types, parameters, "changeObjects");
}
/**
 * @see ListMenuChoice#copyObject(Object).
 */
protected final void executeCopyObject(Object source) {
	Class types[] = { Object.class };
	Object parameters[] = { source };
	showBusy(types, parameters, "copyObject");
}
/**
 * (Short cut.)
 * @see #executeNewObject(Object).
 */
protected final void executeNewObject() {
	executeNewObject(null);
}
/**
 * @see ListMenuChoice#newObject(Object).
 */
protected final void executeNewObject(Object source) {
	Class types[] = { Object.class };
	Object parameters[] = { source };
	showBusy(types, parameters, "newObject");
}
/**
 * @see DetailView#redoObject().
 */
protected final void executeRedoObject() {
	showBusy("redoObject");
}
/**
 * (Short cut.)
 * @see #executeRemoveObject(Object).
 */
protected void executeRemoveObjects() {
	executeRemoveObjects(null);
}
/**
 * @see ListMenuChoice#removeObjects(Object).
 */
protected final void executeRemoveObjects(Object source) {
	Class types[] = { Object.class };
	Object parameters[] = { source };
	showBusy(types, parameters, "removeObjects");
}
/**
 * @see DetailView#saveObject().
 */
protected final void executeSaveObject() {
	showBusy("saveObject");
}
/**
 * @see SearchView#searchObjects().
 */
protected final void executeSearchObjects() {
	showBusy("searchObjects");
}
/**
 * @see DetailView#setCurrentObject(Object).
 */
protected final void executeSetCurrentObject(Object object) {
	Class methodParameterTypes[] = { Object.class };
	Object methodParameters[] = { object };
	showBusy(methodParameterTypes, methodParameters, "setCurrentObject");
}
/**
 * @see DetailView#undoObject().
 */
protected final void executeUndoObject() {
	showBusy("undoObject");
}
/**
 * Export Data of given table into a file.
 * The table data is exported in a generic manner,
 * say given table is exported 1:1 to CSV including
 * Header-Data.
 * @param table
 */
protected final void exportTableData(JTable table) {
	FileChooser saveDialog =  new FileChooser(/*getSettings().getWorkingDirectory()*/);
	saveDialog.setDialogTitle(CommonUserAccess.getMniFileSaveAsText());//$NON-NLS-1$
	saveDialog.addChoosableFileFilter(ch.ehi.basics.view.GenericFileFilter.createCsvFilter());

	if (saveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		try {
			Class types[] = {java.io.File.class, javax.swing.JTable.class};
			Object parameters[] = {saveDialog.getSelectedFile(), table};
			showBusy(types, parameters, "exportTableData");
		} catch(Throwable e) {
			handleException(e);
		}
	}
}
/**
 * Critical Error. Application must be shut down.
 * @param title Dialogtitle
 */
public final void fatalError(JFrame frame, String title, String message, Throwable exception) {
	new ErrorDialog(frame, title, message + "\n" + getResourceString(BaseFrame.class, "CEFatalError"), exception);
	System.exit(-1);
}
/**
 * Display a popup menu.
 * Ex. SearchTable to JPopupMenu
 * 1) Connect MouseReleased-Event (for Windows) and MousePressed-Event (for Unix) from Panel (for e.g. JScrollPane, JTablePane, etc) to this method.
 * 2) Connect JPopupMenu's this-property visually to the MouseReleased-Event's Parameter popupMenu.
 * 3) Overwrite #adaptSelection(MouseEvent, JPopupMenu)
 */
protected final void genericPopupDisplay(java.awt.event.MouseEvent event, javax.swing.JPopupMenu popupMenu) {
	try {
	 	adaptSelection(event, popupMenu);

	 	if (event.getClickCount() == 2) {
		 	// case: double-click
			if (this instanceof ListMenuChoice) {
//				((ListMenuChoice)this).defaultDoubleClickAction(event);
				executeChangeObjects(event.getSource());
			}
	 	} else if (event.isPopupTrigger() && (popupMenu != null)) {
			popupMenu.show(event.getComponent(), event.getX(), event.getY());
		}
   	} catch(Throwable e) {
	   	handleException(e);
   	}
}
/**
 * Return an NLS-String.
 * @param propertyName
 * @return String
 */
protected static String getResourceString(java.lang.Class owner, String propertyName) {
	return ResourceManager.getInstance().getResource(owner, propertyName);
}
/**
 * Return an NLS-String.
 * @return
 */
protected String getResourceString(String propertyName) {
	return getResourceString(this.getClass(), propertyName);
}
/**
 * Calculate the screen size
 * @return Dimension
 */
protected final static Dimension getScreenSize() {
	return(Toolkit.getDefaultToolkit().getScreenSize());
}
/**
 * Return the User-Directory (for e.g. where this Java-Program is located)
 * without a Path-Separator at the end.
 */
public static String getStartupPath(String jarFileName) {
	String home = System.getProperty("user.dir");//$NON-NLS-1$
Tracer.getInstance().debug(BaseFrame.class, "getStartupPath(jarfile)", "user.dir = " + home);
	
    String classpath = System.getProperty("java.class.path");//$NON-NLS-1$
Tracer.getInstance().debug(BaseFrame.class, "getStartupPath(jarfile)", "java.class.path = " + classpath);
/*    int index = classpath.toLowerCase().indexOf(jarFileName.toLowerCase());//$NON-NLS-1$
Tracer.getInstance().debug(BaseFrame.class, "getStartupPath(jarfile)", "index of jarfile = " + index);
    int start = classpath.lastIndexOf(java.io.File.pathSeparator, index) + 1;
    if (start >= 0) {
    	if (index > start) {
    		home = classpath.substring(start, index - 1);
    	} else {
    		home = ".";
    	}
Tracer.getInstance().debug(BaseFrame.class, "getStartupPath(jarfile)", "home[JAR] = " + home);
    }
 */

    return home;
}
/**
 * Retunr GUI Configuration.
 */
protected final ViewOptions getViewOptions() {
	return viewOptions;
}
/**
 * Top-level Handler.
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
protected void handleException(java.lang.Throwable exception) {
	showException(this, exception);
}
/**
 * Intitalize View.
 * Call this method in a View's standard initalize method to setup your stuff.
 * @see #initialize() // user code begin {1}..user code end
 */
protected abstract void initializeView() throws Throwable;
/**
 * Developer utility.
 * Inform user of <Not Yet Implemented> Feature.
 * @param title Dialogtitle
 */
public final void nyi(String title) {
	nyi(title, getResourceString(BaseFrame.class, "CWNotYetImplemented")); //$NON-NLS-1$
}
/**
 * Developer utility.
 * Inform user of <Not Yet Implemented> Feature.
 * @param title Dialogtitle
 * @param message Speaking info for user
 */
public final void nyi(String title, String message) {
	new WarningDialog(this, title, message);
}
/**
 * Set this Frame at center of screen.
 */
public final void setCenterLocation() {
    setCenterLocation(this);
}
/**
 * Set the given Window at center of screen.
 */
public final static void setCenterLocation(Window window) {
    Dimension screenSize = getScreenSize();
    Dimension frameSize = window.getSize();

    if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
    }

    window.setLocation(
        new Point(
            (screenSize.width - frameSize.width) / 2,
            (screenSize.height - frameSize.height) / 2));
}
/**
 * Switch to a new Look&Feel during runtime
 * @see #createLookAndFeelMenu(JMenu)
 */
protected void setLookAndFeel(String style) {
	try {
		// javax.swing.UIManager.getSystemLookAndFeelClassName();
		//	UIManager.getCrossPlatformLookAndFeelClassName();		//metal (Java Standard)
		//	"com.sun.java.swing.plaf.motif.MotifLookAndFeel";		//Unix
		//  "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	//Windows only
		//	"com.sun.java.swing.plaf.mac.MacLookAndFeel"; 			//Mac only
		//	"com.sun.java.swing.plaf.multi.MultiLookAndFeel";
		if ((style == null) || (style.length() == 0)) {
			UIManager.getSystemLookAndFeelClassName();
		} else {
			UIManager.setLookAndFeel(style);
		}
		SwingUtilities.updateComponentTreeUI(this);
	} catch (Throwable e) {
		new WarningDialog(this, getResourceString(BaseFrame.class, "CTlookAndFeel"), NlsUtils.formatMessage(getResourceString(BaseFrame.class, "CWManagerNotAvailable"), style) + "\n" + getResourceString(BaseFrame.class, "CWSuppressManager")); //$NON-NLS-4$//$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
	}
}
/**
 * Set this Frame relative to parent.
 */
public final void setRelativeLocation(java.awt.Window parent) {
	if (parent != null) {
		setLocation(new Point(parent.getX() + X_CHILD_OFFSET,
								parent.getY() + Y_CHILD_OFFSET));
	}
}
/**
 * Set Look & Feel at startup of Application.
 * @see #setLookAndFeel(String)
 */
public final static void setSystemLookAndFeel() throws Throwable {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}
/**
 * Display Window.
 */
public void setVisible(boolean visible) {
//	setSize(getFrameSize());
	
	// setModal @see Dialog.show()
	
	super.setVisible(true);
}
/**
 * Execute a <b>public Method</b> in the hierarchy <b>NON-threaded</b> and 
 * show Busy-Cursor and WaitDialog meanwhile.
 * There is only ONE WaitDialog in case of nested calls of this method.
 * Use #updateProgress(..) to show any Progress-Messages.
 *
 * Ex. 
 * class MyFrame extends BaseFrame {
 *   public void doAnything(Integer c, String s) {
 *     updateProgress(10, "start activity");
 *     ...
 *   }
 *   caller() {
 *     showBusy(.., "doAnything");
 *   }
 *
 * @param parameterTypes	Class parameterTypes[] = { Integer.class, String.class }
 * @param parameters		Object parameters[] = { new Integer(3), "Hello" }
 * @param methodName		methodName "doAnything" must be <b>public</b>
 *
 * @see WaitBlock.run()	inner Class here
 * @see #updateProgress(..)
 */
protected final void showBusy(Class parameterTypes[], Object parameters[], String methodName) {
	try {
		startWaitDialog(null, null);
		// only the WaitDialog is forked here!
		WaitBlock block = new WaitBlock();
		block.start();

		// be careful with threading sequential tasks, such as Database-Transactions!!!
		java.lang.reflect.Method method = this.getClass().getMethod(methodName, parameterTypes);
		method.invoke(this, parameters);

		block.interrupt();
	} catch(Throwable e) {
		Tracer.getInstance().runtimeError(this, "showBusyCursor(..)", e.toString());
		handleException(e);
	} finally {
		stopWaitDialog();
	}
}
/**
 * @see #showBusy(Class[]. Object[], String)
 */
protected final void showBusy(String methodName) {
	Class types[] = {};
	Object parameters[] = {};
	showBusy(types, parameters, methodName);
}
/**
 * Top-level Handler.
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
public final static void showException(Window owner, java.lang.Throwable exception) {
	try {
		// update log
		Tracer.getInstance().runtimeWarning(owner, "handleException(..) -> stackTrace follows...", exception.toString());//$NON-NLS-1$
		exception.printStackTrace(System.out);

		// inform user
		String title = null; //$NON-NLS-1$
		String message = getResourceString(BaseFrame.class, "CWTopLevelHandler"); //$NON-NLS-1$
		if (exception instanceof NumberFormatException) {
			
			if ((exception.getMessage().length() == 0) || exception.getMessage().equals("empty String") || (exception.getMessage().equals("-"))) {//$NON-NLS-2$//$NON-NLS-1$
Tracer.getInstance().hack(BaseFrame.class, "showException(..)", "exception message might change -> use another recognition");//$NON-NLS-2$//$NON-NLS-1$
				Tracer.getInstance().runtimeWarning(BaseFrame.class, "showException(.)", "NumberFormatException ignored: " + exception.toString());
				return;
			}

			title = getResourceString(BaseFrame.class, "CTInputError"); //$NON-NLS-1$
			message = getResourceString(BaseFrame.class, "CENumberFormat") + "\n  => " + exception.getLocalizedMessage();//$NON-NLS-2$ //$NON-NLS-1$
		} else if (exception instanceof DeveloperException) {
			title = ((DeveloperException)exception).getTitle();
			message = exception.getMessage();
		} else if (exception instanceof MissingResourceException) {
			Tracer.getInstance().developerError(BaseFrame.class, "showException(.)", "MissingResourceException ignored: " + exception.getLocalizedMessage());
			return;
		} /* else if (exception instanceof java.sql.SQLException) {
			@see DbBaseFrame#handleException(..)
		} */
		
		if ((owner == null) || (owner instanceof Frame)) {
			new ErrorDialog((Frame)owner, title , message, exception);
		} else if (owner instanceof Dialog) {
			new ErrorDialog((Dialog)owner, title , message, exception);
		} else {
Tracer.getInstance().nyi(BaseFrame.class, "showException(..)");
		}
	} catch(Throwable e) {
		Tracer.getInstance().developerError(BaseFrame.class, "showException(..)", e.getLocalizedMessage());
	} finally {
		// this method must not throw an Exception under any circumstances
		System.out.println("BaseFrame#showException(..) -> finally should not have been reached");
	}
}
/**
 * Show Dialog to shut down application.
 * @return boolean whether exiting was copnfirmed or not
 */
protected boolean showExitDialog(String title) {
	try {
		QueryDialog dialog = new QueryDialog(this, title, getResourceString(BaseFrame.class, "CIExit"));
		return dialog.isYes();
	} catch (Throwable e) {
		Tracer.getInstance().runtimeWarning(BaseFrame.class, "showExitDialog(..)", e.toString());//$NON-NLS-2$
		return true;
	}
}
/**
 * Show a SplashScreen.
 * Typically used at Application startup.
 */
protected final static void showSplashScreen() {
	showSplashScreen(new Dimension(436, 293), ch.ehi.basics.i18n.ResourceBundle.getImageIcon(BaseFrame.class, "splash.png"));
}
/**
 * Show a SplashScreen.
 * Typically used at Application startup.
 */
protected final static void showSplashScreen(Dimension preferredWindowSize, String image) {
	try {
		Window window = new SplashScreen(preferredWindowSize, image);
		
		/* Create the splash screen */
		window.pack();

		/* Center splash screen */
		setCenterLocation(window);
		window.setVisible(true);

		Thread.sleep(5000);

		window.dispose();
	} catch (Throwable e) {
		Tracer.getInstance().runtimeWarning(BaseFrame.class, "showSplashScreen(<image=" + image + ">)", e.toString());//$NON-NLS-2$//$NON-NLS-1$
	}
}
/**
 * @see #showSplashScreen(Dimension, ImageIcon, long)
 */
protected final static void showSplashScreen(Dimension preferredWindowSize, ImageIcon image) {
	showSplashScreen(preferredWindowSize, image, 5000);
}
/**
 * Show a SplashScreen.
 * Typically used at Application startup.
 */
protected final static void showSplashScreen(Dimension preferredWindowSize, ImageIcon image, long timeOut) {
	try {
		Window window = new SplashScreen(preferredWindowSize, image);
		
		/* Create the splash screen */
		window.pack();

		/* Center splash screen */
		setCenterLocation(window);
		window.setVisible(true);

		Thread.sleep(timeOut);

		window.dispose();
	} catch (Throwable e) {
		Tracer.getInstance().runtimeWarning(BaseFrame.class, "showSplashScreen(<image=" + image + ">)", e.toString());//$NON-NLS-2$//$NON-NLS-1$
	}
}
/**
 * 
 */
private synchronized final void startWaitDialog(String title, String imagePath) {
	if (waitCounter == 0) {
		try {
			setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
			waitDialog = new WaitDialog(this, title, imagePath);
		} catch(Throwable e) {
			Tracer.getInstance().runtimeError(this, "startWaitDialog(..)", e.toString());
		} 
	}
	waitCounter++;
}
/**
 * 
 */
private synchronized final void stopWaitDialog() {
	waitCounter--;
	if (waitCounter == 0) {
		try {
			if (waitDialog != null) {
				waitDialog.dispose();
				waitDialog = null;
			}
		} catch(Throwable e) {
			Tracer.getInstance().runtimeError(this, "stopWaitDialog(..)", e.toString());
		} finally {
	 		setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
		} 
	}
}
/**
 * Show Progress in WaitDialog started by #showBusy(..).
 * @param percentage of activity done
 * @param currentActivity User friendly name
 * @see showBusy(..)
 */
protected synchronized final void updateProgress(int percentage, String currentActivity) {
	try {
		if (waitDialog != null) {
			waitDialog.updateProgress(percentage, currentActivity);
			waitDialog.paint(waitDialog.getGraphics());
		}
	} catch(Throwable e) {
		Tracer.getInstance().developerWarning(this, "updateProgress(..)", "Ignoring: " + e.getLocalizedMessage());
	}
}
/**
 * Reset GUI-Components NLS-Strings, such as Component-text 
 * or Component-textToolTip, usually after the Locale#getDefault() has changed.
 * Property "text" and "toolTipText" are changed in a generic, recursive matter.
 * 
 * @see  #getResourceString(..)
 */
protected void updateStringComponent(Component component) {
	// @see SwingUtilities#updateComponentTreeUI(Component)
	java.lang.Class resource = null;
	Component[] children = null;
	if (component instanceof JMenu) {
		children = ((JMenu)component).getMenuComponents();
	} else if (component instanceof Container) {
		children = ((Container)component).getComponents();
		if (!(component.getClass().getName().startsWith("javax.swing") ||
				component.getClass().getName().startsWith("java.awt"))) {
			// container might be an own Part with its own properties-Resource-File
			resource = component.getClass();
		}
	}
	if (children != null) {
		for(int i = 0; i < children.length; i++) {
			Component child = children[i];
			// try change potential NLS-properties
			updateStringProperty(resource, child, "text");
			updateStringProperty(resource, child, "toolTipText");
			// go down UI-Tree recursively
			updateStringComponent(child);
		}
	}
}
/**
 * Look up a String in a properties-Resource-File, according to 
 * the following rule:
 * Component	  = javax.swing.JLabel
 * Component#name = LblMyLabel
 *   => entry in properties-File for "text"        = LblMyLabel_text
 *   => entry in properties-File for "toolTipText" = LblMyLabel_toolTipText
 * @param component Component having the given property
 * @param property (usually "text" or "toolTipText")
 */
private void updateStringProperty(java.lang.Class resource, Component component, String property) {
	if (component.getName() != null) {
		BeanReflector bean = new BeanReflector(component, property);
		if (bean.hasProperty() == BeanReflector.GETTER_AND_SETTER) {
			try {
				String nls = null;
				if (resource == null) {
					nls = getResourceString(component.getName() + "_" + property);
				} else {
					nls = getResourceString(resource, component.getName() + "_" + property);
				}
				bean.setValue(nls);
			} catch(Throwable e) {
				if ((e instanceof MissingResourceException) && 
						(component instanceof javax.swing.JMenuItem)) {
					// try CommonUserAccess.properties
					try {
						String nls = getResourceString(CommonUserAccess.class, component.getName() + "_" + property);
						bean.setValue(nls);
					} catch(Throwable cua) {
						Tracer.getInstance().debug("Resource missing: " + cua.getLocalizedMessage());
					}
				} else {
					Tracer.getInstance().debug("Resource missing: " + e.getLocalizedMessage());
				}
			}
		}
	}
}

/**
 * @see #exportTableData(JTable)
 */
public final void exportTableData(File file, JTable table) {
	try {
	 	FileOutputStream outStream = new FileOutputStream(file);
	  	PrintStream stream = new PrintStream(outStream);

	  	char separator = ';';

	  	// header
		int columnCount = table.getModel().getColumnCount();
		for (int col=0; col<columnCount; col++) {
			stream.print(table.getModel().getColumnName(col) + separator);
		}
		stream.println();

		// data
		int rowCount = table.getModel().getRowCount();
		for (int row=0; row<rowCount; row++) {
			for (int col=0; col<columnCount; col++) {
				Object value = table.getModel().getValueAt(row, col);
				stream.print(StringUtils.getString(value) + separator);
			}
			stream.println();
		}

		outStream.flush();
	  	outStream.close();
	} catch(Throwable e) {
		handleException(e);
	}
}
}
