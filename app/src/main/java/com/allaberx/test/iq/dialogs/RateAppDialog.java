package com.allaberx.test.iq.dialogs;

import static com.allaberx.test.iq.utils.Thesaurus.URI_GOOGLE_PLAY;
import static com.allaberx.test.iq.utils.Thesaurus.URI_MARKET;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.allaberx.test.iq.R;

public class RateAppDialog extends DialogFragment implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {

    RatingBar ratingBar;
    TextView textViewRateAppTy;
    Button buttonSend;

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
        View view = inflater.inflate(R.layout.dialog_rate_app, null);
        textViewRateAppTy = view.findViewById(R.id.textViewRateAppTy);
        buttonSend = view.findViewById(R.id.buttonSend);
        ratingBar = view.findViewById(R.id.ratingBar);
        view.findViewById(R.id.buttonSend).setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSend:
                checkRating();
                break;
        }
    }

    private void checkRating() {
        if (ratingBar.getRating() >= 4) {
            openGooglePlay();
        } else {
            showToast();
        }
        dismiss();
    }

    private void openGooglePlay() {
        final String appPackageName = getActivity().getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URI_MARKET + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URI_GOOGLE_PLAY + appPackageName)));
        }
    }

    private void showToast() {
        Toast.makeText(getActivity(), R.string.string_sent_by, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
        if (rating < 1.0f) {
            textViewRateAppTy.setVisibility(View.INVISIBLE);
            buttonSend.setEnabled(false);
        } else {
            textViewRateAppTy.setVisibility(View.VISIBLE);
            buttonSend.setEnabled(true);
        }
    }
}