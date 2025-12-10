module GUI.project {
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    opens hust.soict.dsai.javafx to javafx.fxml;
    exports hust.soict.dsai.javafx;
    exports hust.soict.dsai.swing;
    opens hust.soict.dsai.swing to javafx.fxml;

}