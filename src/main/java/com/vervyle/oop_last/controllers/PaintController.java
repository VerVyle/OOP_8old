package com.vervyle.oop_last.controllers;

import com.vervyle.oop_last.drawable.ElementType;
import com.vervyle.oop_last.drawable.Point2D;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PaintController implements Initializable {

    @FXML
    public MenuItem menuGroup;
    @FXML
    public MenuItem menuDeGroup;
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
        paneController.deleteAllElements();
    }

    @FXML
    void onLoad(ActionEvent event) {
        paneController.loadPane();
    }

    @FXML
    void onSave(ActionEvent event) {
        paneController.saveAllElements();
    }

    private double parseValue() {
        return Double.parseDouble(toolValue.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toolElementsList.getItems().addAll(Arrays.stream(ElementType.values()).filter(elementType -> !elementType.equals(ElementType.GROUP)).toList());
        paneController = new PaneController(mainPane, toolColorPicker, toolValue, toolElementsList);
        mainPane.setOnMouseClicked(mouseEvent -> {
            Point2D point2D = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (!mouseEvent.getButton().equals(MouseButton.PRIMARY))
                return;
            if (!paneController.intersectsAnyElements(point2D)) {
                ElementType type = toolElementsList.getSelectionModel().getSelectedItem();
                paneController.addElement(point2D, type);
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
        scrollPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                paneController.deleteSelectedElements();
                return;
            }
            if (keyEvent.getCode().equals(KeyCode.W)) {
                paneController.moveSelectedElements(PaneController.Direction.UP, parseValue());
                return;
            }
            if (keyEvent.getCode().equals(KeyCode.S)) {
                paneController.moveSelectedElements(PaneController.Direction.DOWN, parseValue());
                return;
            }
            if (keyEvent.getCode().equals(KeyCode.D)) {
                paneController.moveSelectedElements(PaneController.Direction.RIGHT, parseValue());
                return;
            }
            if (keyEvent.getCode().equals(KeyCode.A)) {
                paneController.moveSelectedElements(PaneController.Direction.LEFT, parseValue());
            }
        });
        toolColorPicker.setOnAction(actionEvent -> paneController.changeColorOnSelectedElements(toolColorPicker.getValue()));
        toolValue.setOnAction(actionEvent -> paneController.resizeSelectedElements(parseValue()));
        menuDeselectAll.setOnAction(actionEvent -> paneController.deselectAllElements());
        menuSelectAll.setOnAction(actionEvent -> paneController.selectAllElements());
        menuGroup.setOnAction(actionEvent -> paneController.groupSelectedElements());
        menuDeGroup.setOnAction(actionEvent -> paneController.deGroupSelectedElements());
        mainPane.setOnMouseMoved(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            toolMousePos.setText("x : " + x + ", y : " + y);
        });
    }
}
