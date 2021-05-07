package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 500);
        gridPane.setPadding(new Insets(50, 50, 50, 50));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        Button btn1 = new Button();
        btn1.setText("Datu Guztiak erakutsi");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.irakurriDena();
            }
            
        });
        
        Button btn2 = new Button();
        btn2.setText("Kategoria baten liburuetako datu guztiak erakutsi");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.irakurriGuztiaKategoriatik();
            }
            
        });
        
        Button btn3 = new Button();
        btn3.setText("Liburu bat sartu");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.liburuakSortu();
            }
            
        });
        
        Button btn4 = new Button();
        btn4.setText("Liburu bat aldatu");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.updateBook();
            }
            
        });
        
        Button btn5 = new Button();
        btn5.setText("Liburu bat ezabatu");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.deleteBook();
            }
            
        });
        
        Button btn6 = new Button();
        btn6.setText("Liburu guztiak ikusi MariaDB");
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.datuakIkusiMariaDB();
            }
            
        });
        
        Button btn7 = new Button();
        btn7.setText("Liburu bat gorde MariaDB");
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.datuaGordeMariaDB();
            }
            
        });
        
        Button btn8 = new Button();
        btn8.setText("Liburu kendu MariaDB");
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main.datuakKenduMariaDB();
            }
            
        });
        
        btn1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn3.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn4.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn5.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn6.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn7.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        btn8.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        
        btn1.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn2.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn3.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn4.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn5.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn6.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn7.setStyle("-fx-font: normal bold 20px 'serif' ");
        btn8.setStyle("-fx-font: normal bold 20px 'serif' ");
        
        
        gridPane.add(btn1, 0, 0);
        gridPane.add(btn2, 1, 0);
        gridPane.add(btn3, 0, 1);
        gridPane.add(btn4, 1, 1);
        gridPane.add(btn5, 0, 2);
        gridPane.add(btn6, 1, 2);
        gridPane.add(btn7, 0, 3);
        gridPane.add(btn8, 1, 3);
        gridPane.setStyle("-fx-background-color: BEIGE;");
        
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("FX Menua CSS-rekin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
