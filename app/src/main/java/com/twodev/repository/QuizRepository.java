package com.twodev.repository;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.twodev.interfaces.IHistoryStorage;
import com.twodev.interfaces.IQuizApiClient;
import com.twodev.models.QuizModel;
import com.twodev.models.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {


    IQuizApiClient iQuizApiClient;
    IHistoryStorage iHistoryStorage;


    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.iQuizApiClient = quizApiClient;
        this.iHistoryStorage = historyStorage;
    }


    @Override
    public void getQuestions(Integer amount, Integer category, String difficulty, QuestionCallBack callBack) {
        iQuizApiClient.getQuestions(amount, category, difficulty, new QuestionCallBack() {
            @Override
            public void onSuccess(QuizModel result) {
                for (Result resultResult : result.getResults()) {
                    resultResult.setQuestion(Html.fromHtml(resultResult.getQuestion()).toString());
                    shuffle(resultResult);
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }
        });
    }

    public void shuffle(Result result) {
        result.getIncorrectAnswers().add(result.getCorrectAnswer());
        Collections.shuffle(result.getIncorrectAnswers());
        Log.d("tag1", "shuffle correct answer: " + result.getCorrectAnswer());
        for (int i = 0; i < result.getIncorrectAnswers().size(); i++) {
            Log.d("tag1", "shuffle:  " + i + ") " + result.getIncorrectAnswers().get(i));
        }
        Log.e("tag1", "shuffle inCorrectAnswers :  " + result.getIncorrectAnswers());

    }


    @Override
    public void getCategory(CategoryCallBack categoryCallBack) {
        iQuizApiClient.getCategory(categoryCallBack);
    }

    @Override
    public Result qResult(int id) {
        return null;
    }

    @Override
    public int saveResult(Result result) {
        return 0;
    }

    @Override
    public LiveData<List<Result>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
