package com.git.cs309.server.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ClassUtils {
	
	private static final class ObjectAnnotationPair {
		private final Object object;
		private final Class<? extends Annotation> annotation;
		
		public ObjectAnnotationPair(final Object object, final Class<? extends Annotation> annotation) {
			this.object = object;
			this.annotation = annotation;
		}
		
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof ObjectAnnotationPair))
				return false;
			ObjectAnnotationPair pair = (ObjectAnnotationPair) other;
			return pair.object.equals(object) && pair.annotation.equals(annotation);
		}
	}
	
	private static final HashMap<ObjectAnnotationPair, List<Method>> METHOD_MAP = new HashMap<>();
	
	public static List<Method> getMethodsAnnotatedWith(final Object object, final Class<? extends Annotation> annotation) {
		ObjectAnnotationPair thisPair = new ObjectAnnotationPair(object, annotation);
		if (METHOD_MAP.containsKey(thisPair)) {
			return METHOD_MAP.get(thisPair);
		}
	    final List<Method> methods = new ArrayList<>();
	    Class<?> _class = object.getClass();
	    while (_class != Object.class) {
	    	final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(_class.getDeclaredMethods()));       
	        for (final Method method : allMethods) {
	            if (method.isAnnotationPresent(annotation)) {
	                methods.add(method);
	            }
	        }
	        _class = _class.getSuperclass();
	    }
	    METHOD_MAP.put(thisPair, methods);
	    return methods;
	}
	
	public static void invokeMethodsAnnotatedWith(final Object object, final Class<? extends Annotation> annotation) {
		for (Method method : ClassUtils.getMethodsAnnotatedWith(object, annotation)) {
			try {
				method.invoke(object, new Object[] {});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
