package com.allaberx.test.iq.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.allaberx.test.iq.models.Question;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionDAO {

    private SQLiteDatabase database;

    public QuestionDAO(Context context) {
        initiationDatabase(context);
    }

    private void initiationDatabase(Context context) {
        DatabaseHelper mDBHelper = new DatabaseHelper(context);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        database = mDBHelper.getWritableDatabase();
    }

    public Question getQuestionToPosition(int position) {
        Cursor cursor = database.rawQuery("SELECT * FROM questions", null);
        if (isLastQuestion(position)) {
            return null;
        }
        cursor.move(position);

        Question question = new Question();
        question.setId(cursor.getInt(0));
        question.setNumberAnswers(cursor.getInt(1));
        question.setQuestionGroup(cursor.getString(2));
        question.setQuestion(cursor.getString(3));
        question.setAnswers1(cursor.getString(4));
        question.setAnswers2(cursor.getString(5));
        question.setAnswers3(cursor.getString(6));
        question.setAnswers4(cursor.getString(7));
        question.setAnswers5(cursor.getString(8));
        question.setAnswers6(cursor.getString(9));
        question.setAnswers7(cursor.getString(10));
        question.setAnswers8(cursor.getString(11));
        question.setCorrect(cursor.getInt(12));
        question.setSelectedAnswer(cursor.getInt(13));
        return question;
    }

    public void setSelectedAnswer(int position, int selectedAnswer) {
        String updateQuery = "UPDATE questions SET selectedAnswer = " + selectedAnswer + " WHERE _id = " + position;
        database.execSQL(updateQuery);
    }

    public ArrayList<Question> getAllQuestion() {
        Cursor cursor = database.rawQuery("SELECT * FROM questions", null);
        ArrayList<Question> arrayList = new ArrayList<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Question question = new Question();
            question.setId(cursor.getInt(0));
            question.setNumberAnswers(cursor.getInt(1));
            question.setQuestionGroup(cursor.getString(2));
            question.setQuestion(cursor.getString(3));
            question.setAnswers1(cursor.getString(4));
            question.setAnswers2(cursor.getString(5));
            question.setAnswers3(cursor.getString(6));
            question.setAnswers4(cursor.getString(7));
            question.setAnswers5(cursor.getString(8));
            question.setAnswers6(cursor.getString(9));
            question.setAnswers7(cursor.getString(10));
            question.setAnswers8(cursor.getString(11));
            question.setCorrect(cursor.getInt(12));
            question.setSelectedAnswer(cursor.getInt(13));
            arrayList.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }


    public boolean isLastQuestion(int position) {
        return position > getQuestionCount();
    }

    public int getQuestionCount() {
        Cursor cursor = database.rawQuery("SELECT * FROM questions", null);
        return cursor.getCount();
    }
}
