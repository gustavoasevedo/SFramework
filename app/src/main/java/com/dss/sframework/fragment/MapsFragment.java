package com.dss.sframework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.util.LocationUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;


public class MapsFragment extends Fragment {

    MapView mapView;
    GoogleMap map;
    View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        context = getActivity();

        loadLayout(savedInstanceState);

        loadMap();

        return view;
    }

    public void loadLayout(Bundle savedInstanceState){
        mapView = (MapView) view.findViewById(R.id.location_map);
        mapView.onCreate(savedInstanceState);
    }


    public void loadMap(){
        map = mapView.getMap();
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
        map.setBuildingsEnabled(true);
        map.setTrafficEnabled(true);

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

