package com.example.midterm;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {

    private String accountNumber;
    private double balance;
    private String bankName;
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

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };


    //Getters and setters
    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getBankName(){
        return bankName;
    }

    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getCustomerFirstName(){
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) { this.customerFirstName = customerFirstName;}

    public String getCustomerLastName(){
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {this.customerLastName = customerLastName; }



    protected Account(Parcel in) {
        accountNumber = in.readString();
        balance = in.readDouble();
        bankName = in.readString();
        customerFirstName = in.readString();
        customerLastName = in.readString();
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


}//End of class
