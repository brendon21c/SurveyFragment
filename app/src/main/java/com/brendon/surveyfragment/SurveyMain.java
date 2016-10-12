package com.brendon.surveyfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

/*
There is a side note with this project. The data is being passed by each fragment by accessing
the data directly from the main Activity. I'm not sure if this is best but it is the only way
I could think of. Also you need to press the back button to get rid of the fragments and I
am really not a fan of this.
 */

public class SurveyMain extends FragmentActivity {



    /*
   This section is to provide a starting point for the values
    */
    private static final int  mAnswerStart1 = 0;
    private static final int mAnswerStart2 = 0;

    public static String mCurrentSurveyQuestion = "Do you like fresh baked chocolate chip cookies?";
    public static String mAnswerkey1 = "yes";
    public static String mAnswerkey2 = "no";

    /*
    Survey answer total will be kep in this Hashmap.
     */
    public static HashMap<String,Integer> surveyBank;


    public  Button mQuestionButton;
    public  Button mUpdateButton;
    public  Button mResultsButton;


    SurveyDatabase DBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);

        DBManager = new SurveyDatabase(this);

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

        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.activity_survey_main);

                if (frag == null) {

                    frag = new ResultsFragment();

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
