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

        //Set up button for activity2
        Button buttonActivity2 = findViewById(R.id.buttonActivity2);
        Button buttonActivity3 = findViewById((R.id.buttonActivity3));

        //Set listener event
        buttonActivity2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent to start activity2
                Intent intent = new Intent(MainActivity.this, Activity2.class);

                //Start activity2
                startActivity(intent);
            }

        });

        buttonActivity3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);

                //Start activity2
                startActivity(intent);
            }
        });
    }
}