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



    public  Button mQuestionButton;
    public  Button mUpdateButton;
    public  Button mResultsButton;


    SurveyDatabase DBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);

        DBManager = new SurveyDatabase(this);

        SurveyQuestion defaultSurvey = new SurveyQuestion("Do you like the default survey question?", "yes", "no", 0, 0);
        DBManager.addSurvey(defaultSurvey);


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



    }

    @Override
    protected void onPause() {
        super.onPause();
        DBManager.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBManager = new SurveyDatabase(this);
    }




}
