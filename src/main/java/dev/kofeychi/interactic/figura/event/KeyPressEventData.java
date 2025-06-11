package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "KeyPressEventData", value = "key_press_event")
public class KeyPressEventData {
    @LuaWhitelist
    @LuaFieldDoc("key_press_event.keycode")
    public int keycode;

    @LuaWhitelist
    @LuaFieldDoc("key_press_event.scancode")
    public int scancode;

    @LuaWhitelist
    @LuaFieldDoc("key_press_event.modifiers")
    public int modifiers;

    public KeyPressEventData(InteractionScreen.KeyPressData event) {
        this.keycode = event.keyCode();
        this.scancode = event.scanCode();
        this.modifiers = event.modifiers();
    }

    @Override
    public String toString() {
        return "KeyPressEventData{" +
                "keycode=" + keycode +
                ", scancode=" + scancode +
                ", modifiers=" + modifiers +
                '}';
    }
}