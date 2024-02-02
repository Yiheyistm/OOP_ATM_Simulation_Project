package com.abeto.javafx;

import javafx.fxml.Initializable;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class  Account {
    private String name;
    private int pin;
    private long accountNo;
    private double balance;
    public static ArrayList<Account> userDetail = new ArrayList<Account>();

    static Scanner scanner = new Scanner(System.in);

    public Account(String name, int pin, long accountNo, double balance) {

        setName(name);
        setAccountNo(accountNo);
        setPin(pin);
        setBalance(balance);
    }
    public Account(){}
    public String toString() {
            return "%s  %d  %d %s".formatted(getName(), getAccountNo(), getPin(),getBalance());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }




}
