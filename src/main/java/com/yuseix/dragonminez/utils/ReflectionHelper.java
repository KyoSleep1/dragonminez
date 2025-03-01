package com.yuseix.dragonminez.utils;

import java.lang.reflect.Field;

public class ReflectionHelper {
	public static <T> T getPrivateField(Object instance, String fieldName, Class<T> fieldType) {
		try {
			Field field = instance.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return fieldType.cast(field.get(instance));
		} catch (Exception e) {
			throw new RuntimeException("Error accessing field: " + fieldName, e);
		}
	}

	public static void setPrivateField(Object instance, String fieldName, Object value) {
		try {
			Field field = instance.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(instance, value);
		} catch (Exception e) {
			throw new RuntimeException("Error setting field: " + fieldName, e);
		}
	}
}

