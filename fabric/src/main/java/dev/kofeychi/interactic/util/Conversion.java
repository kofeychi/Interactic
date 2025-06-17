package dev.kofeychi.interactic.util;

import org.figuramc.figura.math.vector.FiguraVec2;
import org.joml.Vector2d;
import org.luaj.vm2.LuaNumber;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class Conversion {
    public static FiguraVec2 toFiguraVec2(Vector2d vec) {
        return FiguraVec2.of(vec.x(), vec.y());
    }
}
