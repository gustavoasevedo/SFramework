package com.dss.sframework.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by digipronto on 28/04/16.
 */
    public abstract class Permission {

    public static final String CALENDAR = "WRITE_CALENDAR";
    public static final String CAMERA = "CAMERA";
    public static final String CONTACTS = "WRITE_CONTACTS";
    public static final String LOCATION = "ACCESS_FINE_LOCATION";
    public static final String MICROPHONE = "RECORD_AUDIO";
    public static final String SENSORS = "BODY_SENSORS";
    public static final String PHONE = "READ_PHONE_STATE";
    public static final String SMS = "SEND_SMS";
    public static final String STORAGE = "WRITE_EXTERNAL_STORAGE";

    private static final String[] allPerms = {CALENDAR,CAMERA,CONTACTS,LOCATION,MICROPHONE,SENSORS,PHONE,SMS,STORAGE};


    private static final int permsRequestCode = 200;



    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context,String permission){

        return((Activity) context).checkSelfPermission(permission)== PackageManager.PERMISSION_GRANTED;

    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Context context,String permission) {

        if (((Activity)context).shouldShowRequestPermissionRationale("android.permission." + permission)){

            String[] perms = {permission};

            ((Activity) context).requestPermissions(perms, permsRequestCode);
        }else{
            showMessageOKCancel(context,permission);
        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestMultiplePermission(Context context,String[] permissions) {

        for(int i = 0; i < allPerms.length;i++) {

            if (!((Activity)context).shouldShowRequestPermissionRationale("android.permission." + permissions[i])){
                showMessageOKCancel(context,permissions[i]);
                return;
            }
        }
        ((Activity) context).requestPermissions(allPerms, permsRequestCode);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static void requestAllPermissions(Context context) {

        for(int i = 0; i < allPerms.length;i++) {

            if (!((Activity)context).shouldShowRequestPermissionRationale("android.permission." + allPerms[i])){
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
        new AlertDialog.Builder(context,android.R.style.Theme_Material_Dialog_Alert)
                .setMessage("Você precisa garantir acesso as permições do aplicativo! \n Configurações -> Aplicativos -> Permissões, e habilite todas as Configurações")
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
