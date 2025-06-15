package dev.kofeychi.interactic.util.shape;

import dev.kofeychi.interactic.util.Rangeable;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
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
    /*
    public void draw(float y, boolean in, VertexConsumer buffer,Vec3d camera,MatrixStack matrixStack,Camera cam,VertexConsumerProvider.Immediate vertexConsumers) {
        if(points.size() > 3) {
            int color = in ? Colors.GREEN : Colors.RED;
            points.forEach((point) -> {
                renderSmallCubeFrame(buffer, camera, new Vec3d(point.x, y, point.y), color, 0.1, 1);
            });
            Rangeable rangeable1 = new Rangeable(0, points.size(), 0);
            Rangeable rangeable2 = new Rangeable(0, points.size(), 1);
            for (int i = 0; i <= points.size(); i++) {
                rangeable1.cycle(1);
                rangeable2.cycle(1);
                Vector2d v1 = points.get(rangeable1.range.getFirst());
                Vector2d v2 = points.get(rangeable2.range.getFirst());

                float scale = 0f;

                MatrixStack ms = new MatrixStack();
                ms.translate(
                        v1.x - camera.x,
                        y - camera.y,
                        v1.y - camera.z
                );
                ms.scale(scale, scale, scale);

                MatrixStack ms2 = new MatrixStack();
                ms2.translate(
                        v2.x - camera.x,
                        y - camera.y,
                        v2.y - camera.z
                );
                ms2.scale(scale, scale, scale);

                buffer.vertex(ms.peek(), (float) v1.x, y, (float) v1.y).color(color).normal(0, 0, 0);
                buffer.vertex(ms2.peek(), (float) v2.x, y, (float) v2.y).color(color).normal(0, 0, 0);
            }
        }
    }
    public static void renderSmallCubeFrame(
            VertexConsumer vertexConsumer, Vec3d cameraPos, Vec3d boxCenter,
            int color, double scale,float mscale
    ) {
        MatrixStack ms = new MatrixStack();
        ms.translate(
                boxCenter.x - cameraPos.x,
                boxCenter.y - cameraPos.y,
                boxCenter.z - cameraPos.z
        );
        ms.scale(mscale,mscale,mscale);

        float alpha = ((color >> 24) & 0xff) / 255f;
        float red = ((color >> 16) & 0xff) / 255f;
        float green = ((color >> 8) & 0xff) / 255f;
        float blue = (color & 0xff) / 255f;
        WorldRenderer.drawBox(
                ms,
                vertexConsumer,
                -scale / 2,
                -scale / 2,
                -scale / 2,
                scale / 2,
                scale / 2,
                scale / 2,
                red, green, blue, alpha
        );
    }
     */
}
