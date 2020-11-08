package com.twodev.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Html;
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
    Result generalResult;

    private float counter;


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
            generalResult = result;
            Log.d("tag1", "flag: " + result.getVisible());


            mOnItemClickListener.resultModel(result);


            buttonEnabledColor(!result.getVisible());


            binding.answer1TV.setBackgroundColor(Color.WHITE);
            binding.answer2TV.setBackgroundColor(Color.WHITE);
            binding.answer3TV.setBackgroundColor(Color.WHITE);
            binding.answer4TV.setBackgroundColor(Color.WHITE);
            binding.answerTrueTV.setBackgroundColor(Color.WHITE);
            binding.answerFalseTV.setBackgroundColor(Color.WHITE);

            if (result.getType().equals("multiple")) {
                binding.linearContainer.setVisibility(View.GONE);
                binding.constraintContainer.setVisibility(View.VISIBLE);

                binding.answer1TV.setText(Html.fromHtml(result.getIncorrectAnswers().get(0)));
                binding.answer2TV.setText(Html.fromHtml(result.getIncorrectAnswers().get(1)));
                binding.answer3TV.setText(Html.fromHtml(result.getIncorrectAnswers().get(2)));
                binding.answer4TV.setText(Html.fromHtml(result.getIncorrectAnswers().get(3)));
                Log.e("tag1", "onBindCheck: multiple");


            } else {
                Log.e("tag1", "onBindCheck:true & false");
                binding.constraintContainer.setVisibility(View.GONE);
                binding.linearContainer.setVisibility(View.VISIBLE);

                if (result.getIncorrectAnswers().get(0).equals("True")) {
                    binding.answerTrueTV.setText("Yes");
                } else {
                    binding.answerTrueTV.setText("No");
                }
                if (result.getIncorrectAnswers().get(1).equals("True")) {
                    binding.answerFalseTV.setText("Yes");
                } else
                    binding.answerFalseTV.setText("No");


            }
            setColorOnTouch(binding.answer1TV, Color.BLUE);
            setColorOnTouch(binding.answer2TV, Color.BLUE);
            setColorOnTouch(binding.answer3TV, Color.BLUE);
            setColorOnTouch(binding.answer4TV, Color.BLUE);
            setColorOnTouch(binding.answerTrueTV, Color.BLUE);
            setColorOnTouch(binding.answerFalseTV, Color.BLUE);


            if (result.getVisible()) {
                switch (result.getChosen()) {
                    case 0:
                        binding.answer1TV.setBackgroundColor(Color.RED);
                        animation(binding.answer1TV);

                        binding.answerTrueTV.setBackgroundColor(Color.RED);
                        animation(binding.answerTrueTV);
                        break;
                    case 1:
                        binding.answer2TV.setBackgroundColor(Color.RED);
                        animation(binding.answer2TV);
                        binding.answerFalseTV.setBackgroundColor(Color.RED);
                        animation(binding.answerFalseTV);
                        break;
                    case 2:
                        binding.answer3TV.setBackgroundColor(Color.RED);
                        animation(binding.answer3TV);
                        break;
                    case 3:
                        binding.answer4TV.setBackgroundColor(Color.RED);
                        animation(binding.answer4TV);
                        break;
                    default:
                        Log.e("tag1", "onBind switch visible: DEFAULT ERROR ");

                }


            }
            result.setVisible(true);

        }

