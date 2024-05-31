package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityDeposit extends AppCompatActivity {


    //Declare objects
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);


        //Initialize components
        Button buttonSubmit = findViewById(R.id.btnSubmitDeposit);
        Button buttonCancel = findViewById(R.id.btnCancelDeposit);
        EditText editTextDepositAmount = findViewById(R.id.etDepositAmount);


        //Get Account object from intent
        Intent intent = getIntent();
        account = intent.getParcelableExtra("account");


        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double depositAmount;

                //Validate input
                try{
                    depositAmount = Double.parseDouble(editTextDepositAmount.getText().toString());
                }
                catch(NumberFormatException e){
                    Toast.makeText(ActivityDeposit.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                    return;
                }

                account.setBalance(account.getBalance() + depositAmount);

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