package com.mukesh.docscanner;
import android.app.Activity;
import android.content.Intent;

public class DocOrCapture {
    public static final int REQUEST_CODE = 101;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DocOrCaptureActivity.class);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }
}

