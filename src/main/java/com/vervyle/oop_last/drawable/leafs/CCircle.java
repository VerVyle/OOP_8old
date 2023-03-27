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
    public void updateShape() {
        hide();
        shape = new Circle(center.x(), center.y(), radius);
        shape.setFill(color);
        show();
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
