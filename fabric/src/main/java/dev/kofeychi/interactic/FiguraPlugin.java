package dev.kofeychi.interactic;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.figura.api.ScreenAPI;
import dev.kofeychi.interactic.figura.event.*;
import dev.kofeychi.interactic.util.Classes;
import dev.kofeychi.interactic.util.Color;
import dev.kofeychi.interactic.util.ContiniousArrayList;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@FiguraAPIPlugin
@LuaWhitelist
public class FiguraPlugin implements FiguraAPI {
    public FiguraPlugin() {}

    public FiguraPlugin(Avatar avatar) {}

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
        try {
            var classes = Classes.getClasses("dev.kofeychi.interactic.figura",Interactic.class.getClassLoader());
            classes.add(FiguraPlugin.class);
            classes.add(Color.class);
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        var list = new ArrayList<Class<?>>();
        list.add(FiguraPlugin.class);
        return list;
    }

    @Override
    public Collection<Class<?>> getDocsClasses() {
        return List.of();
    }
}
