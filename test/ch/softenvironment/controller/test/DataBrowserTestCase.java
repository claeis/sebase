package ch.softenvironment.controller.test;

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
import java.util.*;

import ch.softenvironment.controller.DataBrowser;

import junit.framework.TestCase;
/**
 * TestCase for DataBrowser.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2006-12-19 09:58:04 $
 */
public class DataBrowserTestCase extends TestCase {
    private DataBrowser browser = null;
    
    public void setUp() {
        browser = new DataBrowser();
    }
    public void tearDown() {
        browser = null;
    }
    public void testEmptyBrowsing() {        
        assertFalse(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertFalse(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());
        assertTrue(browser.getCurrentObject() == null);
        assertTrue(browser.getCurrentIndex() == -1);
        // though not expected
        assertTrue(browser.getNext() == null);
        assertTrue(browser.getPrevious() == null);
        assertTrue(browser.getFirst() == null);
        assertTrue(browser.getLast() == null);
        
        browser.setObjects(new ArrayList());
        assertFalse(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertFalse(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());
        assertTrue(browser.getCurrentObject() == null);
        assertTrue(browser.getCurrentIndex() == -1);
        // though not expected
        assertTrue(browser.getNext() == null);
        assertTrue(browser.getPrevious() == null);
        assertTrue(browser.getFirst() == null);
        assertTrue(browser.getLast() == null);
    }
    public void testSingleBrowsing() {      
        List list = new ArrayList();
        list.add("Hello");
        browser.setObjects(list);
        assertFalse(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertFalse(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());
        
        assertTrue("Hello".equals(browser.getCurrentObject()));
        assertTrue(0 == browser.getCurrentIndex());
        assertTrue("1/1".equals(browser.getScrollIndexString()));
        
        // though not expected
        assertTrue(browser.getNext().equals("Hello"));
        assertTrue(browser.getPrevious().equals("Hello"));
        assertTrue(browser.getFirst().equals("Hello"));
        assertTrue(browser.getLast().equals("Hello"));
    }
    public void testMultiBrowsing() {      
        List list = new ArrayList();
        list.add("Hello");
        list.add("World");
        list.add("today");
        browser.setObjects(list);
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());
        
        assertTrue("Hello".equals(browser.getCurrentObject()));
        assertTrue(0 == browser.getCurrentIndex());
        assertTrue("1/3".equals(browser.getScrollIndexString()));
        
        assertTrue("World".equals(browser.getNext()));
        assertTrue(1 == browser.getCurrentIndex());
        assertTrue(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());
        assertTrue("2/3".equals(browser.getScrollIndexString()));
        
        assertTrue("today".equals(browser.getNext()));
        assertTrue(2 == browser.getCurrentIndex());
        assertFalse(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertFalse(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());
        assertTrue("3/3".equals(browser.getScrollIndexString()));
        
        assertTrue("World".equals(browser.getPrevious()));
        assertTrue(1 == browser.getCurrentIndex());
        assertTrue(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());
        
        assertTrue("Hello".equals(browser.getFirst()));
        assertTrue(0 == browser.getCurrentIndex());
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());
        
        assertTrue("today".equals(browser.getLast()));
        assertTrue(2 == browser.getCurrentIndex());
        assertFalse(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertFalse(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());
        
        assertTrue("World".equals(browser.getPrevious()));
        assertTrue(1 == browser.getCurrentIndex());
        assertTrue(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());
    }
    public void testStep10() {
        browser.setStep(10);
        assertTrue(browser.getCurrentIndex() == -1);
        assertTrue(browser.getCurrentObject() == null);
        
        java.util.List objects = new java.util.ArrayList();
        for (int i=1; i<106; i++) {
            objects.add(new Integer(i));
        }
        browser.setObjects(objects);
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());        
        assertTrue(browser.getCurrentIndex() == 0);
        assertTrue(browser.getCurrentObject().equals(new Integer(1)));
        
        assertTrue(browser.getNext().equals(new Integer(11)));
        assertTrue(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());          
        assertTrue(browser.getCurrentIndex() == 10);
        assertTrue(browser.getCurrentObject().equals(new Integer(11)));
        
        assertTrue(browser.getPrevious().equals(new Integer(1)));
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());          
        assertTrue(browser.getCurrentIndex() == 0);
        assertTrue(browser.getCurrentObject().equals(new Integer(1)));
        
        assertTrue(browser.getNext().equals(new Integer(11)));
        assertTrue(browser.getCurrentIndex() == 10);
        assertTrue(browser.getNext().equals(new Integer(21)));
        assertTrue(browser.getCurrentIndex() == 20);
        assertTrue(browser.getNext().equals(new Integer(31)));
        assertTrue(browser.getCurrentIndex() == 30);
        assertTrue(browser.getPrevious().equals(new Integer(21)));
        assertTrue(browser.getCurrentIndex() == 20);                
                
        assertTrue("beginning of last block", browser.getLast().equals(new Integer(101)));
        assertTrue(browser.getCurrentIndex() == 100);
        assertFalse("though index not on last element, there is no next block", browser.isScrollNextAllowed());
        assertFalse("no next block", browser.isScrollLastAllowed());
        
        assertTrue("first block", browser.getFirst().equals(new Integer(1)));
        assertTrue(browser.getCurrentIndex() == 0);
    }
    public void testStep3() {
        browser.setStep(3);
        assertTrue(browser.getCurrentIndex() == -1);
        assertTrue(browser.getCurrentObject() == null);
        
        java.util.List objects = new java.util.ArrayList();
        for (int i=1; i<101; i++) {
            objects.add(new Integer(i));
        }
        browser.setObjects(objects);
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());        
        assertTrue(browser.getCurrentIndex() == 0);
        assertTrue(browser.getCurrentObject().equals(new Integer(1)));
        
        assertTrue(browser.getNext().equals(new Integer(4)));
        assertTrue(browser.isScrollNextAllowed());
        assertTrue(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertTrue(browser.isScrollFirstAllowed());          
        assertTrue(browser.getCurrentIndex() == 3);
        assertTrue(browser.getCurrentObject().equals(new Integer(4)));
        
        assertTrue(browser.getPrevious().equals(new Integer(1)));
        assertTrue(browser.isScrollNextAllowed());
        assertFalse(browser.isScrollPreviousAllowed());
        assertTrue(browser.isScrollLastAllowed());
        assertFalse(browser.isScrollFirstAllowed());          
        assertTrue(browser.getCurrentIndex() == 0);
        assertTrue(browser.getCurrentObject().equals(new Integer(1)));
        
        assertTrue(browser.getNext().equals(new Integer(4)));
        assertTrue(browser.getCurrentIndex() == 3);
        assertTrue(browser.getNext().equals(new Integer(7)));
        assertTrue(browser.getCurrentIndex() == 6);
        assertTrue(browser.getNext().equals(new Integer(10)));
        assertTrue(browser.getCurrentIndex() == 9);
        assertTrue(browser.getPrevious().equals(new Integer(7)));
        assertTrue(browser.getCurrentIndex() == 6);                
                
        assertTrue("beginning of last block", browser.getLast().equals(new Integer(99)));
        assertTrue(browser.getCurrentIndex() == 98);
        assertFalse("though index not on last element, there is no next block", browser.isScrollNextAllowed());
        assertFalse("no next block", browser.isScrollLastAllowed());
        
        assertTrue("first block", browser.getFirst().equals(new Integer(1)));
        assertTrue(browser.getCurrentIndex() == 0);
    }
    public void testSetCurrentIndex() {
        browser.setStep(10);
        assertTrue(browser.getCurrentIndex() == -1);
        assertTrue(browser.getCurrentObject() == null);
        
        java.util.List objects = new java.util.ArrayList(100);
        for (int i=1; i<106; i++) {
            objects.add(new Integer(i));
        }
        browser.setObjects(objects);
        
        browser.setCurrentIndex(89);
        assertTrue(browser.getCurrentObject().equals(new Integer(90)));
        
//TODO what happens at #getNext/Previous() if step>1 (stay in step or next/previous from currentIndex?)
    }
}
