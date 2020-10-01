package com.twodev.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.twodev.Adapters.QuestionRecyclerAdapter;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.QuestionModel;
import com.twodev.quizapp.R;
import com.twodev.quizapp.databinding.ActivityQuestionBinding;
import com.twodev.ui.QuestionViewModel;

import java.util.List;

public class QuestionActivity extends AppCompatActivity implements OnItemClick {
    ActivityQuestionBinding binding;
    QuestionRecyclerAdapter adapter;
    QuestionViewModel questionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        binding.setQuestionActivity(this);
        binding.getRoot();
        adapter = new QuestionRecyclerAdapter();
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerViewQuestion);
        questionViewModel.listMutableLiveData.observe(this, new Observer<List<QuestionModel>>() {
            @Override
            public void onChanged(List<QuestionModel> questionModels) {
                adapter.setList(questionModels);
            }
        });
        questionViewModel.addingList();
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
        binding.recyclerViewQuestion.scrollToPosition(position+1);
    }
}