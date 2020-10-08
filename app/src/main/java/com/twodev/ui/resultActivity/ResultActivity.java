package com.twodev.ui.resultActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.twodev.quizapp.MainActivity;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private String difficulty;
    private String textCategory;
    private Integer posSeekBar;
    private Float correctAnswer;
    private ActivityResultBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        binding.setResultActivity(this);
        binding.getRoot();


        difficulty = getIntent().getStringExtra("keyDifficultySpnr");
        textCategory = getIntent().getStringExtra("keyTextCategory");
        posSeekBar = getIntent().getIntExtra("keyPosSeekBar", 0);
        correctAnswer = getIntent().getFloatExtra("correctAnswer", 0);
        if (difficulty == null) {
            binding.difficultyTypeTV.setText("All");
        } else {
            binding.difficultyTypeTV.setText(difficulty);
        }
        binding.correctAnswersAmountTV.setText(Math.round(correctAnswer) + "/" + posSeekBar);
        binding.questionPocentageTV.setText(Math.round(correctAnswer/posSeekBar*100)+"%");
        binding.categoryResultTV.setText("Category: "+textCategory);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
}