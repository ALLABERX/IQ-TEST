package com.allaberx.test.iq.presenters.questions;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.databases.QuestionDAO;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.common.BasePresenter;

import java.util.ArrayList;

public class QuestionsPresenter extends BasePresenter {
    QuestionsView view;
    QuestionDAO questionDAO;

    public QuestionsPresenter(QuestionsView view, FragmentActivity activity) {
        this.view = view;
        questionDAO = new QuestionDAO(activity);
    }

    public ArrayList<Question> getAllQuestion(){
        return questionDAO.getAllQuestion();
    }
}
