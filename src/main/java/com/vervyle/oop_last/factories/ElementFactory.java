package com.vervyle.oop_last.factories;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.Point2D;

public interface ElementFactory {
    Element createElement(Point2D center);
    Element createElement();
}
