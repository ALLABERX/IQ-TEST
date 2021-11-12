package com.allaberx.test.iq.presenters.question;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.questions.QuestionsFragment;

public class QuestionFragment extends Fragment implements QuestionView {

    private SwitchFragment switchFragment;
    private QuestionPresenter questionPresenter;

    private ImageView imageButtonHome;
    private ImageView imageButtonNext;
    private ImageView imageButtonPrevious;

    private TextView textViewQuestionCount;

    private ImageView imageViewQuestion;

    private ImageButton imageButtonAnswer1;
    private ImageButton imageButtonAnswer2;
    private ImageButton imageButtonAnswer3;
    private ImageButton imageButtonAnswer4;
    private ImageButton imageButtonAnswer5;
    private ImageButton imageButtonAnswer6;
    private ImageButton imageButtonAnswer7;
    private ImageButton imageButtonAnswer8;

    private ImageView imageViewAnswer1;
    private ImageView imageViewAnswer2;
    private ImageView imageViewAnswer3;
    private ImageView imageViewAnswer4;
    private ImageView imageViewAnswer5;
    private ImageView imageViewAnswer6;
    private ImageView imageViewAnswer7;
    private ImageView imageViewAnswer8;

    private LinearLayout linearLayoutButtons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionPresenter = new QuestionPresenter(this, getActivity());
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("position", 0);

        imageButtonHome = view.findViewById(R.id.imageButtonHome);
        imageButtonNext = view.findViewById(R.id.imageButtonNext);
        imageButtonPrevious  = view.findViewById(R.id.imageButtonPrevious);

        textViewQuestionCount = view.findViewById(R.id.textViewQuestionCount);

        imageViewQuestion = view.findViewById(R.id.imageViewQuestion);

        imageButtonAnswer1 = view.findViewById(R.id.imageButtonAnswer1);
        imageButtonAnswer2 = view.findViewById(R.id.imageButtonAnswer2);
        imageButtonAnswer3 = view.findViewById(R.id.imageButtonAnswer3);
        imageButtonAnswer4 = view.findViewById(R.id.imageButtonAnswer4);
        imageButtonAnswer5 = view.findViewById(R.id.imageButtonAnswer5);
        imageButtonAnswer6 = view.findViewById(R.id.imageButtonAnswer6);
        imageButtonAnswer7 = view.findViewById(R.id.imageButtonAnswer7);
        imageButtonAnswer8 = view.findViewById(R.id.imageButtonAnswer8);

        imageViewAnswer1 = view.findViewById(R.id.imageViewAnswer1);
        imageViewAnswer2 = view.findViewById(R.id.imageViewAnswer2);
        imageViewAnswer3 = view.findViewById(R.id.imageViewAnswer3);
        imageViewAnswer4 = view.findViewById(R.id.imageViewAnswer4);
        imageViewAnswer5 = view.findViewById(R.id.imageViewAnswer5);
        imageViewAnswer6 = view.findViewById(R.id.imageViewAnswer6);
        imageViewAnswer7 = view.findViewById(R.id.imageViewAnswer7);
        imageViewAnswer8 = view.findViewById(R.id.imageViewAnswer8);

        linearLayoutButtons = view.findViewById(R.id.linearLayoutButtons);

        questionPresenter.setQuestion(position);
    }

    @Override
    public void setOnClickListener() {
        imageButtonHome.setOnClickListener(this);
        imageButtonNext.setOnClickListener(this);
        imageButtonPrevious.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonHome:
                switchFragment.setFragment(QuestionsFragment.newInstance());
                break;
            case R.id.imageButtonNext:
                questionPresenter.setNextQuestion();
                break;
            case R.id.imageButtonPrevious:
                questionPresenter.setPreviousQuestion();
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

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public void setQuestion(Question question) {
        textViewQuestionCount.setText(questionPresenter.getPosition() + "/" + questionPresenter.getNumberQuestions());
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
    public void setVisibleImage(ImageView imageView) {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void showHints(int selected, int correct) {
        clearHints();
        setHintsDrawing(selected, "wrong_answer");
        setHintsDrawing(correct, "correct_answer");
    }

    private void clearHints() {
        setVisibleImage(imageViewAnswer1);
        setVisibleImage(imageViewAnswer2);
        setVisibleImage(imageViewAnswer3);
        setVisibleImage(imageViewAnswer4);
        setVisibleImage(imageViewAnswer5);
        setVisibleImage(imageViewAnswer6);
        setVisibleImage(imageViewAnswer7);
        setVisibleImage(imageViewAnswer8);
    }

    @Override
    public void setHintsDrawing(int position, String imageName) {
        switch (position) {
            case 1:
                setImageAndVisibility(imageViewAnswer1, imageName);
                break;
            case 2:
                setImageAndVisibility(imageViewAnswer2, imageName);
                break;
            case 3:
                setImageAndVisibility(imageViewAnswer3, imageName);
                break;
            case 4:
                setImageAndVisibility(imageViewAnswer4, imageName);
                break;
            case 5:
                setImageAndVisibility(imageViewAnswer5, imageName);
                break;
            case 6:
                setImageAndVisibility(imageViewAnswer6, imageName);
                break;
            case 7:
                setImageAndVisibility(imageViewAnswer7, imageName);
                break;
            case 8:
                setImageAndVisibility(imageViewAnswer8, imageName);
                break;
        }
    }

    @Override
    public void setImageAndVisibility(ImageView imageView, String imageName) {
        setImageResource(imageView, imageName);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setImageResource(ImageView imageView, String image) {
        Resources resources = getActivity().getResources();
        String packageName = getContext().getPackageName();
        int imageId = resources.getIdentifier(image, "drawable", packageName);
        imageView.setImageResource(imageId);
    }

    @Override
    public void setVisibilityButtons(boolean visibility) {
        if(visibility)
        linearLayoutButtons.setVisibility(View.VISIBLE);
        else
        linearLayoutButtons.setVisibility(View.GONE);
    }

    @Override
    public void toggleButtonNext(boolean enabled) {
        imageButtonNext.setEnabled(enabled);
    }

    @Override
    public void toggleButtonPrevious(boolean enabled) {
        imageButtonPrevious.setEnabled(enabled);
    }
}