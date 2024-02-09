package com.abeto.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserController extends Account {
    public Label welcomeLabel;
    public TextField receiverAccountField;
    public Label receiverStatus;
    private  Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    public AnchorPane userPane;
    public static Account UserAccount;
    @FXML
     Label fieldStatus;
    @FXML
     TextField  depositField,withdrawField,transferField;

    public void setWelcomeLabel(){
       welcomeLabel.setText("Welcome Back " + UserAccount.getName());
    }
    public void deposit(ActionEvent event){
        try {
            double moneyDeposit = Double.parseDouble(depositField.getText());
            System.out.println(moneyDeposit);
            if (moneyDeposit >0){
                UserAccount.setBalance(moneyDeposit + UserAccount.getBalance());
                fieldStatus.setText("Done");
                fieldStatus.setTextFill(Color.GREEN);
            }
            else{
                fieldStatus.setText("Please enter a proper amount");
                fieldStatus.setTextFill(Color.RED);
            }

        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
            fieldStatus.setText("Please enter a correct number");
            fieldStatus.setTextFill(Color.RED);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        }
    
    public void withdraw(ActionEvent event){
        System.out.println("Deposit");
        try {
            double moneyWithdraw = Double.parseDouble(withdrawField.getText());
            System.out.println(moneyWithdraw +4);
            if (moneyWithdraw <= UserAccount.getBalance()){
                UserAccount.setBalance(UserAccount.getBalance() - moneyWithdraw);
                fieldStatus.setText("Done");
                fieldStatus.setTextFill(Color.GREEN);System.out.println("in if clause");
            }
            else if (moneyWithdraw > UserAccount.getBalance()){
                System.out.println("in else clause");
                fieldStatus.setText("Insufficient Balance");
                fieldStatus.setTextFill(Color.RED);
            }
        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
            fieldStatus.setText("Please enter a correct number");
            fieldStatus.setTextFill(Color.RED);
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.out.println("hello");
        }
    }
    public void transfer(ActionEvent event){
        try{
            boolean isFound = false;
            int index=0;
        double transferMoney = Double.parseDouble(transferField.getText());
        double receiveAcc = Double.parseDouble(receiverAccountField.getText());
        for (Account account: userDetail){
            if (receiveAcc == account.getAccountNo()) {
                isFound = true;
                break;
            }
            index++;
        }
        if (!isFound){
            receiverStatus.setText("Receiver account is't Found, Try again!!");
            receiverStatus.setTextFill(Color.RED);
            receiverStatus.setFont(Font.font("Lucida Calligrapy"));
        }
        else{
            receiverStatus.setText("");
                if (transferMoney <= UserAccount.getBalance()){
                    UserAccount.setBalance(UserAccount.getBalance() - transferMoney);
                    userDetail.get(index).setBalance( userDetail.get(index).getBalance() + transferMoney);
                    fieldStatus.setText(transferMoney + "$ is transferred to " + (int)receiveAcc);
                    fieldStatus.setTextFill(Color.GREEN);System.out.println("in if clause");
                }
                else if (transferMoney > UserAccount.getBalance()){
                    fieldStatus.setText("Insufficient Balance");
                    fieldStatus.setTextFill(Color.RED);
                }}
            }catch (NumberFormatException e){
                System.err.println(e.getMessage());
                fieldStatus.setText("Please enter a correct number");
                fieldStatus.setTextFill(Color.RED);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

    }
    public void balanceCheck(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle( UserAccount.getName() + " Balance");
        alert.setHeaderText("Total Balance = " + UserAccount.getBalance() + "$");
        if (alert.showAndWait().get() == ButtonType.OK) {}
    }
    public void goBack(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-home-scene.fxml")));
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

    public void depositScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deposit-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void withdrawScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("withdraw-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void transferScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("transfer-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
