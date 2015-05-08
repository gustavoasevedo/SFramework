package com.dss.sframework.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

/**
 * Created by Cristiano on 14/01/15.
 */
public class DeviceInfo {
    private Context context;

    final String deviceName = android.os.Build.MODEL;
    final String deviceMan = android.os.Build.MANUFACTURER;
    final String deviceSerial = android.os.Build.SERIAL;
    final String deviceProduct = android.os.Build.PRODUCT;

    public DeviceInfo(Context context){
        this.context = context;
    }

    public String getAndroidId(){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public String getVersionName(){
        PackageInfo pInfo = getPackageInfo();

        return pInfo.versionName;
//		      code=pInfo.versionCode;

    }

    public int getVersionCode(){
        PackageInfo pInfo = getPackageInfo();

        return pInfo.versionCode;
    }

    private PackageInfo getPackageInfo(){
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceMan() {
        return deviceMan;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public String getDeviceProduct() {
        return deviceProduct;
    }
}
