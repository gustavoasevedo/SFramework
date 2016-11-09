package com.dss.sframework.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.tools.util.LocationUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_maps)
public class MapsFragment extends Fragment implements OnMapReadyCallback{

    @ViewById
    MapView location_map;

    GoogleMap map;
    Context context;

    @AfterViews
    void afterViews() {
        context = getActivity();
        loadMap();
    }

    public void loadMap(){
        location_map.onCreate(getArguments());

        initMap();
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
       location_map.getMapAsync(MapsFragment.this);
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

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        this.map.getUiSettings().setMyLocationButtonEnabled(false);
        checkLocation();
        MapsInitializer.initialize(context);
        setLocation();

    }
}

