package com.allaberx.test.iq.presenters.common;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.allaberx.test.iq.dialogs.CommonDialog;
import com.allaberx.test.iq.dialogs.RateAppDialog;
import com.allaberx.test.iq.dialogs.SwitchLanguageDialog;

public class BasePresenter {
    public void showExitDialog(FragmentActivity fragmentActivity) {
        CommonDialog commonDialog = new CommonDialog();
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("dialogType", "exit");
        commonDialog.setArguments(bundle);
        commonDialog.show(transaction, "ExitDialog");
    }

    public void showHomeTestDialog(FragmentActivity fragmentActivity) {
        createCommonDialog(fragmentActivity, "test");
    }

    public void showHomeDialog(FragmentActivity fragmentActivity) {
        createCommonDialog(fragmentActivity, "home");
    }

    private void createCommonDialog(FragmentActivity fragmentActivity, String type) {
        CommonDialog commonDialog = new CommonDialog();
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("dialogType", type);
        commonDialog.setArguments(bundle);
        commonDialog.show(transaction, "CommonDialog");
    }

    public void showRateAppDialog(FragmentActivity fragmentActivity) {
        RateAppDialog rateAppDialog = new RateAppDialog();
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        rateAppDialog.show(transaction, "RateAppDialog");
    }

    public void showSwitchLanguageDialog(FragmentActivity fragmentActivity) {
        SwitchLanguageDialog switchLanguageDialog = new SwitchLanguageDialog();
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switchLanguageDialog.show(transaction, "SwitchLanguageDialog");
    }
}
