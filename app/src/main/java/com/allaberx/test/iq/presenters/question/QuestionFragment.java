package com.allaberx.test.iq.presenters.question;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;

public class QuestionFragment extends Fragment implements QuestionView {

    SwitchFragment switchFragment;
    QuestionPresenter questionPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionPresenter = new QuestionPresenter(this, getActivity());
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("position", 0);
        Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SwitchFragment) {
            switchFragment = (SwitchFragment) context;
        }
    }

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }
}