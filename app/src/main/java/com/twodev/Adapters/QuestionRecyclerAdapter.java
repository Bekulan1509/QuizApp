package com.twodev.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
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
import com.twodev.models.QuestionModel;
import com.twodev.models.QuestionModelBool;
import com.twodev.quizapp.databinding.QuestionViewHolderBinding;

import java.util.List;

import static com.twodev.quizapp.R.*;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder> {
    private List<QuestionModel> list;

    public void setList(List<QuestionModel> list) {
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
        public void onBind(QuestionModel questionModel) {
            binding.setQuestionModel(questionModel);
            if (getAdapterPosition() == 4) {
                changingContainer(binding.constraintContainer, binding.linearContainer);
                setColor(binding.answerTrueTV, Color.BLUE);
                setColor(binding.answerFalseTV, Color.BLUE);
            } else if (getAdapterPosition() == 5) {
                changingContainer(binding.constraintContainer, binding.linearContainer);
                setColor(binding.answerTrueTV, Color.BLUE);
                setColor(binding.answerFalseTV, Color.BLUE);
            } else if (getAdapterPosition() == 6) {
                changingContainer(binding.linearContainer, binding.constraintContainer);
                binding.answer1TV.setBackgroundColor(Color.WHITE);
                binding.answer2TV.setBackgroundColor(Color.WHITE);
                binding.answer3TV.setBackgroundColor(Color.WHITE);
                binding.answer4TV.setBackgroundColor(Color.WHITE);
            }


            setColor(binding.answer1TV, Color.BLUE);
            setColor(binding.answer2TV, Color.BLUE);
            setColor(binding.answer3TV, Color.BLUE);
            setColor(binding.answer4TV, Color.BLUE);


        }


        public void changingContainer(View invisibleView, View visibleView) {
            invisibleView.setVisibility(View.GONE);
            visibleView.setVisibility(View.VISIBLE);

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
            switch (getAdapterPosition()) {
                case 0:
                    if (view.getId() == id.answer1_TV) {
                        binding.answer1TV.setBackgroundColor(Color.GREEN);
                    } else if (view.getId() == id.answer2_TV) {
                        animation(binding.answer2TV);
                    } else if (view.getId() == id.answer3_TV) {
                        animation(binding.answer3TV);
                    } else if (view.getId() == id.answer4_TV) {
                        animation(binding.answer4TV);
                    }
                    break;
                case 1:
                    if (view.getId() == id.answer2_TV) {
                        binding.answer2TV.setBackgroundColor(Color.GREEN);
                    } else if (view.getId() == id.answer1_TV) {
                        animation(binding.answer1TV);
                    } else if (view.getId() == id.answer3_TV) {
                        animation(binding.answer3TV);
                    } else if (view.getId() == id.answer4_TV) {
                        animation(binding.answer4TV);
                    }
                    break;
                case 2:
                    if (view.getId() == id.answer3_TV) {
                        binding.answer3TV.setBackgroundColor(Color.GREEN);
                    } else if (view.getId() == id.answer1_TV) {
                        animation(binding.answer1TV);
                    } else if (view.getId() == id.answer2_TV) {
                        animation(binding.answer2TV);
                    } else if (view.getId() == id.answer4_TV) {
                        animation(binding.answer4TV);
                    }
                    break;
                case 3:
                case 6:
                    if (view.getId() == id.answer4_TV) {
                        binding.answer4TV.setBackgroundColor(Color.GREEN);
                        
                    } else if (view.getId() == id.answer1_TV) {
                        animation(binding.answer1TV);
                    } else if (view.getId() == id.answer2_TV) {
                        animation(binding.answer2TV);
                    } else if (view.getId() == id.answer3_TV) {
                        animation(binding.answer3TV);
                    }
                    break;
                case 4:
                    if (view.getId() == id.answerTrue_TV) {
                        binding.answerTrueTV.setBackgroundColor(Color.GREEN);
                    } else
                        animation(binding.answerFalseTV);
                    break;
                case 5:
                    if (view.getId() == id.answerFalse_TV) {
                        binding.answerFalseTV.setBackgroundColor(Color.GREEN);
                    } else
                        animation(binding.answerTrueTV);

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
