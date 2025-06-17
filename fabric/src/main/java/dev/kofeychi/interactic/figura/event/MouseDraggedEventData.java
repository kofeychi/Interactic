package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MouseDraggedEventData", value = "mouse_drag_event")
public class MouseDraggedEventData {
    @LuaWhitelist
    @LuaFieldDoc("mouse_drag_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    @LuaFieldDoc("mouse_drag_event.delta")
    public FiguraVec2 delta;

    @LuaWhitelist
    @LuaFieldDoc("mouse_drag_event.button")
    public int button;

    @LuaWhitelist
    public FiguraVec2 getPosition() {
        return position;
    }

    @LuaWhitelist
    public FiguraVec2 getDelta() {
        return delta;
    }

    @LuaWhitelist
    public int getButton() {
        return button;
    }

    public MouseDraggedEventData(InteractionScreen.MouseDraggedData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
        this.button = event.button();
        this.delta = FiguraVec2.of(event.deltaX(), event.deltaY());
    }

    @Override
    public String toString() {
        return "MouseDraggedEventData{" +
                "position=" + position.__tostring() +
                ", button=" + button +
                ", delta=" + delta.__tostring() +
                '}';
    }
}