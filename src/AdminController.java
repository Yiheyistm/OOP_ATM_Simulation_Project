package com.abeto.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminController extends Account {
    public Label isAccountTaken,foundStatus,foundUserInfo;
    public TextField searchUser;
    public static int deleteUserIndex;
    private  Scene scene;
    private  Parent root;
    private  Stage stage;
    @FXML
    TextField userName,accNo,pin,intialBalance;
    @FXML
    ScrollPane scrollPane;
    @FXML
    VBox vbox;

   public void addUser(ActionEvent event) throws IOException {
       root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-user-scene.fxml")));
       stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
    public void displayUser(ActionEvent event){
        vbox = new VBox();

        if (!Account.userDetail.isEmpty()){
            int counter = 1;
            Label label1 = new Label(" Name   Account   Pin   Balance\n");
            label1.setFont(Font.font("Arial",35));
            vbox.getChildren().add(label1);
            label1.setTextFill(Color.GREEN);
            for(Account account: Account.userDetail){
                System.out.println(account);
                Label label = new Label(counter+".%s   %d   %d   %.2f $".formatted(account.getName(), account.getAccountNo(), account.getPin(), account.getBalance()));
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("Arial",32));
                vbox.getChildren().add(label);
                counter++;
            }}
        else {
            Label label1 = new Label("\t\t😭😭😭\n\t\tUser list is Empty\n\t\t Please Register Some");
            label1.setFont(Font.font("Forte",40));
            vbox.getChildren().add(label1);
            label1.setTextFill(Color.ORANGERED);
        }
        scrollPane = new ScrollPane(vbox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Scene scene = new Scene(scrollPane, 600, 500);
        scene.setFill(javafx.scene.paint.Color.web("#323b43"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        Image icon = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("images/WDSC_Illustration_834x834.png")));
        stage.getIcons().add(icon);
        stage.setTitle("Vision ATM");
        stage.show();


    }
    public void deleteUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete-user.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void deleteAll(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Trying to Delete all");
        alert.setHeaderText("You'r about Delete all user");
        alert.setContentText("Are sure to delete?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            userDetail.clear();
        }

    }
    public void save(ActionEvent event){
       boolean isAccountReserved = false;
       String name = userName.getText();
       int password = Integer.parseInt(pin.getText());
       long accountNo = Long.parseLong(accNo.getText());
       double balance = Double.parseDouble(intialBalance.getText());

        for (int it = 0; it < userDetail.size(); ++it) {
            if (userDetail.get(it).getAccountNo() == accountNo) {
                isAccountReserved = true;}
            }
            if (!isAccountReserved){
                isAccountTaken.setText("Saved");
                isAccountTaken.setTextFill(Color.PINK);
                Account account = new Account(name,password,accountNo,balance);
                userDetail.add(account);

            }
            else{
                isAccountTaken.setText("Account is Taken");
                isAccountTaken.setTextFill(Color.RED);
            }
    }
    public void goBack(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-home-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goBackHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchUser(ActionEvent event) {
       boolean isFound = false;
       Long accountNo = Long.parseLong(searchUser.getText());
        System.out.println(userDetail.size());
        for (int it = 0; it < userDetail.size(); ++it) {
            System.out.println(userDetail.get(it).getAccountNo());
            if (userDetail.get(it).getAccountNo() == accountNo) {
                Account account = userDetail.get(it);
                isFound = true;
               deleteUserIndex = it;
                foundUserInfo.setText("%s   \t %d   \t  %d    %.2f $ ".formatted(account.getName(), account.getAccountNo(), account.getPin(),account.getBalance()));

            }
        }
        if (!isFound){
foundStatus.setText("User not found");
foundStatus.setTextFill(Color.ORANGERED);
        }
    }
    public void deleteOne(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Trying to Delete User");
        alert.setHeaderText("You'r about Delete user");
        alert.setContentText("Are sure to delete?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            userDetail.remove(deleteUserIndex);
            foundUserInfo.setText("");
            foundStatus.setText("Deletion is Succeeded");
            foundStatus.setTextFill(Color.WHITE);

        }
    }
}


