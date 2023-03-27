package com.vervyle.oop_last.drawable;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class Element implements Savable, Loadable {

    protected boolean selected;
    protected Pane pane;

    public Element() {
    }

    public Element(boolean selected, Pane pane) {
        this.selected = selected;
        this.pane = pane;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void deselect() {
        selected = false;
    }

    public abstract boolean intersects(Point2D point2D);

    public abstract void show();

    public abstract void hide();

    public abstract void resize(double newRadius);

    public abstract void move(double deltaX, double deltaY);

    public abstract void setColor(Color color);

    public abstract boolean isOutOfPane(double deltaX, double deltaY);

    public abstract void updateShape();

    public abstract boolean isOutOfPane(double newRadius);
}
