package com.vervyle.oop_last.controllers;

import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PaintController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private MenuItem menuDeselectAll;
    @FXML
    private MenuItem menuSelectAll;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ColorPicker toolColorPicker;
    @FXML
    private CheckBox toolCtrlEnable;
    @FXML
    private ListView<ElementType> toolElementsList;
    @FXML
    private CheckBox toolGroupSelectionEnable;
    @FXML
    private TextField toolValue;
    @FXML
    private Label toolMousePos;
    @FXML
    private TextField toolPaneX;
    @FXML
    private TextField toolPaneY;
    @FXML
    private TreeView<?> treeView;

    private PaneController paneController;

    @FXML
    void onClose(ActionEvent event) {

    }

    @FXML
    void onLoad(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {

    }

    private void initPane() {
        paneController = new PaneController(mainPane, toolColorPicker, toolValue, toolElementsList);
        mainPane.setOnMouseClicked(mouseEvent -> {
            Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (!mouseEvent.getButton().equals(MouseButton.PRIMARY))
                return;
            if (!paneController.intersectsAnyElements(point2D)) {
                paneController.addElement(point2D);
                return;
            }
            if (!toolCtrlEnable.isSelected() || !mouseEvent.isControlDown())
                paneController.deselectAllElements();
            if (toolGroupSelectionEnable.isSelected()) {
                paneController.selectAllIntersectedElements(point2D);
                return;
            }
            paneController.selectLastCreatedIntersectedElement(point2D);
        });
    }

    private void initList() {
        toolElementsList.getItems().addAll(Arrays.stream(ElementType.values()).filter(elementType -> !elementType.equals(ElementType.GROUP)).toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initList();
        initPane();
    }
}
