package dev.kofeychi.interactic.figura.api.screen;

import dev.kofeychi.interactic.Interactic;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.render.WindowFrameRenderer;
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
    public void render(DrawAPI drawapi,FiguraVec2 mouse) {
        if(render != null) {
            Interactic.run(render, pos, scale, drawapi, isOver(mouse));
        } else {
            WindowFrameRenderer.draw(drawapi.ctx, (int) pos.x, (int) pos.y, (int) scale.x, (int) scale.y);
        }
    }
    @LuaWhitelist
    public boolean isOver(FiguraVec2 mouse) {
        double x1 = pos.x();
        double y1 = pos.y();
        double x2 = pos.x() + scale.x();
        double y2 = pos.y() + scale.y();
        double x3 = mouse.x();
        double y3 = mouse.y();
        return x3 >= x1 && x3 <= x2 && y3 >= y1 && y3 <= y2;
    }
}
