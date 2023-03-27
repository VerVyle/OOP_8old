package com.vervyle.oop_last.factories;

import com.vervyle.oop_last.drawable.Element;
import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
import com.vervyle.oop_last.drawable.leafs.CCircle;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElementFactoryImpl implements ElementFactory {

    private Pane pane;
    private ColorPicker toolColorPicker;
    private TextField toolValue;
    private ListView<ElementType> toolElementList;

    public ElementFactoryImpl(Pane pane, ColorPicker toolColorPicker, TextField toolValue, ListView<ElementType> toolElementList) {
        this.pane = pane;
        this.toolColorPicker = toolColorPicker;
        this.toolValue = toolValue;
        this.toolElementList = toolElementList;
    }

    private double parseRadius() {
        double radius = 0;
        try {
            radius = Double.parseDouble(toolValue.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return radius;
    }

    @Override
    public Element createElement(Point2D center) {
        ElementType type = toolElementList.getSelectionModel().getSelectedItem();
        switch (type) {
            case CIRCLE -> {
                return new CCircle(pane, center, parseRadius(), toolColorPicker.getValue());
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
