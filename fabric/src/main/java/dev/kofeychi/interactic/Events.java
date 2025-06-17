package dev.kofeychi.interactic;

import org.figuramc.figura.lua.api.event.LuaEvent;

public interface Events {
    LuaEvent InteracticAPI$SCREEN_TICK();
    LuaEvent InteracticAPI$SCREEN_RENDER();
    LuaEvent InteracticAPI$SCREEN_INIT();
    LuaEvent InteracticAPI$SCREEN_CLEAR();

    LuaEvent InteracticAPI$KEY_PRESS();
    LuaEvent InteracticAPI$KEY_RELEASE();

    LuaEvent InteracticAPI$MOUSE_DRAGGED();
    LuaEvent InteracticAPI$MOUSE_PRESS();
    LuaEvent InteracticAPI$MOUSE_RELEASE();
    LuaEvent InteracticAPI$MOUSE_SCROLLED();
    LuaEvent InteracticAPI$MOUSE_MOVED();
}
