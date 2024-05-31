package com.example.midterm;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {

    private String accountNumber;
    private String bankName;

    private double balance;
    private String customerFirstName;
    private String customerLastName;

    //Constructor
    public Account(String accountNumber, double balance, String bankName, String customerFirstName, String customerLastName){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankName = bankName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(accountNumber);
        dest.writeDouble(balance);
        dest.writeString(bankName);
        dest.writeString(customerFirstName);
        dest.writeString(customerLastName);
    }

    //Getters and setters
    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public String getBankName(){
        return bankName;
    }

    public String getCustomerFirstName(){
        return customerFirstName;
    }

    public String getCustomerLastName(){
        return customerLastName;
    }



}//End of class
