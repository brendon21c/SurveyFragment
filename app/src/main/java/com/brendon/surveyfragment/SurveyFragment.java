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
import java.util.List;


public class SurveyFragment extends Fragment {

    private TextView mQuestion;
    private Button mButton1;
    private Button mButton2;



    private String mQuestionText;
    private int mVoteOne;
    private int mVoteTwo;

    SurveyDatabase mDatabase;
    QuestionManager mQuestionManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDatabase = new SurveyDatabase(getActivity());

        String question = mQuestionManager.getQuestion();

        List<String> questionBank = mDatabase.question();
        List<String> answerBank = mDatabase.getAnswers();
        List<Integer> votesBank = mDatabase.getVotes(question);



        //mQuestionText = questionBank.get(0);
        String answerOne = answerBank.get(0);
        String answerTwo = answerBank.get(1);

        mVoteOne = votesBank.get(0);
        mVoteTwo = votesBank.get(1);


        View view = inflater.inflate(R.layout.survey_fragment, container, false);

        mQuestion = (TextView) view.findViewById(R.id.survey_question);
        mButton1 = (Button) view.findViewById(R.id.yes_button);
        mButton2 = (Button) view.findViewById(R.id.no_button);



        //mQuestionText = SurveyMain.mCurrentSurveyQuestion;
        mQuestion.setText(question);
        mButton1.setText(answerOne);
        mButton2.setText(answerTwo);


        /*
        This shoudl pull the information from the database and update the "yes" vote column then update.
         */
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mVoteOne = mVoteOne + 1;

                mDatabase.updateYes(mVoteOne, mQuestionText);


            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mVoteTwo = mVoteTwo + 1;

                mDatabase.updateNo(mVoteTwo, mQuestionText);

            }
        });


        return view;

    }
}
