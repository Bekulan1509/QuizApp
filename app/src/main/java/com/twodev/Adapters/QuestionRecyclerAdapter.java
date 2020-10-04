package com.twodev.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.twodev.interfaces.OnItemClick;
import com.twodev.models.Result;
import com.twodev.quizapp.databinding.QuestionViewHolderBinding;

import java.util.ArrayList;
import java.util.List;

import static com.twodev.quizapp.R.*;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder> {
    private List<Result> list = new ArrayList<>();

    public void setList(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    OnItemClick mOnItemClickListener;


    public void setOnItemClick(OnItemClick onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionViewHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout.question_view_holder, parent, false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        QuestionViewHolderBinding binding;

        public QuestionViewHolder(@NonNull final QuestionViewHolderBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.answer1TV.setOnClickListener(this);
            binding.answer2TV.setOnClickListener(this);
            binding.answer3TV.setOnClickListener(this);
            binding.answer4TV.setOnClickListener(this);
            binding.answerTrueTV.setOnClickListener(this);
            binding.answerFalseTV.setOnClickListener(this);


        }

        @SuppressLint("ClickableViewAccessibility")
        public void onBind(Result result) {
            binding.setResult(result);


            if (result.getType().equals("multiple")) {
                binding.linearContainer.setVisibility(View.GONE);
                binding.constraintContainer.setVisibility(View.VISIBLE);
                binding.answer1TV.setBackgroundColor(Color.WHITE);
                binding.answer2TV.setBackgroundColor(Color.WHITE);
                binding.answer3TV.setBackgroundColor(Color.WHITE);
                binding.answer4TV.setBackgroundColor(Color.WHITE);
                binding.answer2TV.setText(result.getIncorrectAnswers().get(0));
                binding.answer3TV.setText(result.getIncorrectAnswers().get(1));
                binding.answer4TV.setText(result.getIncorrectAnswers().get(2));
                Log.e("tag1", "onBindCheck: true");
            } else {
                Log.e("tag1", "onBindCheck: false");
                binding.constraintContainer.setVisibility(View.GONE);
                binding.linearContainer.setVisibility(View.VISIBLE);
                binding.answerTrueTV.setBackgroundColor(Color.WHITE);
                binding.answerFalseTV.setBackgroundColor(Color.WHITE);

                if (result.getCorrectAnswer().equals("True")) {
                    binding.answerTrueTV.setText("Yes");
                } else
                    binding.answerTrueTV.setText("No");

                Log.e("tag1", "checking: "+result.getIncorrectAnswers().get(0));

                if (result.getIncorrectAnswers().get(0).equals("False")){
                    binding.answerFalseTV.setText("No");
                }else
                    binding.answerFalseTV.setText("Yes");
            }
            setColor(binding.answer1TV, Color.BLUE);
            setColor(binding.answer2TV, Color.BLUE);
            setColor(binding.answer3TV, Color.BLUE);
            setColor(binding.answer4TV, Color.BLUE);
            setColor(binding.answerTrueTV, Color.BLUE);
            setColor(binding.answerFalseTV, Color.BLUE);
        }

        @SuppressLint("ClickableViewAccessibility")
        public void setColor(final TextView v, final int color) {
            v.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.setBackgroundColor(color);
                            v.setTextColor(Color.WHITE);

                            return false;
                        case MotionEvent.ACTION_UP:
                            v.setBackgroundColor(Color.WHITE);
                            v.setTextColor(Color.BLACK);
                            return false;
                    }

                    return false;
                }
            });
        }


        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case id.answer1_TV:
                    binding.answer1TV.setBackgroundColor(Color.GREEN);
                    break;
                case id.answer2_TV:
                    animation(binding.answer2TV);
                    break;
                case id.answer3_TV:
                    animation(binding.answer3TV);
                    break;
                case id.answer4_TV:
                    animation(binding.answer4TV);
                    break;
                case id.answerTrue_TV:
                    binding.answerTrueTV.setBackgroundColor(Color.GREEN);
                    break;
                case id.answerFalse_TV:
                    animation(binding.answerFalseTV);
                    break;

            }
            customTimer();

        }

        public void animation(View v) {
            YoYo.with(Techniques.Tada).duration(700).repeat(1).playOn(v);
            v.setBackgroundColor(Color.RED);
        }

        public void customTimer() {
            new CountDownTimer(1100, 100) {
                @Override
                public void onTick(long l) {
                }

                @Override
                public void onFinish() {
                    mOnItemClickListener.onClick(getAdapterPosition());
                }
            }.start();
        }
    }
}
