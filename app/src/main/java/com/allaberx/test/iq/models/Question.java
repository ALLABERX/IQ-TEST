package com.allaberx.test.iq.models;

public class Question {

    public long id;
    public int numberAnswers;
    public String questionGroup;
    public String question;
    public String answers1;
    public String answers2;
    public String answers3;
    public String answers4;
    public String answers5;
    public String answers6;
    public String answers7;
    public String answers8;
    public int correct;
    public int selectedAnswer;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberAnswers() {
        return numberAnswers;
    }

    public void setNumberAnswers(int numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

    public String getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(String questionGroup) {
        this.questionGroup = questionGroup;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers1() {
        return answers1;
    }

    public void setAnswers1(String answers1) {
        this.answers1 = answers1;
    }

    public String getAnswers2() {
        return answers2;
    }

    public void setAnswers2(String answers2) {
        this.answers2 = answers2;
    }

    public String getAnswers3() {
        return answers3;
    }

    public void setAnswers3(String answers3) {
        this.answers3 = answers3;
    }

    public String getAnswers4() {
        return answers4;
    }

    public void setAnswers4(String answers4) {
        this.answers4 = answers4;
    }

    public String getAnswers5() {
        return answers5;
    }

    public void setAnswers5(String answers5) {
        this.answers5 = answers5;
    }

    public String getAnswers6() {
        return answers6;
    }

    public void setAnswers6(String answers6) {
        this.answers6 = answers6;
    }

    public String getAnswers7() {
        return answers7;
    }

    public void setAnswers7(String answers7) {
        this.answers7 = answers7;
    }

    public String getAnswers8() {
        return answers8;
    }

    public void setAnswers8(String answers8) {
        this.answers8 = answers8;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
