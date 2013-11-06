package com.example.direction;

import java.util.Locale;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private final LocationListener mLocationListener = new LocationListener() {
	    @Override
	    public void onLocationChanged(final Location location) {
	        //your code here
	    }

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
        		mLocationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);  
        		  Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        		  String longitude = Double.toString(location.getLongitude());
        		  String latitude = Double.toString(location.getLatitude());
        		  
        //Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
        //Uri.parse("http://maps.google.com/maps?saddr=Bern&daddr=Hinterkappelen,+Wohlen+bei+Bern"));
        //startActivity(intent);
        String uri = "http://maps.google.com/maps?saddr=" + latitude+","+longitude+"&daddr="+"46.949134"+","+"7.4425";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);  
        	
        	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
