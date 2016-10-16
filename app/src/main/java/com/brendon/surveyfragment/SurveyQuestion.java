package com.brendon.surveyfragment;

import android.content.ContentValues;

/**
 * Created by admin on 10/16/16.
 */

public class SurveyQuestion {

	String question;
	String answer1;
	String answer2;

	int answer1count;
	int answer2count;

	public SurveyQuestion(String question, String answer1, String answer2, int yesAnswers, int noAnswers) {
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer1count = yesAnswers;
		this.answer2count = noAnswers;
	}


}
