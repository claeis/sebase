package ch.softenvironment.util.test;

import java.util.Date;
import java.util.Locale;
import ch.softenvironment.util.BeanReflector;

/**
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-08-26 10:16:19 $
 */
public class BeanReflectorTestCase extends junit.framework.TestCase {
	private TestBean bean = null;
	public String fieldTest = null;
	// Internal 
	public class TestBean {
	    public String fieldTest = null;
	    private String fieldText = null;
	    private Double dVal = null;
	    private Long lVal = null;
	    private Integer iVal = null;
	    private Boolean bVal = null;
	    private java.util.Date date = null;
	    
	    /**
	     * @return Returns the dVal.
	     */
	    public Double getDVal() {
	        return dVal;
	    }
	    /**
	     * @param val The dVal to set.
	     */
	    public void setDVal(Double val) {
	        dVal = val;
	    }
	    /**
	     * @return Returns the iVal.
	     */
	    public Integer getIVal() {
	        return iVal;
	    }
	    /**
	     * @param val The iVal to set.
	     */
	    public void setIVal(Integer val) {
	        iVal = val;
	    }
	    /**
	     * @return Returns the lVal.
	     */
	    public Long getLVal() {
	        return lVal;
	    }
	    /**
	     * @param val The lVal to set.
	     */
	    public void setLVal(Long val) {
	        lVal = val;
	    }
	    /**
	     * @return Returns the text.
	     */
	    public String getText() {
	        return fieldText;
	    }
	    /**
	     * @param text The text to set.
	     */
	    public void setText(String text) {
	        this.fieldText = text;
	    }
	    /**
	     * @return Returns the date.
	     */
	    public java.util.Date getDate() {
	        return date;
	    }
	    /**
	     * @param date The date to set.
	     */
	    public void setDate(java.util.Date date) {
	        this.date = date;
	    }
	    /**
	     * @return Returns the bVal.
	     */
	    public Boolean getBVal() {
	        return bVal;
	    }
	    /**
	     * @param val The bVal to set.
	     */
	    public void setBVal(Boolean val) {
	        bVal = val;
	    }
	}
/**
 * StringUtilsTestCase constructor comment.
 * @param name java.lang.String
 */
public BeanReflectorTestCase(String name) {
	super(name);
}
/**
 * Overwrites.
 */
protected void setUp() {
	bean = new TestBean();
	bean.setText("Hello");
	bean.setDVal(new Double(3.2));
	bean.setLVal(new Long(1989));
	bean.setIVal(new Integer(38));
	bean.setBVal(new Boolean(true));
	bean.setDate(new Date());
}
public void testValue() throws Throwable {
    BeanReflector br = new BeanReflector(bean, "text");
    String value = "Bye";
    br.setValue(value);
	assertTrue("BeanReflector.getValue(String)", br.getValue().equals(value));
	assertTrue("BeanReflector.getValue(String)", br.getValue() == value);
}
public void testSetPublicField() throws Throwable {
	String msg = "TEST";
	BeanReflector br = new BeanReflector(bean, "test");
	br.setField(msg);
	assertTrue("BeanReflector->setField", bean.fieldTest.equals(msg));
	assertTrue("BeanReflector->getField", br.getField().get(bean).equals(msg));
	
}
public void testHasProperty() throws Throwable {
    BeanReflector br = new BeanReflector(bean, "iVal");
	assertTrue("BeanReflector", br.hasProperty()==BeanReflector.GETTER_AND_SETTER);
	
	br = new BeanReflector(Locale.getDefault(), "language");
	assertTrue("BeanReflector", br.hasProperty()==BeanReflector.GETTER);
}
public void testCloneValue() throws Throwable {
    BeanReflector br = new BeanReflector(bean, "text");
    Object clonedValue = br.cloneValue();
	assertTrue("BeanReflector->String-Pointer not equal", bean.getText() != clonedValue);
	assertTrue("BeanReflector->String-Contents is equal", bean.getText().equals(clonedValue));
	
	br = new BeanReflector(bean, "dVal");
    clonedValue = br.cloneValue();
    assertTrue("BeanReflector->Double-Pointer not equal", bean.getDVal() != clonedValue);
	assertTrue("BeanReflector->Double-Contents is equal", bean.getDVal().equals(clonedValue));
	
	br = new BeanReflector(bean, "iVal");
    clonedValue = br.cloneValue();
    assertTrue("BeanReflector->Integer-Pointer not equal", bean.getIVal() != clonedValue);
	assertTrue("BeanReflector->Integer-Contents is equal", bean.getIVal().equals(clonedValue));
	
	br = new BeanReflector(bean, "lVal");
    clonedValue = br.cloneValue();
    assertTrue("BeanReflector->Long-Pointer not equal", bean.getLVal() != clonedValue);
	assertTrue("BeanReflector->Long-Contents is equal", bean.getLVal().equals(clonedValue));
	
	br = new BeanReflector(bean, "bVal");
    clonedValue = br.cloneValue();
    assertTrue("BeanReflector->Boolean-Contents is equal", bean.getBVal().equals(clonedValue));
    
	br = new BeanReflector(bean, "date");
    clonedValue = br.cloneValue();
    assertTrue("BeanReflector->Date-Pointer not equal", bean.getDate() != clonedValue);
	assertTrue("BeanReflector->Date-Contents is equal", bean.getDate().equals(clonedValue));
}
}

