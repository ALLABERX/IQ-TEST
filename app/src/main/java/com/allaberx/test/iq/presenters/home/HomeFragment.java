package com.allaberx.test.iq.presenters.home;

import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE_SUFFIX_EN;
import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE_SUFFIX_RU;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.presenters.age.AgeFragment;

import java.util.Locale;

public class HomeFragment extends Fragment implements HomeView {

    HomePresenter homePagePresenter;
    SwitchFragment switchFragment;

    ImageButton imageButtonLanguage;
    Button buttonExit;
    TextView textViewStartTest;
    TextView textViewRateApp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homePagePresenter = new HomePresenter(this, getActivity());
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        imageButtonLanguage = view.findViewById(R.id.imageButtonLanguage);
        buttonExit = view.findViewById(R.id.buttonExit);
        textViewStartTest = view.findViewById(R.id.textViewStartTest);
        textViewRateApp = view.findViewById(R.id.textViewRateApp);
        setLanguageImage();
    }

    @Override
    public void setOnClickListener() {
        imageButtonLanguage.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
        textViewStartTest.setOnClickListener(this);
        textViewRateApp.setOnClickListener(this);
    }

    private void setLanguageImage() {
        String languageSuffix = homePagePresenter.getLanguageSuffix();
        switch (languageSuffix) {
            case APP_LANGUAGE_SUFFIX_EN:
                imageButtonLanguage.setImageResource(R.drawable.ic_en);
                break;
            case APP_LANGUAGE_SUFFIX_RU:
                imageButtonLanguage.setImageResource(R.drawable.ic_ru);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonLanguage:
                homePagePresenter.showSwitchLanguageDialog(getActivity());
                break;
            case R.id.buttonExit:
                homePagePresenter.showExitDialog(getActivity());
                break;
            case R.id.textViewStartTest:
                switchFragment.setFragment(AgeFragment.newInstance());
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