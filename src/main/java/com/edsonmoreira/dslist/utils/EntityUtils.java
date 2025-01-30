package com.edsonmoreira.dslist.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class EntityUtils {
    public static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(source.getClass().getDeclaredFields())
                .filter(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(source) == null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .map(Field::getName)
                .toArray(String[]::new);
    }
}
