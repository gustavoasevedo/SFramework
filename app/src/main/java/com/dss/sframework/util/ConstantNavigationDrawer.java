package com.dss.sframework.util;

import com.dss.sframework.R;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class ConstantNavigationDrawer {

    private static final String[] TITLES = {"Buttons","Events","Mail","Shop","Travel"};
    private static final int[] ICONS = {R.drawable.ic_file,R.drawable.ic_file,R.drawable.ic_file,R.drawable.ic_file,R.drawable.ic_file};
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view
    private static final String NAME = "Akash Bangad";
    private static final String EMAIL = "akash.bangad@android4devs.com";
    private static final int PROFILE = R.drawable.ic_search;

    public static String[] getTITLES() {
        return TITLES;
    }

    public static int[] getICONS() {
        return ICONS;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static int getPROFILE() {
        return PROFILE;
    }
}
