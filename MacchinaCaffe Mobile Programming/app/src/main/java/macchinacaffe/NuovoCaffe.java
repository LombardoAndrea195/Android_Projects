package com.example.andrea.macchinacaffe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NuovoCaffe extends AppCompatActivity {

    RadioGroup g1,g2, g3, g4, g5, g6;
    Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creacaffe);
        g1 = (RadioGroup)findViewById(R.id.radio1);
        g2 = (RadioGroup)findViewById(R.id.radio2);
        g3 = (RadioGroup)findViewById(R.id.radio3);
        g4 = (RadioGroup)findViewById(R.id.radio4);
        g5 = (RadioGroup)findViewById(R.id.radio5);
        g6 = (RadioGroup)findViewById(R.id.radio6);
        btn = (Button)findViewById(R.id.buttonCrea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String valore1 =
                        ((RadioButton)findViewById(g1.getCheckedRadioButtonId()))
                                .getText().toString();
                final String valore2 =
                        ((RadioButton)findViewById(g2.getCheckedRadioButtonId()))
                                .getText().toString();
                final String valore3 =
                        ((RadioButton)findViewById(g3.getCheckedRadioButtonId()))
                                .getText().toString();
                final String valore4 =
                        ((RadioButton)findViewById(g4.getCheckedRadioButtonId()))
                                .getText().toString();
                final String valore5 =
                        ((RadioButton)findViewById(g5.getCheckedRadioButtonId()))
                                .getText().toString();
                final String valore6 =
                        ((RadioButton)findViewById(g6.getCheckedRadioButtonId()))
                                .getText().toString();

                Intent intent = new Intent(NuovoCaffe.this, PreparaCaffe.class);
                intent.putExtra("Chosen1", valore1);
                intent.putExtra("Chosen2", valore2);
                intent.putExtra("Chosen3", valore3);
                intent.putExtra("Chosen4", valore4);
                intent.putExtra("Chosen5", valore5);
                intent.putExtra("Chosen6", valore6);
                startActivity(intent);

            }
        });
    }

}

