package com.mentalsegment.triviaapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mentalsegment.triviaapp.QuizActivity;
import com.mentalsegment.triviaapp.QuizDBHelper;
import com.mentalsegment.triviaapp.R;
import com.mentalsegment.triviaapp.UserScoreBoardModel;
import com.mentalsegment.triviaapp.adapters.UserRankboardAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageButton playButton;
    RecyclerView recyclerView;
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private int highScore;
    public static int sharedHighscore;

    public static int getHighScore() {
        return sharedHighscore;
    }

    TextView HighScoreTv;
    ArrayList<UserScoreBoardModel> userScoreBoardModels;


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
        recyclerView=view.findViewById(R.id.rv_home_users);
        userScoreBoardModels=new ArrayList<>();
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User1","240"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User2","220"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User3","210"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User4","190"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User5","170"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User6","140"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User7","120"));
        userScoreBoardModels.add(new UserScoreBoardModel(R.drawable.ic_person_black_24dp,"User7","100"));

        UserRankboardAdapter userRankboardAdapter=new UserRankboardAdapter(getContext(),userScoreBoardModels);
        recyclerView.setAdapter(userRankboardAdapter);
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
        sharedHighscore=score;
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
