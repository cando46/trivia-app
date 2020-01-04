package com.mentalsegment.triviaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mentalsegment.triviaapp.QuestionContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TriviaApp.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_A + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_B + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_C + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_D + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_CORRECT + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable() {
        QuestionModel q1= new QuestionModel("Test Question A is correct","A","B","C","D",1);
        addQuestion(q1);
    }

    private void addQuestion(QuestionModel questionModel) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,questionModel.getQuestion());
        cv.put(QuestionsTable.COLUMN_ANSWER_A,questionModel.getAnswerA());
        cv.put(QuestionsTable.COLUMN_ANSWER_B,questionModel.getAnswerB());
        cv.put(QuestionsTable.COLUMN_ANSWER_C,questionModel.getAnswerC());
        cv.put(QuestionsTable.COLUMN_ANSWER_D,questionModel.getAnswerD());
        cv.put(QuestionsTable.COLUMN_ANSWER_CORRECT,questionModel.getCorrectAnswer());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);

    }

    public List<QuestionModel> getAllQuestions() {
        List<QuestionModel> questionList = new ArrayList<>();
        db= getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ QuestionsTable.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                QuestionModel question = new QuestionModel();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setAnswerA(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_A)));
                question.setAnswerB(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_B)));
                question.setAnswerC(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_C)));
                question.setAnswerD(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_D)));
                question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_CORRECT)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
