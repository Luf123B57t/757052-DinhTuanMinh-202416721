module AimsProject {
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;
    opens hust.soict.dsai.aims.screen to javafx.fxml;
    exports hust.soict.dsai.aims.screen;
    exports hust.soict.dsai.aims.media;
    exports hust.soict.dsai.aims.cart;
    opens hust.soict.dsai.aims.media to javafx.fxml;
    opens hust.soict.dsai.aims.cart to javafx.fxml;
    exports hust.soict.dsai.aims.exception;

}
