package com.example.andrea.macchinacaffe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PreparaCaffe extends AppCompatActivity {

    TextView tx, tx2,tx3, tx4,tx5, tx6;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparacaffe);
        tx = (TextView)findViewById(R.id.textView1);
        tx2 = (TextView)findViewById(R.id.textView2);
        tx3 = (TextView)findViewById(R.id.textView3);
        tx4 = (TextView)findViewById(R.id.textView4);
        tx5 = (TextView)findViewById(R.id.textView5);
        tx6 = (TextView)findViewById(R.id.textView6);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mes = extras.getString("Chosen1");
            String mes2 = extras.getString("Chosen2");
            String mes3 = extras.getString("Chosen3");
            String mes4 = extras.getString("Chosen4");
            String mes5 = extras.getString("Chosen5");
            String mes6 = extras.getString("Chosen6");
            tx.setText("Caffè "+ mes);
            tx2.setText(mes2);
            tx3.setText(mes3);
            tx4.setText(mes4);
            tx5.setText(mes5);
            tx6.setText(mes6);

        }
    }
}

