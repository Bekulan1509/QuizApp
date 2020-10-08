package com.twodev.ui.firstNavFragmnet;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.data.network.QuizApiService;
import com.twodev.models.CategoryModel;
import com.twodev.App.QuizApp;

public class FirstNavViewModel extends ViewModel {
    public MutableLiveData<String> mResultLiveData = new MutableLiveData<>();
    public MutableLiveData<CategoryModel> mCategories = new MutableLiveData<>();



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