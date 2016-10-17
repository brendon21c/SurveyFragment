package com.brendon.surveyfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SurveyMain extends FragmentActivity {


    private ListView mQuestionTextView;


    private List<String> mQuestionBankImport;
    private ArrayList<String> mQuestionBankList;
    private ArrayAdapter<String> mQuestionAdapter;

    private static final String surveyKey = "survey key";



    SurveyDatabase DBManager;
    QuestionManager mQuestionManager;



    // Allows for this information to be called and recycled.
    private ArrayList<String> updateList() {

        mQuestionBankImport = DBManager.allQuestions(); // Stores all of the questions

        mQuestionBankList = new ArrayList<String>();

        // Adds the imported question into the list for the adapter.
        for (int i = 0 ; i < mQuestionBankImport.size(); i++ ) {

            mQuestionBankList.add(mQuestionBankImport.get(i));

        }

        mQuestionAdapter = new ArrayAdapter<String>(this, R.layout.list_view,
                R.id.list_item_text, mQuestionBankList);

        return mQuestionBankList;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_main);

        DBManager = new SurveyDatabase(this);

        updateList();


        mQuestionTextView = (ListView) findViewById(R.id.questions_list_view);

        mQuestionTextView.setAdapter(mQuestionAdapter);


        mQuestionTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String userSelection = mQuestionTextView.getItemAtPosition(i).toString();

                mQuestionManager = new QuestionManager();
                mQuestionManager.setQuestion(userSelection);

                Intent intent = new Intent(SurveyMain.this, SurveyDisplayActivity.class);

                startActivity(intent);



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

        updateList();
    }




}
