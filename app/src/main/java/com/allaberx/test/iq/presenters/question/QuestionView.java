package com.allaberx.test.iq.presenters.question;

import android.widget.ImageView;

import com.allaberx.test.iq.models.Question;
import com.allaberx.test.iq.presenters.common.BaseView;

public interface QuestionView extends BaseView {
    void setQuestion(Question question);
    void setVisibleImage(ImageView imageView);
    void showHints(int selected, int correct);
    void setHintsDrawing(int position, String imageName);
    void setImageAndVisibility(ImageView imageView, String imageName);
    void setImageResource(ImageView imageView, String image);
    void setVisibilityButtons(boolean visibility);
    void toggleButtonNext(boolean enabled);
    void toggleButtonPrevious(boolean enabled);
}
