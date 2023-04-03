package com.vervyle.oop_last.drawable.leafs;

import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SSquare extends RegularPolygon {

    public SSquare(Pane pane, Point2D center, double radius, Color color) {
        super(pane, center, radius, color);
    }

    private double width() {
        return radius * Math.sqrt(2);
    }

    @Override
    public boolean intersects(Point2D point2D) {
        double width = width();
        if (center.x() - width / 2 <= point2D.x() && point2D.x() <= center.x() + width / 2)
            return center.y() - width / 2 <= point2D.y() && point2D.y() <= center.y() + width / 2;
        return false;
    }

    @Override
    public void createShape() {
        double width = width();
        shape = new Rectangle(center.x() - width / 2, center.y() - width / 2, width, width);
    }
}
