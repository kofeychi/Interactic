package dev.kofeychi.interactic.figura.api;

import dev.kofeychi.interactic.figura.api.task.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.utils.TextUtils;

@LuaWhitelist
@LuaTypeDoc(name = "DrawAPI", value = "draw")
public class DrawAPI {
    public final Avatar owner;
    public final DrawContext ctx;
    public final RenderTickCounter counter;
    public TaskAPI task;
    public DrawContextAPI drawContextAPI;

    public DrawAPI(DrawContext context, RenderTickCounter counter) {
        ctx = context;
        this.counter = counter;
        this.owner = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        drawContextAPI = new DrawContextAPI(this);
    }
    @LuaWhitelist
    public float getLastFrameDuration() {
        return counter.getLastFrameDuration();
    }
    @LuaWhitelist
    public float getTickDelta(boolean bl) {
        return counter.getTickDelta(bl);
    }
    @LuaWhitelist
    public float getLastDuration() {
        return counter.getLastDuration();
    }

    @LuaWhitelist
    public TaskAPI getTaskAPI() {
        return task;
    }
    @LuaWhitelist
    public DrawContextAPI getDrawContextAPI() {
        return drawContextAPI;
    }

    @Override
    public String toString() {
        return "DrawAPI";
    }
}
