package com.example.demo.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import static org.springframework.beans.BeanUtils.copyProperties;

public class SpringBeanUtil {
	
	//constructor that takes the data source in and data target
	//give source and it becomes data target
	public static void mergeNotNull(Object source, Object target) {
		copyProperties(source,target,getNullPropertyName(source));
	}
	
	
	private static String[] getNullPropertyName(Object source) {
		final BeanWrapper wrappedSourceObject = new BeanWrapperImpl(source);
		//loop through our data that gets passed
		Set<String> propertyNames = new HashSet<>();
		for(PropertyDescriptor propDesc : wrappedSourceObject.getPropertyDescriptors()) {
			if(wrappedSourceObject.getPropertyValue(propDesc.getName())==null)
				propertyNames.add(propDesc.getName());
			
		}return propertyNames.toArray(new String[propertyNames.size()]);
	}
	//this just checks our object is not null during merge and prevents spring from breaking

}