package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity {

    /**
     * SharedPreference Object that has the defaultSharedPreference so the whole app can share data
     */
    SharedPreferences sharedPref;

    private Questions Question = new Questions();
    private TextView QuestionView;
    private Button Back;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    public int QuestionNum = 0;

    /**
     * If user is on first page, show cancel instead of just static "back"
     */
    private boolean _cancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        QuestionView = (TextView)findViewById(R.id.question);
        Button1 = (Button)findViewById(R.id.answer1);
        Button2 = (Button)findViewById(R.id.answer2);
        Button3 = (Button)findViewById(R.id.answer3);
        Button4 = (Button)findViewById(R.id.answer4);
        Back = (Button)findViewById(R.id.Back);

        QuestionView.setText(Question.getQuestion(QuestionNum));
        Button1.setText(Question.getAnswer(QuestionNum,0));
        Button2.setText(Question.getAnswer(QuestionNum,1));
        Button3.setText(Question.getAnswer(QuestionNum,2));
        Button4.setText(Question.getAnswer(QuestionNum,3));

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion(QuestionNum);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion(QuestionNum);
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion(QuestionNum);
            }
        });
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion(QuestionNum);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastQuestion();
            }
        });
        Back.setText("Skip survey");
    }

    private void nextQuestion(int a){

        //if cancel was false, the user was on first question, change text to SKIP SURVEY
        if(_cancel){
            _cancel = true;
            Back.setText("Back");
        }

        a++;

        if(a < Question.getSurveySize()){
            QuestionView.setText(Question.getQuestion(a));
            Button1.setText(Question.getAnswer(a, 0));
            Button2.setText(Question.getAnswer(a, 1));
            Button3.setText(Question.getAnswer(a, 2));
            Button4.setText(Question.getAnswer(a, 3));
        }
        else {
            //we have answered the last question
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("SurveyTaken", true);
            editor.apply();
//            System.out.println("HERE 2: " + sharedPref.getBoolean("SurveyTaken",false));
            finish();
        }
    }

    private void lastQuestion(){
        if(QuestionNum != 0){

            if(!_cancel && QuestionNum == 1){
                _cancel = true;
                Back.setText("CANCEL");
            }

            QuestionNum--;
            QuestionView.setText(Question.getQuestion(QuestionNum));
            Button1.setText(Question.getAnswer(QuestionNum,0));
            Button2.setText(Question.getAnswer(QuestionNum,1));
            Button3.setText(Question.getAnswer(QuestionNum,2));
            Button4.setText(Question.getAnswer(QuestionNum,3));
        }else {
            finish();
        }


    }
}