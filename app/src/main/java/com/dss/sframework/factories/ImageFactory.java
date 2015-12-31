package com.dss.sframework.factories;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dss.sframework.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by gustavo.vieira on 06/05/2015.
 */
public abstract class ImageFactory {

    /**
     * Receive a bitmap image, convert and return a Base64 string
     * @param bitmap
     * @return
     */
    public static String convertPhotoToBase64(Bitmap bitmap) {
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
    public static Bitmap convertPhotoFromBase64(String base64,Context context) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedImage;
    }



    /**
     * Generate a pop-up with a message and an image received via url.
     * @param context
     * @param titulo
     * @param url
     * @return
     */
    public static void showDialogImage(final Context context, String titulo, String url) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }

        dialog.setContentView(R.layout.dialog_image);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.txtDescricao);
        text.setText(titulo);
        ImageView image = (ImageView) dialog.findViewById(R.id.imgFoto);



        Glide.with(context)
//                .load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRqdDzVXBlT7uvIhzEG0IEeGJjGBNSWSqTXskMUGynnZTAmGeMv7nCPbTV1uw")
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(image);

        Button dialogButton = (Button) dialog.findViewById(R.id.closeButton);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
