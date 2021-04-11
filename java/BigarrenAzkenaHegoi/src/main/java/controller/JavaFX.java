package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Liburuak gestionatzeko menua");
        Button btn1 = new Button();
        btn1.setText("Datu Guztiak erakutsi");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.irakurriDena();
            }

        });

        Button btn2 = new Button();
        btn2.setText("Kategoria baten liburuetako datu guztiak erakutsi");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.irakurriGuztiaKategoriatik();
            }

        });

        Button btn3 = new Button();
        btn3.setText("Liburu bat sartu");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.liburuakSortu();
            }

        });

        Button btn4 = new Button();
        btn4.setText("Liburu bat aldatu");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.updateBook();
            }

        });

        Button btn5 = new Button();
        btn5.setText("Liburu bat ezabatu");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.Main.deleteBook();
            }

        });
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
