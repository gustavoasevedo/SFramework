package com.dss.sframework.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.util.LocationUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_maps)
public class MapsFragment extends Fragment {


    GoogleMap map;
    Context context;

    MapView location_map;

    @AfterViews
    void afterViews() {
        context = getActivity();

        location_map.onCreate(this.getArguments());

        loadMap();

    }



    public void loadMap(){
        map = location_map.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);

        if(LocationUtils.gpsIsEnabled(context)){

            try{

                map.setMyLocationEnabled(true);

            }catch (SecurityException e){
                Toast.makeText(context,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

        }

        MapsInitializer.initialize(context);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LocationUtils.getLocation(context), 18);
        map.animateCamera(cameraUpdate);
        map.setTrafficEnabled(true);

    }

    @Override
    public void onResume() {
        location_map.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        location_map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        location_map.onLowMemory();
    }
}

