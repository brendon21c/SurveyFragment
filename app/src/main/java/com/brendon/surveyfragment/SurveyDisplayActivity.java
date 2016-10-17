package com.brendon.surveyfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class SurveyDisplayActivity extends FragmentActivity {


    private  Button mQuestionButton;
    private  Button mUpdateButton;
    private  Button mResultsButton;

    QuestionManager mQuestionManager;
    SurveyDatabase mSurveyDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();


        String userQuestion = mQuestionManager.getQuestion();

        mSurveyDatabase = new SurveyDatabase(this);


        mQuestionButton = (Button) findViewById(R.id.survey_button);
        mUpdateButton = (Button) findViewById(R.id.update_button);
        mResultsButton = (Button) findViewById(R.id.reset_button);



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
                            .replace(R.id.activity_survey_main, frag)
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
}
