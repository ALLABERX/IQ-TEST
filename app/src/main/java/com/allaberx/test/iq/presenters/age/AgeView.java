package com.allaberx.test.iq.presenters.age;

import com.allaberx.test.iq.presenters.common.BaseView;

public interface AgeView extends BaseView {
    void saveAgeToSharedPreferences(int age);
}
