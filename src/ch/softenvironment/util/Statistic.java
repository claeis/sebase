package ch.softenvironment.util;

import java.io.*;
import java.util.*;

/**
 * Register performance times for any tasks.
 * @author: Peter Hirzel <i>soft</i>Environment
 */
public final class Statistic {
	public transient String useCase;
	public transient int count;
	public transient int max;
	public transient int min;
	public transient int total;

	private static TreeMap statisticMap = new TreeMap();
/**
 * @param name of UseCase to measure in time.
 */
public Statistic(String useCase) {
    this.useCase = useCase;
    clear();
    synchronized (statisticMap) {
        statisticMap.put(useCase, this);
    }
}
public final void add(int sampleValue) {
    if (sampleValue > max) {
        max = sampleValue;
    }
    if (sampleValue < min) {
        min = sampleValue;
    }
    total += sampleValue;
    count++;
}
public void clear() {
    count = 0;
    max = Integer.MIN_VALUE;
    min = Integer.MAX_VALUE;
    total = 0;
}
public final static void clear_all() {
    for (Iterator i = statisticMap.entrySet().iterator(); i.hasNext();) {
        Map.Entry e = (Map.Entry) i.next();
        ((Statistic) e.getValue()).clear();
    }
}
/**
 * Print measured statistics into given Stream.
 */
public final static void dump(PrintStream out) {
    for (Iterator i = statisticMap.entrySet().iterator(); i.hasNext();) {
        Map.Entry e = (Map.Entry) i.next();
        Statistic s = (Statistic) e.getValue();
        if (s.count > 0) {
	    	out.println(s.useCase);
 	    	out.println("count  =" + s.count);
  	    	out.println("min    =" + s.min);
   	    	out.println("max    =" + s.max);
    		out.println("average=" + s.total / s.count);
     		out.println("total  =" + s.total);
        }
    }
}
}
