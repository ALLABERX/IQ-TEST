package com.allaberx.test.iq.presenters.result;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.presenters.questions.QuestionsFragment;

public class ResultFragment extends Fragment implements ResultView {

    SwitchFragment switchFragment;
    ResultPresenter resultPresenter;

    ImageView imageViewHome;
    Button buttonViewResults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        resultPresenter = new ResultPresenter(this);
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        imageViewHome = view.findViewById(R.id.imageViewHome);
        buttonViewResults = view.findViewById(R.id.buttonViewResults);
    }

    @Override
    public void setOnClickListener() {
        imageViewHome.setOnClickListener(this);
        buttonViewResults.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewHome:
                resultPresenter.showHomeDialog(getActivity());
                break;
            case R.id.buttonViewResults:
                switchFragment.setFragment(QuestionsFragment.newInstance());
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

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }
}