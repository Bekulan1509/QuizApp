package com.twodev.data.network;

import android.accounts.NetworkErrorException;
import android.util.Log;
import com.twodev.models.CategoryModel;
import com.twodev.models.QuizModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build();

    QuizApi api = retrofit.create(QuizApi.class);

    public void getResponses(Integer amount, Integer category, String difficulty, final QuizCallBack callBack) {
        Call<QuizModel> call = api.getResponse(amount, category,difficulty);
        call.enqueue(new Callback<QuizModel>() {
            @Override
            public void onResponse(Call<QuizModel> call, Response<QuizModel> response) {
                if (response.isSuccessful() && response != null) {
                    callBack.onSuccess(response.body());
                    Log.d("tag1", "onResponse: " + response.body().getResults());
                } else
                    Log.d("tag1", "onResponse: is null");

            }

            @Override
            public void onFailure(Call<QuizModel> call, Throwable t) {
                callBack.onFailure(new NetworkErrorException());
                Log.d("tag1", "onFailure:  error");
            }
        });
    }
    public void getCategory(QuizCallBackCategory callBack){
        Call<CategoryModel> call = api.getCategory();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.isSuccessful() && response!=null){
                    callBack.onSuccess(response.body());

                }else
                    Log.e("tag1", "onResponse: Response is null");
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                callBack.onFailure(t);
                Log.e("tag1", "onResponse: Error");
            }
        });
    }

    public interface QuizCallBack {
        void onSuccess(QuizModel quizModel);
        void onFailure(Throwable Exception);
    }

    public interface QuizCallBackCategory {
        void onSuccess(CategoryModel categoryModel);

        void onFailure(Throwable Exception);
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
