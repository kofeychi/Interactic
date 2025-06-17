package dev.kofeychi.interactic.figura.api.screen;

import org.figuramc.figura.lua.LuaWhitelist;
import org.luaj.vm2.LuaFunction;

import java.util.ArrayList;
import java.util.stream.Stream;

@LuaWhitelist
public class Frame {
    public ArrayList<Frame> childFrames = new ArrayList<>();
    public ArrayList<ButtonContainer> buttons = new ArrayList<>();

    public LuaFunction onLeftClick;
    public LuaFunction onRightClick;

    public Stream<Frame> traverse(){
        return Stream.concat(Stream.of(this), childFrames.stream().flatMap(Frame::traverse));
    }
}
