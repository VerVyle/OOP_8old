package com.vervyle.oop_last.factories;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
import javafx.scene.paint.Color;

public interface ElementFactory {
    Element createElement(Point2D center, double radius, Color color, ElementType type);

    Element createElement();
}
