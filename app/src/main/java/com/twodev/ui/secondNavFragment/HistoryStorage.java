package com.twodev.ui.secondNavFragment;

import androidx.lifecycle.LiveData;

import com.twodev.interfaces.IHistoryStorage;
import com.twodev.models.Result;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {
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
