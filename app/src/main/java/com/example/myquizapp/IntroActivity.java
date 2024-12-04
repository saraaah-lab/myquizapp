package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        Button yourButton = (Button) findViewById(R.id.startQuizButton);

        yourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(IntroActivity.this, SignupActivity.class));
                //Toast.makeText(MainActivity.this,"cbon",Toast.LENGTH_LONG).show();
            }

        });// Ensure you are referencing the correct layout file
    }
}

