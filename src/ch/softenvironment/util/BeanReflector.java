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
 * Reflection-Utility for JavaBeans.
 * Encapsulates an Object's property according to Java Beans Specification,
 * by means a Property is defined usually as follows, if MyObject has a Property "myProperty":
 *   -> MyObject#fieldMyProperty			// field/property itself
 *   -> MyObject#setMyProperty(Object any)	// the setter-Method
 *   -> MyObject#getMyProperty()			// the getter-Method
 *
 * @author: Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.5 $ $Date: 2004-05-20 15:16:27 $
 */
public class BeanReflector extends java.util.EventObject {
	private transient String property = null;

	public static final int NONE = 0;
	public static final int GETTER = 1;
	public static final int SETTER = 2;
	public static final int GETTER_AND_SETTER = GETTER + SETTER;
/**
 * ObjectChange constructor.
 * @param source Object to be changed
 * @param property property of interest of given source (starts with lowercase)
 *
 * Example:
 *  change = new ObjectProperty(Locale.getDefault, "language")
 *  if (change.getValue().equals("de")) {
 *    change.setValue("fr"); // switch default Language
 *  }
 */
public BeanReflector(Object source, String property) {
	super(source);
	this.property = property;
}
/**
 * Return the Field for given Property-Name.
 */
public java.lang.reflect.Field getField() {
	try {
		return getSource().getClass().getField("field" + getPropertyUpper());
	} catch(NoSuchFieldException e) {
		throw new DeveloperException(this, "getField()", "<" + getSource().getClass().getName() + "> must implement <" + "field" + getPropertyUpper() + ">");
	}
}
/**
 * Return the Getter Method for the property.
 */
private java.lang.reflect.Method getGetterMethod() throws NoSuchMethodException {
	Class parameterTypes[] = {};
 	return getSource().getClass().getMethod("get" + getPropertyUpper(), parameterTypes);
}
/**
 * Return the Property-Name.
 */
public String getProperty() {
	return property;
}
/**
 * Transform "myProperty" to "MyProperty".
 */
protected String getPropertyUpper() {
	return property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
}
/**
 * Return the setter-Method for given Object and Property.
 */
private java.lang.reflect.Method getSetterMethod() throws NoSuchMethodException {
	Class parameterTypes[] = { getType() };
	return getSource().getClass().getMethod("set" + getPropertyUpper(), parameterTypes);
}
/**
 * Return the Getter Method Name.
 */
public java.lang.Class getType() throws NoSuchMethodException {
	return getGetterMethod().getReturnType();
}
/**
 * Return the Source's Property-value
 * by calling the sources getter-Method.
 */
public Object getValue() throws IllegalAccessException, java.lang.reflect.InvocationTargetException {
	try {
		Object parameters[] = {};
 		return getGetterMethod().invoke(getSource(), parameters);
 	} catch(NoSuchMethodException e) {
		throw new DeveloperException(this, "getValue()", "<" + getSource().getClass().getName() + "> must implement <" + "get" + getPropertyUpper() + "()");
	}
}
/**
 * Return how the encapsulated Object implements the given property.
 */
public int hasProperty() {
	int implementationDegree = NONE;
	
	try {
		getGetterMethod();
		implementationDegree = GETTER;
	} catch (NoSuchMethodException e) {
		Tracer.getInstance().developerWarning(this, "hasProperty()", "NoSuchMethodException ignored: no public getter-Method for property <" + getProperty() + "> implemented");
	}

	try {
		getSetterMethod();
		implementationDegree = implementationDegree + SETTER;
	} catch (NoSuchMethodException e) {
		Tracer.getInstance().developerWarning(this, "hasProperty()", "NoSuchMethodException ignored: no public setter-Method for property <" + getProperty() + "> implemented");
	}

	return implementationDegree;
}
/**
 * Set the Property by its <b>Field</b> with given value => no change-event.
 * Suppose property-Field to be public.
 */
public void setField(Object value) {
	try {
		getField().set(getSource(), value);
	} catch(IllegalAccessException e) {
		throw new DeveloperException(this, "setField(Object)", "<" + getSource().getClass().getName() + "#field" + getPropertyUpper() + ">  must be PUBLIC!");
	}
}
/**
 * Set the Property by its <b>setter-Method</b> with given value.
 * The Setter-Method must be public.
 * For e.g.: source.setProperty(Object value)
 */
public void setValue(Object value) throws java.lang.reflect.InvocationTargetException, IllegalAccessException {
	try {
		Object args[] = { value };
		getSetterMethod().invoke(getSource(), args);
	} catch(NoSuchMethodException e) {
		throw new DeveloperException(this, "setValue()", "<" + getSource().getClass().getName() + "> must implement <" + "set" + getPropertyUpper() + "(<type>)");
	}
}

/**
 * Create an instance of the given Class by the default constructor.
 * MyObject object = (MyObject)BeanReflector.createInstance(MyObject.class);
 * @param target The Target-Object's Type
 */
public static java.lang.Object createInstance(java.lang.Class target) throws InstantiationException, java.lang.reflect.InvocationTargetException {
	try {
		Class types[] = {};
		Object args[] = {};
		java.lang.reflect.Constructor constructor = target.getConstructor(types);
		return constructor.newInstance(args);
	} catch(NoSuchMethodException e) {
		throw new DeveloperException(BeanReflector.class, "creatInstance(Class)", "Class <" + target + "> must implement: Constructor()!");
	} catch(IllegalAccessException e) {
		throw new DeveloperException(BeanReflector.class, "creatInstance(Class)", "Class <" + target + ".Constructor()> must be: PUBLIC!");
	}
}
}
