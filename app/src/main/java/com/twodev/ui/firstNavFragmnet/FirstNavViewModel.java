package com.twodev.ui.firstNavFragmnet;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.data.network.QuizApiService;
import com.twodev.models.CategoryModel;
import com.twodev.models.QuizModel;
import com.twodev.App.QuizApp;

public class FirstNavViewModel extends ViewModel {
    public MutableLiveData<String> mResultLiveData = new MutableLiveData<>();
    public MutableLiveData<String> mResultLiveDataSeekBar = new MutableLiveData<>();
    public MutableLiveData<CategoryModel> mCategories = new MutableLiveData<>();
    public MutableLiveData<QuizModel> mQuestions = new MutableLiveData<>();

    public void test() {
        String result;
        for (int i = 0; i < 10; i++) {
            result = String.valueOf(i);
            Log.d("tag", "result: " + result);
            mResultLiveData.setValue(result);
        }

    }
    public void spinnerAdding() {

        QuizApp.quizApiService.getCategory(new QuizApiService.QuizCallBackCategory() {
            @Override
            public void onSuccess(CategoryModel categoryModel) {
                mCategories.setValue(categoryModel);
            }

            @Override
            public void onFailure(Throwable Exception) {

            }
        });
    }


}