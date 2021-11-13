package com.allaberx.test.iq.activities;

import androidx.fragment.app.Fragment;

public interface SwitchFragment {
    void setFragment(Fragment fragment);
    void setAppLanguage();
    void recreateActivity();
}