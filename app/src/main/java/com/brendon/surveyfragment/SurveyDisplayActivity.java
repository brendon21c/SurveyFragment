package com.brendon.surveyfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class SurveyDisplayActivity extends FragmentActivity {


    private Button mQuestionButton;
    private Button mUpdateButton;
    private Button mResultsButton;

    private String question;

    //QuestionManager mQuestionManager;
    SurveyDatabase mSurveyDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_view_activity);

        mQuestionButton = (Button) findViewById(R.id.survey_button);
        mUpdateButton = (Button) findViewById(R.id.update_button);
        mResultsButton = (Button) findViewById(R.id.reset_button);

        Bundle bundleQuestion = getIntent().getExtras();

        question = bundleQuestion.getString("survey key");

        mSurveyDatabase = new SurveyDatabase(this);



        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.survey_activity);


                if (frag == null) {

                    Bundle bundleSurvey = new Bundle();
                    bundleSurvey.putString("key", question);
                    frag = new SurveyFragment();
                    frag.setArguments(bundleSurvey);

                    fm.beginTransaction()
                            .add(R.id.survey_activity, frag)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.survey_activity);

                if (frag == null) {

                    frag = new UpdateFragment();

                    fm.beginTransaction()
                            .add(R.id.survey_activity, frag)
                            .addToBackStack(null)
                            .commit();


                }


            }
        });

        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.survey_activity);

                if (frag == null) {

                    Bundle questionResults = new Bundle();
                    questionResults.putString("key", question);
                    frag = new ResultsFragment();
                    frag.setArguments(questionResults);

                    fm.beginTransaction()
                            .add(R.id.survey_activity, frag)
                            .addToBackStack(null)
                            .commit();

                }


            }
        });

    }

    // Sets the result so the list will update on previous screen.
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_OK);

    }
}
