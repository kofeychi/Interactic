package dev.kofeychi.interactic;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.api.event.LuaEvent;

import java.util.function.Function;

public class Interactic implements ModInitializer {

    @Override
    public void onInitialize() {
        FiguraPlugin.init();
    }

    public static void run(Function<Events, LuaEvent> event, Object... objects) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar == null||avatar.luaRuntime == null) {
            return;
        }
        LuaEvent Event = event.apply(((Events) avatar.luaRuntime.events));
        avatar.run(Event, avatar.tick,objects);
    }
    public static void run(Object runnable, Object... objects) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar == null||avatar.luaRuntime == null) {
            return;
        }
        avatar.run(runnable, avatar.tick,objects);
    }
    public static Identifier of(String id) {
        return Identifier.of("interactic", id);
    }
}
