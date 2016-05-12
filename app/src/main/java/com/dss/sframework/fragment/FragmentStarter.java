package com.dss.sframework.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import com.dss.sframework.R;

/**
 * Created by digipronto on 12/05/16.
 */
public abstract class FragmentStarter {


    public static void startDemoFragment(Context context){
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DemoFragment demoFragment = new DemoFragment();
        fragmentTransaction.replace(R.id.fragment_container, demoFragment, "demoFragment");
        fragmentTransaction.commit();
    }

    public static void startListFragment(Context context) {

        FragmentManager fragmentManager2 = ((Activity) context).getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        ListFragment listFragment = new ListFragment();
        fragmentTransaction2.replace(R.id.fragment_container, listFragment, "listFragment");
        fragmentTransaction2.commit();

    }

}
