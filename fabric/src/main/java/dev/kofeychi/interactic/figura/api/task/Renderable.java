package dev.kofeychi.interactic.figura.api.task;

import dev.kofeychi.interactic.util.ClampedInt;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.matrix.FiguraMat3;
import org.figuramc.figura.math.matrix.FiguraMat4;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.utils.LuaUtils;

@LuaWhitelist
@LuaTypeDoc(name = "RenderableHowDidYouGetThisReportThisPlsIBegYou", value = "renderable")
public class Renderable {
    @LuaWhitelist
    public boolean rendering = true;
    @LuaWhitelist
    public ClampedInt opacity = new ClampedInt(0,100);
    @LuaWhitelist
    public FiguraMat4 pos = new FiguraMat4();
    @LuaWhitelist
    public FiguraMat3 normal = new FiguraMat3();

    @LuaWhitelist
    public boolean isRendering() {
        return rendering;
    }
    public void verify() {
        if(opacity.get() <= 0) {
            rendering = false; // save up time so dont friggin render it
        }
    }
    @LuaWhitelist
    public Renderable setRendering(boolean rendering) {
        this.rendering = rendering;
        return this;
    }

    @LuaWhitelist
    public Renderable setOpacity(int opacity) {
        this.opacity.set(opacity);
        verify();
        return this;
    }
    @LuaWhitelist
    public int getOpacity() {
        return opacity.get();
    }

    @LuaWhitelist
    public FiguraMat3 getNormalMat() {
        return normal;
    }

    @LuaWhitelist
    public Renderable setNormalMat(FiguraMat3 normal) {
        this.normal = normal;
        return this;
    }

    @LuaWhitelist
    public FiguraMat4 getPosMat() {
        return pos;
    }

    @LuaWhitelist
    public Renderable setPosMat(FiguraMat4 pos) {
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
            value = "renderable.set_scale"
    )
    public Renderable setScale(Object x, Double y, Double z) {
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
            value = "renderable.set_rot"
    )
    public Renderable setRot(Object x, Double y, Double z) {
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
            value = "renderable.set_pos"
    )
    public Renderable setPos(Object x, Double y, Double z) {
        FiguraVec3 vec = LuaUtils.parseOneArgVec("setPos", x, y, z, 1d);
        pos.translate(vec);
        return this;
    }
}
