package com.mukesh.docscanner;


import android.content.Context;
import android.graphics.Bitmap;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class PdfUtils {
    public static String createPdfFromBitmap(Context context, Bitmap bitmap) {
        try {
            File file = new File(context.getExternalFilesDir(null), "captured.pdf");
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Image image = Image.getInstance(bitmapToBytes(bitmap));
            image.scaleToFit(500, 500);
            document.add(image);
            document.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
