package com.vervyle.oop_last.drawable.leafs.utilities;

import com.vervyle.oop_last.drawable.Point2D;

public class VertexInitHelper {

    public double[] calcVertices(Point2D center, double radius, int num_of_vertices) {
        double[] vertices = new double[num_of_vertices * 2];
        double angle_step = 360.0 / num_of_vertices / 180 * Math.PI;
        double x, y;
        double angle_normal_vertex;
        for (int i = 0; i < num_of_vertices; i++) {
            angle_normal_vertex = angle_step * i;
            x = center.x() + Math.sin(angle_normal_vertex) * radius;
            y = center.y() + Math.cos(angle_normal_vertex) * radius;
            vertices[2 * i] = x;
            vertices[2 * i + 1] = y;
        }
        return vertices;
    }

    public boolean isLower(double b, double k, Point2D point2D) {
        return point2D.y() < point2D.x() * k + b;
    }
}
