package com.allaberx.test.iq.presenters.result;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.allaberx.test.iq.R;
import com.allaberx.test.iq.presenters.common.BasePresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultPresenter extends BasePresenter {

    ResultView view;
    FragmentActivity activity;

    public ResultPresenter(ResultView view, FragmentActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    public void shareResult() {
        Bitmap b = BitmapFactory.decodeResource(activity.getResources(), R.drawable.question1);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("*/*");
        share.putExtra(Intent.EXTRA_TEXT, "Hello");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(activity.getContentResolver(), b, "Title", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        activity.startActivity(Intent.createChooser(share, "Select"));
    }
}
