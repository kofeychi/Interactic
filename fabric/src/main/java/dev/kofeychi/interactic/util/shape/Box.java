package dev.kofeychi.interactic.util.shape;

import org.joml.Vector2d;

import java.util.List;

public class Box extends AbstractShape {
    public Box(Vector2d pos1, Vector2d pos2) {
        super(createBox(pos1, pos2));
    }
    private static List<Vector2d> createBox(Vector2d pos1, Vector2d pos2) {
        if(pos1.x > pos2.x) {
            var temp = pos1.x;
            pos1.x = pos2.x;
            pos2.x = temp;
        }
        if(pos1.y > pos2.y) {
            var temp = pos1.y;
            pos1.y = pos2.y;
            pos2.y = temp;
        }
        var x1 = pos1.x;
        var y1 = pos1.y;
        var x2 = pos2.x;
        var y2 = pos2.y;
        return List.of(
                new Vector2d(x1, y1),
                new Vector2d(x1, y2),
                new Vector2d(x2, y1),
                new Vector2d(x2, y2)
        );
    }
}
