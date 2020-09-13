package com.twodev.ui.firstNavFragmnet;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstNavViewModel extends ViewModel {
   public MutableLiveData<String> mResultLiveData = new MutableLiveData<>();

    public void test() {
        String result;
//        int firstNum = 1;
//        int secondNum = 1;
//        result = String.valueOf(firstNum + secondNum);
        for (int i = 0; i <10 ; i++) {
            result = String.valueOf(i);
            Log.d("tag", "result: "+result);
            mResultLiveData.setValue(result);
        }

    }
}