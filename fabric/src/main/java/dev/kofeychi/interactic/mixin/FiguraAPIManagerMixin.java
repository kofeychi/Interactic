package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.figura.api.ScreenAPI;
import dev.kofeychi.interactic.figura.event.*;
import org.figuramc.figura.lua.FiguraAPIManager;
import org.figuramc.figura.lua.FiguraLuaRuntime;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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

        WHITELISTED_CLASSES.add(KeyPressEventData.class);
        WHITELISTED_CLASSES.add(KeyReleasedEventData.class);
        WHITELISTED_CLASSES.add(MouseDraggedEventData.class);
        WHITELISTED_CLASSES.add(MouseMovedEventData.class);
        WHITELISTED_CLASSES.add(MousePressEventData.class);
        WHITELISTED_CLASSES.add(MouseReleasedEventData.class);
        WHITELISTED_CLASSES.add(MouseScrollEventData.class);
        WHITELISTED_CLASSES.add(ScreenRenderEventData.class);

        API_GETTERS.put("screen", ScreenAPI::new);
    }
}
