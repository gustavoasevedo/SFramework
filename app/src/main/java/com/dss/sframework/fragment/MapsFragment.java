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
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_maps)
public class MapsFragment extends Fragment {

    GoogleMap map;
    Context context;

    @ViewById
    MapView location_map;

    @AfterViews
    void afterViews() {
        context = getActivity();
        loadMap();
    }

    public void loadMap(){
        location_map.onCreate(getArguments());

        initMap();
        checkLocation();

        MapsInitializer.initialize(context);


        setLocation();

    }

    public void checkLocation(){
        if(LocationUtils.gpsIsEnabled(context)){
            try{
                map.setMyLocationEnabled(true);
            }catch (SecurityException e){
                Toast.makeText(context,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initMap(){
        map = location_map.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
    }

    public void setLocation(){
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

