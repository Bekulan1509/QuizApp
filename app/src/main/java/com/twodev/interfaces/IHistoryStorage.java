package com.twodev.interfaces;

import androidx.lifecycle.LiveData;

import com.twodev.models.Result;

import java.util.List;

public interface IHistoryStorage {
    Result qResult(int id);

    int saveResult(Result result);

    LiveData<List<Result>> getAll();
    void delete(int id);
    void deleteAll();


}
