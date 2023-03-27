package com.vervyle.oop_last.drawable.leafs;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.List;

public abstract class RegularPolygon extends Element {
    protected Color color;
    protected Point2D center;
    protected double radius;
    protected Shape shape;
    protected double[] vertices;
    protected int numOfVertices;

    public RegularPolygon() {
    }

    public RegularPolygon(Pane pane, Point2D center, double radius, Color color) {
        super(false, pane);
        this.color = color;
        this.center = center;
        this.radius = radius;
        numOfVertices = 0;
    }

    @Override
    public boolean intersects(Point2D point2D) {
        return false;
    }

    /**
     * ready to use
     */
    @Override
    public void show() {
        pane.getChildren().add(shape);
    }

    /**
     * ready to use
     */
    @Override
    public void hide() {
        pane.getChildren().remove(shape);
    }

    @Override
    public void resize(double newRadius) {
        if (isOutOfPane(newRadius))
            System.out.printf("Cannot resize element (" + this.toString() + "), it doesn't fit pane size");
        radius = newRadius;
        updateShape();
    }

    @Override
    public void move(double deltaX, double deltaY) {
        if (isOutOfPane(deltaX, deltaY))
            System.out.println("Cannot move element (" + this.toString() + "), it doesn't fit pane size");
        center = new Point2D(center.x() + deltaX, center.y() + deltaY);
        updateShape();
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        updateShape();
    }

    @Override
    public boolean isOutOfPane(double deltaX, double deltaY) {
        if (center.x() + deltaX - radius < 0 || center.x() + deltaX + radius > pane.getWidth())
            return true;
        return center.y() + deltaY - radius < 0 || center.y() + deltaY + radius > pane.getHeight();
    }

    @Override
    public boolean isOutOfPane(double newRadius) {
        if (center.x() - radius < 0 || center.x() + radius > pane.getWidth())
            return true;
        return center.y() - radius < 0 || center.y() + radius > pane.getHeight();
    }

    @Override
    public void updateShape() {
        hide();
        shape = new Polygon(vertices);
        shape.setFill(color);
        show();
    }
}
