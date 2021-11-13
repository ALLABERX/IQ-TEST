package com.allaberx.test.iq.presenters.home;

import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE;
import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE_SUFFIX_EN;
import static com.allaberx.test.iq.utils.Thesaurus.APP_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.presenters.common.BasePresenter;

public class HomePresenter extends BasePresenter {

    HomeView view;
    FragmentActivity activity;

    public HomePresenter(HomeView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    public String getLanguageSuffix() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(APP_LANGUAGE)) {
            return sharedPreferences.getString(APP_LANGUAGE, "en");
        }
        return APP_LANGUAGE_SUFFIX_EN;
    }
}
