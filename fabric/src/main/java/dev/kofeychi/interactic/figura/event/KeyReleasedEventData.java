package dev.kofeychi.interactic.figura.event;

import dev.kofeychi.interactic.screen.InteractionScreen;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "KeyReleasedEventData", value = "key_released_event")
public class KeyReleasedEventData {
    @LuaWhitelist
    @LuaFieldDoc("key_released_event.keycode")
    public int keycode;

    @LuaWhitelist
    @LuaFieldDoc("key_released_event.scancode")
    public int scancode;

    @LuaWhitelist
    @LuaFieldDoc("key_released_event.modifiers")
    public int modifiers;

    public KeyReleasedEventData(InteractionScreen.KeyReleasedData event) {
        this.keycode = event.keyCode();
        this.scancode = event.scanCode();
        this.modifiers = event.modifiers();
    }

    @Override
    public String toString() {
        return "KeyReleasedEventData{" +
                "keycode=" + keycode +
                ", scancode=" + scancode +
                ", modifiers=" + modifiers +
                '}';
    }

    @LuaWhitelist
    public int getKeycode() {
        return keycode;
    }

    @LuaWhitelist
    public int getScancode() {
        return scancode;
    }

    @LuaWhitelist
    public int getModifiers() {
        return modifiers;
    }
}