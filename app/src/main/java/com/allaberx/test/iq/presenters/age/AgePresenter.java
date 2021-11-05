package com.allaberx.test.iq.presenters.age;

import static com.allaberx.test.iq.utils.Utils.isBlankString;

import com.allaberx.test.iq.presenters.common.BasePresenter;

public class AgePresenter extends BasePresenter {

    AgeView view;

    public AgePresenter( AgeView view) {
        this.view = view;
    }

    public boolean ageCheck(String text){
        if (isBlankString(text)) return false;
        Integer age = Integer.parseInt(text);
        if (age > 0) {
            view.saveAgeToSharedPreferences(age);
            return true;
        }
        return false;
    }
}
