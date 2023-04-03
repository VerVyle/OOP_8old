package com.vervyle.oop_last.drawable.leafs;

import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.drawable.leafs.utilities.RegularPolygon;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TTriangle extends RegularPolygon {
    public static int NUM_OF_VERTICES = 3;

    public TTriangle(Pane pane, Point2D center, double radius, Color color) {
        super(pane, center, radius, color);
    }

    @Override
    public void createShape() {
        vertices = vertexInitHelper.calcVertices(center, radius, NUM_OF_VERTICES);
        super.createShape();
    }

    @Override
    public boolean intersects(Point2D point2D) {
        double k, b;
        Point2D p1 = new Point2D(vertices[4], vertices[5]);
        Point2D p2 = new Point2D(vertices[0], vertices[1]);
        k = (p2.y() - p1.y()) / (p2.x() - p1.x());
        b = p1.y() - p1.x() * k;
        if (!vertexInitHelper.isLower(b, k, point2D))
            return false;

        p1 = p2;
        p2 = new Point2D(vertices[2], vertices[3]);
        k = (p2.y() - p1.y()) / (p2.x() - p1.x());
        b = p1.y() - p1.x() * k;
        if (!vertexInitHelper.isLower(b, k, point2D))
            return false;

        p1 = new Point2D(vertices[2], vertices[3]);
        p2 = new Point2D(vertices[4], vertices[5]);
        k = (p2.y() - p1.y()) / (p2.x() - p1.x());
        b = p1.y() - p1.x() * k;
        return !vertexInitHelper.isLower(b, k, point2D);
    }
}
