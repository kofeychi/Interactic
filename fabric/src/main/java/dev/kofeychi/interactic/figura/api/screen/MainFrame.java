package dev.kofeychi.interactic.figura.api.screen;

import org.figuramc.figura.lua.LuaWhitelist;

@LuaWhitelist
public class MainFrame extends Frame {
    public void addFrame(Frame frame) {
        childFrames.add(frame);
    }
}
