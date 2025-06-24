package dev.kofeychi.interactic.render;

import net.minecraft.client.gui.DrawContext;
import org.joml.Vector2i;

import java.util.Optional;

public class WindowFrameRenderer {
    public static void draw(DrawContext context, int X, int Y, int width, int height, Optional<Boolean> over) {
        int bs = 3;
        Vector2i pos = new Vector2i(0, over.map(aBoolean -> aBoolean ? 9 : 0).orElse(0));
        context.fill(
                X+bs,
                Y+bs,
                X+width-bs,
                Y+height-bs,
                GuiTexture.getSingleColor(GuiTexture.GUI_PANEL_SINGLE).getColor()
        );
        for (int i = bs; i < width-bs; i++) {
            GuiTexture.drawShiftable(context,X+i,Y,GuiTexture.GUI_PANEL_UP,pos);
            GuiTexture.drawShiftable(context,X+i,Y+height-bs,GuiTexture.GUI_PANEL_DOWN,pos);
        }
        for (int i = bs; i < height-bs; i++) {
            GuiTexture.drawShiftable(context,X,Y+i,GuiTexture.GUI_PANEL_LEFT,pos);
            GuiTexture.drawShiftable(context,X+width-bs,Y+i,GuiTexture.GUI_PANEL_RIGHT,pos);
        }

        GuiTexture.drawShiftable(context,X,Y,GuiTexture.GUI_CORNER_LEFT_UP,pos);
        GuiTexture.drawShiftable(context,X+width-bs,Y,GuiTexture.GUI_CORNER_RIGHT_UP,pos);
        GuiTexture.drawShiftable(context,X,Y+height-bs,GuiTexture.GUI_CORNER_LEFT_DOWN,pos);
        GuiTexture.drawShiftable(context,X+width-bs,Y+height-bs,GuiTexture.GUI_CORNER_RIGHT_DOWN,pos);
    }
    /*public static void drawIntersectionHorisontal(DrawContext context, int X, int Y, int width,int bs,boolean lit){
        Vector2i pos = lit? new Vector2i(0,9) : new Vector2i(0,0);
        GuiTexture.drawShiftable(context,X,Y+bs,GuiTexture.GUI_INTERSECTION_LEFT,pos);
        GuiTexture.drawShiftable(context,X+width-1-bs,Y+bs,GuiTexture.GUI_INTERSECTION_RIGHT,pos);
        for (int i = bs; i < width-bs; i++) {
            GuiTexture.drawShiftable(context,X+i-1,Y+bs,GuiTexture.GUI_INTERSECTION_PANEL_UP,pos);
        }
    }
    public static boolean isHovered(double mouseX, double mouseY,int X,int Y,int width,int height) {
        return mouseX >= X && mouseY >= Y && mouseX < X + width && mouseY < Y + height;
    }
     */
}
