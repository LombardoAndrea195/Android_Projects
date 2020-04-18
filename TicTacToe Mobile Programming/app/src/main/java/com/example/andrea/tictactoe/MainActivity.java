package com.example.andrea.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHome=(Button)findViewById(R.id.buttonGioca);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPage=new Intent(MainActivity.this, SecondActivity.class);
                startActivity(openPage);
            }
        });
    }


    }


