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

public class EndWindow extends Stage {
    private final VBox layout;

    public EndWindow() {
        super();
        super.initModality(Modality.APPLICATION_MODAL);
        super.setTitle("Game over");
        super.setMinWidth(500);
        super.setMaxWidth(500);

        layout = new VBox(10);
        Label label = new Label();

        Text text = new Text();
        text.setText("You died");
        text.setFont(Font.font("Verdana", 35));
        text.setFill(Color.DARKRED);
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));
        Button playAgainButton = new Button("Play Again");
        layout.getChildren().addAll(label, text, playAgainButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        super.setScene(scene);
        super.showAndWait();
    }
}
