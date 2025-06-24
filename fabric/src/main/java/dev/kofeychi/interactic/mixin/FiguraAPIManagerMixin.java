package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.FiguraPlugin;
import dev.kofeychi.interactic.Interactic;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.figura.api.ScreenAPI;
import dev.kofeychi.interactic.figura.event.*;
import dev.kofeychi.interactic.screen.InteractionScreen;
import dev.kofeychi.interactic.util.Classes;
import dev.kofeychi.interactic.util.Color;
import org.figuramc.figura.lua.FiguraAPIManager;
import org.figuramc.figura.lua.FiguraLuaRuntime;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
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
        try {
            WHITELISTED_CLASSES.addAll(new FiguraPlugin().getWhitelistedClasses());
        } catch (Exception e) {
            e.printStackTrace();
        }

        API_GETTERS.put("screen", ScreenAPI::new);
    }
}
