package com.brendon.surveyfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class SurveyMain extends FragmentActivity {

    private static final String TAG = "MainActivity";
    public static final String INDEX_KEY= "Survey bank";
    private static final String QUESTION_INDEX_KEY = "Question key";
    private static final String HASH_INDEX_KEY = "Hash key";
    private static final int SURVEY_REQUEST_CODE = 1;
    private static final int UPDATE_REQUEST_CODE = 2;


    /*
   This section is to provide a starting point for the values
    */
    private static final int  mAnswerStart1 = 0;
    private static final int mAnswerStart2 = 0;
    private String answerTemp = "";

    public static String mCurrentSurveyQuestion = "Do you like fresh baked chocolate chip cookies?";
    public static String mAnswerkey1 = "yes";
    public static String mAnswerkey2 = "no";

    /*
    Survey answer total will be kep in this Hashmap.
     */
    public static HashMap<String,Integer> surveyBank;

    private Button mQuestionButton;
    private Button mUpdateButton;
    private Button mResultsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);

        mQuestionButton = (Button) findViewById(R.id.survey_button);
        mUpdateButton = (Button) findViewById(R.id.update_button);
        mResultsButton = (Button) findViewById(R.id.results_button);



        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.activity_survey_main);


                if (frag == null) {

                    frag = new SurveyFragment();

                    fm.beginTransaction()
                            .add(R.id.activity_survey_main, frag)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.activity_survey_main);

                if (frag == null) {

                    frag = new UpdateFragment();

                    fm.beginTransaction()
                            .add(R.id.activity_survey_main, frag)
                            .addToBackStack(null)
                            .commit();

                }


            }
        });



        createSurvey();



    }

    // Updates the surveyBank
    private void createSurvey() {

        if (surveyBank == null) {

            surveyBank = new HashMap<String, Integer>();
            surveyBank.put(mAnswerkey1, mAnswerStart1);
            surveyBank.put(mAnswerkey2, mAnswerStart2);

        }

    }



}
