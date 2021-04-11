package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Liburuak gestionatzeko menua");
        Button btn = new Button();
        btn.setText("Datu Guztiak erakutsi");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.irakurriDena();
            }
        });

        StackPane pantaila = new StackPane();
        pantaila.getChildren().add(btn);
        primaryStage.setScene(new Scene(pantaila, 300, 250));
        primaryStage.show();
    }
}
