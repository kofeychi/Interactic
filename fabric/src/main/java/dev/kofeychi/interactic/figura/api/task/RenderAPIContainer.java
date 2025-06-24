package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.util.ClampedInt;
import dev.kofeychi.interactic.util.Color;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.matrix.FiguraMat3;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.math.vector.FiguraVec4;
import org.figuramc.figura.model.rendertasks.RenderTask;
import org.figuramc.figura.utils.LuaUtils;

@LuaWhitelist
@LuaTypeDoc(name = "RenderAPIContainedHowDidYouGetThisReportThisPlsIBegYou", value = "rapic")
public abstract class RenderAPIContainer extends Renderable{
    @LuaWhitelist
    public DrawAPI parent;
    public Avatar owner;
    @LuaWhitelist
    public String name = "NOT IMPLEMENTED";
    @LuaWhitelist
    public int light = 0;
    @LuaWhitelist
    public int overlay = 0;

    public static Color toColor(FiguraVec3 vec) {
        return toColor(FiguraVec4.of(
                vec.x,
                vec.y,
                vec.z,
                255
        ));
    }
    public static Color toColor(FiguraVec4 vec) {
        return Color.ofRGBA(
                (int) vec.x,
                (int) vec.y,
                (int) vec.z,
                (int) vec.w
        );
    }
    public static MatrixStack toStack(FiguraMat4 pos, FiguraMat3 normal) {
        var stack = new MatrixStack();
        stack.push();
        pos.copyDataTo(stack.peek().getPositionMatrix());
        normal.copyDataTo(stack.peek().getNormalMatrix());
        return stack;
    }

    public RenderAPIContainer(DrawAPI parent,String name) {
        this.parent = parent;
        this.owner = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        this.name = name;
    }

    @LuaWhitelist
    public FiguraVec2 getLight() {
        return FiguraVec2.of(LightmapTextureManager.getBlockLightCoordinates(light),LightmapTextureManager.getSkyLightCoordinates(light));
    }



    @LuaWhitelist
    public FiguraVec2 getOverlay() {
        return FiguraVec2.of(overlay & 0xFFFF, overlay >> 16);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = FiguraVec2.class,
                            argumentNames = "overlay"
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Integer.class, Integer.class},
                            argumentNames = {"whiteOverlay", "hurtOverlay"}
                    )
            },
            value = "rapic.set_overlay")
    public RenderAPIContainer setOverlay(Object whiteOverlay, Double hurtOverlay) {
        if (whiteOverlay == null) {
            overlay = 0;
            return this;
        }

        FiguraVec2 overlayVec = LuaUtils.parseVec2("setOverlay", whiteOverlay, hurtOverlay);
        overlay = OverlayTexture.packUv((int) overlayVec.x, (int) overlayVec.y);
        return this;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = {FiguraVec2.class},
                            argumentNames = "light"
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Integer.class, Integer.class},
                            argumentNames = {"blockLight", "skyLight"}
                    )
            },
            value = "rapic.set_light")
    public RenderAPIContainer setLight(Object blockLight, Double skyLight) {
        if (blockLight == null) {
            light = 0;
            return this;
        }

        FiguraVec2 lightVec = LuaUtils.parseVec2("setLight", blockLight, skyLight);
        light = LightmapTextureManager.pack((int) lightVec.x, (int) lightVec.y);
        return this;
    }


    public void render(){
        if(!shouldRenderPROT()){
            return;
        }
        var stack = toStack(pos,normal);
        stack.scale(16,16,16);
        render(stack,parent.ctx.getVertexConsumers());
        pos.reset();
        normal.reset();
    }

    protected abstract void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer);
    public boolean shouldRenderPROT(){
        return pos != null
                &&normal !=null
                &&parent != null
                &&shouldRender()
                &&rendering
                ;
    }
    public abstract boolean shouldRender();

    public String str(){
        return name+" ( "+this.getClass().getSimpleName()+" instance )";
    }

    @Override
    public String toString() {
        return str();
    }

    public RenderAPIContainer set(RenderAPIContainer container){
        this.owner = container.owner;
        this.name = container.name;
        this.light = container.light;
        this.overlay = container.overlay;
        this.normal = container.normal;
        this.parent = container.parent;
        this.rendering = container.rendering;
        this.pos = container.pos;
        this.opacity = container.opacity;
        return this;
    }

}
