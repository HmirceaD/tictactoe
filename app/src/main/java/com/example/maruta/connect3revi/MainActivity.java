package com.example.maruta.connect3revi;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageView board;

    ImageView g11;
    ImageView g12;
    ImageView g13;

    ImageView g21;
    ImageView g22;
    ImageView g23;

    ImageView g31;
    ImageView g32;
    ImageView g33;

    ImageView mat_view[][];

    TextView playerView;
    TextView winView;

    Button resetButton;

    int playerTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.board);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(event -> reset());

        g11 = findViewById(R.id.g11);
        g12 = findViewById(R.id.g12);
        g13 = findViewById(R.id.g13);

        g21 = findViewById(R.id.g21);
        g22 = findViewById(R.id.g22);
        g23 = findViewById(R.id.g23);

        g31 = findViewById(R.id.g31);
        g32 = findViewById(R.id.g32);
        g33 = findViewById(R.id.g33);


        playerView = findViewById(R.id.playerView);
        winView = findViewById(R.id.winView);

        playerTurn = 1;

        setTurn();

        mat_view  = new ImageView[3][3];

        pusheen();

        setListenerAux();




    }

    public void makeTurn(ImageView v){

        if(v.getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                || v.getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()){
            return;
        }else {
            if(playerTurn == 1){
                v.setImageResource(R.drawable.red);
                playerTurn = 2;
                checkWin();
                setTurn();
            } else  if(playerTurn == 2){
                v.setImageResource(R.drawable.yellow);
                playerTurn = 1;
                checkWin();
                setTurn();
            }
        }

    }

    public void checkWin(){

        checkRows();
        checkCol();
        checkHorz();

    }

    public void setTurn(){

        if(playerTurn == 1){
            playerView.setText("Player's 1 turn");
        } else if(playerTurn == 2){
            playerView.setText("Player's 2 turn");
        }

    }

    public void pusheen(){

        mat_view[0][0] = g11;
        mat_view[0][1] = g12;
        mat_view[0][2] = g13;

        mat_view[1][0] = g21;
        mat_view[1][1] = g22;
        mat_view[1][2] = g23;

        mat_view[2][0] = g31;
        mat_view[2][1] = g32;
        mat_view[2][2] = g33;
    }

    public void reset(){

        for(ImageView[] v:mat_view){
            for(ImageView v1: v) {
                v1.setImageResource(0);
            }
        }

        playerTurn = 1;
        setTurn();

    }

    public void setListenerAux(){

        for(ImageView[] v: mat_view){
            for(ImageView v1: v){
                v1.setOnClickListener(event -> makeTurn(v1));

            }
        }
    }

    private void checkRows(){
        /*All the conditions*/

        for(int i = 0; i < 2; i++){

            if(mat_view[i][0].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                    && mat_view[i][1].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                    && mat_view[i][2].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()){
                playerTurn = -1;
                playerView.setText("End");
                winView.setText("Player 1 Wins!");

            }else if(mat_view[i][0].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                    && mat_view[i][1].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                    && mat_view[i][2].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()){
                playerTurn = -1;
                playerView.setText("End");
                winView.setText("Player 2 Wins!");
            }
        }
    }

    private void checkCol(){
        for(int i = 0; i < 2; i++){

            if(mat_view[0][i].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                    && mat_view[1][i].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                    && mat_view[2][i].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()){
                playerTurn = -1;
                playerView.setText("End");
                winView.setText("Player 1 Wins!");

            }else if(mat_view[0][i].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                    && mat_view[1][i].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                    && mat_view[2][i].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()){
                playerTurn = -1;
                playerView.setText("End");
                winView.setText("Player 2 Wins!");
            }
        }
    }

    private void checkHorz(){

        if((mat_view[0][0].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                && mat_view[1][1].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                && mat_view[2][2].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState())
                || (mat_view[0][2].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                && mat_view[1][1].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState()
                && mat_view[2][0].getDrawable().getConstantState() == getDrawable(R.drawable.red).getConstantState())){
            playerTurn = -1;
            playerView.setText("End");
            winView.setText("Player 1 Wins!");

        }else if(mat_view[0][0].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                && mat_view[1][1].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                && mat_view[2][2].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                || (mat_view[0][2].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                && mat_view[1][1].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState()
                && mat_view[2][0].getDrawable().getConstantState() == getDrawable(R.drawable.yellow).getConstantState())){
            playerTurn = -1;
            playerView.setText("End");
            winView.setText("Player 2 Wins!");
        }
    }
}
