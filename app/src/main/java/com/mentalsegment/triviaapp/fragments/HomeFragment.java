package com.mentalsegment.triviaapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mentalsegment.triviaapp.QuizActivity;
import com.mentalsegment.triviaapp.QuizDBHelper;
import com.mentalsegment.triviaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button playButton;
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private int highScore;
    TextView HighScoreTv;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        HighScoreTv = view.findViewById(R.id.tv_home_highScore);
        loadHighScore();
        playButton = view.findViewById(R.id.btn_homeFrag_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(getContext(), QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == Activity.RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highScore) {
                    updateScore(score);
                }
            }

        }
    }

    private void updateScore(int score) {

        highScore = score;
        String hsText = "High score: " + highScore;
        HighScoreTv.setText(hsText);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();


    }

    private void loadHighScore(){
        SharedPreferences prefs=this.getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        highScore=prefs.getInt(KEY_HIGHSCORE,0);
        String hsText = "High score: " + highScore;
        HighScoreTv.setText(hsText);
    }
}