//        @RequiresApi(api = Build.VERSION_CODES.M)
//        private void showCorrectButton(Question question) {
//            String correctAnc = question.getCorrectAnswer();
//            int positionCorrectAnc = 0;
//            for (int i = 0; i < question.getIncorrectAnswers().size(); i++) {
//                if (correctAnc.equals(question.getIncorrectAnswers().get(i)))
//                    positionCorrectAnc = i;
//            }
//            switch (positionCorrectAnc) {
//                case 0:
//                    item.button1.setBackgroundResource(R.drawable.item_button_2);
//                    item.button1.setTextAppearance(R.style.item_btn_text);
//
//                    item.type2Button.setBackgroundResource(R.drawable.item_button_2);
//                    item.type2Button.setTextAppearance(R.style.item_btn_text);
//                    break;
//                case 1:
//                    item.button2.setBackgroundResource(R.drawable.item_button_2);
//                    item.button2.setTextAppearance(R.style.item_btn_text);
//
//                    item.type2Button1.setBackgroundResource(R.drawable.item_button_2);
//                    item.type2Button1.setTextAppearance(R.style.item_btn_text);
//                    break;
//                case 2:
//                    item.button3.setBackgroundResource(R.drawable.item_button_2);
//                    item.button3.setTextAppearance(R.style.item_btn_text);
//                    break;
//                case 3:
//                    item.button4.setBackgroundResource(R.drawable.item_button_2);
//                    item.button4.setTextAppearance(R.style.item_btn_text);
//                    break;
//            }
//
//        }



        @SuppressLint("ClickableViewAccessibility")
        public void setColorOnTouch(final TextView v, final int color) {
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
                    correctCheck(binding.answer1TV, 0);
                    break;
                case id.answer2_TV:
                    correctCheck(binding.answer2TV, 1);
                    break;
                case id.answer3_TV:
                    correctCheck(binding.answer3TV, 2);
                    break;
                case id.answer4_TV:
                    correctCheck(binding.answer4TV, 3);
                    break;

                case id.answerTrue_TV:
                    correctCheck(binding.answerTrueTV, 0);
                    break;
                case id.answerFalse_TV:
                    correctCheck(binding.answerFalseTV, 1);
                    break;
            }
            mOnItemClickListener.count(counter);

        }


        public void correctCheck(TextView textView, int number) {

            if (textView.getText().equals("Yes") && generalResult.getCorrectAnswer().equals("True")) {
                forCorrectCheck(textView, number);
            } else if (textView.getText().equals("No") && generalResult.getCorrectAnswer().equals("False")) {
                forCorrectCheck(textView, number);

            }  if (generalResult.getCorrectAnswer().equals(textView.getText().toString())) {
                forCorrectCheck(textView, number);
                Log.e("tag2", "correctCheck: it is worked "+number);
            } else {
                textView.setBackgroundColor(Color.RED);
                generalResult.setChosen(number);
                customTimer();
            }
        }


        public void forCorrectCheck(TextView textView, int number) {
            binding.getResult().setChosen(number);
            textView.setBackgroundColor(Color.GREEN);
            counter++;
            customTimer();
        }

        public void animation(View v) {
            YoYo.with(Techniques.Tada).duration(700).repeat(1).playOn(v);
            v.setBackgroundColor(Color.BLUE);
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

        public void buttonEnabledColor(boolean b) {
            if (generalResult.getCorrectAnswer().equals(binding.answer1TV.toString())) {
                binding.answer1TV.setBackgroundColor(Color.GREEN);
            } else if (generalResult.getCorrectAnswer().equals(binding.answer2TV.toString())) {
                binding.answer2TV.setBackgroundColor(Color.GREEN);
            } else if (generalResult.getCorrectAnswer().equals(binding.answer3TV.toString())) {
                binding.answer3TV.setBackgroundColor(Color.GREEN);
            } else if (generalResult.getCorrectAnswer().equals(binding.answer4TV.toString())) {
                binding.answer4TV.setBackgroundColor(Color.GREEN);
            }
            binding.answer1TV.setEnabled(b);
            binding.answer2TV.setEnabled(b);
            binding.answer3TV.setEnabled(b);
            binding.answer4TV.setEnabled(b);

            binding.answerTrueTV.setBackgroundColor(Color.GREEN);
            binding.answerTrueTV.setEnabled(b);
            binding.answerFalseTV.setEnabled(b);
        }


    }
}
