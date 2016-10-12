package com.brendon.surveyfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SurveyDatabase {

    private Context context;
    private SQLiteDatabase db;
    private SQLHelper helper;

    protected static final String DB_NAME = "Survey";
    protected static final int DB_VERSION = 1;
    protected static final String DB_TABLE = "Survey Table";

    protected static final String QUESTION_COL = "Question";
    protected static final String ANSWER_ONE_COL = "Answer one";
    protected static final String ANSWER_TWO_COL = "Answer two";
    protected static final String ANSWER_ONE_SURVEY_COL = "Answer one vote";
    protected static final String ANSWER_TWO_SURVEY_COLL = "Answer two vote";

    private static final String SQLTAG = "SQLHelper" ;


    public SurveyDatabase(Context c) {

        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();

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
            String createSQL = String.format(createSQLbase, QUESTION_COL, ANSWER_ONE_COL, ANSWER_TWO_COL, ANSWER_ONE_SURVEY_COL,
                    ANSWER_TWO_SURVEY_COLL); // Adding new columns to database.
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
