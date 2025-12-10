package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.awt.*;

public class PainterController {
    @FXML
    private Pane drawingArea;

    @FXML
    private RadioButton penRadio;

    @FXML
    private RadioButton eraserRadio;

    private final ToggleGroup toolGroup = new ToggleGroup();

    // initialize is called by the FXMLLoader after fields are injected
    @FXML
    private void initialize() {
        penRadio.setToggleGroup(toolGroup);
        eraserRadio.setToggleGroup(toolGroup);
        penRadio.setSelected(true); // default to pen
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingArea.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color color = Color.BLACK;
        double radius = 4;
        if(eraserRadio.isSelected()) {
            color = Color.WHITE;
            radius = 8;
        }

        Circle circle = new Circle(event.getX(), event.getY(), radius, color);
        drawingArea.getChildren().add(circle);
    }

}