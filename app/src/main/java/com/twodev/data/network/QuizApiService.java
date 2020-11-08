package com.twodev.data.network;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.twodev.interfaces.IQuizApiClient;
import com.twodev.models.CategoryModel;
import com.twodev.models.QuizModel;

import java.security.Provider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService implements IQuizApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build();

    QuizApi api = retrofit.create(QuizApi.class);



    @Override
    public void getQuestions(Integer amount, Integer category, String difficulty, QuestionCallBack callBack) {
        api.getResponse(amount, category, difficulty).enqueue(new Callback<QuizModel>() {
            @Override
            public void onResponse(Call<QuizModel> call, Response<QuizModel> response) {
                if (response != null && response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    Log.e("tag1", "onResponse:  response body is null or  empty");
                }
            }

            @Override
            public void onFailure(Call<QuizModel> call, Throwable t) {
                callBack.onFailure(new Exception("Response is empty: " + t.getMessage()));
                Log.e("tag1", "onResponse: Error");

            }
        });
    }

    @Override
    public void getCategory(CategoryCallBack categoryCallBack) {
        api.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.isSuccessful() && response != null) {
                    categoryCallBack.onSuccess(response.body());

                } else
                    Log.e("tag1", "onResponse: Response is null");
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                categoryCallBack.onFailure(new Exception("Response is empty: " + t.getMessage()));
            }
        });
    }


    interface QuizApi {
        @GET("api.php")
        Call<QuizModel> getResponse(
                @Query("amount") Integer amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<CategoryModel> getCategory();
    }

}
