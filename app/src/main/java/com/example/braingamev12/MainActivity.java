package com.example.braingamev12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView time;
    TextView query;
    TextView score;
    Button button;

    //array full of options generated
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView judge;
    TextView optiona;
    TextView optionb;
    TextView optionc;
    TextView optiond;
    int rightAnswer;
    int marks =0;
    int totalQuestions =0;


public void judging(){
    judge.setVisibility(View.VISIBLE);
}
public void  chooseAnswer (View view ){
if (String.valueOf(rightAnswer).equals(view.getTag().toString())){
  judge.setText(" Correct :) ");
    marks++;
}else {
    judge.setText(" Incorrect :(");
    judging();
}
    judging();
totalQuestions++;
score.setText(Integer.toString(marks) + "/" + Integer.toString(totalQuestions));
newQuestion();
}

//function for question
public void newQuestion(){
    //generate random number for query
    Random rand = new Random();
    int a = rand.nextInt(21);
    int b = rand.nextInt(21) ;

    //setting query
    query.setText(Integer.toString(a) + " + " + Integer.toString(b));
//generate position for right answer
    rightAnswer = rand.nextInt(4);

    //clear answers if present before
    answers.clear();

    for(int i=0;i<4;i++){
        if(i == rightAnswer){
            answers.add(a+b);
        }else {
//generating wrong options
            int wrongAnswer = rand.nextInt(41);
//when wrong answer match with right answer, generate more random number
            while (wrongAnswer== a+b) {
                wrongAnswer = rand.nextInt(41);
            }
            answers.add(wrongAnswer);
        }
    }

    //placing generated options in grid
    optiona.setText(Integer.toString(answers.get(0)));
    optionb.setText(Integer.toString(answers.get(1)));
    optionc.setText(Integer.toString(answers.get(2)));
    optiond.setText(Integer.toString(answers.get(3)));
}

//options can not be clicked after the game is over
public void gameover(){
    optiona.setClickable(false);
    optionb.setClickable(false);
    optionc.setClickable(false);
    optiond.setClickable(false);
}

//setting options clickable again upon clicking play again
    public void gameoon(){
        optiona.setClickable(true);
        optionb.setClickable(true);
        optionc.setClickable(true);
        optiond.setClickable(true);
    }
//setting question visible and asnwer status invisible again upon clicking play again
public void onceAgain(){
    query.setVisibility(View.VISIBLE);
    judge.setVisibility(View.INVISIBLE);
    button.setVisibility(View.INVISIBLE);
}

//PLAY AGAIN button function
    public void playagain( View view){
    marks =0;
    totalQuestions=0;
    time.setText("30s");
    gameoon();
    onceAgain();

    // generate question
    newQuestion();

   score.setText(Integer.toString(marks) + "/" + Integer.toString(totalQuestions));

    //timer
    new CountDownTimer(30100, 1000){
        public void onTick(long l){
            time.setText(String.valueOf(l/1000) + "s");
        }
        public void onFinish(){
            query.setVisibility(View.INVISIBLE);
            judge.setText("Finished" );
            button.setVisibility(View.VISIBLE);
           gameover();
        }
    }.start();
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         time = findViewById(R.id.Time);
        query = findViewById(R.id.query);
        score = findViewById(R.id.score);
        judge = findViewById(R.id.judge);
        button = findViewById(R.id.button);
         optiona = findViewById(R.id.optiona);
         optionb = findViewById(R.id.optionb);
         optionc = findViewById(R.id.optionc);
         optiond = findViewById(R.id.optiond);

         playagain(findViewById(R.id.optionc));


    }
}