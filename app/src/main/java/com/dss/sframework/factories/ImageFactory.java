package com.dss.sframework.factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by gustavo.vieira on 06/05/2015.
 */
public class ImageFactory {

    public String convertPhotoToBase64(String sPhoto) {
        String encodedImage = null;
        try {
            Bitmap bm = BitmapFactory.decodeFile(sPhoto);

            bm = Bitmap.createScaledBitmap(bm, 1280, 720, false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos); // bm is the
            // bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            encodedImage = Base64
                    .encodeToString(byteArrayImage, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(e.toString(), "Error converting photo: " + sPhoto);
        }

        return encodedImage;
    }

    public Drawable convertPhotoFromBase64(String base64,Context context) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Drawable thumb = new BitmapDrawable(context.getResources(), decodedImage);

        return thumb;
    }
}
