package com.twodev.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twodev.models.QuestionModel;
import com.twodev.models.QuestionModelBool;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends ViewModel {
    MutableLiveData<List<QuestionModel>> listMutableLiveData = new MutableLiveData<>();

    public void addingList() {
        List<QuestionModel> list = new ArrayList<>();
        list.add(new QuestionModel("what is city?", "a", "b", "c", "d"));
        list.add(new QuestionModel("what is country?", "a", "b", "c", "d"));
        list.add(new QuestionModel("what is flat?", "a", "b", "c", "d"));
        list.add(new QuestionModel("what is device?", "a", "b", "c", "d"));
        list.add(new QuestionModelBool("do you like any dogs ? "));
        list.add(new QuestionModelBool("do you like any cats ? "));
        list.add(new QuestionModel("would you like to have a sport bike?", "a", "b", "c", "d"));
        listMutableLiveData.setValue(list);
    }
}
