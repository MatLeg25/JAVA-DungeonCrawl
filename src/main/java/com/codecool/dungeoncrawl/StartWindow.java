package com.codecool.dungeoncrawl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartWindow extends Stage {
    private final TextField name = new TextField();
    private final VBox layout;

    public StartWindow() {
        super();
        super.initModality(Modality.APPLICATION_MODAL);
        super.setTitle("Dungeon Crawl 2021");
        super.setMaxWidth(500);
        super.setMinWidth(500);
        super.setMaxHeight(500);
        super.setMinHeight(500);

        name.setText("Enter your name.");
        Button submit = new Button("Submit");
        Button clear = new Button("Clear");


        layout = new VBox(10);
        layout.getChildren().addAll(name, submit, clear);
        layout.setAlignment(Pos.CENTER);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty())) {
                    name.setText(name.getText());
                    StartWindow.super.close();
                } else {
                    name.setText("Enter your name");
                }
            }
        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                name.clear();
            }
        });


        Scene scene = new Scene(layout);
        super.setScene(scene);
        super.showAndWait();
    }

    public String getName() {
        return name.getText();
    }
}

