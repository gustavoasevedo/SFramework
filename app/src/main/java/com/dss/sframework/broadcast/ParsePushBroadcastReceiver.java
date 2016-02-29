package com.dss.sframework.broadcast;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.dss.sframework.R;
import com.dss.sframework.activity.MainActivity;
import com.dss.sframework.constant.ConstantIntent;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ParsePushBroadcastReceiver extends
        com.parse.ParsePushBroadcastReceiver {

    public static final String ACTION_COMPLETE = "com.dss.sframework.broadcast.parsepushbroadcastreceivercustom.action.NOTIFICATION";
    public static int NOTIFICATION_ICON = 0;

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.e("Push", "Clicked");
        Intent i = new Intent(context, MainActivity.class);
        Bundle b = intent.getExtras();

        try {
            JSONObject jObject;
            String m = b != null ? b.getString("com.parse.Data") : "";

            Log.e("Push", m);

            jObject = new JSONObject(m);
            String header = "O Cara";

            if (header.length() > 0) {
                String alert[] = jObject.getString("alert").split("\\r");
                i.putExtra("txtTitulo", alert[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        NotificationManager nMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
        changeNotificationIconBegin(context);
        context.startActivity(i);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

    }

    @Override
    protected void onPushReceive(Context context, Intent intent ) {
        Log.e("Push", "Received");

        Bundle b = intent.getExtras();

        try {
            JSONObject jObject;
            String m = b != null ? b.getString("com.parse.Data") : "";

            Log.e("Push", m);

            jObject = new JSONObject(m);

            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            SimpleDateFormat data_json = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            String currentDate = data.format(new Date());
            String currentDate_JSON = data_json.format(new Date());

            String mensagem;
            String origem;

            if (jObject != null){
//                origem = jObject.getString("origem");
                mensagem = jObject.getString("alert");

                if (AplicacaoBackground(context)) {
                    NOTIFICATION_ICON += 1;
                    changeNotificationIcon(context, NOTIFICATION_ICON);
                    generateNotification(context,intent.getExtras(), mensagem);
                }
                else{
                    NOTIFICATION_ICON = 0;
                    changeNotificationIconBegin(context);
                }

//                if (origem.equals("SERVER_FOTO_UPDATE")){
//                }

                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(ACTION_COMPLETE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateNotification(Context context, Bundle extras,
                                            String message) {
        Bitmap bmpIcon = BitmapFactory.decodeResource(context.getResources(),
                R.id.image);

        // Show the notification
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context,
                MainActivity.class);

        notificationIntent.putExtras(extras);
        String header = "";


        Bundle b = extras;
        try {
            JSONObject jObject;
            String m = b != null ? b.getString("com.parse.Data") : "";

            Log.e("Push", m);

            jObject = new JSONObject(m);

            if (header.length() > 0) {
                String alert[] = jObject.getString("alert").split("\\r");
                notificationIntent.putExtra("txtTitulo", alert[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title).setContentText(message)
                .setContentIntent(intent).setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bmpIcon).build();

        notification.vibrate = new long[] { 500, 500 };
        notification.sound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notification.flags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;

        notificationManager.notify(0, notification);

    }

    private boolean AplicacaoBackground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private void changeNotificationIcon(Context context, int value) {
        PackageManager pm = context.getPackageManager();

        String lastEnabled = getLastEnabled(context);

        ComponentName componentName = new ComponentName(ConstantIntent.getINTENTPATH(),
                lastEnabled);
        pm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Log.i("DEMO", "Removing : " + lastEnabled);

        componentName = new ComponentName(ConstantIntent.getINTENTPATH(), lastEnabled);

        pm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Log.i("DEMO", "Adding : " + lastEnabled);
        setLastEnabled(lastEnabled, context);
    }

    public static void changeNotificationIconBegin(Context context) {
        String lastEnabled = getLastEnabled(context);
        if (lastEnabled.length() > 0) {
            ComponentName componentName = new ComponentName(
                    ConstantIntent.getINTENTPATH(), lastEnabled);
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                pm.setComponentEnabledSetting(componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                componentName = new ComponentName(ConstantIntent.getINTENTPATH(),
                        lastEnabled);
                pm.setComponentEnabledSetting(componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                setLastEnabled(lastEnabled, context);
            }
            ParsePushBroadcastReceiver.NOTIFICATION_ICON = 0;
        }
    }


    public static String getLastEnabled(Context context) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        return pref.getString("LastEnabled", "");
    }

    public static void setLastEnabled(String value, Context context) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("LastEnabled", value);
        editor.commit();
    }
}
