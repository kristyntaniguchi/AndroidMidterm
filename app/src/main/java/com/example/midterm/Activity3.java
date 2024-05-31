package com.example.midterm;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    private Account accountNani;
    private ActivityResultLauncher<Intent> depositActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //Initialize
        Button depositBtn = findViewById(R.id.btnDeposit);
        Button withdrawBtn = findViewById(R.id.btnWithdrawal);
        TextView balanceTv = findViewById(R.id.tvAccountBalance);

        accountNani = new Account("10001", 912.15, "Bank of Renton", "Nani", "Pelekai");

        //Set balance text view
        balanceTv.setText(String.format("%.2f",accountNani.getBalance()));

        //Register launcher
        // Register the launcher and define the callback
        depositActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Account updatedAccount = result.getData().getParcelableExtra("updated_account");
                        if (updatedAccount != null) {
                            accountNani = updatedAccount;
                            balanceTv.setText(String.format("%.2f", accountNani.getBalance()));
                        }
                    }
                }
        );

        //Deposit Button Click listener
        depositBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, ActivityDeposit.class);
                intent.putExtra("account", accountNani);

                depositActivityLauncher.launch(intent);
            }

        });//End of Deposit.setOnClickListener()

        //Withdraw Button Click listener
        withdrawBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, ActivityWithdraw.class);

                //Start activity withdrawal
                intent.putExtra("account", accountNani);

                depositActivityLauncher.launch(intent);
            }

        });//End of Withdraw.setOnClickListener()


    }//End of onCreate()



}//End of class