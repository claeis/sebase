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
 * This is a <b>Developer Tracing-Tool</b>, meant to trace important
 * info a Developer should be aware of <b>while developing and testing an application</b>.
 * Except of the runtime-logs the API of this Tool should not be missunderstood
 * as a User-Log Tool, rather hide log-infos created by this tool from user.
 *
 * Eventually this Tool could help in a productive environment for remote assistance,
 * by starting it in a command-shell only (the application parameters should forward
 * any start-parameters to start a Tracer-Instance.
 *
 * Design Pattern: Singleton
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.8 $ $Date: 2005-03-12 17:46:42 $
 */
public class Tracer {
	// Mode's
	public final static int SILENT = 1;  		// supress all Trace-logs
	public final static int NORMAL = 2;	 		// ~User-Compatible
	public final static int DEBUG = 3;   		// show specific debug-messages
	public final static int TRACE_BACKEND = 4;	// show specific Backend-Target-logs
	public final static int ALL = 5;			// show all Trace-logs
	
	private static Tracer instance = null;
	private java.io.PrintStream outStream = null;
	private int mode = SILENT;
/**
 * Tracer constructor.
 * @see start(..)
 */
private Tracer() {
	super();
}
/**
 * Log a debug message, for e.g. to help a developer to trace its code
 * without using a Debugger. This method is meant for temporaly use only, be means
 * any debug-logs should be removed after testing or within productional releases.
 * @deprecated (should not remain in productive versions)
 */
public void debug(Object source, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Debug: ", source, methodName, comment);
	}
}
/**
 * Log a debug message.
 * @deprecated
 */
public void debug(String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Debug: " + comment);
	}
}
/**
 * Log Developer Errors, for e.g. when a developer missunderstood the
 * concept of a called method/mechanism. If this happens, the developer is urged
 * to change this part of code immediately.
 */
public void developerError(Object source, String methodName, String error) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Developer Error: ", source, methodName, error);
	}
}
/**
 * Log developer Warnings, for e.g. if something was wrongly used by Developer,
 * but work around has taken place automatically. The developer should change its
 * code as soon as possible if this trace-log happens to remain compatible for future releases.
 */
public void developerWarning(Object source, String methodName, String warning) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("Developer Warning: ", source, methodName, warning);
	}
}
/**
 * @return console-error Stream
 */
private static java.io.OutputStream getConsoleError() {
	return System.err;
}
/**
 * Design Pattern: Singleton
 * @return Tracer.
 */
public static Tracer getInstance() {
	if (instance == null) {
		start(SILENT);
	}
	return instance;
}
/**
 * Print given Log-Message with leading Timestamp.
 */
private void log(String logMessage) {
	if (mode != SILENT) {
		outStream.println(NlsUtils.formatDateTime(new java.util.Date()) + ">" + logMessage);
	}
}
/**
 * Print Log-Infos in a well formatted way.
 * @param source Instance where Trace-Event happened
 * @param methodName source-code method where Trace-Event happened.
 * @see #log(String)
 */
private void log(String errorType, Object source, String methodName, String comment) {
	String sender = null;
	if (source == null) {
		sender = "<???>";
	} else if (source.getClass().equals(java.lang.Class.class)) {
		// class was the sender
		sender = ((java.lang.Class)source).getName();
	} else {
		// instance was the sender
		sender = source.getClass().getName();
	}
	log(errorType + "->" + comment + " in <" + sender + "#" + methodName + ">");
}
/**
 * Log a command executed on a backend Target (for e.g. a DBMS or any other Server-System).
 * @param command the remotely executed statement on Target-System
 */
public void logBackendCommand(Object source, String methodName, String command) {
	log("Backend: " + command);
}
/**
 * Keep <b>Not Yet Implemented</b> code references.
 * Developer note, to keep reference where something has to be realized soon.
 * @deprecated (a //TODO remark should be preferred)
 */
public void nyi(Object source, String methodName, String comment) {
	if ((mode == DEBUG) || (mode == ALL)) {
		log("NYI:", source, methodName, comment);
	}
}
/**
 * Log Errors during runtime, by means a serious error happened, which might corrupt
 * application behaviour sooner or later.
 */
public void runtimeError(Object source, String methodName, String error) {
	log("Runtime Error:", source, methodName, error);
}
/**
 * Log informations during runtime, by means this may represent a valuable
 * info which might help to interprete application behaviour.
 */
public void runtimeInfo(Object source, String methodName, String info) {
	log("Info:", source, methodName, info);
}
/**
 * Log Warnings during runtime, by means errors might have been happened
 * but were handled automatically. However any other follow-up
 * errors might be expected.
 */
public void runtimeWarning(Object source, String methodName, String warning) {
	log("Runtime Warning:", source, methodName, warning);
}
/**
 * Start Tracer and use Console-Error.
 * @param args Command line arguments ("-all, -silent", "-trace", "traceSQL" or "-debug")
 */
public static java.lang.String[] start(java.lang.String[] args) {
	int mode = SILENT;	// default
	java.util.ArrayList ret=new java.util.ArrayList(java.util.Arrays.asList(args));
	if (args != null) {
		java.util.Iterator it=ret.iterator();
		while (it.hasNext()) {
			String option = (String)it.next();
			if (option.equalsIgnoreCase("-debug")) {
				mode = DEBUG;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-trace")) {
				mode = NORMAL;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-traceSQL")) {
				mode = TRACE_BACKEND;
				it.remove();
				break;
			} else if (option.equalsIgnoreCase("-all")) {
				mode = ALL;
				it.remove();
				break;
			}
		}
	}
	start((java.io.PrintStream)getConsoleError(), mode);
	return (java.lang.String[])ret.toArray(new String[0]);
}
/**
 * Start Tracer and use Console-Error.
 * @param mode (SILENT, NORMAL, DEBUG, TRACE_SQL, ALL)
 */
public static void start(int mode) {
	start((java.io.PrintStream)getConsoleError(), mode);
}
/**
 * Start Tracer.
 * @param stream Outstream for logging
 * @param traceOn (whether silent or not) 
 */
public static void start(java.io.PrintStream stream, int mode) {
	instance = new Tracer();
	instance.outStream = stream;
	instance.mode = mode;
	instance.log("Java Version: " + System.getProperty("java.version"));
	instance.log("Java VM Version: " + System.getProperty("java.vm.version"));
	instance.log("OS Name: " + System.getProperty("os.name"));
	instance.log("OS Architecture: " + System.getProperty("os.arch"));
	instance.log("OS Version: " + System.getProperty("os.version"));
	instance.log("OS Locale: " + java.util.Locale.getDefault().toString());
}
/**
 * Stop Tracer.
 */
public void stop() {
//	try {
		outStream.close();
		instance = null;
/*	} catch(java.io.IOException e) {
		e.printStackTrace(System.out);
	}
*/
}
/**
 * Use this message to trace non-visually handled exceptions which
 * might be ignored during application run. Serious malfunctioning
 * application behaviour might be possible.
 */
public void uncaughtException(Object source, String methodName, Throwable exception) {
	exception.printStackTrace(outStream);
	log("Uncaught Exception:", source, methodName, exception.toString());
}
}
