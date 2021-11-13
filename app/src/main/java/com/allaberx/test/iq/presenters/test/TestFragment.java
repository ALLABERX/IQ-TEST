package com.allaberx.test.iq.presenters.test;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.home.HomeFragment;
import com.allaberx.test.iq.presenters.result.ResultFragment;
import com.allaberx.test.iq.tasks.MyTimerTask;

import java.util.Timer;

public class TestFragment extends Fragment implements TestView {

    TestPresenter testPresenter;
    SwitchFragment switchFragment;

    ImageView imageButtonHome;
    TextView textViewQuestionCount;
    ImageView imageViewTimer;
    TextView textViewTimer;

    private Timer timer;
    private MyTimerTask mMyTimerTask;

    LinearLayout linearLayoutButtons;
    LinearLayout linearLayoutTimeIsOver;
    ScrollView scrollViewTest;
    ImageView imageViewQuestion;
    ImageButton imageButtonAnswer1;
    ImageButton imageButtonAnswer2;
    ImageButton imageButtonAnswer3;
    ImageButton imageButtonAnswer4;
    ImageButton imageButtonAnswer5;
    ImageButton imageButtonAnswer6;
    ImageButton imageButtonAnswer7;
    ImageButton imageButtonAnswer8;
    Button buttonNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);
        testPresenter = new TestPresenter(this, getActivity());
        initiationViewElements(rootView);
        setOnClickListener();
        startTimer();
        testPresenter.setQuestion();
        return rootView;
    }

    @Override
    public void initiationViewElements(View view) {
        imageButtonHome = view.findViewById(R.id.imageViewHome);
        textViewQuestionCount = view.findViewById(R.id.textViewQuestionCount);
        imageViewTimer = view.findViewById(R.id.imageViewTimer);
        textViewTimer = view.findViewById(R.id.textViewTimer);

        linearLayoutButtons = view.findViewById(R.id.linearLayoutButtons);
        linearLayoutTimeIsOver = view.findViewById(R.id.linearLayoutTimeIsOver);
        scrollViewTest = view.findViewById(R.id.scrollViewTest);
        imageViewQuestion = view.findViewById(R.id.imageViewQuestion);
        imageButtonAnswer1 = view.findViewById(R.id.imageButtonAnswer1);
        imageButtonAnswer2 = view.findViewById(R.id.imageButtonAnswer2);
        imageButtonAnswer3 = view.findViewById(R.id.imageButtonAnswer3);
        imageButtonAnswer4 = view.findViewById(R.id.imageButtonAnswer4);
        imageButtonAnswer5 = view.findViewById(R.id.imageButtonAnswer5);
        imageButtonAnswer6 = view.findViewById(R.id.imageButtonAnswer6);
        imageButtonAnswer7 = view.findViewById(R.id.imageButtonAnswer7);
        imageButtonAnswer8 = view.findViewById(R.id.imageButtonAnswer8);
        buttonNext = view.findViewById(R.id.buttonNext);
    }

    @Override
    public void setOnClickListener() {
        imageButtonHome.setOnClickListener(this);
        imageButtonAnswer1.setOnClickListener(this);
        imageButtonAnswer2.setOnClickListener(this);
        imageButtonAnswer3.setOnClickListener(this);
        imageButtonAnswer4.setOnClickListener(this);
        imageButtonAnswer5.setOnClickListener(this);
        imageButtonAnswer6.setOnClickListener(this);
        imageButtonAnswer7.setOnClickListener(this);
        imageButtonAnswer8.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewHome:
                testPresenter.showHomeDialog(getActivity());
                break;
            case R.id.imageButtonAnswer1:
                testPresenter.selectedButton(imageButtonAnswer1, 1);
                break;
            case R.id.imageButtonAnswer2:
                testPresenter.selectedButton(imageButtonAnswer2, 2);
                break;
            case R.id.imageButtonAnswer3:
                testPresenter.selectedButton(imageButtonAnswer3, 3);
                break;
            case R.id.imageButtonAnswer4:
                testPresenter.selectedButton(imageButtonAnswer4, 4);
                break;
            case R.id.imageButtonAnswer5:
                testPresenter.selectedButton(imageButtonAnswer5, 5);
                break;
            case R.id.imageButtonAnswer6:
                testPresenter.selectedButton(imageButtonAnswer6, 6);
                break;
            case R.id.imageButtonAnswer7:
                testPresenter.selectedButton(imageButtonAnswer7, 7);
                break;
            case R.id.imageButtonAnswer8:
                testPresenter.selectedButton(imageButtonAnswer8, 8);
                break;
            case R.id.buttonNext:
                testPresenter.setQuestionOrFragment(buttonNext);
                break;
        }
    }

    @Override
    public Integer getTextTimer() {
        return Integer.parseInt(textViewTimer.getText().toString());
    }

    @Override
    public void setTextTimer(Integer time) {
        textViewTimer.setText(String.valueOf(time));
    }

    @Override
    public void finishTestByTime() {
        timer.cancel();
        scrollViewTest.setVisibility(View.GONE);
        linearLayoutTimeIsOver.setVisibility(View.VISIBLE);
        buttonNext.setEnabled(true);
        buttonNext.setText(getActivity().getResources().getString(R.string.string_start_over));
    }

    @Override
    public void changeTimerColor() {
        imageViewTimer.setImageDrawable(getResources().getDrawable(R.drawable.timer_low));
        textViewTimer.setTextColor(getResources().getColor(R.color.color_timer_low));
    }

    @Override
    public void startTimer() {
        timer = new Timer();
        mMyTimerTask = new MyTimerTask(getActivity(), testPresenter);
        timer.schedule(mMyTimerTask, 1000, 60000);
    }

    @Override
    public void setToHomeFragment() {
        switchFragment.setFragment(HomeFragment.newInstance());
    }

    @Override
    public void setButtonState(ImageButton imageButton) {
        imageButton.setEnabled(false);
    }

    @Override
    public void resetButtonsState() {
        imageButtonAnswer1.setEnabled(true);
        imageButtonAnswer2.setEnabled(true);
        imageButtonAnswer3.setEnabled(true);
        imageButtonAnswer4.setEnabled(true);
        imageButtonAnswer5.setEnabled(true);
        imageButtonAnswer6.setEnabled(true);
        imageButtonAnswer7.setEnabled(true);
        imageButtonAnswer8.setEnabled(true);
    }

    @Override
    public void setQuestion(Question question) {
        setImageResource(imageViewQuestion, (question.getQuestion()));
        setImageResource(imageButtonAnswer1, (question.getAnswers1()));
        setImageResource(imageButtonAnswer2, (question.getAnswers2()));
        setImageResource(imageButtonAnswer3, (question.getAnswers3()));
        setImageResource(imageButtonAnswer4, (question.getAnswers4()));
        setImageResource(imageButtonAnswer5, (question.getAnswers5()));
        setImageResource(imageButtonAnswer6, (question.getAnswers6()));
        setImageResource(imageButtonAnswer7, (question.getAnswers7()));
        setImageResource(imageButtonAnswer8, (question.getAnswers8()));
    }

    @Override
    public void setImageResource(ImageView imageView, String imageName) {
        Resources resources = getActivity().getResources();
        String packageName = getActivity().getPackageName();
        int drawable = resources.getIdentifier(imageName, "drawable", packageName);
        imageView.setImageResource(drawable);
    }

    @Override
    public void setVisibilityButtons(boolean visibility) {
        if (visibility) {
            linearLayoutButtons.setVisibility(View.VISIBLE);
        } else {
            linearLayoutButtons.setVisibility(View.GONE);
        }
    }

    @Override
    public void setEnabledButtonNext(boolean enabled) {
        if (enabled) buttonNext.setEnabled(true);
        else buttonNext.setEnabled(false);
    }

    @Override
    public void setQuestionCount(int position, int number) {
        textViewQuestionCount.setText(position + "/" + number);
    }

    @Override
    public void finishTest() {
        timer.cancel();
        switchFragment.setFragment(ResultFragment.newInstance());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SwitchFragment) {
            switchFragment = (SwitchFragment) context;
        }
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }
}
