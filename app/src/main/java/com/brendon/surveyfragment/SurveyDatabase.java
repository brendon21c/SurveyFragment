package com.brendon.surveyfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SurveyDatabase {

    private Context context;
    private SQLiteDatabase db;
    private SQLHelper helper;

    protected static final String DB_NAME = "Survey";
    protected static final int DB_VERSION = 1;
    protected static final String DB_TABLE = "Survey_Table";

    protected static final String QUESTION_COL = "Question";
    protected static final String ANSWER_ONE_COL = "Answer_one";
    protected static final String ANSWER_TWO_COL = "Answer_two";
    protected static final String ANSWER_ONE_SURVEY_COL = "Answer_one_vote";
    protected static final String ANSWER_TWO_SURVEY_COLL = "Answer_two_vote";

    private static final String SQLTAG = "SQLHelper" ;


    public SurveyDatabase(Context c) {

        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();

    }

    public void close() {

        helper.close();
    }

    public Cursor getall() {

        Cursor cursor = db.query(DB_TABLE, null, null, null, null, null, QUESTION_COL);

        return cursor;

    }



    // Gets all the questions for the textView.
    public List<String> allQuestions() {


        List<String> questions = new ArrayList<>();

        String[] cols = { QUESTION_COL };

        Cursor cursor = db.query(DB_TABLE,cols, null, null, null, null, null);

        String result = "";

        try {

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                result = cursor.getString(cursor.getColumnIndex(QUESTION_COL));
                questions.add(result);

                cursor.moveToNext();

            }

        } finally {

            cursor.close();

        }

        return questions;

    }

    public List<String> getAnswers(String question) {

        List<String> answers = new ArrayList<>();

        String[] cols = { ANSWER_ONE_COL, ANSWER_TWO_COL };

        String where = QUESTION_COL + " = ? ";
        String[] whereArgs = { question };

        Cursor cursor = db.query(DB_TABLE, cols, where, whereArgs, null, null, null);

        String answerOne = "";
        String answerTwo = "";

        try {

            cursor.moveToLast();

            while (!cursor.isAfterLast()) {

                answerOne = cursor.getString(cursor.getColumnIndex(ANSWER_ONE_COL));
                answerTwo = cursor.getString(cursor.getColumnIndex(ANSWER_TWO_COL));

                answers.add(answerOne);
                answers.add(answerTwo);

                if (answers.size() == 2) {

                    break;

                } else {

                    cursor.moveToNext();

                }
            }

        } finally {

            cursor.close();
        }

        return answers;

    }

    // Gets the vote count for the query.
    public List<Integer> getVotes(String question) {


        List<Integer> votes = new ArrayList<>();

        String[] cols = { ANSWER_ONE_SURVEY_COL, ANSWER_TWO_SURVEY_COLL };

        String where = QUESTION_COL + " = ? ";
        String[] whereArgs = { question };

        Cursor cursor = db.query(DB_TABLE, cols, where, whereArgs, null, null, null);

        int voteOne;
        int voteTwo;

        if (cursor.moveToFirst()) {

            voteOne = cursor.getInt(cursor.getColumnIndex(ANSWER_ONE_SURVEY_COL));
            voteTwo = cursor.getInt(cursor.getColumnIndex(ANSWER_TWO_SURVEY_COLL));

            votes.add(voteOne);
            votes.add(voteTwo);

        }

        cursor.close();

        return votes;

    }




    public boolean addNewQuestion(String question, String answerOne, String answerTwo) {

        /*
        This is for setting up intial voting records when the User creates a new question.
         */
        int voteOneStart = 0;
        int voteTwoStart = 0;

        ContentValues newQuestion = new ContentValues();
        newQuestion.put(QUESTION_COL, question);
        newQuestion.put(ANSWER_ONE_COL, answerOne);
        newQuestion.put(ANSWER_TWO_COL, answerTwo);
        newQuestion.put(ANSWER_ONE_SURVEY_COL, voteOneStart);
        newQuestion.put(ANSWER_TWO_SURVEY_COLL, voteTwoStart);

        try {

            db.insertOrThrow(DB_TABLE, null, newQuestion);
            return true;

        } catch (SQLiteConstraintException sqlce) {

            return false;

        }

    }

    public boolean updateYes(int vote, String question) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ANSWER_ONE_SURVEY_COL, vote);

        String where = QUESTION_COL + " = ? ";
        String[] whereArgs = { question };

        int rowMod = db.update(DB_TABLE, contentValues, where, whereArgs);

        if (rowMod == 1) {

            return true;

        } else {

            return false;

        }

    }

    public boolean updateNo(int vote, String question) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ANSWER_TWO_SURVEY_COLL, vote);

        String where = QUESTION_COL + " = ? ";
        String[] whereArgs = { question };

        int rowMod = db.update(DB_TABLE, contentValues, where, whereArgs);

        if (rowMod == 1) {

            return true;

        } else {

            return false;
        }

    }





    /*
    I know I took this Database format from your program, but I wanted some more work with it
    to better understand how it works.
     */

    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c){
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createSQLbase = "CREATE TABLE %s ( %s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER )";
            String createSQL = String.format(createSQLbase, DB_TABLE, QUESTION_COL, ANSWER_ONE_COL, ANSWER_TWO_COL, ANSWER_ONE_SURVEY_COL, ANSWER_TWO_SURVEY_COLL); // Adding new columns to database.
            db.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
            Log.w(SQLTAG, "Upgrade table - drop and recreate it");
        }


    }






}
