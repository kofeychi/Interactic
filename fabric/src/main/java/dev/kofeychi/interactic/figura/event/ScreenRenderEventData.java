package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;

@LuaWhitelist
@LuaTypeDoc(name = "ScreenRenderEventData", value = "screen_render_event")
public class ScreenRenderEventData {
    @LuaWhitelist
    @LuaFieldDoc("screen_render_event.position")
    public FiguraVec2 position;

    @LuaWhitelist
    @LuaFieldDoc("screen_render_event.amount")
    public double delta;

    @LuaWhitelist
    public FiguraVec2 getPosition() {
        return position;
    }

    @LuaWhitelist
    public double getDelta() {
        return delta;
    }

    public ScreenRenderEventData(InteractionScreen.RenderData event) {
        this.position = FiguraVec2.of(event.mouseX(), event.mouseY());
        this.delta = event.delta();
    }


    @Override
    public String toString() {
        return "ScreenRenderEventData{" +
                "position=" + position.__tostring() +
                ", delta=" + delta +
                '}';
    }
}
