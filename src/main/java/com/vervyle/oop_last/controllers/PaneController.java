package com.vervyle.oop_last.controllers;

import com.vervyle.oop_last.Container;
import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.factories.ElementFactory;
import com.vervyle.oop_last.factories.ElementFactoryImpl;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class PaneController {

    private final Pane pane;
    private ColorPicker toolColorPicker;
    private TextField toolValue;
    private ListView<ElementType> toolElementList;
    private Container container;

    private ElementFactory elementFactory;

    public PaneController(Pane pane, ColorPicker toolColorPicker, TextField toolValue, ListView<ElementType> toolElementList) {
        this.pane = pane;
        this.toolColorPicker = toolColorPicker;
        this.toolValue = toolValue;
        this.toolElementList = toolElementList;
        elementFactory = new ElementFactoryImpl(pane, toolColorPicker, toolValue, toolElementList);
    }

    public void addElement(Point2D center) {
        Element element;
        element = elementFactory.createElement(center);
        Objects.requireNonNull(element);
        container.addElement(element);
    }

    public boolean intersectsAnyElements(Point2D point2D) {
        return false;
    }

    public void selectAllIntersectedElements(Point2D point2D) {
        container.selectAllElements(point2D);
    }

    public void selectAllElements() {
        container.selectAllElements();
    }

    public void selectLastCreatedIntersectedElement(Point2D point2D) {
        container.selectLastElement(point2D);
    }

    public void deselectAllElements() {
        container.deselectAllElements();
    }
}
