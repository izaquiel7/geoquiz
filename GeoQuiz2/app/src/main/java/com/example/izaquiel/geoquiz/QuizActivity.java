﻿package com.example.izaquiel.geoquiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String tag = "QuizActivity";
    private static final String i_chave = "index";
	private static final int request_code_cheat = 0;
	


    private Button bCheat;
    private Button bTrue;
    private Button bFalse;
    private Button bNext;
    private Button bBack;
    private TextView questionTextView;
    private int currentIndex =0;

    //Questões estão aqui!!!
    Question[] questionBank= new Question[]{
            new Question(R.string.question1, false),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, true),
            new Question(R.string.question6, true),
            new Question(R.string.question7, true),
            new Question(R.string.question8, true),
            new Question(R.string.question9, true),
            new Question(R.string.question10, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "Metodo o create() chamado");
        setContentView(R.layout.activity_quiz);


        if(savedInstanceState !=null) {
            currentIndex = savedInstanceState.getInt(i_chave, 0);
        }

        bBack = (Button) findViewById(R.id.backbuttonId);
        bTrue = (Button) findViewById(R.id.Btrue);
        bFalse = (Button) findViewById(R.id.Bfalse);
        bNext = (Button) findViewById(R.id.nextbuttonId);
        questionTextView = (TextView) findViewById(R.id.textviewid);
        bCheat = (Button) findViewById(R.id.Bcheat);


        bCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Iniciando uma nova atividade (CheatActivity)
                Boolean answerIsTrue = questionBank[currentIndex].isAnswerTrue();

               Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);

              startActivityForResult(intent, request_code_cheat);


            }
        });



        questionTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            currentIndex = (currentIndex+1)%questionBank.length;
            updateQuestion();
        }
    });

        bTrue.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            checkAnswer(true);
        }
    });

        bFalse.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            checkAnswer(false);
        }
    });
    //Seta a questão atual!
    updateQuestion();

        bNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            currentIndex = (currentIndex+1)%questionBank.length;
            updateQuestion();
        }

    });
        bBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (currentIndex == 0){
                currentIndex = questionBank.hashCode();
            }

            currentIndex = (currentIndex-1)%questionBank.length;
            updateQuestion();

        }
    });
}

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questionBank[currentIndex].isAnswerTrue();
        int messageId = (answerIsTrue==userPressedTrue)?
                R.string.t_correto:
                R.string.t_incorreto;
        Toast.makeText(this, messageId,  Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion(){
        int question = questionBank[currentIndex] .getIdTextRes();
        questionTextView.setText(question);
    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.d(tag, "esse negocio chamou onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Log.d(tag, "esse negocio chamou onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();

        Log.d(tag, "esse negocio chamou onPause()");
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.i(tag, "salvando a instancia do estado.");
        savedInstanceState.putInt(i_chave, currentIndex);
    }
    @Override
    protected void onStop(){
        super.onStop();

        Log.d(tag, "esse negocio chamou onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.d(tag, "esse negocio chamou onDestroy()");
    }
}