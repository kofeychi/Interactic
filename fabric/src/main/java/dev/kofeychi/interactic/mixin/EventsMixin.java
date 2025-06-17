package dev.kofeychi.interactic.mixin;

import dev.kofeychi.interactic.Events;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.EventsAPI;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = EventsAPI.class, remap = false)
public class EventsMixin implements Events {
    @Shadow
    @Final
    private Map<String, LuaEvent> events;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.SCREEN_TICK")
    public LuaEvent Interactic$SCREEN_TICK;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.SCREEN_RENDER")
    public LuaEvent Interactic$SCREEN_RENDER;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.KEY_PRESS")
    public LuaEvent Interactic$KEY_PRESS;
    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.KEY_RELEASE")
    public LuaEvent Interactic$KEY_RELEASE;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.MOUSE_PRESS")
    public LuaEvent Interactic$MOUSE_PRESS;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.MOUSE_RELEASE")
    public LuaEvent Interactic$MOUSE_RELEASE;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.MOUSE_DRAGGED")
    public LuaEvent Interactic$MOUSE_DRAGGED;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.MOUSE_DRAGGED")
    public LuaEvent Interactic$MOUSE_SCROLLED;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.MOUSE_DRAGGED")
    public LuaEvent Interactic$MOUSE_MOVED;
    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.SCREEN_INIT")
    public LuaEvent Interactic$SCREEN_INIT;
    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.SCREEN_CLEAR")
    public LuaEvent Interactic$SCREEN_CLEAR;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void events(CallbackInfo ci) {
        Interactic$SCREEN_TICK = new LuaEvent();
        Interactic$KEY_PRESS = new LuaEvent();
        Interactic$MOUSE_PRESS = new LuaEvent();
        Interactic$KEY_RELEASE = new LuaEvent();
        Interactic$MOUSE_RELEASE = new LuaEvent();
        Interactic$MOUSE_DRAGGED = new LuaEvent();
        Interactic$MOUSE_SCROLLED = new LuaEvent();
        Interactic$MOUSE_MOVED = new LuaEvent();
        Interactic$SCREEN_RENDER = new LuaEvent();
        Interactic$SCREEN_INIT = new LuaEvent();
        Interactic$SCREEN_CLEAR = new LuaEvent();
        events.put("SCREEN_TICK", Interactic$SCREEN_TICK);
        events.put("KEY_PRESS", Interactic$KEY_PRESS);
        events.put("MOUSE_PRESS", Interactic$MOUSE_PRESS);
        events.put("KEY_RELEASE", Interactic$KEY_RELEASE);
        events.put("MOUSE_RELEASE", Interactic$MOUSE_RELEASE);
        events.put("MOUSE_DRAGGED", Interactic$MOUSE_DRAGGED);
        events.put("MOUSE_SCROLLED", Interactic$MOUSE_SCROLLED);
        events.put("MOUSE_MOVED", Interactic$MOUSE_MOVED);
        events.put("SCREEN_RENDER", Interactic$SCREEN_RENDER);
        events.put("SCREEN_INIT", Interactic$SCREEN_INIT);
        events.put("SCREEN_CLEAR", Interactic$SCREEN_CLEAR);
    }

    @Override
    public LuaEvent InteracticAPI$SCREEN_TICK() {
        return Interactic$SCREEN_TICK;
    }

    @Override
    public LuaEvent InteracticAPI$SCREEN_RENDER() {
        return Interactic$SCREEN_RENDER;
    }

    @Override
    public LuaEvent InteracticAPI$SCREEN_INIT() {
        return Interactic$SCREEN_INIT;
    }

    @Override
    public LuaEvent InteracticAPI$SCREEN_CLEAR() {
        return Interactic$SCREEN_CLEAR;
    }

    @Override
    public LuaEvent InteracticAPI$KEY_PRESS() {
        return Interactic$KEY_PRESS;
    }

    @Override
    public LuaEvent InteracticAPI$MOUSE_PRESS() {
        return Interactic$MOUSE_PRESS;
    }

    @Override
    public LuaEvent InteracticAPI$MOUSE_RELEASE() {
        return Interactic$MOUSE_RELEASE;
    }

    @Override
    public LuaEvent InteracticAPI$MOUSE_SCROLLED() {
        return Interactic$MOUSE_SCROLLED;
    }

    @Override
    public LuaEvent InteracticAPI$MOUSE_MOVED() {
        return Interactic$MOUSE_MOVED;
    }

    @Override
    public LuaEvent InteracticAPI$KEY_RELEASE() {
        return Interactic$KEY_RELEASE;
    }

    @Override
    public LuaEvent InteracticAPI$MOUSE_DRAGGED() {
        return Interactic$MOUSE_DRAGGED;
    }
}
