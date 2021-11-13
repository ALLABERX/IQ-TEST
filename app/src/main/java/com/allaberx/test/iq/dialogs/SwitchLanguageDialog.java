package com.allaberx.test.iq.dialogs;

import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE;
import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE_SUFFIX_EN;
import static com.allaberx.test.iq.utils.Thesaurus.APP_LANGUAGE_SUFFIX_RU;
import static com.allaberx.test.iq.utils.Thesaurus.APP_PREFERENCES;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.presenters.home.HomeFragment;
import com.allaberx.test.iq.presenters.test.TestFragment;

import java.util.Locale;

public class SwitchLanguageDialog extends DialogFragment implements View.OnClickListener {

    SwitchFragment switchFragment;

    LinearLayout linearLayoutRu;
    LinearLayout linearLayoutEn;
    SharedPreferences sharedPreferences;

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_switch_language, null);

        initiationViewElements(view);
        setOnClickListener();
        setLinearLayoutState();
        builder.setView(view);
        return builder.create();
    }


    private void initiationViewElements(View view) {
        sharedPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        view.findViewById(R.id.textViewCancel).setOnClickListener(this);
        linearLayoutRu = view.findViewById(R.id.linearLayoutRu);
        linearLayoutEn = view.findViewById(R.id.linearLayoutEn);
    }

    private void setOnClickListener() {
        linearLayoutRu.setOnClickListener(this);
        linearLayoutEn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewCancel:
                getDialog().dismiss();
                break;
            case R.id.linearLayoutRu:
                setAppLanguage("ru");
                break;
            case R.id.linearLayoutEn:
                setAppLanguage("en");
                break;
        }
    }

    private void setLinearLayoutState(){
        String languageSuffix = getLanguageSuffix();
        switch (languageSuffix) {
            case APP_LANGUAGE_SUFFIX_EN:
                linearLayoutEn.setEnabled(false);
                break;
            case APP_LANGUAGE_SUFFIX_RU:
                linearLayoutRu.setEnabled(false);
                break;
        }
    }

    private String getLanguageSuffix() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(APP_LANGUAGE)) {
            return sharedPreferences.getString(APP_LANGUAGE, "en");
        }
        return APP_LANGUAGE_SUFFIX_EN;
    }

    private void setAppLanguage(String language){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_LANGUAGE, language);
        editor.apply();
        getDialog().dismiss();
        switchFragment.setAppLanguage();
        switchFragment.recreateActivity();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SwitchFragment) {
            switchFragment = (SwitchFragment) context;
        }
    }
}