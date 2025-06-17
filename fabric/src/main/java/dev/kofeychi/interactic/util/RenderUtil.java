package dev.kofeychi.interactic.util;

import net.minecraft.client.render.RenderLayer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class RenderUtil {
    public static RenderLayer getRenderLayer(String name) {
        try {
            var fields = RenderLayer.class.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            if (fieldList.contains(name)) {
                var field = RenderLayer.class.getDeclaredField(name);
                field.setAccessible(true);
                return (RenderLayer) field.get(null);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
