package com.example.lombi.cf;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


import android.text.Html;
import android.text.format.Time;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onCalcolaClicked( v);
            }
        });
    }


    public void onBtnClicked(View v){
         EditText editTextName = (EditText) findViewById(R.id.nome);
        EditText editTextCognome = (EditText) findViewById(R.id.cognome);
        EditText editTextSesso = (EditText) findViewById(R.id.sesso);
        EditText editgiorno=(EditText) findViewById(R.id.datagiorno);
        EditText editmese=(EditText) findViewById(R.id.datamese);
        EditText editanno=(EditText) findViewById(R.id.dataanno);
        EditText editTextComune = (EditText) findViewById(R.id.luogo);

        Time now = new Time();
        now.setToNow();
        int anno = now.year;
        int mese = now.month;
        int giorno = now.monthDay;

        editTextName.setText("");
        editTextCognome.setText("");
        editTextComune.setText("");

    }



    public void onCalcolaClicked(View v){


        String nome;
        String cognome;
        int giorno;
        int mese;
        int anno;
        String sesso;
        String comuneDiNascita;

        try {
            EditText editTextName = (EditText) findViewById(R.id.nome);
            EditText editTextCognome = (EditText) findViewById(R.id.cognome);
            EditText editTextSesso = (EditText) findViewById(R.id.sesso);
             EditText editgiorno=(EditText) findViewById(R.id.datagiorno);
            EditText editmese=(EditText) findViewById(R.id.datamese);
            EditText editanno=(EditText) findViewById(R.id.dataanno);
            EditText editTextComune = (EditText) findViewById(R.id.luogo);


            nome = editTextName.getText().toString();
            cognome = editTextCognome.getText().toString();
            sesso = (String) editTextSesso.getText().toString();
            giorno= Integer.parseInt(editgiorno.getText().toString());
            mese = Integer.parseInt(editmese.getText().toString());
            anno = Integer.parseInt(editanno.getText().toString());
            comuneDiNascita = editTextComune.getText().toString();


            CodiceFiscale codiceFiscale = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita);
            String codiceFiscaleGenerato = codiceFiscale.calcola();


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Il codice fiscale è" + ":\n" + Html.fromHtml("<center>" + codiceFiscaleGenerato + "</center>"))
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

}