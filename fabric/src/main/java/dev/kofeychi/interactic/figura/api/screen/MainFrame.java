package dev.kofeychi.interactic.figura.api.screen;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
public class MainFrame extends Frame {
    public void addFrame(Frame frame) {
        childFrames.add(frame);
    }
    public void render(DrawAPI drawapi, FiguraVec2 mouse) {
        childFrames.forEach(childFrame -> {
            childFrame.render(drawapi, mouse);
        });
    }
}
