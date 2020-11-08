package com.twodev.ui.questionActivity;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.interfaces.IQuizApiClient;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.QuizModel;
import com.twodev.App.QuizApp;
import com.twodev.ui.resultActivity.ResultActivity;


public class QuestionViewModel extends ViewModel {
    MutableLiveData<QuizModel> mQuestions = new MutableLiveData<>();
    MutableLiveData<Integer> counter = new MutableLiveData<>();
    int position=1;

    public void gettingQuestions(Integer posSeekBar, Integer posCategorySpnr, String difficultySpnr) {
        QuizApp.quizRepository.getQuestions(posSeekBar, posCategorySpnr, difficultySpnr, new IQuizApiClient.QuestionCallBack() {
            @Override
            public void onSuccess(QuizModel result) {
                mQuestions.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void onBackClick() {
        counter.setValue(position--);

    }

    public void onClick(Context context, String difficultySpnr, String textCategory, Integer posSeekBar, float counter, Boolean flagSkip, Boolean flag) {
        if (position >= posSeekBar) {
            start(context, difficultySpnr, textCategory, posSeekBar, counter);
        } else
            this.counter.setValue(position++);
    }


    public static void start(Context context, String difficultySpnr, String textCategory, Integer posSeekBar, float counter) {
        Intent starter = new Intent(context, ResultActivity.class);
        starter.putExtra("keyDifficultySpnr", difficultySpnr);
        starter.putExtra("keyTextCategory", textCategory);
        starter.putExtra("keyPosSeekBar", posSeekBar);
        starter.putExtra("correctAnswer", counter);
        context.startActivity(starter);
    }


    public void checkingLastPos(Context context,int pos, Integer posSeekBar, String difficultySpnr, String textCategory, float counter){
        if (pos == posSeekBar - 1) {
            QuestionViewModel.start(context, difficultySpnr, textCategory, posSeekBar, counter);
        }
    }
}
