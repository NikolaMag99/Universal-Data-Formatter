package app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class FirstView extends Stage {

    private static FirstView instance = null;
    private Button button;
    private TextField textField;
    private Label label;
    private Scene scena;

    private FirstView() {
        textField = new TextField();
        button = new Button("Start");
        label = new Label("Enter a name");
        label.setPrefHeight(40);
        label.setPrefWidth(120);
        textField.setPrefHeight(40);
        textField.setPrefWidth(120);
        VBox vBox = new VBox(40);
        vBox.getChildren().addAll(label, textField, button);
        //vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setPadding(new Insets(40));
        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                MainView.getInstance();
                close();

            }
        });
        scena = new Scene(vBox);
        setScene(scena);
        setHeight(500);
        setWidth(300);

        show();

    }


    public static FirstView getInstance() {
        if (instance == null)
            instance = new FirstView();
        return instance;
    }
}
