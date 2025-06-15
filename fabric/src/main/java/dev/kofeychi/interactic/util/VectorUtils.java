package dev.kofeychi.interactic.util;

import net.minecraft.util.math.Vec3d;
import org.joml.Vector2d;

public class VectorUtils {
    /**
     *
     * @param Origin origin of rotation
     * @param Point point to rotate,offsets from origin
     * @param angle angle to rotate
     * @return Position of Origin + point and rotated ? degrees
     */
    public static Vec3d RotateAround(Vec3d Origin, Vector2d Point, double angle){
        Vector2d vec = rotateAround(
                Point.add(Origin.x,Origin.z),
                new Vector2d(Origin.x,Origin.z),
                Math.toRadians(angle)
        );
        return new Vec3d(
                vec.x,
                Origin.y,
                vec.y
        );
    }
    public static Vector2d rotateAround(Vector2d vertex, Vector2d rotationPoint, double angle) {
        double translatedToOriginX = vertex.x - rotationPoint.x;
        double translatedToOriginY = vertex.y - rotationPoint.y;

        double rotatedX = translatedToOriginX * Math.cos(angle) - translatedToOriginY * Math.sin(angle);
        double rotatedY = translatedToOriginX * Math.sin(angle) + translatedToOriginY * Math.cos(angle);

        double reverseTranslatedX = rotatedX + rotationPoint.x;
        double reverseTranslatedY = rotatedY + rotationPoint.y;

        return new Vector2d(reverseTranslatedX, reverseTranslatedY);
    }

    /**
     *
     * @param Origin origin of rotation
     * @param Point point to rotate,offsets from origin
     * @param angle angle to rotate
     * @return Position of Origin + point and rotated ? degrees
     */
    public static Vector2d RotateAround(Vector2d Origin, Vector2d Point, double angle){
        Vector2d vec = rotateAround(
                Point.add(Origin.x,Origin.y),
                new Vector2d(Origin.x,Origin.y),
                Math.toRadians(angle)
        );
        return new Vector2d(
                vec.x,
                vec.y
        );
    }

    public static boolean isOver(double mouseX, double mouseY,int X,int Y,int width,int height) {
        return mouseX >= X && mouseY >= Y && mouseX < X + width && mouseY < Y + height;
    }
}
