package com.dss.sframework.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.dss.sframework.R;
import com.dss.sframework.services.GPSTracker;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by digipronto on 02/06/16.
 */
public class LocationUtils{



    public static boolean gpsIsEnabled(Context context) {
        boolean enabled = false;

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(Permission.hasPermission(context, Permission.LOCATION))) {
                Permission.requestPermission(context, Permission.LOCATION);
                return enabled;
            }
        }

        LocationManager mlocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return enabled;

    }


    public static void showSettingsAlert(final Context context) {
        AlertDialog.Builder alertDialog;
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alertDialog = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog);
        }else{
            alertDialog = new AlertDialog.Builder(context);
        }

        // Setting Dialog Title
        alertDialog.setTitle(context.getResources().getString(R.string.gps_is_settings));

        // Setting Dialog Message
        alertDialog.setMessage(context.getResources().getString(R.string.gps_not_available));

        // On pressing Settings button
        alertDialog.setPositiveButton(context.getResources().getString(R.string.action_settings), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    public static LatLng getLocation(Context context) {


        GPSTracker mGPS = new GPSTracker(context);
        Location gps_localizacao = new Location("dummyprovider");
        LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        double latitude = 0.0;
        double longitude = 0.0;
        long minTime = 1000;
        float minDistance = 1;


            gps_localizacao.setLatitude(0);
            gps_localizacao.setLongitude(0);
            gps_localizacao = mGPS.getLocation();

            try {
                latitude = gps_localizacao.getLatitude();
                longitude = gps_localizacao.getLongitude();
            }catch(Exception e){
                //erro nativo
                //at dalvik.system.NativeStart.main(Native Method)
                //Caused by: java.lang.NullPointerException
                //at CoachingPDVActivity.onCreate(CoachingPDVActivity.java:129) latitude = gps_localizacao.getLatitude();
                Log.e("CoachingPDVActivity", "Erro ao receber LatLong");
                latitude = longitude = 0.0;
            }


        LatLng latLng = new LatLng(latitude,longitude);

        return latLng;

    }




}
