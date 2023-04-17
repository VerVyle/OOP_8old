package com.vervyle.oop_last.controllers;

import com.vervyle.oop_last.Container;
import com.vervyle.oop_last.containers.MyList;
import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.GGroup;
import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.factories.ElementFactory;
import com.vervyle.oop_last.factories.ElementFactoryImpl;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class PaneController {

    private final Pane pane;
    private TextField toolValue;
    private ColorPicker toolColorPicker;
    private ListView<ElementType> toolElementList;
    private Container container;
    private ElementFactory elementFactory;

    public void saveAllElements() {
        container.saveAllElements(toolValue.getText() + ".json");
    }

    public void deleteAllElements() {
        container.deleteAllElements();
    }

    public void loadPane() {
        deleteAllElements();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        JSONArray jsonArray;
        Element element;
        try {
            fileReader = new FileReader(toolValue.getText() + ".json");
            bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            JSONObject jsonObject = new JSONObject(str);
            jsonArray = jsonObject.getJSONArray("elements");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                jsonObject = (JSONObject) iterator.next();
                element = elementFactory.createElement(jsonObject);
                container.addElement(element);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void groupSelectedElements() {
        GGroup group = (GGroup) elementFactory.createElement(container.getSelectedElements());
        container.replaceElementsWithGroup(group);
    }

    public void deGroupSelectedElements() {
        MyList<Element> selected = container.getSelectedElements();
        if ((selected.size() == 0) || !(selected.getLast() instanceof GGroup)) {
            System.out.println("Selected element is not a Group!");
            return;
        }
        GGroup group = (GGroup) selected.getLast();
        container.replaceGroupWithElements(group);
    }

    public enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }

    public PaneController(Pane pane, ColorPicker toolColorPicker, TextField toolValue, ListView<ElementType> toolElementList) {
        this.pane = pane;
        this.toolValue = toolValue;
        this.toolColorPicker = toolColorPicker;
        this.toolElementList = toolElementList;
        elementFactory = new ElementFactoryImpl(pane);
        container = new Container();
    }

    private double parseDouble() {
        return Double.parseDouble(toolValue.getText());
    }

    public void addElement(Point2D center, ElementType type) {
        Element element;
        element = elementFactory.createElement(center, parseDouble(), toolColorPicker.getValue(), type);
        try {
            Objects.requireNonNull(element);
        } catch (NullPointerException e) {
            return;
        }
        container.addElement(element);
        container.deselectAllElements();
        container.selectLastElement();
    }

    public boolean intersectsAnyElements(Point2D point2D) {
        return container.intersectsAnyElements(point2D);
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

    public void deleteSelectedElements() {
        container.deleteSelectedElements();
        container.selectLastElement();
    }

    public void moveSelectedElements(Direction direction, double delta) {
        //double delta = Double.parseDouble(toolValue.getText());
        switch (direction) {
            case UP -> container.moveSelectedElements(0, -1 * delta);
            case DOWN -> container.moveSelectedElements(0, delta);
            case LEFT -> container.moveSelectedElements(-1 * delta, 0);
            case RIGHT -> container.moveSelectedElements(delta, 0);
            default -> throw new RuntimeException("A KUDA??");
        }
    }

    public void changeColorOnSelectedElements(Color color) {
        container.changeColorOnSelectedElements(color);
    }

    public void resizeSelectedElements(double newRadius) {
        //double newRadius = Double.parseDouble(toolValue.getText());
        container.resizeSelectedElements(newRadius);
    }

}
