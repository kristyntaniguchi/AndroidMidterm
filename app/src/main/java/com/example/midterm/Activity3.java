package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Account account1 = new Account("10001", 912.15, "Bank of Renton", "Nani", "Pelekai");

        //Initialize buttons and textviews
        Button depositBtn = findViewById(R.id.btnDeposit);
        Button withdrawBtn = findViewById(R.id.btnWithdrawal);

        //Deposit Button Click listener
        depositBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, ActivityDeposit.class);
                intent.putExtra("Account")
                //Start activity2
                startActivity(intent);
            }

        });//End of Deposit.setOnClickListener()

        //Withdraw Button Click listener
        withdrawBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, ActivityWithdraw.class);

                //Start activity2
                startActivity(intent);
            }

        });//End of Withdraw.setOnClickListener()


    }//End of onCreate()


    //Deposit button

}//End of class