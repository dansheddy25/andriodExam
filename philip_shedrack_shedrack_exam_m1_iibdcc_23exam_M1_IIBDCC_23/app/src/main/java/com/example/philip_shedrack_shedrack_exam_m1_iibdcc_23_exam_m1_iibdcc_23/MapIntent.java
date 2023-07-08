package com.example.philip_shedrack_shedrack_exam_m1_iibdcc_23_exam_m1_iibdcc_23;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapIntent extends FragmentActivity implements OnMapReadyCallback {

    private MapView map;
    private GoogleMap mMap;
    private String LatLand;
    private FloatingActionButton back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_intent);

        map = (MapView) findViewById(R.id.map);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);

        Intent intent = getIntent();
        LatLand = intent.getStringExtra("LatLand");

    }

    public void backHome(View view) {;
        //Back to home button
        back_home = findViewById(R.id.back_home);
        Intent home = new Intent(view.getContext(), MainActivity.class);
        startActivity(home);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String latitude = LatLand.split(",")[0];
        String longitude = LatLand.split(",")[1];

        LatLng location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        mMap.addMarker(new MarkerOptions().position(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(location);
        circleOptions.radius(100);
        circleOptions.fillColor(Color.TRANSPARENT);
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.BLUE);
        mMap.addCircle(circleOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }
}