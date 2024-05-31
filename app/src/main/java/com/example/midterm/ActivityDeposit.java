package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;


public class ActivityDeposit extends AppCompatActivity implements Parcelable {


    double accountBalance = Account.balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);


        //Initialize components
        Button buttonSubmit = findViewById(R.id.btnSubmitDeposit);
        TextView textViewDepositAmount = findViewById(R.id.tvDepositAmt);



    }//End of onCreate()




}//End of class