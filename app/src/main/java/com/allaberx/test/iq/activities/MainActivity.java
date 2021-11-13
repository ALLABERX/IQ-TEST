package com.allaberx.test.iq.activities;

import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE;
import static com.allaberx.test.iq.utils.Thesaurus.APP_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.presenters.home.HomeFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SwitchFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAppLanguage();
        setFragment(HomeFragment.newInstance());
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setAppLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(APP_LANGUAGE)) {
            String language = sharedPreferences.getString(APP_LANGUAGE, "en");
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, null);
        }
    }

    @Override
    public void recreateActivity() {
        recreate();
    }
}