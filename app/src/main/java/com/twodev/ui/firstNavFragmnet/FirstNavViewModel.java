package com.twodev.ui.firstNavFragmnet;

import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.w3c.dom.Text;

public class FirstNavViewModel extends ViewModel {
    public MutableLiveData<String> mResultLiveData = new MutableLiveData<>();
    public MutableLiveData<String> mResultLiveDataSeekBar = new MutableLiveData<>();
    private int count = 0;

    public void test() {
        String result;
        for (int i = 0; i < 10; i++) {
            result = String.valueOf(i);
            Log.d("tag", "result: " + result);
            mResultLiveData.setValue(result);
        }

    }



    public void seekBarWorking(SeekBar seekBar){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("tag1", "onProgressChanged: "+i);
                mResultLiveDataSeekBar.setValue(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void plusMinusWorking(TextView t1, TextView t2) {

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                mResultLiveData.setValue(String.valueOf(count));
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                mResultLiveData.setValue(String.valueOf(count));
            }
        });
    }
}