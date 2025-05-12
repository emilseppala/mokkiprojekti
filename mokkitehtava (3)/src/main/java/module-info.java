module com.example.mokkitehtava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mokkitehtava to javafx.fxml;
    exports com.example.mokkitehtava;
}