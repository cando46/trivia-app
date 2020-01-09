package com.mentalsegment.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView scoreTv;
    TextView questionCount;
    TextView timer;
    ImageButton half;
    ImageButton ata;
    ImageButton doubleDip;
    ImageButton skip;
    TextView question;
    Button answerA;
    Button answerB;
    Button answerC;
    Button answerD;
    CoordinatorLayout coordinatorLayout;

    private static final int DELAY_TIME = 1000;
    private static final long COUNTDOWN_IN_MILLIS = 21000;
    public static final String EXTRA_SCORE = "extraScore";

    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<QuestionModel> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuestionModel currentQuestion;
    private int score;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        textColorDefaultCd=timer.getTextColors();
        QuizDBHelper quizDBHelper = new QuizDBHelper(this);
        questionList = quizDBHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
    }

    private void showNextQuestion() {
        //reset();
        setAnswerButtonsEnabled(true);
        setDefaultAnswersButtonColor();
        if (questionCounter < questionCountTotal) {

            currentQuestion = questionList.get(questionCounter);

            question.setText(currentQuestion.getQuestion());
            answerA.setText(currentQuestion.getAnswerA());
            answerB.setText(currentQuestion.getAnswerB());
            answerC.setText(currentQuestion.getAnswerC());
            answerD.setText(currentQuestion.getAnswerD());

            questionCounter++;
            String scoreText = "Score: "+ score;
            String questionCountText = "Question: " + questionCounter + "/" + questionCountTotal;
            questionCount.setText(questionCountText);
            scoreTv.setText(scoreText);
            
            timeLeftInMillis=COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis=0;
                updateCountDownText();
                updateScore(false);
                setAnswerButtonsEnabled(false);
                setCorrectAnswerGreen();
                showNextQuestionWithDelay(DELAY_TIME);
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes=(int)(timeLeftInMillis/1000)/60;
        int seconds=(int)(timeLeftInMillis/1000)%60;

        String timeFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        timer.setText(timeFormatted);
        if(timeLeftInMillis<10000){
            timer.setTextColor(getColor(R.color.colorRed));
        }
        else{
            timer.setTextColor(textColorDefaultCd);
        }

    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private void init() {
        scoreTv = findViewById(R.id.tv_quiz_score);
        questionCount = findViewById(R.id.tv_quiz_questionCount);
        timer = findViewById(R.id.tv_quiz_timer);
        half = findViewById(R.id.img_btn_quiz_5050);
        ata = findViewById(R.id.img_btn_quiz_ATA);
        doubleDip = findViewById(R.id.img_btn_quiz_x2);
        skip = findViewById(R.id.img_btn_quiz_skip);
        question = findViewById(R.id.tv_quiz_questionDescription);
        answerA = findViewById(R.id.btn_quiz_answer_A);
        answerB = findViewById(R.id.btn_quiz_answer_B);
        answerC = findViewById(R.id.btn_quiz_answer_C);
        answerD = findViewById(R.id.btn_quiz_answer_D);
        coordinatorLayout = findViewById(R.id.coordinator_layout_quiz);
        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        half.setOnClickListener(this);
        ata.setOnClickListener(this);
        doubleDip.setOnClickListener(this);
        skip.setOnClickListener(this);
        score = 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_quiz_5050:
                onClick5050();
                break;
            case R.id.img_btn_quiz_ATA:
                onClickATA();
                break;
            case R.id.img_btn_quiz_x2:
                onClickx2();
                break;
            case R.id.img_btn_quiz_skip:
                onClickSkip();
                break;
            case R.id.btn_quiz_answer_A:
                onClickA();
                break;
            case R.id.btn_quiz_answer_B:
                onClickB();
                break;
            case R.id.btn_quiz_answer_C:
                onClickC();
                break;
            case R.id.btn_quiz_answer_D:
                onClickD();
                break;
        }
    }


    private void onClickD() {
        if (currentQuestion.getCorrectAnswer() == 4) {
            updateScore(true);

            // answerD.setBackgroundColor(getColor(R.color.colorGreen));
            answerD.setTextColor(getColor(R.color.colorGreen));
        } else {
            updateScore(false);
            // answerD.setBackgroundColor(getColor(R.color.colorRed));
            answerD.setTextColor(getColor(R.color.colorRed));
            setCorrectAnswerGreen();
        }
        setAnswerButtonsEnabled(false);
        countDownTimer.cancel();
        showNextQuestionWithDelay(DELAY_TIME);
    }

    private void onClickC() {
        if (currentQuestion.getCorrectAnswer() == 3) {
            updateScore(true);
            answerC.setTextColor(getColor(R.color.colorGreen));
        } else {
            updateScore(false);
            answerC.setTextColor(getColor(R.color.colorRed));
            setCorrectAnswerGreen();
        }
        setAnswerButtonsEnabled(false);
        countDownTimer.cancel();
        showNextQuestionWithDelay(DELAY_TIME);

    }

    private void onClickB() {
        if (currentQuestion.getCorrectAnswer() == 2) {
            updateScore(true);
            answerB.setTextColor(getColor(R.color.colorGreen));
        } else {
            updateScore(false);
            answerB.setTextColor(getColor(R.color.colorRed));
            setCorrectAnswerGreen();
        }
        setAnswerButtonsEnabled(false);
        countDownTimer.cancel();
        showNextQuestionWithDelay(DELAY_TIME);

    }

    private void onClickA() {
        if (currentQuestion.getCorrectAnswer() == 1) {
            updateScore(true);
            answerA.setTextColor(getColor(R.color.colorGreen));
        } else {
            updateScore(false);
            answerA.setTextColor(getColor(R.color.colorRed));
            setCorrectAnswerGreen();
        }
        setAnswerButtonsEnabled(false);
        countDownTimer.cancel();
        showNextQuestionWithDelay(DELAY_TIME);

    }

    private void onClickSkip() {

        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Are you sure to skip question", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skip.setVisibility(View.INVISIBLE);
                        updateScore(true);
                        countDownTimer.cancel();
                        showNextQuestion();
                    }
                }).setActionTextColor(getColor(R.color.colorGreen));
        snackbar.show();
    }

    private void onClickx2() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Are you sure to use Double Dip", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doubleDip.setVisibility(View.INVISIBLE);
                    }
                }).setActionTextColor(getColor(R.color.colorGreen));
        snackbar.show();

    }

    private void onClickATA() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Are you sure to use Ask the Audience", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ata.setVisibility(View.INVISIBLE);
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Audience thinks " +
                                currentQuestion.getCorrectAnswer() + ". option is correct", Snackbar.LENGTH_LONG);
                        snackbar1.show();
                    }
                }).setActionTextColor(getColor(R.color.colorGreen));
        snackbar.show();


    }

    private void onClick5050() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Are you sure to use Half/Half", Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        half.setVisibility(View.INVISIBLE);
                        disableTwoWrongOption();
                    }
                }).setActionTextColor(getColor(R.color.colorGreen));
        snackbar.show();


    }

    private void disableTwoWrongOption() {
        int correct = currentQuestion.getCorrectAnswer();
        setAnswerButtonsEnabled(false);
        switch (correct) {
            case 1:
                answerA.setEnabled(true);
                answerC.setEnabled(true);
                break;
            case 2:
                answerB.setEnabled(true);
                answerD.setEnabled(true);
                break;
            case 3:
                answerB.setEnabled(true);
                answerC.setEnabled(true);
                break;
            case 4:
                answerD.setEnabled(true);
                answerA.setEnabled(true);
                break;
        }
    }


    private void updateScore(Boolean isCorrectAnswer) {
        if (isCorrectAnswer) {
            score = score + 10+((int)timeLeftInMillis/1000);
        } else {
            score = score - 5;
        }
    }


    public void setAnswerButtonsEnabled(Boolean bool) {
        answerA.setEnabled(bool);
        answerB.setEnabled(bool);
        answerC.setEnabled(bool);
        answerD.setEnabled(bool);
    }

    public void setCorrectAnswerGreen() {
        switch (currentQuestion.getCorrectAnswer()) {
            case 1:
                answerA.setTextColor(getColor(R.color.colorGreen));
                break;
            case 2:
                answerB.setTextColor(getColor(R.color.colorGreen));
                break;
            case 3:
                answerC.setTextColor(getColor(R.color.colorGreen));
                break;
            case 4:
                answerD.setTextColor(getColor(R.color.colorGreen));
                break;
        }
    }

    public void setDefaultAnswersButtonColor() {
        answerA.setTextColor(getColor(R.color.colorWhite));
        answerB.setTextColor(getColor(R.color.colorWhite));
        answerC.setTextColor(getColor(R.color.colorWhite));
        answerD.setTextColor(getColor(R.color.colorWhite));
    }

    public void showNextQuestionWithDelay(int DelayMs) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showNextQuestion();
            }
        }, DelayMs);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish quiz!", Toast.LENGTH_LONG).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
}
