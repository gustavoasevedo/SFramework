package com.dss.sframework.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import android.widget.TextView;

import com.dss.sframework.R;
import com.dss.sframework.navigation.NavigationDrawer;
import com.dss.sframework.constant.ConstantNavigationDrawer;
import com.dss.sframework.util.DeviceInfo;

/**
 * Created by gustavo.vieira on 07/05/2015.
 */
public class MainHelper {

    private TextView textVersionNumber,textVersionName;
    private Toolbar toolbar;

    public void MainActivity (Context context){
        textVersionNumber = (TextView) ((Activity)context).findViewById(R.id.textVersionNumber);
        textVersionName = (TextView) ((Activity)context).findViewById(R.id.textVersionName);
    }

    public void setText(Context context){
        DeviceInfo deviceInfo = new DeviceInfo(context);
        textVersionNumber.setText("Version Number: " + String.valueOf(deviceInfo.getVersionCode()));
        textVersionName.setText("Version Name: " +  deviceInfo.getVersionName());

    }

    public void startNavigation(Context context){

        toolbar = (Toolbar) ((Activity)context).findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        ((ActionBarActivity)context).setSupportActionBar(toolbar); // Setting toolbar as the ActionBar with setSupportActionBar() call

        NavigationDrawer navigationDrawer = new NavigationDrawer(ConstantNavigationDrawer.getTITLES(),ConstantNavigationDrawer.getICONS(),ConstantNavigationDrawer.getNAME(),
                ConstantNavigationDrawer.getEMAIL(),ConstantNavigationDrawer.getPROFILE(),context);
        navigationDrawer.initDrawer(context, toolbar);
    }

}
