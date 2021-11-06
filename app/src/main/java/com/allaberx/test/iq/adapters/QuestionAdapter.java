package com.allaberx.test.iq.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.question.QuestionFragment;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private ArrayList<Question> questions;
    private Context context;

    public QuestionAdapter(Context context, ArrayList<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Question question = questions.get(position);
        TextView textView = viewHolder.textViewName;
        int questionNumber = position + 1;
        textView.setText(context.getResources().getString(R.string.string_question) + " " + questionNumber);

        ImageView imageViewResult = viewHolder.imageViewResult;

        if(question.getCorrect() == question.getSelectedAnswer()){
            imageViewResult.setImageDrawable(context.getResources().getDrawable(R.drawable.correct_answer));
        } else {
            imageViewResult.setImageDrawable(context.getResources().getDrawable(R.drawable.wrong_answer));
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public ImageView imageViewResult;
        public ConstraintLayout constraintLayoutButton;

        private SwitchFragment switchFragment;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewResult = itemView.findViewById(R.id.imageViewResult);
            constraintLayoutButton = itemView.findViewById(R.id.constraintLayoutButton);
            constraintLayoutButton.setOnClickListener(this);

            if (context instanceof SwitchFragment) {
                switchFragment = (SwitchFragment) context;
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {

                switch (view.getId()){
                    case R.id.constraintLayoutButton:
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position + 1);
                        QuestionFragment questionFragment = QuestionFragment.newInstance();
                        questionFragment.setArguments(bundle);
                        switchFragment.setFragment(questionFragment);
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}