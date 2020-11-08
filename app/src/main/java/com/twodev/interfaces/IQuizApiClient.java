package com.twodev.interfaces;

import com.twodev.core.IBaseCallBack;
import com.twodev.models.CategoryModel;
import com.twodev.models.QuizModel;

public interface IQuizApiClient {
    void getQuestions(Integer amount, Integer category, String difficulty, QuestionCallBack callBack);

    void getCategory(CategoryCallBack categoryCallBack);

    interface QuestionCallBack extends IBaseCallBack<QuizModel> {

        @Override
        void onSuccess(QuizModel result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoryCallBack extends IBaseCallBack<CategoryModel> {
        @Override
        void onSuccess(CategoryModel result);

        @Override
        void onFailure(Exception e);
    }
}
