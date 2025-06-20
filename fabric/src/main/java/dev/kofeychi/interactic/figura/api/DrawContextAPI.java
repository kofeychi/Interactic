package dev.kofeychi.interactic.figura.api;

import dev.kofeychi.interactic.figura.api.task.RenderAPIContainer;
import dev.kofeychi.interactic.figura.api.task.Renderable;
import dev.kofeychi.interactic.util.Color;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec2;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.math.vector.FiguraVec4;

@LuaWhitelist
@LuaTypeDoc(name = "DrawContext", value = "dctx")
public class DrawContextAPI extends Renderable {
    private final DrawContext ctx;

    public DrawContextAPI(DrawAPI parent) {
        ctx = parent.ctx;
    }

    private void applymatrices(){
        var peek = ctx.getMatrices().peek();
        peek.getPositionMatrix().set(pos.toMatrix4f());
        peek.getNormalMatrix().set(normal.toMatrix4f());
    }

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
    @LuaWhitelist
    public DrawContextAPI enablescissors(int x, int y, int x1, int y1) {
        if(!rendering) {return this;}
        ctx.enableScissor(x, y, x1, y1);
        return this;
    }
    @LuaWhitelist
    public DrawContextAPI enablescissors(FiguraVec2 vec1, FiguraVec2 vec2) {
        if(!rendering) {return this;}
        ctx.enableScissor((int) vec1.x, (int) vec1.y, (int) vec2.x, (int) vec2.y);
        return this;
    }
    @LuaWhitelist
    public DrawContextAPI disablescissors() {
        if(!rendering) {return this;}
        ctx.disableScissor();
        return this;
    }
    @LuaWhitelist
    public boolean scissorContains(int x,int y) {
        if(!rendering) {return false;}
        return ctx.scissorContains(x,y);
    }
    @LuaWhitelist
    public boolean scissorContains(FiguraVec2 vec) {
        if(!rendering) {return false;}
        return ctx.scissorContains((int) vec.x, (int) vec.y);
    }
    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {@LuaMethodOverload(
                    argumentTypes = {FiguraVec2.class,FiguraVec2.class,FiguraVec4.class},
                    argumentNames = {"val1","val2","val3"}
            ), @LuaMethodOverload(
                    argumentTypes = {FiguraVec2.class,FiguraVec2.class,Integer.class,FiguraVec4.class},
                    argumentNames = {"val1","val2","val3","val4"}
            ), @LuaMethodOverload(
                    argumentTypes = {Integer.class,Integer.class,Integer.class,Integer.class,FiguraVec4.class},
                    argumentNames = {"val1","val2","val3","val4","val5"}
            ), @LuaMethodOverload(
                    argumentTypes = {Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,FiguraVec4.class},
                    argumentNames = {"val1","val2","val3","val4","val5","val5"}
            )
            },
            value = "dctx.fill")
    public DrawContextAPI fill(
            Object val1,
            Object val2,
            Object val3,
            Object val4,
            Object val5,
            Object val6
    ) {
        if(!rendering) {return this;}
        if(
                val1 instanceof FiguraVec2 vec1 &&
                val2 instanceof FiguraVec2 vec2 &&
                val3 instanceof FiguraVec4 color
        ) {
            fill(vec1,vec2,0,color);
        } else if(
                val1 instanceof FiguraVec2 vec1 &&
                val2 instanceof FiguraVec2 vec2 &&
                val3 instanceof Integer z &&
                val4 instanceof FiguraVec4 color
        ) {
            fill(vec1,vec2,z,color);
        } else if(
                val1 instanceof Integer x1 &&
                val2 instanceof Integer y1 &&
                val3 instanceof Integer x2 &&
                val4 instanceof Integer y2 &&
                val5 instanceof FiguraVec4 color
        ) {
            fill(FiguraVec2.of(x1,y1),FiguraVec2.of(x2,y2),0,color);
        }
        else if(
                val1 instanceof Integer x1 &&
                val2 instanceof Integer y1 &&
                val3 instanceof Integer x2 &&
                val4 instanceof Integer y2 &&
                val5 instanceof Integer z &&
                val6 instanceof FiguraVec4 color
        ) {
            fill(FiguraVec2.of(x1,y1),FiguraVec2.of(x2,y2),z,color);
        }
        return this;
    }
    public void fill(FiguraVec2 vec1,FiguraVec2 vec2,int z,FiguraVec4 color) {
        applymatrices();
        ctx.fill((int) vec1.x, (int) vec1.y, (int) vec2.x, (int) vec2.y,z,toColor(color).getColor());
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
