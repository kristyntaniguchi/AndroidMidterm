package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class Activity2 extends AppCompatActivity {


    int player1Rolls = 0;
    int player2Rolls = 0;

    int maxRolls = 3;
    int pointsToWin = 50;
    boolean isThereAWinner = false;
    int round = 1;

    double player1Pot = 50.00;
    double player2Pot = 50.00;
    double p1BetAmtDouble;
    double p2BetAmtDouble;

    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Initialize images and button
        ImageView dice1 = findViewById(R.id.dice1Img);
        ImageView dice2 = findViewById(R.id.dice2Img);
        ImageView dice3 = findViewById(R.id.dice3Img);
        TextView messageBoard = findViewById((R.id.messageBoard));

        Button nextRound = findViewById(R.id.buttonNextRound);


        //Player 1
        Button rollDiceP1 = findViewById(R.id.buttonRollDiceP1);
        TextView p1ScoreTextView = findViewById(R.id.player1ScoreTextView);
        TextView p1TotalScoreTextView = findViewById(R.id.p1TotalScore);
        EditText p1BetEditView = findViewById(R.id.etP1Bet);
        TextView p1PotTextView= findViewById(R.id.tvP1PotAmt);

        //Player 2
        Button rollDiceP2 = findViewById(R.id.buttonRollDiceP2);
        TextView p2ScoreTextView = findViewById(R.id.player2ScoreTextView);
        TextView p2TotalScoreTextView = findViewById(R.id.p2TotalScore);
        EditText p2BetEditView = findViewById(R.id.etP2Bet);
        TextView p2PotTextView= findViewById(R.id.tvP2PotAmt);

        //Get player pot balances
        String p1PotFormattedString = String.format("%.2f", player1Pot);
        String p2PotFormattedString = String.format("%.2f", player2Pot);

        //Set player pots
        p1PotTextView.setText(String.valueOf(p1PotFormattedString));
        p2PotTextView.setText(String.valueOf(p2PotFormattedString));


        //Set listener for the button and implement methods
        rollDiceP1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                messageBoard.setText("Roll Dice!");

                //Validate bet input
               try{
                   p1BetAmtDouble = Double.parseDouble(p1BetEditView.getText().toString());
               }
               catch(NumberFormatException e){
                   Toast.makeText(Activity2.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                   return;
               }



                //Make sure they've placed a bet
                if(p1BetAmtDouble != 0){
                    //Make sure they have enough to cover the bet
                    if(p1BetAmtDouble > player1Pot){
                        messageBoard.setText("Player 1 has insufficient funds");
                    }
                    else{
                        //Make sure they still have rolls left
                        if(player1Rolls < maxRolls){
                            int d1 = rollDice();
                            int d2 = rollDice();
                            int d3 = rollDice();

                            setDiceImage(dice1, d1);
                            setDiceImage(dice2, d2);
                            setDiceImage(dice3, d3);

                            int score = addDice(d1, d2, d3);
                            player1Rolls++;
                            displayScore(score, p1ScoreTextView);
                        }
                        else{
                            messageBoard.setText("Player 1 has reached the max number of rolls");
                        }
                    }

                }
                else{
                    messageBoard.setText("Player 1, please place your bet");
                }


            }//End of rollDiceP1.onClick
        });//End of rollDiceP1.setOnClickListener

        //Set listener for the button and implement methods
        rollDiceP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                messageBoard.setText("Roll Dice!");

                //Validate bet input
                try{
                    p2BetAmtDouble = Double.parseDouble(p2BetEditView.getText().toString());

                }
                catch(NumberFormatException e){
                    Toast.makeText(Activity2.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Make sure they've placed a bet
                if(p2BetAmtDouble != 0){
                    //Make sure they have enough to cover the bet
                    if(p2BetAmtDouble > player2Pot){
                        messageBoard.setText("Player 2 has insufficient funds");
                    }
                    else{
                        //Make sure they still have rolls left
                        if(player2Rolls < maxRolls){
                            int d1 = rollDice();
                            int d2 = rollDice();
                            int d3 = rollDice();

                            setDiceImage(dice1, d1);
                            setDiceImage(dice2, d2);
                            setDiceImage(dice3, d3);

                            int score = addDice(d1, d2, d3);
                            player2Rolls++;
                            displayScore(score, p2ScoreTextView);
                        }
                        else{
                            messageBoard.setText("Player 2 has reached the max number of rolls");
                        }
                    }

                }
                else{
                    messageBoard.setText("Player 2, please place your bet");
                }

            }//End of rollDiceP2.onClick
        });//End of rollDiceP2.setOnClickListener

        //Add score and move to the next round
        nextRound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Make sure both players have gone
                int totalScoreP1 = 0;
                int totalScoreP2 = 0;


                //Make sure players both have rolled
                if (player1Rolls == 0 && player2Rolls == 0){
                    messageBoard.setText("Both players still needs to roll");
                }
                //Make sure player 1 has rolled
                else if (player1Rolls == 0 ){
                    messageBoard.setText("Player 1 still needs to roll");
                }
                //Make sure player 2 has rolled
                else if(player2Rolls == 0){
                    messageBoard.setText("Player 2 still needs to roll");
                }
                else{
                    //Get player 1 score for the round
                    int p1RoundScore = Integer.parseInt(p1ScoreTextView.getText().toString());
                    //Get player 1 total score
                    int p1CurrentTotalScore = Integer.parseInt(p1TotalScoreTextView.getText().toString());
                    //Update the total score
                    totalScoreP1 = addTotalScore(p1CurrentTotalScore, p1RoundScore);

                    p1TotalScoreTextView.setText(String.valueOf(totalScoreP1));

                    //Reset number of rolls to 0
                    player1Rolls = 0;

                    //Get player 2 score, update total, and display
                    int p2RoundScore = Integer.parseInt(p2ScoreTextView.getText().toString());
                    ;//Integer.parseInt(String.valueOf(p2Score));
                    int p2CurrentTotalScore = Integer.parseInt(p2TotalScoreTextView.getText().toString());
                    ;//Integer.parseInt(String.valueOf(p2TotalScore));
                    totalScoreP2 = addTotalScore(p2CurrentTotalScore, p2RoundScore);

                    p2TotalScoreTextView.setText(String.valueOf(totalScoreP2));

                    //Reset number of rolls to 0
                    player2Rolls = 0;

                    //Add 1 to round
                    round++;
                }

                //Check for winner
                if(totalScoreP1 >= pointsToWin && totalScoreP2 >= pointsToWin){
                    if(totalScoreP1 > totalScoreP2){
                        messageBoard.setText("Player 1 Wins!");
                        //Update pot amounts
                        player1Pot += p2BetAmtDouble;
                        player2Pot -= p2BetAmtDouble;
                        isThereAWinner = true;
                    }
                    else if(totalScoreP2 > totalScoreP1){
                        messageBoard.setText("Player 2 Wins!");
                        player2Pot += p1BetAmtDouble;
                        player1Pot -= p1BetAmtDouble;

                        isThereAWinner = true;
                    }
                    else{
                        messageBoard.setText("Tie Game!");
                        isThereAWinner = true;
                    }
                }
                else if (totalScoreP1 >= pointsToWin) {
                    messageBoard.setText("Player 1 Wins!" );
                    player1Pot += p2BetAmtDouble;
                    player2Pot -= p2BetAmtDouble;
                    isThereAWinner = true;
                }
                else if (totalScoreP2 >= pointsToWin) {
                    messageBoard.setText("Player 2 Wins!");
                    player2Pot += p1BetAmtDouble;
                    player1Pot -= p1BetAmtDouble;
                    isThereAWinner = true;
                }


                //Restart
                if(isThereAWinner){
                    //Set no winner
                    isThereAWinner = false;
                    round = 0;

                    //Get player pot balances
                    String p1PotFormattedString = String.format("%.2f", player1Pot);
                    String p2PotFormattedString = String.format("%.2f", player2Pot);

                    //Update views
                    p1PotTextView.setText(String.valueOf(p1PotFormattedString));
                    p2PotTextView.setText(String.valueOf(p2PotFormattedString));

                }

                if(round == 0){
                    //Reset scores
                    totalScoreP1 = 0;
                    totalScoreP2 = 0;

                    p1ScoreTextView.setText("0");
                    p1TotalScoreTextView.setText("0");
                    p2ScoreTextView.setText("0");
                    p2TotalScoreTextView.setText("0");

                    p1BetEditView.setText("0.00");
                    p2BetEditView.setText("0.00");
                }


            }//End of nextRound.onClick

        });//End of nextRound.setOnClickListener

    }//End of onCreate()


    //Create method to roll the dice and get the number
    private int rollDice(){
        //Make random object
        Random rand = new Random();

        //Set the number range between 1 and 6.
        int diceRollResult = rand.nextInt(6) + 1;

        return  diceRollResult;
    }//End of rollDice()

    private void setDiceImage(ImageView diceImg, int diceRollResult){

        //Set the image according to the roll number
        if(diceRollResult == 1){
            diceImg.setImageResource(R.drawable.dice1);
        }
        else if(diceRollResult == 2){
            diceImg.setImageResource(R.drawable.dice2);
        }
        else if(diceRollResult == 3){
            diceImg.setImageResource(R.drawable.dice3);
        }
        else if(diceRollResult == 4){
            diceImg.setImageResource(R.drawable.dice4);
        }
        else if(diceRollResult == 5){
            diceImg.setImageResource(R.drawable.dice5);
        }
        else {
            diceImg.setImageResource(R.drawable.dice6);
        }
    }//End of setDiceImage()

    private int addDice(int dice1, int dice2, int dice3){
        return dice1 + dice2 + dice3;
    }//End of addScore()

    private void displayScore(int score, TextView textView){
            textView.setText(String.valueOf(score));
    }//End of displayScore()

    private int addTotalScore(int currentScore, int roundScore){
        return currentScore + roundScore;
    }//End of addTotalScore()



}//End of class


