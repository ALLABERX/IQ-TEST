package com.allaberx.test.iq.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.activities.MainActivity;
import com.allaberx.test.iq.presenters.home.HomeFragment;

public class CommonDialog extends DialogFragment implements View.OnClickListener {

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
        View view = inflater.inflate(R.layout.dialog_common, null);
        String dialogType = getArguments().getString("dialogType", "home");

        switch (dialogType) {
            case "exit":
                isExitDialogType(view);
                break;
            case "home":
                isHomeDialogType(view);
                break;
        }
        view.findViewById(R.id.textViewCancel).setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    private void isExitDialogType(View view) {
        TextView textView = view.findViewById(R.id.textViewDialogBody);
        textView.setText(R.string.string_dialog_body_exit);
        view.findViewById(R.id.textViewOk).setOnClickListener(view1 -> {
            getActivity().finish();
        });
    }

    private void isHomeDialogType(View view) {
        TextView textView = view.findViewById(R.id.textViewDialogBody);
        textView.setText(R.string.string_dialog_body_home);
        view.findViewById(R.id.textViewOk).setOnClickListener(view1 -> {
            ((MainActivity) getActivity()).setFragment(HomeFragment.newInstance());
            getDialog().dismiss();
        });

    }

    @Override
    public void onClick(View view) {
        getDialog().dismiss();
    }
}