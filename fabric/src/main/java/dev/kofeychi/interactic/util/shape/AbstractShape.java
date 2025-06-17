package dev.kofeychi.interactic.util.shape;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.kofeychi.interactic.util.Rangeable;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class AbstractShape {
    public ArrayList<Vector2d> points;

    public AbstractShape() {
        points = new ArrayList<>();
    }

    public AbstractShape(List<Vector2d> points) {
        this.points = new ArrayList<>(points);
    }

    public boolean isInside(Vector2d point) {
        List<Boolean> booleans = new ArrayList<>();
        if(points.size() > 3) {
            Rangeable rangeable1 = new Rangeable(0, points.size(), 1);
            Rangeable rangeable2 = new Rangeable(0, points.size(), 0);
            for (int i = 0; i < points.size(); i++) {
                rangeable1.cycle(1);
                rangeable2.cycle(1);
                Vector2d v1 = points.get(rangeable1.range.getFirst());
                Vector2d v2 = points.get(rangeable2.range.getFirst());
                booleans.add(ShapeUtil.insideLine(v1, v2, point));
            }
            return !booleans.contains(false);
        }
        return false;
    }
    public void draw(Vector2d pos, DrawContext drawContext) {
        if(points.size() > 3) {
            int color = isInside(pos) ? Colors.GREEN : Colors.RED;
            points.forEach((point) -> {
                drawContext.drawBorder(
                        (int) (point.x-3),
                        (int) (point.y-3),
                        7,
                        7,
                        color
                );
            });
            var buf = drawContext.getVertexConsumers().getBuffer(RenderLayer.getGui());
            Matrix4f matrix4f = drawContext.getMatrices().peek().getPositionMatrix();
            Rangeable rangeable1 = new Rangeable(0, points.size(), 0);
            Rangeable rangeable2 = new Rangeable(0, points.size(), 1);
            for (int i = 0; i <= points.size(); i++) {
                rangeable1.cycle(1);
                rangeable2.cycle(1);
                Vector2d v1 = points.get(rangeable1.range.getFirst());
                Vector2d v2 = points.get(rangeable2.range.getFirst());

                buf.vertex(matrix4f,(float) v1.x, (float) 0, (float) v1.y).color(color);
                buf.vertex(matrix4f,(float) v2.x, (float) 0, (float) v2.y).color(color);
            }
            drawContext.tryDraw();
        }
    }
}
