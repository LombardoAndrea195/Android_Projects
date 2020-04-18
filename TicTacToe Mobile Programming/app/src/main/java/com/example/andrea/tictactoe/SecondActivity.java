package com.example.andrea.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] griglia = new Button[3][3];
    private boolean Turno = true;
    private int Player1Punteggio;
    private int Player2Punteggio;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private int TotMosse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                griglia[i][j] = findViewById(resID);
                griglia[i][j].setOnClickListener(this);
            }
        }
        Button reset = findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if (Turno) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        TotMosse++;

        if (verificaVittoria()) {
            if (Turno) {
                vittoriaPlayer1();
            } else {
                vittoriaPlayer2();
            }
        } else if (TotMosse == 9) {
            draw();
        } else {
            Turno = !Turno;
        }
    }

    private boolean verificaVittoria() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = griglia[i][j].getText().toString();

            }
        }
        for (int i = 0; i < 3; i++) {
            if ((field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))) {
                return true;
            }}
         for (int i = 0; i < 3; i++) {
            if (((field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))))
                return true;
            }

    if (((field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")))){
            return true;
    }
    if(((field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")))){
        return true;
        }

    return false;
    }
    private void vittoriaPlayer1(){
        Player1Punteggio++;
        Toast.makeText(this,"Vittoria del Player 1",Toast.LENGTH_SHORT ).show();
        updatePointsText();
        resetGriglia();
}
    private void vittoriaPlayer2(){
        Player2Punteggio++;
        Toast.makeText(this,"Vittoria del Player 2",Toast.LENGTH_SHORT ).show();
        updatePointsText();
        resetGriglia();
    }
    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetGriglia();
    }
    private void updatePointsText(){
        textViewPlayer1.setText("player1:"+Player1Punteggio);
        textViewPlayer2.setText("player2:"+Player2Punteggio);
    }
    private void resetGriglia() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                griglia[i][j].setText("");
            }
        }
        Turno=true;
        TotMosse=0;

    }
    private void resetGame(){
        Player1Punteggio=0;
        Player2Punteggio=0;
        updatePointsText();
        resetGriglia();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TotMosse=savedInstanceState.getInt("TotMosse");
        Player2Punteggio=savedInstanceState.getInt("Player2Punteggio");
        Player1Punteggio=savedInstanceState.getInt("Player1Punteggio");
        Turno=savedInstanceState.getBoolean("Turno");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TotMosse",TotMosse);
        outState.putInt("Player1Punteggio",Player1Punteggio);
        outState.putInt("Player2Punteggio",Player2Punteggio);
        outState.putBoolean("Turno",Turno);

    }
}
