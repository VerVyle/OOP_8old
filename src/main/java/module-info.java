module com.vervyle.oop_last {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vervyle.oop_last to javafx.fxml;
    exports com.vervyle.oop_last;
}