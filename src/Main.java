package com.abeto.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {



        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("main-scene.fxml")));
            Scene scene = new Scene(root, 680, 560);
            Image icon = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
            stage.setTitle("Vision ATM");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(event -> {
                event.consume();
                exiting(stage);

            });
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Something is going wrong");
        }
    }

    public static void main(String[] args) {
        launch();
    }
    public void exiting(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Trying to Exit 🏃‍♂️🏃‍♂️");
        alert.setHeaderText("You'r about closing the program 😭😭😭");
        alert.setContentText("Are sure to exit?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("successfully logged out");
            copyToFile(Account.userDetail);
            stage.close();
        }
    }
    private  void copyToFile(ArrayList<Account> list) {
        try{
        FileWriter writer = new FileWriter("user-data.txt", true);
        for (Account account: list){
            writer.write(account + System.lineSeparator());
        }
            System.out.println("Data is stored");
            writer.close();
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Something went wrong on File Write!!");
            System.out.println(e.getMessage());
        }
    }
}