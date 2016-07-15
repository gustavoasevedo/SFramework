package com.dss.sframework.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dss.sframework.R;

/**
 * Created by digipronto on 12/05/16.
 */
public abstract class FragmentStarter {


    public static Fragment startDemoFragment(Context context){
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DemoFragment_ demoFragment = new DemoFragment_();
        fragmentTransaction.replace(R.id.fragment_container, demoFragment, "demoFragment");
        fragmentTransaction.addToBackStack("demoFragment");
        fragmentTransaction.commit();
        return demoFragment;
    }

    public static Fragment startListFragment(Context context) {

        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListFragment_ listFragment = new ListFragment_();
        fragmentTransaction.replace(R.id.fragment_container, listFragment, "listFragment");
        fragmentTransaction.addToBackStack("listFragment");
        fragmentTransaction.commit();

        return listFragment;

    }

    public static Fragment startMapsFragment(Context context) {

        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapsFragment listFragment = new MapsFragment();
        fragmentTransaction.replace(R.id.fragment_container, listFragment, "mapsFragment");
        fragmentTransaction.addToBackStack("mapsFragment");
        fragmentTransaction.commit();

        return listFragment;

    }


    public static Fragment startVideoFragment(Context context) {

        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        VideoFragment listFragment = new VideoFragment();
        fragmentTransaction.replace(R.id.fragment_container, listFragment, "videoFragment");
        fragmentTransaction.addToBackStack("videoFragment");
        fragmentTransaction.commit();

        return listFragment;

    }
}
