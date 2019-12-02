package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    private Button[] mGameButtons = new Button[9];

    private TextView mGameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameStatus = findViewById(R.id.mGameStatus);

        for (int i = 0; i < 9; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            mGameButtons[i] = findViewById(resID);
            mGameButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayButtonClick( v );
                }
            });
        }

        Button mGame = findViewById(R.id.mGame);
        mGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewGameClick();
            }
        });
    }



    private void isGameOver() {
        if (checkForWinner()){
            mGameStatus.setText("You Win");
            return;
        }
        if (checkForCat()){
            mGameStatus.setText("Cat Game");
            return;
        }
        getComputerMove();
        if (checkForWinner()){
            mGameStatus.setText("You Lose");
        }
    }

    private boolean checkForCat() {
        for (int i = 0; i < 9; i++) {
            if (mGameButtons[i].getText().toString().equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForWinner() {
        if (mGameButtons[0].getText().equals(mGameButtons[1].getText())
            && mGameButtons[0].getText().equals(mGameButtons[2].getText())
            && !mGameButtons[0].getText().toString().equals("")){
            return true;
        } else if (mGameButtons[0].getText().equals(mGameButtons[4].getText())
                && mGameButtons[0].getText().equals(mGameButtons[8].getText())
                && !mGameButtons[0].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[0].getText().equals(mGameButtons[3].getText())
                && mGameButtons[0].getText().equals(mGameButtons[6].getText())
                && !mGameButtons[0].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[4].getText().equals(mGameButtons[1].getText())
                && mGameButtons[4].getText().equals(mGameButtons[7].getText())
                && !mGameButtons[4].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[4].getText().equals(mGameButtons[3].getText())
                && mGameButtons[4].getText().equals(mGameButtons[5].getText())
                && !mGameButtons[4].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[4].getText().equals(mGameButtons[2].getText())
                && mGameButtons[4].getText().equals(mGameButtons[6].getText())
                && !mGameButtons[4].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[8].getText().equals(mGameButtons[2].getText())
                && mGameButtons[8].getText().equals(mGameButtons[5].getText())
                && !mGameButtons[8].getText().toString().equals("")) {
            return true;
        } else if (mGameButtons[8].getText().equals(mGameButtons[6].getText())
                && mGameButtons[8].getText().equals(mGameButtons[7].getText())
                && !mGameButtons[8].getText().toString().equals("")) {
            return true;
        }
        return false;
    }

    private void getComputerMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(9);

        if (mGameButtons[randomNumber].getText().toString().equals("")) {
            mGameButtons[randomNumber].setTextColor((getResources().getColor(R.color.blue)));
            mGameButtons[randomNumber].setText("O");

        }else {
            getComputerMove();
        }
    }

    private void onPlayButtonClick(View v) {
        if (!((Button) v).getText().toString().equals("")
            || !mGameStatus.getText().toString().equals("")) {
            return;
        }
        setPlayerMove( v );
        isGameOver();
    }

    private void setPlayerMove(View v) {
        ((Button) v).setTextColor(getResources().getColor(R.color.red));
        ((Button) v).setText("X");

    }

    private void onNewGameClick() {
        for (int i = 0; i < 9; i++) {
            mGameButtons[i].setText("");
         }
        mGameStatus.setText("");
    }
}
