package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MouseMovedEventData", value = "mouse_moved_event")
public class MouseMovedEventData {
    @LuaWhitelist
    @LuaFieldDoc("mouse_moved_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    public FiguraVec2 getPosition() {
        return position;
    }

    public MouseMovedEventData(InteractionScreen.MouseMovedData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
    }

    @Override
    public String toString() {
        return "MouseMovedEventData{" +
                "position=" + position.__tostring() +
                '}';
    }
}