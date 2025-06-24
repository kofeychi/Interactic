package dev.kofeychi.interactic.figura.api.screen;

import com.mojang.logging.LogUtils;
import dev.kofeychi.interactic.Interactic;
import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.render.WindowFrameRenderer;
import dev.kofeychi.interactic.util.LuaUtils;
import net.minecraft.client.MinecraftClient;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.luaj.vm2.LuaFunction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@LuaWhitelist
@LuaTypeDoc(name = "Frame",value = "frame")
public class Frame {
    @LuaWhitelist
    public Frame getParent() {
        return parent;
    }

    public Frame parent;
    @LuaWhitelist
    public FiguraVec2 pos = FiguraVec2.of();
    @LuaWhitelist
    public FiguraVec2 scale = FiguraVec2.of();

    public Stream<Frame> traverse() {
        return Stream.concat(Stream.of(this),this.childFrames.stream().flatMap(Frame::traverse));
    }
    public ArrayList<Frame> childFrames = new ArrayList<>();
    public ArrayList<ButtonContainer> buttons = new ArrayList<>();

    public LuaFunction onLeftClick;
    public LuaFunction onRightClick;
    public LuaFunction render;

    public Frame(FiguraVec2 pos, FiguraVec2 scale,Frame parent) {
        this.pos = pos;
        this.scale = scale;
        this.parent = parent;
    }
    public FiguraVec2 pos(){
        var pos = getPos();
        if(parent != null){
            pos = pos.plus(parent.pos());
        }
        return pos;
    }

    @LuaWhitelist
    public FiguraVec2 getPos() {
        return pos;
    }
    @LuaWhitelist
    public void setRender(LuaFunction render) {
        this.render = render;
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
    public void setPos(FiguraVec2 pos) {
        this.pos = pos;
    }
    @LuaWhitelist
    public FiguraVec2 getScale() {
        return scale;
    }
    @LuaWhitelist
    public void setScale(FiguraVec2 scale) {
        this.scale = scale;
    }
    public boolean click = false;
    public void render(DrawAPI drawapi, FiguraVec2 mouse,double delta) {
        if(render != null) {
            Interactic.run(render,
                    (a) -> a.render,
                    pos(), scale, drawapi, isOver(mouse));
        } else {
            WindowFrameRenderer.draw(drawapi.ctx, (int) pos().x, (int) pos().y, (int) scale.x, (int) scale.y, Optional.of(isOver(mouse)));
        }
        childFrames.forEach(childFrame -> {
            childFrame.render(drawapi, mouse,delta);
        });
        buttons.forEach(button -> {
            button.render(drawapi, mouse,delta);
        });
    }
    public void onLeftClick() {
        if(isOver(LuaUtils.mouse())&&onLeftClick!=null) {
            Interactic.run(onLeftClick,
                    (a) -> a.tick,
                    pos(), scale, this,LuaUtils.mouse(),pos());
        }
        childFrames.forEach(Frame::onLeftClick);
        buttons.forEach(ButtonContainer::onLeftClick);
    }
    public void onRightClick() {
        if(isOver(LuaUtils.mouse())&&onRightClick!=null) {
            Interactic.run(onRightClick,
                    (a) -> a.tick,
                    pos(), scale, this,LuaUtils.mouse(),pos());
        }
        childFrames.forEach(Frame::onRightClick);
        buttons.forEach(ButtonContainer::onRightClick);
    }
    @LuaWhitelist
    public boolean isOver(FiguraVec2 mouse) {
        double x1 = pos().x();
        double y1 = pos().y();
        double x2 = pos().x() + scale.x();
        double y2 = pos().y() + scale.y();
        double x3 = mouse.x();
        double y3 = mouse.y();
        return x3 >= x1 && x3 <= x2 && y3 >= y1 && y3 <= y2;
    }
    @LuaWhitelist
    public Frame addButton(ButtonContainer button) {
        buttons.add(button);
        return this;
    }
    @LuaWhitelist
    public ButtonContainer newButton(FiguraVec2 pos,FiguraVec2 scale,Frame frame) {
        return new ButtonContainer(pos,scale,frame);
    }
    @LuaWhitelist
    public Frame newFrame(FiguraVec2 pos, FiguraVec2 scale) {
        return new Frame(pos,scale,null);
    }
    @LuaWhitelist
    public Frame frame(FiguraVec2 pos, FiguraVec2 scale) {
        var frame = new Frame(pos,scale,this);
        addFrame(frame);
        return frame;
    }
    @LuaWhitelist
    public ButtonContainer button(FiguraVec2 pos, FiguraVec2 scale) {
        var button = new ButtonContainer(pos,scale,this);
        addButton(button);
        return button;
    }
    @LuaWhitelist
    public void addFrame(Frame frame) {
        childFrames.add(frame);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
