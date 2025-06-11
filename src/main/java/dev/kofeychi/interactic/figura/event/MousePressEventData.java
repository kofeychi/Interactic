package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MousePressEventData", value = "mouse_press_event")
public class MousePressEventData {
    @LuaWhitelist
    @LuaFieldDoc("mouse_press_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    @LuaFieldDoc("mouse_press_event.button")
    public int button;

    public MousePressEventData(InteractionScreen.MousePressData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
        this.button = event.button();
    }

    @Override
    public String toString() {
        return "MousePressEventData{" +
                "position=" + position.__tostring() +
                ", button=" + button +
                '}';
    }
}