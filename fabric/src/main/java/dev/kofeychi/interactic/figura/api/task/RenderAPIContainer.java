package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.figura.api.DrawAPI;
import dev.kofeychi.interactic.util.ClampedInt;
import dev.kofeychi.interactic.util.Color;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.math.vector.FiguraVec4;
import org.figuramc.figura.model.rendertasks.RenderTask;
import org.figuramc.figura.utils.LuaUtils;

@LuaWhitelist
@LuaTypeDoc(name = "RenderAPIContainedHowDidYouGetThisReportThisPlsIBegYou", value = "rapic")
public abstract class RenderAPIContainer {
    public final DrawAPI parent;
    public boolean rendering = true;
    public ClampedInt opacity = new ClampedInt(0,100);
    public Avatar owner;
    public FiguraMat4 pos = new FiguraMat4();

    public FiguraMat4 normal = new FiguraMat4();

    public int light = 0;
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
    public static MatrixStack toStack(FiguraMat4 pos,FiguraMat4 normal) {
        var stack = new MatrixStack();
        stack.push();
        stack.peek().getPositionMatrix().set(pos.toMatrix4f());
        stack.peek().getNormalMatrix().set(normal.toMatrix4f());
        return stack;
    }

    public RenderAPIContainer(DrawAPI parent) {
        this.parent = parent;
        this.owner = parent.owner;
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

    @LuaWhitelist
    public RenderAPIContainer setOpacity(int opacity) {
        this.opacity.set(opacity);
        verify();
        return this;
    }
    @LuaWhitelist
    public int getOpacity() {
        return opacity.get();
    }
    @LuaWhitelist
    public RenderAPIContainer setRendering(boolean rendering) {
        this.rendering = rendering;
        return this;
    }
    @LuaWhitelist
    public boolean isRendering() {
        return rendering;
    }
    private void verify() {
        if(opacity.get() <= 0) {
            rendering = false; // save up time so dont friggin render it
        }
    }

    public void render(){
        render(toStack(pos,normal),parent.ctx.getVertexConsumers());
    }

    protected abstract void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer);

    @LuaWhitelist
    public FiguraMat4 getNormalMat() {
        return normal;
    }

    @LuaWhitelist
    public RenderAPIContainer setNormalMat(FiguraMat4 normal) {
        this.normal = normal;
        return this;
    }

    @LuaWhitelist
    public FiguraMat4 getPosMat() {
        return pos;
    }

    @LuaWhitelist
    public RenderAPIContainer setPosMat(FiguraMat4 pos) {
        this.pos = pos;
        return this;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "scale"
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"}
                    )
            },
            value = "rapic.set_scale"
    )
    public RenderAPIContainer setScale(Object x, Double y, Double z) {
        FiguraVec3 vec = LuaUtils.parseOneArgVec("setScale", x, y, z, 1d);
        pos.scale(vec);
        return this;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "rot"
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"}
                    )
            },
            value = "rapic.set_rot"
    )
    public RenderAPIContainer setRot(Object x, Double y, Double z) {
        FiguraVec3 vec = LuaUtils.parseOneArgVec("setRot", x, y, z, 1d);
        pos.rotate(vec.x, vec.y, vec.z);
        return this;
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "rot"
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"}
                    )
            },
            value = "rapic.set_pos"
    )
    public RenderAPIContainer setPos(Object x, Double y, Double z) {
        FiguraVec3 vec = LuaUtils.parseOneArgVec("setPos", x, y, z, 1d);
        pos.translate(vec);
        return this;
    }
}
