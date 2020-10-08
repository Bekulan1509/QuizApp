package com.twodev.ui.questionActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.twodev.Adapters.QuestionRecyclerAdapter;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.QuizModel;
import com.twodev.models.Result;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.ActivityQuestionBinding;
import com.twodev.ui.resultActivity.ResultActivity;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private ActivityQuestionBinding binding;
    private QuestionRecyclerAdapter adapter;
    private QuestionViewModel questionViewModel;
    private Integer posSeekBar;
    private Integer posCategorySpnr;
    private int pos;
    private String difficultySpnr;
    private String textCategory;
    private float counter;
    private Boolean flag = false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        binding.setQuestionActivity(this);
        binding.getRoot();

         posCategorySpnr = getIntent().getIntExtra("keyPos", 0);
        posSeekBar = getIntent().getIntExtra("keyPosSeekBar", 0);
        difficultySpnr = getIntent().getStringExtra("keyDifficultySpnr");
        textCategory = getIntent().getStringExtra("keyTextCategory");

        binding.categorySelectedTV.setText(textCategory);


        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.gettingQuestions(posCategorySpnr, posSeekBar, difficultySpnr);
        questionViewModel.mQuestions.observe(this, new Observer<QuizModel>() {
            @Override
            public void onChanged(QuizModel quizModel) {
                Log.e("tag1", "onChanged: " + quizModel.getResults());
                adapter.setList(quizModel.getResults());
            }
        });


        binding.skipBtn.setOnClickListener(this);
        binding.skipBtn.setOnLongClickListener(this);

        adapter = new QuestionRecyclerAdapter();
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerViewQuestion);

        binding.posTV.setText(0 + "/" + posSeekBar);

        binding.recyclerViewQuestion.setAdapter(adapter);
        binding.recyclerViewQuestion.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public void setOrientation(int orientation) {
                super.setOrientation(RecyclerView.HORIZONTAL);
            }
        });
        adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onClick(int position) {
                pos = position;
                binding.progressbar.setMax(posSeekBar);
                binding.progressbar.setProgress(binding.progressbar.getProgress() + 1);
                binding.recyclerViewQuestion.scrollToPosition(position + 1);
                binding.posTV.setText(position + 1 + "/" + posSeekBar);
                pos += 1;
            }

            @Override
            public void result(Result result) {
                result.setCheckStateFlag(flag);
            }

            @Override
            public void count(float count) {
                counter = count;
                Log.e("tag1", "count: " + counter);
            }

        });

    }

    @Override
    public void onBackPressed() {
        if (pos > 0) {
            flag = true;
            binding.recyclerViewQuestion.scrollToPosition(pos - 1);
            pos--;
            binding.posTV.setText(pos + "/" + posSeekBar);
            binding.progressbar.setProgress(binding.progressbar.getProgress() - 1);
            Log.e("tag1", "onBackPressed: Pressed ");
            Log.e("tag1", "onBackPressed: pos " + pos);
        } else
            super.onBackPressed();
    }


    @Override
    public void onClick(View view) {
        if (pos >= posSeekBar) {
            givingParamIntent();
        } else
            flag = false;
        pos++;
        binding.progressbar.setMax(posSeekBar);
        binding.progressbar.setProgress(binding.progressbar.getProgress() + 1);
        binding.recyclerViewQuestion.scrollToPosition(pos + 1);
        binding.posTV.setText(pos + "/" + posSeekBar);
    }

    @Override
    public boolean onLongClick(View view) {
        givingParamIntent();
        return true;
    }

    private void givingParamIntent() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("keyDifficultySpnr", difficultySpnr);
        intent.putExtra("keyTextCategory", textCategory);
        intent.putExtra("keyPosSeekBar", posSeekBar);
        intent.putExtra("correctAnswer", counter);
        Log.d("tag1", "onClick intent countResult: " + counter);
        startActivity(intent);
    }
}