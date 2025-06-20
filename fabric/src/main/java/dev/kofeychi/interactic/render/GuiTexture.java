package dev.kofeychi.interactic.render;

import dev.kofeychi.interactic.Interactic;
import dev.kofeychi.interactic.util.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import org.joml.Vector2i;

import static dev.kofeychi.interactic.render.TextureIds.TEXTURE_SHEET;

public enum GuiTexture {
    GUI_CORNER_LEFT_UP(0,0,3,3,TEXTURE_SHEET),
    GUI_CORNER_RIGHT_UP(6,0,3,3,TEXTURE_SHEET),

    GUI_CORNER_LEFT_DOWN(0,6,3,3,TEXTURE_SHEET),
    GUI_CORNER_RIGHT_DOWN(6,6,3,3,TEXTURE_SHEET),

    GUI_PANEL_UP(4,0,1,3,TEXTURE_SHEET),
    GUI_PANEL_DOWN(4,6,1,3,TEXTURE_SHEET),

    GUI_PANEL_LEFT(0,4,3,1,TEXTURE_SHEET),
    GUI_PANEL_RIGHT(6,4,3,1,TEXTURE_SHEET),

    GUI_INTERSECTION_LEFT(10,0,2,3,TEXTURE_SHEET),
    GUI_INTERSECTION_RIGHT(15,6,2,3,TEXTURE_SHEET),

    GUI_INTERSECTION_UP(15,1,3,2,TEXTURE_SHEET),
    GUI_INTERSECTION_DOWN(9,6,3,2,TEXTURE_SHEET),

    GUI_INTERSECTION_PANEL_LEFT(10,4,3,1,TEXTURE_SHEET),
    GUI_INTERSECTION_PANEL_RIGHT(14,4,3,1,TEXTURE_SHEET),

    GUI_INTERSECTION_PANEL_UP(13,1,1,3,TEXTURE_SHEET),
    GUI_INTERSECTION_PANEL_DOWN(13,5,1,3,TEXTURE_SHEET),

    GUI_HIDE_BUTTON(18,0,9,9,TEXTURE_SHEET),
    GUI_DEL_BUTTON(27,0,9,9,TEXTURE_SHEET),

    GUI_PANEL_SINGLE(4,4,1,1, Interactic.of("window")),

    GUI_TRANSPARENT(255,255,1,1, Interactic.of("window"));


    public final int x;
    public final int y;
    public final int w;
    public final int h;
    public final Identifier texture;
    GuiTexture(int x, int y, int width, int height, Identifier tex){
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.texture = tex;
    }

    public static void draw(DrawContext context, int X, int Y, GuiTexture texture){
        context.drawTexture(texture.texture, X, Y, texture.x, texture.y,texture.w, texture.h);
    }
    public static void drawShiftable(DrawContext context, int X, int Y, GuiTexture texture, Vector2i shiftable){
        context.drawTexture(texture.texture, X, Y, texture.x+shiftable.x, texture.y+shiftable.y,texture.w, texture.h);
    }
    public static Color getSingleColor(GuiTexture texture){
        return getColor(texture.x, texture.y, texture.texture);
    }
    public static Color getColor(int X, int Y, Identifier texture){
        NativeImage image = MinecraftClient.getInstance().getGuiAtlasManager().getSprite(texture).getContents().image;
        return Color.ofTransparent(image.getColor(X,Y));
    }
}
