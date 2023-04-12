package com.vervyle.oop_last.factories;

import com.vervyle.oop_last.containers.MyLinkedList;
import com.vervyle.oop_last.containers.MyList;
import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.GGroup;
import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.drawable.leafs.CCircle;
import com.vervyle.oop_last.drawable.leafs.HHexagon;
import com.vervyle.oop_last.drawable.leafs.SSquare;
import com.vervyle.oop_last.drawable.leafs.TTriangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

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
            case TRIANGLE -> {
                return new TTriangle(pane, center, radius, color);
            }
            default -> {
                System.out.println("Unable to create this element");
                return null;
            }
        }
    }

    @Override
    public Element createElement(JSONObject jsonObject) {
        Object type = jsonObject.get("type");
        Element element;
        if (type.equals(GGroup.class.getName())) {
            JSONArray children = jsonObject.getJSONArray("children");
            Iterator iterator = children.iterator();
            JSONObject child;
            MyList<Element> elements = new MyLinkedList<>();
            while (iterator.hasNext()) {
                child = (JSONObject) iterator.next();
                element = createElement(child);
                elements.add(element);
            }
            element = new GGroup(pane, elements);
            return element;
        }
        double x = jsonObject.getJSONArray("center").getDouble(0);
        double y = jsonObject.getJSONArray("center").getDouble(1);
        Point2D center = new Point2D(x, y);
        double red = jsonObject.getJSONArray("color").getDouble(0);
        double green = jsonObject.getJSONArray("color").getDouble(1);
        double blue = jsonObject.getJSONArray("color").getDouble(2);
        Color color = new Color(red, green, blue, 1);
        double radius = jsonObject.getDouble("radius");
        if (type.equals(CCircle.class.getName())) {
            element = new CCircle(pane, center, radius, color);
            return element;
        }
        if (type.equals(SSquare.class.getName())) {
            element = new SSquare(pane, center, radius, color);
            return element;
        }
        if (type.equals(HHexagon.class.getName())) {
            element = new HHexagon(pane, center, radius, color);
            return element;
        }
        if (type.equals(TTriangle.class.getName())) {
            element = new TTriangle(pane, center, radius, color);
            return element;
        }
        throw new RuntimeException();
    }

    @Override
    public Element createElement(MyList<Element> children) {
        GGroup gGroup = new GGroup(pane, children);
        return gGroup;
    }
}
