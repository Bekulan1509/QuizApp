package com.twodev.ui.questionActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.data.network.QuizApiService;
import com.twodev.models.QuizModel;
import com.twodev.App.QuizApp;


public class QuestionViewModel extends ViewModel {
    MutableLiveData<QuizModel> mQuestions = new MutableLiveData<>();


    public void gettingQuestions(Integer posCategorySpnr,Integer posSeekBar,String difficultySpnr ) {
        QuizApp.quizApiService.getResponses(posSeekBar, posCategorySpnr, difficultySpnr, new QuizApiService.QuizCallBack() {
            @Override
            public void onSuccess(QuizModel quizModel) {
                mQuestions.setValue(quizModel);
            }

            @Override
            public void onFailure(Throwable Exception) {

            }
        });
    }


}
