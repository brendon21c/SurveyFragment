package com.brendon.surveyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateFragment extends Fragment {


    private TextView mQuestionTextView;
    private TextView mAnswerOneTextView;
    private TextView mAnswerTwoTextView;
    private EditText mQuestionEntry;
    private EditText mAnswerOneEntry;
    private EditText mAnswerTwoEntry;
    private Button mUpdateButton;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_fragment, container, false);

        mQuestionTextView = (TextView) view.findViewById(R.id.question_label);
        mQuestionEntry = (EditText) view.findViewById(R.id.question_entry);
        mAnswerOneTextView = (TextView) view.findViewById(R.id.answer_one_label);
        mAnswerOneEntry = (EditText) view.findViewById(R.id.answer_one_entry);
        mAnswerTwoTextView = (TextView) view.findViewById(R.id.question_label);
        mAnswerTwoEntry = (EditText) view.findViewById(R.id.answer_two_entry);
        mUpdateButton = (Button) view.findViewById(R.id.update_button);



        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String questionTemp = mQuestionEntry.getText().toString();
                String answerOneTemp = mAnswerOneEntry.getText().toString();
                String answerTwoTemp = mAnswerTwoEntry.getText().toString();




                if (questionTemp.length() == 0 || answerOneTemp.length() == 0 || answerTwoTemp.length() == 0) {

                    Toast.makeText(getActivity(), "You are missing information", Toast.LENGTH_LONG).show();
                    return;

                } else {

                    SurveyMain.mCurrentSurveyQuestion = questionTemp;
                    SurveyMain.mAnswerkey1 = answerOneTemp;
                    SurveyMain.mAnswerkey2 = answerTwoTemp;

                    SurveyMain.surveyBank.clear();
                    SurveyMain.surveyBank.put(answerOneTemp,0);
                    SurveyMain.surveyBank.put(answerTwoTemp,0);


                }



            }
        });




        return view;

    }
}
