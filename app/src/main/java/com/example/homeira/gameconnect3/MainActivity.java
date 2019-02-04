package com.example.homeira.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int playCounter =0;
    int[] playState = {2,2,2,2,2,2,2,2,2};
    int[][] winnerPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer =0; //0:yellow  1:red   2:empty
    boolean gameActive= true;


    public void dropIn(View view){
        ImageView counterImageView = (ImageView) view;
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

        int tappedPosition = Integer.parseInt(counterImageView.getTag().toString());

        if(playState[tappedPosition]==2 && gameActive==true) {
            playState[tappedPosition] = activePlayer;
            playCounter++;
            counterImageView.setTranslationY(-1500);
            if (activePlayer == 0) {
                counterImageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counterImageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counterImageView.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int winnerPosition[] : winnerPositions) {
                if (playState[winnerPosition[0]] == playState[winnerPosition[1]] && playState[winnerPosition[1]] == playState[winnerPosition[2]] && playState[winnerPosition[0]] != 2)
                {
                    String message = "";
                    gameActive = false;
                    if (activePlayer == 0) {
                        message = "Red";
                    } else {
                        message = "Yellow";
                    }

                   winnerTextView.setText(message + " won!");
                }
            }
        }
        if (playCounter == 9 && gameActive==true)
        {
            winnerTextView.setText("Equal!");
        }
    }

    public void playAgain(View view)
    {
        playState= new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        activePlayer =0;
        gameActive= true;
        playCounter=0;
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        winnerTextView.setText("");

        android.support.v7.widget.GridLayout gridLayout= (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayoutId);
        for(int i=0; i< gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
