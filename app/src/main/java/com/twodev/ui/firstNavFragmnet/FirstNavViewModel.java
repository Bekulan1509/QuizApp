package com.twodev.ui.firstNavFragmnet;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.interfaces.IQuizApiClient;
import com.twodev.models.CategoryModel;
import com.twodev.App.QuizApp;

public class FirstNavViewModel extends ViewModel {
    public MutableLiveData<CategoryModel> mCategories = new MutableLiveData<>();


    public void spinnerAdding() {
        QuizApp.quizRepository.getCategory(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(CategoryModel result) {
                mCategories.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }


}