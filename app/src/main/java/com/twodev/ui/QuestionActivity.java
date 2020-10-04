package com.twodev.ui;

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

import com.twodev.Adapters.QuestionRecyclerAdapter;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.QuizModel;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.ActivityQuestionBinding;

public class QuestionActivity extends AppCompatActivity implements OnItemClick {
    ActivityQuestionBinding binding;
    QuestionRecyclerAdapter adapter;
    QuestionViewModel questionViewModel;
    Integer posSeekBar;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        Integer posCategorySpnr = getIntent().getIntExtra("keyPos", 0);
        posSeekBar = getIntent().getIntExtra("keyPosSeekBar", 0);
        String difficultySpnr = getIntent().getStringExtra("keyDifficultySpnr");
        questionViewModel.gettingQuestions(posCategorySpnr, posSeekBar, difficultySpnr);
        questionViewModel.mQuestions.observe(this, new Observer<QuizModel>() {
            @Override
            public void onChanged(QuizModel quizModel) {
                Log.e("tag1", "onChanged: " + quizModel.getResults());
                adapter.setList(quizModel.getResults());
            }
        });




        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        binding.setQuestionActivity(this);
        binding.getRoot();
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
        adapter.setOnItemClick(this);
    }

    @Override
    public void onClick(int position) {
        binding.progressbar.setMax(posSeekBar);
        binding.progressbar.setProgress(binding.progressbar.getProgress() + 1);
        binding.recyclerViewQuestion.scrollToPosition(position + 1);

        binding.posTV.setText(position+1+ "/" + posSeekBar);

    }


}