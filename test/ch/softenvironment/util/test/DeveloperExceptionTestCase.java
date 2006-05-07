package ch.softenvironment.util.test;
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
import ch.softenvironment.util.DeveloperException;

import junit.framework.TestCase;
/**
 * Test class DeveloperException.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2006-05-07 14:53:51 $
 */
public class DeveloperExceptionTestCase extends TestCase {
    private static final String METHOD = "MyMethod()";
    private static final String FAULT_TITLE = "My Title";
    private static final String FAULT_MSG = "My fault!";
    
    public void testConstructorClassMethodMessage() {
        try {
            throw new DeveloperException(DeveloperExceptionTestCase.class, METHOD, FAULT_MSG);
        } catch(DeveloperException e) {
            assertTrue(e.getCause() == null);
            assertTrue(e.getMessage().equals(FAULT_MSG));
            assertTrue(e.getTitle() !=  null); // "Entwicklungsfehler"
            assertTrue(e.getLocalizedMessage().indexOf(METHOD) >= 0);
            assertTrue(e.getLocalizedMessage().indexOf("DeveloperException") >= 0);
        }
    }
    public void testConstructorClassMethodMessageTitle() {
        try {
            throw new DeveloperException(DeveloperExceptionTestCase.class, METHOD, FAULT_MSG, FAULT_TITLE);
        } catch(DeveloperException e) {
            assertTrue(e.getCause() == null);
            assertTrue(e.getMessage().equals(FAULT_MSG));
            assertTrue(e.getTitle().equals(FAULT_TITLE));
            assertTrue(e.getLocalizedMessage().indexOf(METHOD) >= 0);
            assertTrue(e.getLocalizedMessage().indexOf("DeveloperException") >= 0);
        }
    }
    public void testConstructorClassMethodMessageTitleException() {
        try {
            throw new DeveloperException(DeveloperExceptionTestCase.class, METHOD, FAULT_MSG, FAULT_TITLE, new RuntimeException("Other cause"));
        } catch(DeveloperException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().equals("Other cause"));
            assertTrue(e.getMessage().equals(FAULT_MSG));
            assertTrue(e.getTitle().equals(FAULT_TITLE));
            assertTrue(e.getLocalizedMessage().indexOf(METHOD) >= 0);
            assertTrue(e.getLocalizedMessage().indexOf("DeveloperException") >= 0);
            
        }
    }
    public void testConstructorInstanceMethodMessageTitleException() {
        try {
            throw new DeveloperException(this, METHOD, FAULT_MSG, FAULT_TITLE, new RuntimeException("Other cause"));
        } catch(DeveloperException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().equals("Other cause"));
            assertTrue(e.getMessage().equals(FAULT_MSG));
            assertTrue(e.getTitle().equals(FAULT_TITLE));
            assertTrue(e.getLocalizedMessage().indexOf(METHOD) >= 0);
            assertTrue(e.getLocalizedMessage().indexOf("DeveloperExceptionTestCase") >= 0);
            
        }
    }
}
