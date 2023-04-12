module com.vervyle.oop_last {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.vervyle.oop_last to javafx.fxml;
    exports com.vervyle.oop_last;
    exports com.vervyle.oop_last.controllers;
    opens com.vervyle.oop_last.controllers to javafx.fxml;
    exports com.vervyle.oop_last.drawable;
    exports com.vervyle.oop_last.drawable.leafs;
    exports com.vervyle.oop_last.containers;
}