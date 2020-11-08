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

import com.twodev.models.CategoryModel;
import com.twodev.models.TriviaCategory;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.MainFragmentBinding;
import com.twodev.ui.questionActivity.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class FirstNavFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirstNavViewModel mViewModel;
    private SeekBar seekBar;
    private TextView textView;
    private Spinner spinnerCategory, spinnerDifficulty;
    MainFragmentBinding binding;
    public Integer pos;
    public String difficultySpnr;
    public Integer posSeekBar = 10;
    private String textCategory;

    public static FirstNavFragment newInstance() {
        return new FirstNavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
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
        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    difficultySpnr = null;
                    Log.e("tag1", "onItemSelected: " + difficultySpnr);
                } else if (i == 1) {
                    difficultySpnr = "easy";
                    Log.e("tag1", "onItemSelected: " + difficultySpnr);
                } else if (i == 2) {
                    difficultySpnr = "medium";
                    Log.e("tag1", "onItemSelected: " + difficultySpnr);
                } else if (i == 3) {
                    difficultySpnr = "hard";
                    Log.e("tag1", "onItemSelected: " + difficultySpnr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("tag1", "onProgressChanged: " + i);
                textView.setText("Question amount: " + i);
                posSeekBar = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FirstNavViewModel.class);
        mViewModel.spinnerAdding();
        mViewModel.mCategories.observe(getActivity(), new Observer<CategoryModel>() {
            @Override
            public void onChanged(CategoryModel categoryModel) {
                List<TriviaCategory> mod;
                mod = categoryModel.getTriviaCategories();
                List<String> name = new ArrayList<>();
                for (TriviaCategory bravo : mod) {
                    name.add(bravo.getName());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategory.setAdapter(adapter);
                    spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            pos = i + 9;
                            textCategory = spinnerCategory.getSelectedItem().toString();
                            Log.d("tag1", "onItemSelected: " + textCategory);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            pos = null;
                        }
                    });
                }
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
        Intent intent = new Intent(getContext(), QuestionActivity.class);
        intent.putExtra("keyPos", pos);
        intent.putExtra("keyTextCategory", textCategory);
        intent.putExtra("keyPosSeekBar", posSeekBar);
        intent.putExtra("keyDifficultySpnr", difficultySpnr);
        startActivity(intent);
    }

}