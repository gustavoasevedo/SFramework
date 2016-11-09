package com.dss.sframework.tools.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by digipronto on 28/04/16.
 */
public abstract class Permission {

    public static final String CALENDAR = "android.permission.WRITE_CALENDAR";
    public static final String CAMERA = "android.permission.CAMERA";
    public static final String CONTACTS = "android.permission.WRITE_CONTACTS";
    public static final String LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String MICROPHONE = "android.permission.RECORD_AUDIO";
    public static final String SENSORS = "android.permission.BODY_SENSORS";
    public static final String PHONE = "android.permission.READ_PHONE_STATE";
    public static final String SMS = "android.permission.SEND_SMS";
    public static final String STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    private static final String[] allPerms = {CALENDAR,CAMERA,CONTACTS,LOCATION,MICROPHONE,SENSORS,PHONE,SMS,STORAGE};


    private static final int permsRequestCode = 200;



    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context,String permission){
        int result = ContextCompat.checkSelfPermission(context, permission);

        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Context context,String permission) {

            String[] perms = {permission};

            ActivityCompat.requestPermissions((Activity) context, perms, permsRequestCode);

    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestMultiplePermission(Context context,String[] permissions) {

        for(int i = 0; i < allPerms.length;i++) {

            if (!(ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, permissions[i]))){
                showMessageOKCancel(context,permissions[i]);
                return;
            }
        }
        ((Activity) context).requestPermissions(allPerms, permsRequestCode);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestAllPermissions(Context context) {

        for(int i = 0; i < allPerms.length;i++) {

            if (!(ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, allPerms[i]))){
                showMessageOKCancel(context,allPerms[i]);
                return;
            }
        }

        ((Activity) context).requestPermissions(allPerms, permsRequestCode);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean PermissionsResult(int permsRequest, String[] permissions, int[] grantResults) {

        boolean result = true;

        ArrayList<Boolean> status = new ArrayList<>();

        if(permsRequestCode == permsRequest){
            for (int i = 0; i < grantResults.length; i++) {

                status.add(grantResults[i] == PackageManager.PERMISSION_GRANTED);
            }
        }

        for(Boolean b : status){
            if(!b){
                result = false;
                break;
            }
        }


        return result;

    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void showMessageOKCancel(final Context context, final String permission) {

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new AlertDialog.Builder(context,android.R.style.Theme_Material_Light_Dialog)
                    .setMessage("Você precisa garantir acesso as permissões do aplicativo! \n Configurações -> Aplicativos -> Procure pelo Aplicativo, selecione Permissões, e habilite todas as Configurações")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermission(context,permission);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }else{
            new AlertDialog.Builder(context)
                    .setMessage("Você precisa garantir acesso as permissões do aplicativo! \n Configurações -> Aplicativos -> Procure pelo Aplicativo, selecione Permissões, e habilite todas as Configurações")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermission(context,permission);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }

    }
}
