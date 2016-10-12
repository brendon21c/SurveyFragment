package com.brendon.surveyfragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;



public class SurveyFragment extends Fragment {

    private TextView mQuestion;
    private Button mButton1;
    private Button mButton2;

    //private HashMap<String, Integer> mSurveybank = new HashMap<String, Integer>();
    private String mQuestionText;

    SurveyDatabase mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDatabase = new SurveyDatabase(getActivity());


        View view = inflater.inflate(R.layout.survey_fragment, container, false);

        mQuestion = (TextView) view.findViewById(R.id.survey_question);
        mButton1 = (Button) view.findViewById(R.id.yes_button);
        mButton2 = (Button) view.findViewById(R.id.no_button);

        /*
        Bundle bundle = this.getArguments();

        if (bundle.getSerializable("hash key") != null) {

            mSurveybank = (HashMap<String,Integer>) bundle.getSerializable("hash key");

        } */

        mQuestionText = SurveyMain.mCurrentSurveyQuestion;
        mQuestion.setText(mQuestionText);
        mButton1.setText(SurveyMain.mAnswerkey1);
        mButton2.setText(SurveyMain.mAnswerkey2);


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int temp = SurveyMain.surveyBank.get(SurveyMain.mAnswerkey1);

                SurveyMain.surveyBank.put(SurveyMain.mAnswerkey1, temp + 1);

                System.out.println(SurveyMain.surveyBank.get(SurveyMain.mAnswerkey1));

            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int temp = SurveyMain.surveyBank.get(SurveyMain.mAnswerkey2);

                SurveyMain.surveyBank.put(SurveyMain.mAnswerkey2, temp + 1);

                System.out.println(SurveyMain.surveyBank.get(SurveyMain.mAnswerkey2));

            }
        });







        return view;

    }
}
