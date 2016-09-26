package com.brendon.surveyfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class ResultsFragment extends Fragment {

    private TextView mQuestionText;
    private TextView mResultsText;
    private Button mResetButton;

    private String answerOne = SurveyMain.mAnswerkey1;
    private String answerTwo = SurveyMain.mAnswerkey2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.results_fragment, container, false);

        mQuestionText = (TextView) view.findViewById(R.id.question_view);
        mResultsText = (TextView) view.findViewById(R.id.results_view);
        mResetButton = (Button) view.findViewById(R.id.reset_button);

        mQuestionText.setText(SurveyMain.mCurrentSurveyQuestion + "\n");

        mResultsText.setText("The number of people who voted " + answerOne + " is " +
        SurveyMain.surveyBank.get(answerOne) + ". \n" + "The number of people who voted " +
                answerTwo + " is " + SurveyMain.surveyBank.get(answerTwo) + ".");




        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog confirmReset = new AlertDialog.Builder(getActivity())
                        .setTitle("Reset survey results?")
                        .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SurveyMain.surveyBank.put(answerOne, 0);
                                SurveyMain.surveyBank.put(answerTwo, 0);

                            }
                        })
                        .setNegativeButton(R.string.no_button, null)
                        .create();


                confirmReset.show();

            }
        });



        return view;

    }
}
