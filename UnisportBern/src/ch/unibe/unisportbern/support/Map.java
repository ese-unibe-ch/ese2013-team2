package ch.unibe.unisportbern.support;

import java.util.Locale;

import android.R;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Map extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        		  
        //Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
        //Uri.parse("http://maps.google.com/maps?saddr=Bern&daddr=Hinterkappelen,+Wohlen+bei+Bern"));
        //startActivity(intent);
        String uri = "http://maps.google.com/maps?saddr=" + "46.94155"+","+"7.3333"+"&daddr="+"46.949134"+","+"7.4425";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);  
        	
        	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailsView, menu);
        return true;
    }
    
}
