package com.vervyle.oop_last.drawable.leafs;

import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HHexagon extends RegularPolygon {
    public static int NUM_OF_VERTICES = 6;
    public static double ANGLE_STEP = 360.0 / NUM_OF_VERTICES / 180 * Math.PI;

    public HHexagon(Pane pane, Point2D center, double radius, Color color) {
        super(pane, center, radius, color);
    }

    private void calcVertices() {
        vertices = new double[NUM_OF_VERTICES * 2];
        numOfVertices = NUM_OF_VERTICES;
        double x, y;
        double angle_normal_vertex;
        for (int i = 0; i < NUM_OF_VERTICES; i++) {
            angle_normal_vertex = ANGLE_STEP * i;
            x = center.x() + Math.sin(angle_normal_vertex) * radius;
            y = center.y() + Math.cos(angle_normal_vertex) * radius;
            vertices[2 * i] = x;
            vertices[2 * i + 1] = y;
        }
    }

    @Override
    public void createShape() {
        calcVertices();
        super.createShape();
    }

    private boolean isLower(double a, double b, double c, Point2D point2D) {
        return a * point2D.x() + b * point2D.y() + c <= 0;
    }

    @Override
    public boolean intersects(Point2D point2D) {
        double a, b, c;
        Point2D p1 = new Point2D(vertices[10], vertices[11]);
        Point2D p2 = new Point2D(vertices[0], vertices[1]);
        a = p2.y() - p1.y();
        b = p1.x() - p2.x();
        c = -1 * p1.x() * a - p1.y() * b;
        if (!isLower(a, b, c, point2D))
            return false;
        return true;
    }
}
