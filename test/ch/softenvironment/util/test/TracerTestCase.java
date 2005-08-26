package ch.softenvironment.util.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import ch.softenvironment.util.Tracer;

import junit.framework.TestCase;

/**
 * <Description>
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:16:19 $
 */
public class TracerTestCase extends TestCase {
    public void testLogFile() {
        try {
	        FileOutputStream stream = new FileOutputStream("C:/Temp/TracerTestCase.log", false);
	//      PrintWriter printer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"); 
	                
	        Tracer.start(new PrintStream(stream), Tracer.ALL);
	        Tracer.getInstance().developerError(this, "testLogFile()", "do you see a developerError entry?");
	        Tracer.getInstance().stop();
        } catch(FileNotFoundException e) {
            fail("testLogFile: " + e.getLocalizedMessage());
        }
    }
}
