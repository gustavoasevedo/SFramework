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

    /**
     * Receive a bitmap image, convert and return a Base64 string
     * @param bitmap
     * @return
     */
    public String convertPhotoToBase64(Bitmap bitmap) {
        String encodedImage = null;
        try {

            ByteArrayOutputStream bitmapArray = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bitmapArray); // bm is the
            byte[] byteArrayImage = bitmapArray.toByteArray();
            encodedImage = Base64
                    .encodeToString(byteArrayImage, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(e.toString(), "Error converting photo: " + bitmap);
        }

        return encodedImage;
    }

    /**
     * Receive a base64 string and a context, convert and return a Bitmap image.
     * @param base64
     * @param context
     * @return
     */
    public Bitmap convertPhotoFromBase64(String base64,Context context) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedImage;
    }
}
