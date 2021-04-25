package com.codecool.dungeoncrawl;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryWindow extends Stage {
    private final VBox layout;

    public InventoryWindow(String message) {
        super();
        super.initModality(Modality.APPLICATION_MODAL);
        super.setTitle("Inventory");
        super.setMinWidth(500);
        super.setMaxWidth(500);

        layout = new VBox(10);
        Label label = new Label();

        Text text = new Text();
        text.setText(message);
        text.setFont(Font.font("URW Bookman", 25));
        text.setFill(Color.DARKMAGENTA);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> super.close());

        layout.getChildren().addAll(label, text, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        super.setScene(scene);
        super.showAndWait();
    }
}
