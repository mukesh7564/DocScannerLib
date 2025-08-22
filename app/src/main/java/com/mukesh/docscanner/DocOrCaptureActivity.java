package com.mukesh.docscanner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DocOrCaptureActivity extends AppCompatActivity {
    private static final int PICK_DOC = 102;
    private static final int CAPTURE_IMAGE = 103;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickDocument();
    }

    private void pickDocument() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimeTypes = {"application/pdf", "application/msword", "image/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, PICK_DOC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_DOC) {
            if (resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                // Return URI as result
                Intent result = new Intent();
                result.putExtra("file_uri", uri.toString());
                setResult(Activity.RESULT_OK, result);
                finish();
            } else {
                // User canceled -> open camera
//                Intent intent = new Intent(this, CameraCaptureActivity.class);
//                startActivityForResult(intent, CAPTURE_IMAGE);
            }
        } else if (requestCode == CAPTURE_IMAGE) {
            if (resultCode == RESULT_OK && data != null) {
                String pdfPath = data.getStringExtra("pdf_path");
                Intent result = new Intent();
                result.putExtra("pdf_path", pdfPath);
                setResult(Activity.RESULT_OK, result);
            }
            finish();
        }
    }
}
