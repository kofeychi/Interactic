package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.figura.ScreenAPI;
import dev.kofeychi.interactic.figura.event.KeyPressEventData;
import dev.kofeychi.interactic.figura.event.MousePressEventData;
import dev.kofeychi.interactic.util.Classes;
import org.figuramc.figura.lua.FiguraAPIManager;
import org.figuramc.figura.lua.FiguraLuaRuntime;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Mixin(value = FiguraAPIManager.class, remap = false)
public class FiguraAPIManagerMixin {
    @Shadow
    @Final
    public static Set<Class<?>> WHITELISTED_CLASSES;

    @Shadow
    @Final
    public static Map<String, Function<FiguraLuaRuntime, Object>> API_GETTERS;

    static {
        WHITELISTED_CLASSES.add(ScreenAPI.class);
        List<Class> classes = null;
        try {
            classes = Classes.getClasses("dev.kofeychi.interactic.figura.event",null);
        } catch (Exception e) {
        }
        classes.forEach(clazz -> {
            WHITELISTED_CLASSES.add(clazz);
        });

        API_GETTERS.put("screen", ScreenAPI::new);
    }
}
