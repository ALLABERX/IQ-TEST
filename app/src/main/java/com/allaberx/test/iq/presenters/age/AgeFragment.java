package com.allaberx.test.iq.presenters.age;

import static com.allaberx.test.iq.utils.Thesaurus.APP_PREFERENCES;
import static com.allaberx.test.iq.utils.Thesaurus.APP_PREFERENCES_AGE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.SwitchFragment;
import com.allaberx.test.iq.presenters.test.TestFragment;
import com.allaberx.test.iq.utils.TextValidator;

public class AgeFragment extends Fragment implements AgeView {

    AgePresenter agePresenter;
    SwitchFragment switchFragment;

    EditText editTextNumberYourAge;
    Button buttonNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);
        agePresenter = new AgePresenter(this);
        initiationViewElements(view);
        setOnClickListener();
        return view;
    }

    @Override
    public void initiationViewElements(View view) {
        editTextNumberYourAge = view.findViewById(R.id.editTextNumberYourAge);
        buttonNext = view.findViewById(R.id.buttonNext);

        editTextNumberYourAge.addTextChangedListener(new TextValidator(editTextNumberYourAge) {
            @Override
            public void validate(TextView textView, String text) {
                view.findViewById(R.id.buttonNext).setEnabled(agePresenter.ageCheck(text));
            }
        });

    }

    @Override
    public void setOnClickListener() {
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switchFragment.setFragment(TestFragment.newInstance());
    }

    @Override
    public void saveAgeToSharedPreferences(int age) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_PREFERENCES_AGE, age);
        editor.apply();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SwitchFragment) {
            switchFragment = (SwitchFragment) context;
        }
    }

    public static AgeFragment newInstance() {
        return new AgeFragment();
    }
}
