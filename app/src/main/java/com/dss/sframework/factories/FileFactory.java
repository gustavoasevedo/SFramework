package com.dss.sframework.factories;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class FileFactory {

    private String PATH;
    Context context;



    public FileFactory(Context context) {
        this.context = context;
    }

    public String getFilePath() throws IOException {
        try {
            File sd = Environment.getExternalStorageDirectory();;

            if (sd.canWrite()) {
                File directory = new File(sd, getPATH());

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


    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
}
