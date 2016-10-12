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

    private static final int QUESTION_COL = 0;
    private static final int ANSWER_ONE_COL = 1;
    private static final int ANSWER_TWO_COL = 2;
    private static final int ANSWER_ONE_VOTE_COL = 3;
    private static final int ANSWER_TWO_VOTE_COL = 4;


    //private HashMap<String, Integer> mSurveybank = new HashMap<String, Integer>();
    private String mQuestionText;

    SurveyDatabase mDatabase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDatabase = new SurveyDatabase(getActivity());
        final Cursor cursor = mDatabase.getall();


        View view = inflater.inflate(R.layout.survey_fragment, container, false);

        mQuestion = (TextView) view.findViewById(R.id.survey_question);
        mButton1 = (Button) view.findViewById(R.id.yes_button);
        mButton2 = (Button) view.findViewById(R.id.no_button);



        //mQuestionText = SurveyMain.mCurrentSurveyQuestion;
        mQuestion.setText(cursor.getString(QUESTION_COL));
        mButton1.setText(cursor.getString(ANSWER_ONE_COL));
        mButton2.setText(cursor.getString(ANSWER_TWO_COL));


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String questionTemp = cursor.getString(QUESTION_COL);

                int answeVoteTemp = cursor.getInt(ANSWER_ONE_VOTE_COL) + 1;

                mDatabase.updateYes(answeVoteTemp, questionTemp);
                mDatabase.getall();

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
