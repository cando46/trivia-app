package com.mentalsegment.triviaapp;

import android.provider.BaseColumns;

public final class QuestionContract {

    private QuestionContract(){}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME="quiz_questions";
        public static final String COLUMN_QUESTION="question";
        public static final String COLUMN_ANSWER_A="answer_a";
        public static final String COLUMN_ANSWER_B="answer_b";
        public static final String COLUMN_ANSWER_C="answer_c";
        public static final String COLUMN_ANSWER_D="answer_d";
        public static final String COLUMN_ANSWER_CORRECT="answer_correct";


    }
}
