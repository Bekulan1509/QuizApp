package com.twodev.ui.firstNavFragmnet;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.twodev.quizapp.R;

public class FirstNavFragment extends Fragment {

    private FirstNavViewModel mViewModel;
    private SeekBar seekBar;
    private TextView textView;
    private TextView textViewPlus;
    private TextView textViewMinus;
    private TextView textViewCount;

    public static FirstNavFragment newInstance() {
        return new FirstNavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar = view.findViewById(R.id.seekBar);
        textView = view.findViewById(R.id.TV_amount);
        textViewCount = view.findViewById(R.id.count);
        textViewPlus = view.findViewById(R.id.plus);
        textViewMinus = view.findViewById(R.id.minus);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FirstNavViewModel.class);
       // mViewModel.test();

        mViewModel.seekBarWorking(seekBar);
        mViewModel.mResultLiveDataSeekBar.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText("Question amount: " +s);
            }
        });

        mViewModel.plusMinusWorking(textViewPlus,textViewMinus);

        mViewModel.mResultLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewCount.setText(s);
                //Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });

    }
}