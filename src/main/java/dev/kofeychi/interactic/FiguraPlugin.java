package dev.kofeychi.interactic;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import java.util.Collection;
import java.util.List;

@FiguraAPIPlugin
@LuaWhitelist
public class FiguraPlugin implements FiguraAPI {

    public FiguraPlugin(Avatar avatar) {
    }

    public static void init() {}

    @Override
    public FiguraAPI build(Avatar avatar) {
        return new FiguraPlugin(avatar);
    }

    @Override
    public String getName() {
        return "interactic";
    }

    @Override
    public Collection<Class<?>> getWhitelistedClasses() {
        return List.of(FiguraPlugin.class);
    }

    @Override
    public Collection<Class<?>> getDocsClasses() {
        return List.of();
    }
}
