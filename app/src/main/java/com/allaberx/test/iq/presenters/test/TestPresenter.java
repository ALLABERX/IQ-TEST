package com.allaberx.test.iq.presenters.test;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.databases.QuestionDAO;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.common.BasePresenter;

public class TestPresenter extends BasePresenter {

    TestView view;
    FragmentActivity activity;

    private int questionPosition = 1;
    private QuestionDAO questionDAO;


    public TestPresenter(TestView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
        questionDAO = new QuestionDAO(activity);
    }

    public void setQuestion() {
        Question question = questionDAO.getQuestionToPosition(questionPosition);
        if (isLastQuestion(question)) return;
        setVisibilityButtons(question.getNumberAnswers());
        view.setQuestion(question);
        setQuestionCount();
    }

    public void nextQuestion() {
        view.setEnabledButtonNext(false);
        questionPosition = questionPosition + 1;
        setQuestion();
        view.resetButtonsState();
    }

    public void selectedButton(ImageButton imageButton, int selectedAnswer) {
        int positionInDb = questionPosition - 1;
        questionDAO.setSelectedAnswer(positionInDb, selectedAnswer);
        view.resetButtonsState();
        view.setButtonState(imageButton);
        view.setEnabledButtonNext(true);
    }

    public void setQuestionOrFragment(Button buttonNext){
        String text = buttonNext.getText().toString();
        if(text == activity.getResources().getString(R.string.string_start_over)){
            view.setToHomeFragment();
        } else {
            nextQuestion();
        }
    }

    private boolean isLastQuestion(Question question) {
        if (question == null) {
            view.finishTest();
            return true;
        }
        return false;
    }

    private void setVisibilityButtons(int number) {
        if (number > 6) {
            view.setVisibilityButtons(true);
        } else {
            view.setVisibilityButtons(false);
        }
    }

    private void setQuestionCount() {
        view.setQuestionCount(questionPosition, getNumberQuestions());
    }

    public int getNumberQuestions() {
        return questionDAO.getQuestionCount();
    }

    public void finishTestByTime() {
        view.finishTestByTime();
    }

    public void setTextTimer(Integer time) {
        view.setTextTimer(time);
    }

    public Integer getTextTimer() {
        return view.getTextTimer();
    }

    public void changeTimerColor() {
        view.changeTimerColor();
    }
}
