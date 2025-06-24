package dev.kofeychi.interactic.figura.api.screen;

import dev.kofeychi.interactic.Interactic;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.render.WindowFrameRenderer;
import dev.kofeychi.interactic.util.Conversion;
import dev.kofeychi.interactic.util.LuaUtils;
import net.minecraft.client.gui.DrawContext;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.joml.Vector2d;
import org.luaj.vm2.LuaFunction;

import java.util.Optional;

@LuaWhitelist
@LuaTypeDoc(name = "ButtonContainer",value = "btn")
public class ButtonContainer {
    @LuaWhitelist
    public FiguraVec2 pos = FiguraVec2.of();
    @LuaWhitelist
    public FiguraVec2 scale = FiguraVec2.of();

    public LuaFunction onLeftClick;
    public LuaFunction onRightClick;
    public Frame parentFrame;

    @LuaWhitelist
    public FiguraVec2 getScale() {
        return scale;
    }
    @LuaWhitelist
    public void setScale(FiguraVec2 scale) {
        this.scale = scale;
    }
    @LuaWhitelist
    public FiguraVec2 getPos() {
        return pos;
    }
    @LuaWhitelist
    public void setPos(FiguraVec2 pos) {
        this.pos = pos;
    }
    @LuaWhitelist
    public void setOnRightClick(LuaFunction onRightClick) {
        this.onRightClick = onRightClick;
    }
    @LuaWhitelist
    public void setOnLeftClick(LuaFunction onLeftClick) {
        this.onLeftClick = onLeftClick;
    }

    @LuaWhitelist
    public void setRender(LuaFunction render) {
        this.render = render;
    }

    public LuaFunction render;

    public ButtonContainer(FiguraVec2 pos, FiguraVec2 scale,Frame parentFrame) {
        this.pos = pos;
        this.scale = scale;
        this.parentFrame = parentFrame;
    }
    public void render(DrawAPI drawapi,FiguraVec2 mouse,double delta) {
        if(render != null) {
            Interactic.run(render,
                    (a) -> a.render,
                    pos, scale, drawapi, isOver(mouse,parentFrame));
        } else {
            WindowFrameRenderer.draw(drawapi.ctx, (int) (pos.x+parentFrame.pos().x), (int) (pos.y+parentFrame.pos().y), (int) scale.x, (int) scale.y, Optional.of(isOver(mouse,parentFrame)));
        }
    }
    public void onLeftClick() {
        if(isOver(LuaUtils.mouse(), parentFrame)&&onLeftClick!=null) {
            Interactic.run(onLeftClick,
                    (a) -> a.tick,
                    pos, scale, parentFrame,this,LuaUtils.mouse(),parentFrame.pos());
        }
    }
    public void onRightClick() {
        if(isOver(LuaUtils.mouse(), parentFrame)&&onRightClick!=null) {
            Interactic.run(onRightClick,
                    (a) -> a.tick,
                    pos, scale, parentFrame,this,LuaUtils.mouse(),parentFrame.pos());
        }
    }
    @LuaWhitelist
    public boolean isOver(FiguraVec2 mouse,Frame parent) {
        double x1 = pos.x()+parent.pos().x;
        double y1 = pos.y()+parent.pos().y;
        double x2 = pos.x()+parent.pos().x + scale.x();
        double y2 = pos.y()+parent.pos().y + scale.y();
        double x3 = mouse.x();
        double y3 = mouse.y();
        return x3 >= x1 && x3 <= x2 && y3 >= y1 && y3 <= y2;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
