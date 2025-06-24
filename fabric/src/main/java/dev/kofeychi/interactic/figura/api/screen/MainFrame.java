package dev.kofeychi.interactic.figura.api.screen;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MainFrame",value = "mframe")
public class MainFrame extends Frame {
    public MainFrame(FiguraVec2 pos, FiguraVec2 scale) {
        super(pos, scale,null);
    }
    public void render(DrawAPI drawapi, FiguraVec2 mouse,double delta) {
        childFrames.forEach(childFrame -> {
            childFrame.render(drawapi, mouse,delta);
        });
    }
    @LuaWhitelist
    public Frame getFrame(Frame frame) {
        return traverse().filter(f -> f == frame).findFirst().get();
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
