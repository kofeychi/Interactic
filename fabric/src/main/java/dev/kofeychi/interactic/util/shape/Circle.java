package dev.kofeychi.interactic.util.shape;

import dev.kofeychi.interactic.util.VectorUtils;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Circle extends AbstractShape {
    public Circle(int segments, Vector2d pointOffset,Vector2d origin) {
        super(generateCircle(segments, pointOffset, origin));
    }
    private static List<Vector2d> generateCircle(int segments, Vector2d pointOffset,Vector2d origin) {
        if(segments <= 4) {
            throw new IllegalArgumentException("segments must be greater than or equal to 4");
        }
        List<Vector2d> circle = new ArrayList<>();
        float degrees = (float) 360 / segments;
        for(int i=0; i<segments; i++) {
            circle.add(
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            pointOffset,
                            degrees
                    ).add(origin)
            );
        }
        return circle;
    }
}
