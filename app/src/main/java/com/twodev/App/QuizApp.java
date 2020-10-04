package com.twodev.App;

import android.app.Application;

import com.twodev.data.network.QuizApiService;

public class QuizApp extends Application {
    public static QuizApiService quizApiService;
    @Override
    public void onCreate() {
        super.onCreate();
        quizApiService = new QuizApiService();
    }
}
