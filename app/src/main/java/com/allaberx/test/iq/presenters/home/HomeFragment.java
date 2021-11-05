package com.allaberx.test.iq.presenters.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;

public class HomeFragment extends Fragment implements HomeView {

    HomePresenter homePagePresenter;
    SwitchFragment switchFragment;

    Button buttonExit;
    TextView textViewStartTest;
    TextView textViewRateApp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homePagePresenter = new HomePresenter(this);
        initiationViewElements(view);
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        buttonExit = view.findViewById(R.id.buttonExit);
        textViewStartTest = view.findViewById(R.id.textViewStartTest);
        textViewRateApp = view.findViewById(R.id.textViewRateApp);
        setOnClickListener();
    }

    @Override
    public void setOnClickListener() {
        buttonExit.setOnClickListener(this);
        textViewStartTest.setOnClickListener(this);
        textViewRateApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonExit:
                homePagePresenter.showExitDialog(getActivity());
                break;
            case R.id.textViewStartTest:
//                switchFragment.setFragment(TestFragment.newInstance());
                break;
            case R.id.textViewRateApp:
                homePagePresenter.showRateAppDialog(getActivity());
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}