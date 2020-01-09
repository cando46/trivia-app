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
        QuestionModel q1= new QuestionModel("What year was the very first model of the iPhone released ?",
                "2006","2007","2008","2005",2);
        insertQuestion(q1);
        QuestionModel q2= new QuestionModel("What’s the shortcut for the “copy” function ?",
                "Ctrl + V","Ctrl + P","Ctrl + C","Ctrl + W",3);
        insertQuestion(q2);
        QuestionModel q3= new QuestionModel("What country won the very first FIFA World Cup in 1930?",
                "Uruguay","France","Turkey","Zimbabwe",1);
        insertQuestion(q3);
        QuestionModel q4= new QuestionModel("Which planet has the most gravity ?",
                "Earth","Venus","Saturn","Jupiter",4);
        insertQuestion(q4);
        QuestionModel q5= new QuestionModel("How many molecules of oxygen does ozone have ?",
                "1","2","3","4",3);
        insertQuestion(q5);
        QuestionModel q6= new QuestionModel("Which country produces the most coffee in the world ?",
                "Turkey","Germany","China","Brazil",4);
        insertQuestion(q6);
        QuestionModel q7= new QuestionModel("Which country invented tea ?",
                "France","Italy","China","Turkey",3);
        insertQuestion(q7);
        QuestionModel q8= new QuestionModel("How many films did Sean Connery play James Bond in ?",
                "4","5","7","9",3);
        insertQuestion(q8);
        QuestionModel q9= new QuestionModel("In what year was the first episode of South Park aired?",
                "1997","1998","1999","2000",1);
        insertQuestion(q9);
        QuestionModel q10= new QuestionModel("How many hearts does an octopus have ?",
                "3","2","1","None",1);
        insertQuestion(q10);

    }

    private void insertQuestion(QuestionModel questionModel) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,questionModel.getQuestion());
        cv.put(QuestionsTable.COLUMN_ANSWER_A,questionModel.getAnswerA());
        cv.put(QuestionsTable.COLUMN_ANSWER_B,questionModel.getAnswerB());
        cv.put(QuestionsTable.COLUMN_ANSWER_C,questionModel.getAnswerC());
        cv.put(QuestionsTable.COLUMN_ANSWER_D,questionModel.getAnswerD());
        cv.put(QuestionsTable.COLUMN_ANSWER_CORRECT,questionModel.getCorrectAnswer());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);

    }
    public void addQuestion(QuestionModel questionModel){
        db=getWritableDatabase();
        insertQuestion(questionModel);
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
