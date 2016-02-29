package com.dss.sframework.util;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Gustavo on 30/12/2015.
 */
public class ParseUtil {
    public static void parseLogin(String username) {
        final String usuario = username;
        ParseInstallation install = ParseInstallation.getCurrentInstallation();
        install.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                final String channel = "C_" + usuario;

                ParsePush.subscribeInBackground(channel,
                        new SaveCallback() {

                            @Override
                            public void done(ParseException ex) {
                                if (ex == null) {
                                    Log.e("PARSE", "Success");
                                } else {
                                    Log.e("PARSE", ex.getMessage());
                                }
                            }
                        });
            }
        });
    }

    public static void parseLogout(String username) {
        final String usuario = username;
        ParseInstallation install = ParseInstallation.getCurrentInstallation();
        install.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                final String channel = "C_" + usuario;
                ParsePush.unsubscribeInBackground(channel,
                        new SaveCallback() {
                            @Override
                            public void done(ParseException ex) {
                                if (ex == null) {
                                    Log.e("PARSE", "Success");
                                } else {
                                    Log.e("PARSE", ex.getMessage());
                                }
                            }
                        });
            }
        });
    }
}
