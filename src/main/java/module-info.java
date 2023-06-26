module com.example.system_image_generator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.system_image_generator to javafx.fxml;
    exports com.example.system_image_generator;
}