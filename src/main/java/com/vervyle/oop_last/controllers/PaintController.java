package com.vervyle.oop_last;

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
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintController implements Initializable {

    @FXML
    private AnchorPane main_pane;
    @FXML
    private MenuItem menu_deselect_all;
    @FXML
    private MenuItem menu_select_all;
    @FXML
    private ScrollPane scroll_pane;
    @FXML
    private ColorPicker tool_color;
    @FXML
    private CheckBox tool_ctrl_enable;
    @FXML
    private ListView<?> tool_element_list;
    @FXML
    private CheckBox tool_groups_enable;
    @FXML
    private Label tool_mouse_location;
    @FXML
    private TextField tool_pane_x;
    @FXML
    private TextField tool_pane_y;
    @FXML
    private TextField tool_value;
    @FXML
    private TreeView<?> tree_view;

    @FXML
    void onClose(ActionEvent event) {

    }

    @FXML
    void onLoad(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
