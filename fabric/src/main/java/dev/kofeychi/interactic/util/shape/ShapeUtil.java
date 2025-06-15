package dev.kofeychi.interactic.util.shape;

import org.joml.Vector2d;

public class ShapeUtil {
    public static boolean insideLine(final double ax, final double az, final double bx, final double bz, final double cx, final double cz) {
        return (bx - ax) * (cz - az) > (bz - az) * (cx - ax);
    }
    public static boolean insideLine(Vector2d a,Vector2d b,Vector2d point) {
        return insideLine(a.x(), a.y(), b.x(), b.y(), point.x(), point.y());
    }
}
