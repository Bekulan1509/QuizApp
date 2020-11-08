package com.twodev.App;

import android.app.Application;

import com.twodev.data.network.QuizApiService;
import com.twodev.interfaces.IHistoryStorage;
import com.twodev.interfaces.IQuizApiClient;
import com.twodev.repository.QuizRepository;
import com.twodev.ui.secondNavFragment.HistoryStorage;

public class QuizApp extends Application {
    public static QuizApiService quizApiService;
    public static QuizRepository quizRepository;
    public static IHistoryStorage iHistoryStorage;
    public static IQuizApiClient iQuizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        quizApiService = new QuizApiService();
        iHistoryStorage = new HistoryStorage();
        iQuizApiClient = new QuizApiService();
        quizRepository = new QuizRepository(iQuizApiClient,iHistoryStorage);
    }
}
