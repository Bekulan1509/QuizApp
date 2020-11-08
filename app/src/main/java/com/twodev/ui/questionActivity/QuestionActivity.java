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
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.twodev.Adapters.QuestionRecyclerAdapter;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.QuizModel;
import com.twodev.models.Result;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.ActivityQuestionBinding;

import java.util.List;

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
    private Boolean flagSkip = false;
    private boolean flagBack = false;

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
        questionViewModel.gettingQuestions(posSeekBar, posCategorySpnr, difficultySpnr);
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
                ck();
                adapter.notifyDataSetChanged();
                pos = position;
                Log.e("tag1", "onClickCheck: " + pos);

                questionViewModel.checkingLastPos(QuestionActivity.this, pos, posSeekBar, difficultySpnr, textCategory, counter);

                binding.progressbar.setMax(posSeekBar);
                binding.progressbar.setProgress(binding.progressbar.getProgress() + 1);
                binding.recyclerViewQuestion.scrollToPosition(position + 1);
                binding.posTV.setText(position + 1 + "/" + posSeekBar);
                pos += 1;
            }


            @Override
            public void count(float count) {
                counter = count;
                Log.e("tag1", "count: " + counter);
            }

            @Override
            public void resultModel(Result result) {
                result.setBackClickedCheck(flagBack);
            }


        });

    }

    @Override
    public void onBackPressed() {
        onBackClicked();
        if (pos == 0) {
            super.onBackPressed();
        } else
            binding.recyclerViewQuestion.scrollToPosition(pos - 1);
        if (pos != 0) {
            binding.posTV.setText(pos - 1 + "/" + posSeekBar);
        }
        binding.progressbar.setProgress(binding.progressbar.getProgress() - 1);
        Log.e("tag1", "onBackPressed: Pressed ");
        Log.e("tag1", "onBackPressed: pos " + pos);

    }

    public void onBackClicked() {
        questionViewModel.onBackClick();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        ck();
        adapter.notifyDataSetChanged();
        binding.progressbar.setMax(posSeekBar);
        binding.recyclerViewQuestion.scrollToPosition(pos + 1);
        binding.progressbar.setProgress(binding.progressbar.getProgress() + 1);

        binding.posTV.setText(pos + "/" + posSeekBar);

    }

    @Override
    public boolean onLongClick(View view) {
        QuestionViewModel.start(this, difficultySpnr, textCategory, posSeekBar, counter);
        return true;
    }

    public void ck() {
         questionViewModel.onClick(this, difficultySpnr, textCategory, posSeekBar, counter, flagSkip, flag);
                questionViewModel.counter.observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        pos = integer;
                        Log.d("tag1", "MVVM onClick: " + pos);
                    }
                });
            }
        }