package dev.kofeychi.interactic.figura.api.screen;

import dev.kofeychi.interactic.util.Conversion;
import net.minecraft.client.gui.DrawContext;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.joml.Vector2d;
import org.luaj.vm2.LuaFunction;

@LuaWhitelist
public class ButtonContainer {
    @LuaWhitelist
    public FiguraVec2 pos = FiguraVec2.of();
    @LuaWhitelist
    public FiguraVec2 scale = FiguraVec2.of();

    public LuaFunction onLeftClick;
    public LuaFunction onRightClick;
    public LuaFunction render;

    public ButtonContainer(FiguraVec2 pos, FiguraVec2 scale) {
        this.pos = pos;
        this.scale = scale;
    }
    public void render(DrawContext dc) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if(avatar.luaRuntime == null||render == null) {
            return;
        }
        avatar.run(render, avatar.tick, pos,scale);
    }
}
