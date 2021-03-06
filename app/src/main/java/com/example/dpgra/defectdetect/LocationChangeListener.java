package com.example.dpgra.defectdetect;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import model.Pothole;

public class LocationChangeListener implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnMarkerClickListener {

    private MapFragment fragment;

    public LocationChangeListener(MapFragment fragment) {
        this.fragment =fragment;
    }

    @SuppressLint("MissingPermission")
    @Override
    public boolean onMyLocationButtonClick() {
        fragment.ButtonClicked = true;
        return fragment.ButtonClicked;
    }
    

    @Override
    public void onCameraMoveStarted(int i) {
        if ( i == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE ) {
            fragment.ButtonClicked = false;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        fragment.ButtonClicked = false;
        Object o = marker.getTag();
        if ( o instanceof Pothole) {
            String id = ((Pothole) o).getId();
            double lat = ((Pothole) o).getLat();
            double lon = ((Pothole) o).getLon();
            String latStr = String.format("%.4f", lat);
            String lonStr = String.format("%.4f", lon);
            Toast.makeText(fragment.getContext(), "Pothole Id: " + id + "\nLongitude: " + lonStr + "\nLatitude: " + latStr, Toast.LENGTH_LONG ).show();
        }
        return false;
    }
}
