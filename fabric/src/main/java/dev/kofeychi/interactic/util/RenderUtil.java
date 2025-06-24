package dev.kofeychi.interactic.util;

import net.minecraft.client.render.RenderLayer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RenderUtil {
    public static Object getRenderLayer(String name) {
        try {
            var fields = RenderLayer.class.getDeclaredFields();
            List<String> strList = Arrays.stream(fields).map(Field::getName).toList();
            if (strList.contains(name)) {
                var field = RenderLayer.class.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(null);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Class<?> getRenderLayerType(String name) {
        try {
            var fields = RenderLayer.class.getDeclaredFields();
            List<String> strList = Arrays.stream(fields).map(Field::getName).toList();
            if (strList.contains(name)) {
                var field = RenderLayer.class.getDeclaredField(name);
                field.setAccessible(true);
                return field.getType();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
