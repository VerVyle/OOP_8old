package com.vervyle.oop_last.drawable.leafs.utilities;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class RegularPolygon extends Element {
    protected Color color;
    protected Point2D center;
    protected double radius;
    protected Shape shape;
    protected double[] vertices;
    protected VertexInitHelper vertexInitHelper;

    public RegularPolygon() {
    }

    public RegularPolygon(Pane pane, Point2D center, double radius, Color color) {
        super(false, pane);
        this.color = color;
        this.center = center;
        this.radius = radius;
        vertexInitHelper = new VertexInitHelper();
        updateShape();
    }

    @Override
    public boolean intersects(Point2D point2D) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void show() {
        pane.getChildren().add(shape);
    }

    @Override
    public void hide() {
        pane.getChildren().remove(shape);
    }

    @Override
    public void select() {
        super.select();
        shape.setStyle("-fx-stroke: #FF0000; -fx-stroke-width: 3px");
    }

    @Override
    public void deselect() {
        super.deselect();
        shape.setStyle("-fx-stroke: #000000; -fx-stroke-width: 3px");
    }

    @Override
    public void resize(double newRadius) {
        if (isOutOfPane(newRadius)) {
            System.out.println("Cannot resize element (" + this + "), it doesn't fit pane size");
            return;
        }
        radius = newRadius;
        updateShape();
    }

    @Override
    public void move(double deltaX, double deltaY) {
        if (isOutOfPane(deltaX, deltaY)) {
            System.out.println("Cannot move element (" + this + "), it doesn't fit pane size");
            return;
        }
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
        if (center.x() - newRadius < 0 || center.x() + newRadius > pane.getWidth())
            return true;
        return center.y() - newRadius < 0 || center.y() + newRadius > pane.getHeight();
    }

    @Override
    public final void updateShape() {
        hide();
        createShape();
        shape.setFill(color);
        show();
        if (isSelected())
            select();
    }

    public void createShape() {
        shape = new Polygon(vertices);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save() {

    }
}
