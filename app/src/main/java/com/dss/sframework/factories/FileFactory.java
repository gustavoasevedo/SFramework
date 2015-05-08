package com.dss.sframework.factories;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class FileFactory {

    private static final String PIC_PATH = "//Teste//";
    Context context;



    public FileFactory(Context context) {
        this.context = context;
    }

    public String getFilePath() throws IOException {
        try {
            File sd = Environment.getExternalStorageDirectory();;

            if (sd.canWrite()) {
                File directory = new File(sd, PIC_PATH);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                return directory.getPath();
            }
        } catch (Exception e) {
            throw new IOException("Error generating file");
        }

        return null;
    }

    public boolean deleteFile(String filePath){
        File file = new File(filePath);

        boolean ok = file.delete();

        return ok;
    }
}
