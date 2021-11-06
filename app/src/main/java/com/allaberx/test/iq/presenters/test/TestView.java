package com.allaberx.test.iq.presenters.test;

import android.widget.ImageButton;
import android.widget.ImageView;

import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.common.BaseView;

public interface TestView extends BaseView {
    Integer getTextTimer();
    void setTextTimer(Integer time);
    void finishTestByTime();
    void changeTimerColor();
    void startTimer();

    void setToHomeFragment();
    void setButtonState(ImageButton imageButton);
    void resetButtonsState();
    void setQuestion(Question question);
    void setImageResource(ImageView imageView, String imageName);
    void setVisibilityButtons(boolean visibility);
    void setEnabledButtonNext(boolean enabled);
    void setQuestionCount(int position, int number);
    void finishTest();
}
