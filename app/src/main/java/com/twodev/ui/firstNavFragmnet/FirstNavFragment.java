package com.twodev.ui.firstNavFragmnet;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.MainFragmentBinding;
import com.twodev.ui.QuestionActivity;

public class FirstNavFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirstNavViewModel mViewModel;
    private SeekBar seekBar;
    private TextView textView;
    private Spinner spinnerCategory, spinnerDifficulty;
    MainFragmentBinding binding;

    public static FirstNavFragment newInstance() {
        return new FirstNavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setFirstNavigationFragment(this);
        seekBar = view.findViewById(R.id.seekBar);
        textView = view.findViewById(R.id.TV_amount);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerDifficulty = view.findViewById(R.id.spinner_difficulty);

        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(requireContext(), R.array.categoryes, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
        spinnerCategory.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(requireContext(), R.array.difficulty, android.R.layout.simple_spinner_item);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
        spinnerDifficulty.setOnItemSelectedListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FirstNavViewModel.class);
        mViewModel.seekBarWorking(seekBar);
        mViewModel.mResultLiveDataSeekBar.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText("Question amount: " + s);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void buttonStart() {
        Log.e("ololo", "buttonStart: ololo");
        startActivity(new Intent(getContext(), QuestionActivity.class));
    }
}