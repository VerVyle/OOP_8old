package com.vervyle.oop_last.factories;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.drawable.leafs.CCircle;
import com.vervyle.oop_last.drawable.leafs.HHexagon;
import com.vervyle.oop_last.drawable.leafs.SSquare;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ElementFactoryImpl implements ElementFactory {

    private Pane pane;

    public ElementFactoryImpl(Pane pane) {
        this.pane = pane;
    }

    private boolean fitsPane(Point2D center, double radius) {
        if (center.x() - radius >= 0 && center.x() + radius <= pane.getWidth())
            return center.y() - radius >= 0 && center.y() + radius <= pane.getHeight();
        return false;
    }

    @Override
    public Element createElement(Point2D center, double radius, Color color, ElementType type) {
        if (!fitsPane(center, radius)) {
            System.out.println("Cannot create element, it doesn't fit pane size");
            return null;
        }
        switch (type) {
            case CIRCLE -> {
                return new CCircle(pane, center, radius, color);
            }
            case SQUARE -> {
                return new SSquare(pane, center, radius, color);
            }
            case HEXAGON -> {
                return new HHexagon(pane, center, radius, color);
            }
            default -> {
                System.out.println("Unable to create this element");
                return null;
            }
        }
    }

    @Override
    public Element createElement() {
        throw new UnsupportedOperationException();
    }
}
