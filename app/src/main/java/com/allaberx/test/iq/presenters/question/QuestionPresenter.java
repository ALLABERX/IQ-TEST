package com.allaberx.test.iq.presenters.question;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.databases.QuestionDAO;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.common.BasePresenter;
import com.allaberx.test.iq.presenters.questions.QuestionsView;

public class QuestionPresenter extends BasePresenter {

    QuestionView view;
    FragmentActivity activity;
    QuestionDAO questionDAO;
    int position;

    public QuestionPresenter(QuestionView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
        questionDAO = new QuestionDAO(activity);
    }

    public void setNextQuestion() {
        int positionNext = position + 1;
        if(validatePosition(positionNext)) return;
        setQuestion(positionNext);
    }

    public void setPreviousQuestion() {
        int positionPrevious = position - 1;
        if(validatePosition(positionPrevious)) return;
        setQuestion(positionPrevious);
    }

    public void setQuestion(int position) {
        this.position = position;
        Question question = questionDAO.getQuestionToPosition(position);
        setVisibilityButtons(question.numberAnswers);
        setEnableNavigationButtons(position);
        view.showHints(question.getSelectedAnswer(), question.getCorrect());
        view.setQuestion(question);
    }

    private void setVisibilityButtons(int number) {
        if (number > 6) {
            view.setVisibilityButtons(true);
        } else {
            view.setVisibilityButtons(false);
        }
    }

    private boolean validatePosition(int validatePosition){
        if(validatePosition == 0) return true;
        if(validatePosition == getNumberQuestions() + 1) return true;
        return false;
    }

    private void setEnableNavigationButtons(int validatePosition) {
        if(validatePosition == getNumberQuestions()) view.toggleButtonNext(false);
        else view.toggleButtonNext(true);

        if(validatePosition == 1) view.toggleButtonPrevious(false);
        else view.toggleButtonPrevious(true);
    }

    public int getPosition() {
        return position;
    }

    public int getNumberQuestions() {
        return questionDAO.getQuestionCount();
    }
}
