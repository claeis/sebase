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
import java.util.HashSet;
import java.util.Set;

import ch.softenvironment.util.ListUtils;

import junit.framework.TestCase;
/**
 * TestCase for ListUtils.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2008-06-11 08:21:46 $
 */
public class ListUtilsTestCase extends TestCase {
    public static class ListObject {
    	protected Long id;
    	public Long getId() {
    		return id;
    	}
        public String getMyToString() {
            return "sample";
        }
    }
    public void testCreateList() {
        java.util.List list = ListUtils.createList("hello");
        assertTrue(list.get(0).equals("hello"));
    }
    public void testConvertToString() {
        Object obj = new ListObject();
        java.util.List list = new java.util.ArrayList();
        list.add(obj);        
        assertTrue("sample;".equals(ListUtils.convertToString(list, "myToString", ';')));
        
/*        
        list = new java.util.ArrayList();
        list.add(new ListUtilsTestCase());
        assertTrue((obj.toString() + "?").equals(ListUtils.convertToString(list, null, '?')));
*/
    }
    public void testCreateIntersection() {
        Set set0 = new HashSet(4);
        set0.add(new Long(12));
        set0.add(new Long(15));
        set0.add(new Long(-101));
        set0.add(new Long(45));
        Set set1 = new HashSet(4);
        set1.add(new Long(121));
        set1.add(new Long(15));
        set1.add(new Long(-102));
        set1.add(new Long(45));
        Set result = ListUtils.createIntersection(set0, set1);
        assertTrue("same type of elements", result.size() == 2);
        assertTrue(result.contains(new Long(15)));
        assertTrue(result.contains(new Long(45)));
        assertFalse(result.contains(new Long(12)));
        assertFalse(result.contains(new Long(-102)));
        
        set1 = new HashSet();
        set1.add(new Integer(121));
        set1.add(new Integer(15));
        set1.add(new Integer(-102));
        set1.add("45");
        result = ListUtils.createIntersection(set0, set1);
        assertTrue("different types", result.size() == 0);
    }
    public void testEliminateDuplicates() {   
    	try {
	    	java.util.List list = new java.util.ArrayList();
	    	ListUtils.eliminateDuplicates(list, "Id");
	    	assertTrue("empty", list.size() == 0);
	    	
	    	ListObject lo = new ListObject();
	    	lo.id = new Long(12);
	    	list.add(lo);
	    	ListUtils.eliminateDuplicates(list, "Id");
	    	assertTrue("no elimination", list.size() == 1);
	    	
	    	lo = new ListObject();
	    	lo.id = new Long(13);
	    	list.add(lo);
	    	ListUtils.eliminateDuplicates(list, "Id");
	    	assertTrue("no elimination", list.size() == 2);
	    	
	    	list.add(lo);
	    	ListUtils.eliminateDuplicates(list, "Id");
	    	assertTrue("real elimination", list.size() == 2);
    	} catch(Throwable ex) {
    		fail(ex.getLocalizedMessage());
    	}
    }
}
