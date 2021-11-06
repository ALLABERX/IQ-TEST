package com.allaberx.test.iq.tasks;

import androidx.fragment.app.FragmentActivity;


import com.allaberx.test.iq.presenters.test.TestPresenter;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    FragmentActivity fragmentActivity;
    TestPresenter testPresenter;

    public MyTimerTask(FragmentActivity fragmentActivity, TestPresenter testPresenter) {
        this.fragmentActivity = fragmentActivity;
        this.testPresenter = testPresenter;
    }

    @Override
    public void run() {
        fragmentActivity.runOnUiThread(() -> {
            Integer time = testPresenter.getTextTimer();

            time = time - 1;
            if (time == 3) {
                testPresenter.changeTimerColor();
            }
            if (time == 0) {
                testPresenter.finishTestByTime();
            }
            testPresenter.setTextTimer(time);
        });
    }
}