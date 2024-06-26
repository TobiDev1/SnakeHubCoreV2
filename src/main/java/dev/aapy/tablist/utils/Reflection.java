package dev.aapy.tablist.utils;


import java.lang.reflect.Field;

public class Reflection {

    public static Field setAccessibleAndGet(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field;
    }
}
