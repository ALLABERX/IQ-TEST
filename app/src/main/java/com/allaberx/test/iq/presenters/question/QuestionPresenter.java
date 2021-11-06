package com.allaberx.test.iq.presenters.question;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.presenters.common.BasePresenter;
import com.allaberx.test.iq.presenters.questions.QuestionsView;

public class QuestionPresenter extends BasePresenter {

    QuestionView view;

    public QuestionPresenter(QuestionView view, FragmentActivity activity) {
        this.view = view;
    }
}
