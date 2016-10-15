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

import java.util.List;


public class ResultsFragment extends Fragment {

    private TextView mQuestionText;
    private TextView mResultsText;
    private Button mResetButton;

    private String mCurrentQuestion = "";

    private String mAnswerOne = "";
    private String mAnswerTwo = "";

    private int mAnswerOneVote;
    private int mAnswerTwoVote;

    SurveyDatabase mDatabase;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDatabase = new SurveyDatabase(getActivity());

        List<String> questionBank = mDatabase.question();
        List<String> answerBank = mDatabase.getAnswers();
        List<Integer> votesBank = mDatabase.getVotes();



        mCurrentQuestion = questionBank.get(0);
        mAnswerOne = answerBank.get(0);
        mAnswerTwo = answerBank.get(1);

        mAnswerOneVote = votesBank.get(0);
        mAnswerTwoVote = votesBank.get(1);


        View view = inflater.inflate(R.layout.results_fragment, container, false);

        mQuestionText = (TextView) view.findViewById(R.id.question_view);
        mResultsText = (TextView) view.findViewById(R.id.results_view);
        mResetButton = (Button) view.findViewById(R.id.reset_button);

        mQuestionText.setText(mCurrentQuestion);

        mResultsText.setText(" The results for the number of people voting for " + mAnswerOne + " are: " + mAnswerOneVote +
        ". \n" + "And the results for the number of people voting for " + mAnswerTwo + " are: " + mAnswerTwoVote + ".");



        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog confirmReset = new AlertDialog.Builder(getActivity())
                        .setTitle("Reset survey results?")
                        .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                mDatabase.updateYes(0, mCurrentQuestion);
                                mDatabase.updateNo(0, mCurrentQuestion);

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
