package com.vervyle.oop_last.drawable.leafs;

import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CCircle extends RegularPolygon {

    public CCircle(Pane pane, Point2D center, double radius, Color color) {
        super(pane, center, radius, color);
    }

    @Override
    public void createShape() {
        shape = new Circle(center.x(), center.y(), radius);
    }

    @Override
    public boolean intersects(Point2D point2D) {
        double x = center.x() - point2D.x();
        double y = center.y() - point2D.y();
        return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2);
    }
}
