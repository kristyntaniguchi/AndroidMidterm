package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up buttons for activities
        Button buttonActivity2 = findViewById(R.id.buttonActivity2);
        Button buttonActivity3 = findViewById((R.id.buttonActivity3));


        //Activity 2 Button Click listener
        buttonActivity2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent to start activity2
                Intent intent = new Intent(MainActivity.this, Activity2.class);

                //Start activity2
                startActivity(intent);
            }

        });//End of buttonActivity2.setOnClickListener()

        //Activity 3 Button Click listener
        buttonActivity3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);

                //Start activity2
                startActivity(intent);
            }
        });//End of buttonActivity3.setOnClickListener()




    }//End of onCreate()

}//End of class