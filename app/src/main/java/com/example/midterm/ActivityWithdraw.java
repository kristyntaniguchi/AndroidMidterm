package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityWithdraw extends AppCompatActivity {

    //Declare objects
    private Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        //Initialize
        Button buttonSubmit = findViewById(R.id.btnSubmitWithdrawal);
        Button buttonCancel = findViewById(R.id.btnCancelWithdrawal);
        TextView textViewBalanceWithdrawalDisplay = findViewById(R.id.tvBalanceAmtWithdrawal);
        EditText editTextWithdrawalAmount = findViewById(R.id.etWithdrawalAmount);

        //Get Account object from intent
        Intent intent = getIntent();
        account = intent.getParcelableExtra("account");

        textViewBalanceWithdrawalDisplay.setText(String.format("%.2f",account.getBalance()));

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                double withdrawalAmount = 0;
                try{
                    withdrawalAmount = Double.parseDouble(editTextWithdrawalAmount.getText().toString());
                }
                catch(NumberFormatException e){
                    Toast.makeText(ActivityWithdraw.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Make sure we have enough to withdraw
                if(withdrawalAmount > account.getBalance()){
                    Toast.makeText(ActivityWithdraw.this, "Insufficient funds", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Update balance
                account.setBalance(account.getBalance() - withdrawalAmount);

                //Send updated account info to Activity 3
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updated_account", account);

                setResult(Activity3.RESULT_OK, resultIntent);
                //Go back to Activity3

                finish();

            }
        });//End of buttonSubmit.setOnClickListener()

        buttonCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });//End of buttonCancel.setOnClickListener

    }//End of onCreate()



}//End of class