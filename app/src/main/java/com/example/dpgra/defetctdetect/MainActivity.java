package com.example.dpgra.defetctdetect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

import model.Darknet;

public class MainActivity extends AppCompatActivity {

    private Darknet net;
    private BottomNavigationView bottomNavigationView;
    private List<Fragment> fragList;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.camera:
                    System.out.println("Selected camera");
                    fragment = new CameraFragment();
                    transaction.replace(R.id.frameholder, fragment);
                    //transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.map:
                    System.out.println("Selected map");
                    fragment = new MapFragment();
                    transaction.replace(R.id.frameholder, fragment);
                    //transaction.addToBackStack(null);
                    //transaction.setTransition(1);
                    transaction.commit();
                    return true;
                case R.id.list:
                    System.out.println("Selected list");
                    fragment = new PotholeListFragment();
                    transaction.replace(R.id.frameholder, fragment);
                    //transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.camera);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) != ConnectionResult.SUCCESS ) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this), 1);
        }
        //Set default fragment
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frameholder, new CameraFragment()).commit();

       // mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_dashboard);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //mOpenCvCameraView = (CameraBridgeViewBase)findViewById(R.id.CameraView);
        //mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        //mOpenCvCameraView.setCvCameraViewListener(this);
        /*
        File fileDir = getFilesDir();
        System.out.println(fileDir.getAbsolutePath());
        File cfgFile = null;
        File weightsFile = null;
        for ( File file : fileDir.listFiles() ) {
            if ( file.getName().endsWith(".cfg") ) {
                cfgFile = file;
            } else if ( file.getName().endsWith(".weights") ) {
                weightsFile = file;
            }
            System.out.println(file.getAbsolutePath());
        }
        if ( cfgFile != null && weightsFile != null ) {
            net = new Darknet(cfgFile.getAbsolutePath(), weightsFile.getAbsolutePath());
        }
        */
    }


}