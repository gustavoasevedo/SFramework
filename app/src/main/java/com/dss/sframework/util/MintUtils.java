package com.dss.sframework.util;

import android.content.Context;

import com.splunk.mint.Mint;

/**
 * Created by Gustavo on 31/12/2015.
 */
public abstract class MintUtils {
    public static void mintStart(Context context) {
        Mint.initAndStartSession(context, "31dff299");
    }
}
