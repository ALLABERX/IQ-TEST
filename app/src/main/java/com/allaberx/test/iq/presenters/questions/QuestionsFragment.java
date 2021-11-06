package com.allaberx.test.iq.presenters.questions;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.adapters.QuestionAdapter;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.result.ResultFragment;

import java.util.ArrayList;

public class QuestionsFragment extends Fragment implements QuestionsView {

    SwitchFragment switchFragment;
    QuestionsPresenter questionsPresenter;

    ImageView imageViewHome;
    ImageView imageViewBack;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        questionsPresenter = new QuestionsPresenter(this, getActivity());
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        imageViewHome = view.findViewById(R.id.imageViewHome);
        imageViewBack = view.findViewById(R.id.imageViewBack);
        recyclerView = view.findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    @Override
    public void setOnClickListener() {
        imageViewHome.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }

    private void initRecyclerView(){
        ArrayList<Question> allQuestion = questionsPresenter.getAllQuestion();
        QuestionAdapter questionAdapter = new QuestionAdapter(getActivity(), allQuestion);
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewHome:
                    questionsPresenter.showHomeDialog(getActivity());
                break;
            case R.id.imageViewBack:
                    switchFragment.setFragment(ResultFragment.newInstance());
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SwitchFragment) {
            switchFragment = (SwitchFragment) context;
        }
    }

    public static QuestionsFragment newInstance() {
        return new QuestionsFragment();
    }
}