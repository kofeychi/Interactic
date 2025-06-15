package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MouseScrolledEventData", value = "mouse_scroll_event")
public class MouseScrollEventData {
    @LuaWhitelist
    @LuaFieldDoc("mouse_scroll_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    @LuaFieldDoc("mouse_scroll_event.amount")
    public FiguraVec2 amount;

    public MouseScrollEventData(InteractionScreen.MouseScrolledData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
        this.amount = FiguraVec2.of(event.horizontalAmount(), event.verticalAmount());
    }

    @Override
    public String toString() {
        return "MouseScrolledEventData{" +
                "position=" + position.__tostring() +
                ", amount=" + amount.__tostring() +
                '}';
    }
}