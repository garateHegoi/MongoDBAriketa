package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
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
        btn5.setText("Liburuak Ikusi MariaDB-rekin");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main2.datuakIkusiMariaDB();
            }
            
        });
        
        Button btn7 = new Button();
        btn5.setText("Liburu bat ezabatu");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main2.datuaGordeMariaDB();
            }
            
        });
        
        Button btn8 = new Button();
        btn5.setText("Liburu bat ezabatu");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.Main2.datuakKenduMariaDB();
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
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 400);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(btn1, 0, 0);
        gridPane.add(btn2, 1, 0);
        gridPane.add(btn3, 0, 1);
        gridPane.add(btn4, 1, 1);
        gridPane.add(btn5, 0, 2);
        gridPane.add(btn6, 1, 2);
        gridPane.add(btn7, 2, 0);
        gridPane.add(btn8, 2, 1);
        gridPane.setStyle("-fx-background-color: BEIGE;");
        
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("FX Menua CSS-rekin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
