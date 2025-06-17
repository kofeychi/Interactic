package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "MouseReleaseEventData", value = "mouse_release_event")
public class MouseReleasedEventData {
    @LuaWhitelist
    @LuaFieldDoc("mouse_release_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    public FiguraVec2 getPosition() {
        return position;
    }

    @LuaWhitelist
    public int getButton() {
        return button;
    }

    @LuaWhitelist
    @LuaFieldDoc("mouse_release_event.button")
    public int button;

    public MouseReleasedEventData(InteractionScreen.MouseReleasedData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
        this.button = event.button();
    }

    @Override
    public String toString() {
        return "MouseReleaseEventData{" +
                "position=" + position.__tostring() +
                ", button=" + button +
                '}';
    }
}